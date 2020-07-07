package org.hydev.logger.format

import java.util.*

object AnsiConstants
{
    const val ESC_PREFIX = "\u001b["
    const val SUFFIX = "m"
    const val FORMAT_PREFIX = "&"
    const val FOREGROUND = "38;"
    const val BACKGROUND = "48;"

    val formatsIndex: MutableMap<Char, String> = HashMap()

    init
    {
        // Index enum values
        AnsiColor.values().forEach { it.placeholders.forEach { char -> formatsIndex[char] = it.value } }
        AnsiFormat.values().forEach { it.placeholders.forEach { char -> formatsIndex[char] = it.value } }
    }
}
