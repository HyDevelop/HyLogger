package org.hydev.logger.utils

import org.hydev.logger.coloring.ColorCombo
import org.hydev.logger.coloring.MultiPointLinearGradient
import org.hydev.logger.format.AnsiFormat.RESET
import org.hydev.logger.line
import java.awt.Color
import kotlin.math.tan

/**
 * 此类由 Hykilpikonna 在 2018/07/05 创建!
 * Created by Hykilpikonna on 2018/07/05!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
class TextColoringUtil(private val text: String)
{
    fun getGradientText(gradient: MultiPointLinearGradient): String
    {
        val chars = text.toCharArray()
        val colors = gradient.getColors(chars.size)
        val result = StringBuilder()
        for (i in chars.indices)
        {
            result.append(colors[i].toString()).append(chars[i])
        }
        return result.toString()
    }

    fun getGradientText(color1: Color, color2: Color, vararg colors: Color): String
    {
        return getGradientText(
            MultiPointLinearGradient(
                color1,
                color2,
                *colors
            )
        )
    }

    companion object
    {
        fun getGradientParagraph(text: String, gradient: MultiPointLinearGradient, degrees: Int): String
        {
            val chars = text.lines().map { it.toCharArray() }

            // x = 一句里最多多少字符
            // y = 一共多少句
            val yMax = chars.size - 1
            val xMax: Int = chars[0].size - 1
            val slope = tan(Math.toRadians(degrees.toDouble())) // y = slope * x

            // 0. 获取Offset

            // Offset算法:
            //
            //    获取的渐变颜色的行数, offset = 8
            //    ↓
            //     0  =\
            //     1  = \
            //     2  =  \
            //     3  =   \
            //     4  =    \
            //     5  =     \
            //     6  =      \
            //     7  =       \
            //     8  = 角度 ( \
            //   0 9  ##########
            //   1 10 ###文字###
            //   2 11 ##########
            //   ↑
            //   实际文字的行数
            val offset = (slope * xMax).toInt()
            val yWithOffset = yMax + offset

            // 1. 获取Color[][]

            // 获取算法:
            //
            // 从一条1维的颜色数组按照倾斜度(slope)映射进去
            //
            //     0  =\
            //     1  =\\
            //     2  =\\\
            //     3  =\\\\
            //     4  =\\\\\
            //     5  =\\\\\\
            //     6  =\\\\\\\
            //     7  =\\\\\\\\
            //     8  =\\\\\\\\\
            //   0 9  |--------|\
            //   1 10 |\\文字\\|\\
            //   2 11 |________|\\\
            val newColors =
                Array(yWithOffset + 1) { arrayOfNulls<ColorCombo>(xMax + 1) }
            val verticalColors = gradient.getColors(yWithOffset + 1)
            for (sourceY in verticalColors.indices)
            {
                val color = verticalColors[sourceY]
                for (x in 0 until xMax + 1)
                {
                    // 从斜度(slope)用X获取实际点
                    val newY = sourceY + (slope * x).toInt()
                    try
                    {
                        newColors[newY][x] = color
                    } catch (e: IndexOutOfBoundsException)
                    {
                        break
                    }
                }
            }

            // 2. 把颜色二维数组映射到实际文字
            val result = StringBuilder()
            for (y in 0 until yMax + 1)
            {
                val sentence = chars[y]
                val colors = newColors[y + offset]
                val oneResult = StringBuilder()
                for (x in 0 until xMax + 1)
                {
                    val charInASentence = sentence[x]
                    val colorInASentence = colors[x]
                    if (charInASentence == '\u0000') continue
                    oneResult.append(colorInASentence ?: RESET)
                    oneResult.append(charInASentence)
                }
                result.line(oneResult.toString())
            }
            return result.toString()
        }
    }

}
