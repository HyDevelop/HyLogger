package org.hydev.logger.utils

/**
 * 此类由 Hykilpikonna 在 2018/12/02 创建!
 * Created by Hykilpikonna on 2018/12/02!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
object FormatUtils
{
    /**
     * Format a string.
     *
     * @param format Format
     * @param args Args
     * @return Formatted string.
     */
    fun resolve(format: String, vararg args: Any?): String
    {
        val result = StringBuilder()
        var count = 0
        var i = 0
        while (i < format.length - 1)
        {
            val charAt = format[i]
            val twoChars = format.substring(i, i + 2)

            // Ignore \*
            if (charAt == '\\')
            {
                i++
                if (i < format.length)
                {
                    result.append(format[i])
                }
                i++
                continue
            }

            // Resolve {}
            if (twoChars == "{}")
            {
                result.append(args[count])
                count++
                i++
                i++
                continue
            }
            result.append(format[i])
            i++
        }
        val last = format.substring(format.length - 2)
        if (last != "{}") result.append(last[1])
        return result.toString()
    }
}
