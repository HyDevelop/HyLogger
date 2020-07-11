package org.hydev.logger.coloring

import org.hydev.logger.*
import org.hydev.logger.format.AnsiColor.RESET
import java.awt.Color
import kotlin.math.roundToInt
import kotlin.math.tan

class LinearGradient(private val colors: MutableList<GradientPoint>)
{
    init
    {
        colors.sortBy { it.pos }
    }

    constructor(c1: GradientPoint, c2: GradientPoint, vararg colors: GradientPoint):
        this(mutableListOf(c1, c2, *colors))

    constructor(c1: Color, c2: Color, vararg colors: Color) :
        this(mutableListOf(c1, c2, *colors).mapIndexed { i, it -> GradientPoint(it, i * 10) }.toMutableList())

    /**
     * Create a gradient list of colors for a set size.
     *
     * @param size How many pixels (chars) are there?
     * @return A gradient of colors, one color for every pixel (char)
     */
    fun getColors(size: Int): List<Color>
    {
        val result = ArrayList<Color>()
        val scaled = getScaled(size).toMutableList()

        var c1 = scaled.removeAt(0)
        var c2 = scaled.removeAt(0)

        for (x in 0 until size)
        {
            // Switches from one color to the next
            if (c2.pos == x)
            {
                result.add(c2.color)
                c1 = c2
                c2 = scaled.removeAt(0)
                continue
            }

            // If they are the same color
            if (c1 == c2)
            {
                result.add(c1.color)
                continue
            }

            // Ratio is calculated with the relative position of the two colors
            val ratio = (x - c1.pos).toFloat() / (c2.pos - c1.pos).toFloat()

            // Calculate proportional rgb values
            val r = getColorWithRatio(c1.color.r, c2.color.r, ratio)
            val g = getColorWithRatio(c1.color.g, c2.color.g, ratio)
            val b = getColorWithRatio(c1.color.b, c2.color.b, ratio)

            result.add(Color(r, g, b))
        }
        return result
    }

    fun getColorWithRatio(v1: Int, v2: Int, ratio: Float) = (v2 * ratio + v1 * (1 - ratio)).toInt()

    /**
     * Scale a list of color points to a specific pixel size
     *
     * Example: size = 7
     *   colors = [(0, Red), (100, Blue), (200, Orange), (250, Purple)]
     *
     * Return: [(0, Red), (3, Blue), (6, Orange), (7, Purple)]
     *
     * @param colors List of color points
     * @param size How many pixels (chars) are there?
     * @return List with positions scaled to match the size
     */
    fun getScaled(size: Int): List<GradientPoint>
    {
        val scale = size.toDouble() / colors.last().pos
        return colors.map { GradientPoint(it.color, (it.pos * scale).roundToInt()) }
    }

    /**
     * Get a 2D plane of gradient colors, can be tilted.
     *
     * @param width
     * @param height
     * @param degrees
     * @return Plane of colors (Note: Y first!)
     */
    fun getColorPlane(width: Int, height: Int, degrees: Double): List<List<Color>>
    {
        // y = slope * x
        val slope = tan(Math.toRadians(degrees))

        // 0. Obtain offset:
        // - Offset defines how many pixels of gradient we need.
        //
        //    Offset = 8
        //    ↓
        //     0  =\
        //     1  = \
        //     2  =  \
        //     3  =   \
        //     4  =    \
        //     5  =     \
        //     6  =      \
        //     7  =       \
        //     8  =      ( \
        //   0 9  ##########
        //   1 10 ###Text###
        //   2 11 ##########
        //   ↑
        //   Actual lines of text

        val yOffset = (slope * width).toInt()
        val yWithOffset = height + yOffset

        // 1. Convert a line of gradient pixels to a plane of colors:
        // - Each line segment drawn parallel to the slope has a consistent
        //   color equal to the color of the beginning of the line.
        //
        //     0  =\
        //     1  =\\
        //    ...
        //     7  =\\\\\\\\
        //     8  =\\\\\\\\\
        //   0 9  |--------|\
        //   1 10 |\\Text\\|\\
        //   2 11 |________|\\\

        val colorPlane = List(height) { ArrayList<Color>() }
        val verticalColors = getColors(yWithOffset)
        for (sourceY in verticalColors.indices)
        {
            for (x in 0..width)
            {
                // Use x and slope to calculate the actual y value
                val actualY = sourceY + (slope * x).toInt() - yOffset

                if (actualY in 0 until height)
                    colorPlane[actualY].add(0, verticalColors[sourceY])
            }
        }

        return colorPlane
    }

    /**
     * Colorize some text, no angles involved
     *
     * @param text
     * @return Colored text
     */
    fun colorText(text: String): String
    {
        if (text.isBlank()) return text

        val lines = text.lines()
        val colors = getColors(lines.map { it.widthLength() }.max()!!)
        val result = StringBuilder()

        for (line in lines)
        {
            var colorIndex = 0
            line.forEach { c ->
                result.append(colors[colorIndex].foreground()).append(c)
                colorIndex++

                // Account for full width characters
                if (c.isFullWidth()) colorIndex++
            }
            result.append(RESET).append("\n")
        }

        return result.toString().trimEnd('\n')
    }

    /**
     * Colorize some text, can be tilted
     *
     * @param text
     * @param degrees
     * @return Colored text
     */
    fun colorText(text: String, degrees: Double): String
    {
        // Get array of char arrays
        if (text.isBlank()) return text
        val lines = text.replace("\u0000", "").lines()

        // x = How many chars are in a line
        // y = How many lines are in text
        val width = lines.map { it.widthLength() }.max()!! - 1
        val colorPlane = getColorPlane(width, lines.size, degrees)

        // 2. Map the color plane to the actual texts.
        val result = StringBuilder()
        for (y in lines.indices)
        {
            var colorIndex = 0
            val one = lines[y].map { c ->
                val line = colorPlane[y][colorIndex].foreground() + c
                colorIndex++

                // Account for full width characters
                if (c.isFullWidth()) colorIndex++
                line
            }.joinToString("", "", "")

            result.line(one + RESET).toString()
        }
        return result.toString().trimEnd('\n')
    }
}
