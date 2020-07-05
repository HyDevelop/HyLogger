package org.hydev.logger.format

/**
 * 此类由 Hykilpikonna 在 2018/05/04 创建!
 * Created by Hykilpikonna on 2018/05/04!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
enum class AnsiFormat(var code: String, vararg placeholders: String)
{
    RESET("0"), HIGH_INTENSITY("1", "l"), LOW_INTENSITY("2"), ITALIC("3", "o"), UNDERLINE(
    "4",
    "n"
),
    BLINK("5"), RAPID_BLINK("6"), REVERSE_VIDEO("7"), INVISIBLE_TEXT("8");

    override fun toString(): String
    {
        return AnsiConstants.ESC_PREFIX + code + AnsiConstants.SUFFIX
    }

    companion object
    {
        fun replaceAllFormatWithANSI(original: String): String
        {
            val result = arrayOf(original)
            AnsiConstants.formatsPlaceholderIndex.forEach { (k: String?, v: AnsiFormat?) ->
                result[0] = result[0].replace(AnsiConstants.FORMAT_PREFIX + k, v.toString())
            }
            AnsiConstants.colorsPlaceholderIndex.forEach { (k: String?, v: AnsiColor?) ->
                result[0] = result[0].replace(AnsiConstants.FORMAT_PREFIX + k, v.toString())
            }
            return result[0]
        }
    }

    init
    {
        AnsiConstants.formats.add(this)
        for (placeholder in placeholders) AnsiConstants.formatsPlaceholderIndex[placeholder] = this
    }
}
