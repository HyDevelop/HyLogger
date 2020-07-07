package org.hydev.logger

import org.hydev.logger.coloring.AnsiColorMode
import org.hydev.logger.format.AnsiConstants
import org.hydev.logger.format.AnsiConstants.ESC_PREFIX
import org.hydev.logger.format.AnsiConstants.FOREGROUND
import org.hydev.logger.format.AnsiConstants.SUFFIX
import java.awt.Color
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

/**
 * Get the string with formats and colors removed.
 * Or this.replace("\u011B\\[[0-9;]*?m".toRegex(), "")
 */
fun String.withoutFormat() = this.replace("\u001B\\[[;\\d]*m".toRegex(), "")

fun String.withoutRGB() = this.replace("\u001B\\[38;[25];.*?m".toRegex(), "")

// Shortcuts for RGB
val Color.r: Int get() = red
val Color.g: Int get() = green
val Color.b: Int get() = blue

/**
 * Convert color to 8Bit color value
 */
fun Color.to8Bit(): Int
{
    var grayPossible = true
    var gray = false
    var sep = 42.5f

    while (grayPossible)
    {
        if (r < sep || g < sep || b < sep)
        {
            gray = r < sep && g < sep && b < sep
            grayPossible = false
        }
        sep += 42.5f
    }

    return if (gray) (232f + (r + g + b) / 33f).roundToInt()
    else 16 + (r / 256f * 6f).toInt() * 36 + (g / 256f * 6f).toInt() * 6 + (b / 256f * 6f).toInt()
}

fun Color.foreground(mode: AnsiColorMode) = "" + ESC_PREFIX + FOREGROUND + mode.format(this) + SUFFIX

/**
 * String to date pattern
 */
fun String.toDatePattern(): DateTimeFormatter = DateTimeFormatter.ofPattern(this)

/**
 * Date pattern to time now
 */
fun DateTimeFormatter.now(): String = LocalDateTime.now().format(this)

/**
 * Add a line to a stringBuilder
 */
fun StringBuilder.line(line: String): StringBuilder = append(line).append("\n")

/**
 * Replace format/color codes like &r with actual Ansi colors
 */
fun String.parseFormats(): String
{
    // TODO: Optimize this
    var result = this
    AnsiConstants.formatsIndex.forEach { (k: Char, v: String) ->
        result = result.replace(AnsiConstants.FORMAT_PREFIX + k, v)
    }
    return result
}
