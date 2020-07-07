package org.hydev.logger.format

import org.hydev.logger.format.AnsiConstants.ESC_PREFIX
import org.hydev.logger.format.AnsiConstants.SUFFIX

@Suppress("ConvertToStringTemplate")
enum class AnsiColor(val code: Int, vararg val placeholders: Char)
{
    RESET(0, 'r'),
    BLACK(30, '0', '8'),
    RED(31, '4', 'c'),
    GREEN(32, '2', 'a'),
    YELLOW(33, '6', 'e'),
    BLUE(34, '1', '9'),
    PURPLE(35, '5', 'd'),
    CYAN(36, '3', 'b'),
    WHITE(37, '7', 'f');

    override fun toString() = value

    val value = ESC_PREFIX + code + SUFFIX
    val bright = ESC_PREFIX + code + ";1" + SUFFIX
    val background = ESC_PREFIX + (code + 10) + SUFFIX
    val brightBg = ESC_PREFIX + (code + 70) + SUFFIX
}
