package org.hydev.logger.utils

object FormatUtils
{
    /**
     * Format a string.
     *
     * @param format Format
     * @param args Args
     * @return Formatted string.
     */
    @JvmStatic
    fun resolve(format: String, vararg args: Any): String
    {
        val result = StringBuilder()
        var count = 0
        var i = 0

        loop@ while (i < format.length - 1)
        {
            when (format.substring(i, i + 2))
            {
                "\\{" ->
                {
                    result.append("{")
                    i += 2
                }

                "{}" ->
                {
                    result.append(args[count])
                    count++
                    i += 2

                    // End early
                    if (args.size <= count)
                    {
                        result.append(format.substring(i))
                        break@loop
                    }
                }

                else ->
                {
                    result.append(format[i])
                    i++
                }
            }
        }
        return result.toString()
    }
}
