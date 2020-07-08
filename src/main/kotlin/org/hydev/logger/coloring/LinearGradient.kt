package org.hydev.logger.coloring

import org.hydev.logger.b
import org.hydev.logger.foreground
import org.hydev.logger.g
import org.hydev.logger.r
import java.awt.Color
import kotlin.math.roundToInt

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
     * Colorize some text, no angles involved
     *
     * @param text
     * @return Colored text
     */
    fun colorText(text: String): String
    {
        if (text.isBlank()) return text

        val lines = text.lines()
        val colors = getColors(lines.maxBy { it.length }!!.length)
        val result = StringBuilder()

        for (line in lines)
        {
            line.forEachIndexed { i, c -> result.append(colors[i].foreground()).append(c) }
            result.append("\n")
        }

        return result.toString().trimEnd('\n')
    }
}
