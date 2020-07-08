package org.hydev.logger.utils

import org.hydev.logger.coloring.LinearGradient
import org.hydev.logger.foreground
import org.hydev.logger.format.AnsiColor.RESET
import org.hydev.logger.line
import java.awt.Color
import java.lang.Math.toRadians
import kotlin.math.tan

/**
 * 此类由 Hykilpikonna 在 2018/07/05 创建!
 * Created by Hykilpikonna on 2018/07/05!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
class TextColoringUtil(private val text: String)
{
    fun getGradientText(gradient: LinearGradient): String
    {
        val chars = text.toCharArray()
        val colors = gradient.getColors(chars.size)
        val result = StringBuilder()
        for (i in chars.indices)
        {
            result.append(colors[i].foreground()).append(chars[i])
        }
        return result.toString()
    }

    fun getGradientText(c1: Color, c2: Color, vararg colors: Color): String
    {
        return getGradientText(LinearGradient(c1, c2, *colors))
    }

    companion object
    {
        fun getGradientParagraph(text: String, gradient: LinearGradient, degrees: Double): String
        {
            // Get array of char arrays
            if (text.isBlank()) return text
            val lines = text.replace("\u0000", "").lines()

            // x = How many chars are in a line
            // y = How many lines are in text
            val xMax = lines.maxBy { it.length }!!.length - 1
            val slope = tan(toRadians(degrees)) // y = slope * x

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

            val yOffset = (slope * xMax).toInt()
            val yWithOffset = lines.size + yOffset

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

            val colorPlane = Array(lines.size) { ArrayList<Color>() }
            val verticalColors = gradient.getColors(yWithOffset)
            for (sourceY in verticalColors.indices)
            {
                for (x in 0..xMax)
                {
                    // Use x and slope to calculate the actual y value
                    val actualY = sourceY + (slope * x).toInt() - yOffset

                    if (actualY in lines.indices)
                        colorPlane[actualY].add(0, verticalColors[sourceY])
                }
            }

            // 2. Map the color plane to the actual texts.
            val result = StringBuilder()
            for (y in lines.indices)
            {
                val one = lines[y].mapIndexed { x, it -> colorPlane[y][x].foreground() + it }
                    .joinToString("", "", "")

                result.line(one + RESET).toString()
            }
            return result.toString()
        }
    }

}
