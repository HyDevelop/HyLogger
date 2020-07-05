package org.hydev

import java.awt.Color
import kotlin.math.roundToInt

/**
 * Get the string with formats and colors removed.
 */
fun String.withoutFormat() = this.replace("\u001B\\[[;\\d]*m".toRegex(), "")

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
