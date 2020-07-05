package org.hydev.logger.format

import java.util.*

/**
 * 此类由 Hykilpikonna 在 2018/05/04 创建!
 * Created by Hykilpikonna on 2018/05/04!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
object AnsiConstants
{
    const val ESC_PREFIX = "\u001b["
    const val SUFFIX = "m"
    const val FORMAT_PREFIX = "&"
    const val FOREGROUND = "38;"
    const val BACKGROUND = "48;"

    var colors: ArrayList<AnsiColor> = ArrayList()
    var colorsPlaceholderIndex: MutableMap<String, AnsiColor> = HashMap()
    var formats: ArrayList<AnsiFormat> = ArrayList()
    var formatsPlaceholderIndex: MutableMap<String, AnsiFormat> = HashMap()

    init
    {
        // 必须要先加载上才行
        var temp: Any
        temp = AnsiColor.RESET
        temp = AnsiFormat.RESET
    }
}
