package cc.moecraft.logger.format

/**
 * 此类由 Hykilpikonna 在 2018/05/04 创建!
 * Created by Hykilpikonna on 2018/05/04!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
enum class AnsiColor(private val code: Int, vararg placeholders: String)
{
    RESET(0, "r"), BLACK(30, "0", "8"), RED(31, "4", "c"), GREEN(32, "2", "a"), YELLOW(33, "6", "e"), BLUE(34, "1", "9"),
    PURPLE(35, "5", "d"), CYAN(36, "3", "b"), WHITE(37, "7", "f");

    override fun toString(): String
    {
        return AnsiConstants.ESC_PREFIX + code + AnsiConstants.SUFFIX
    }

    /**
     * @return The bright ansi color code for this color preset.
     */
    val bright: String
        get() = AnsiConstants.ESC_PREFIX + code + ";1" + AnsiConstants.SUFFIX

    /**
     * @return The background ansi color code for this color preset.
     */
    val background: String
        get() = AnsiConstants.ESC_PREFIX + (code + 10) + AnsiConstants.SUFFIX

    /**
     * @return The bright background ansi color code for this color preset.
     */
    val brightBg: String
        get() = AnsiConstants.ESC_PREFIX + (code + 70) + AnsiConstants.SUFFIX

    init
    {
        AnsiConstants.colors!!.add(this)
        for (placeholder in placeholders) AnsiConstants.colorsPlaceholderIndex!![placeholder] = this
    }
}
