package cc.moecraft.logger.environments

import org.fusesource.jansi.AnsiConsole

/**
 * 此类由 Hykilpikonna 在 2018/07/03 创建!
 * Created by Hykilpikonna on 2018/07/03!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
class ConsoleColoredEnv(colorSupportLevel: ColorSupportLevel) : LogEnvironment()
{
    private val colorSupportLevel: ColorSupportLevel

    override fun logRaw(message: String)
    {
        when (colorSupportLevel)
        {
            ColorSupportLevel.FORCED ->
            {
                println(message)
            }
            ColorSupportLevel.PRESET_ONLY ->
            {
                AnsiConsole.out.println(removeRGB(message))
            }
            ColorSupportLevel.DISABLED ->
            {
                println(removeColor(message))
            }
            else ->
            {
                AnsiConsole.out.println(message)
            }
        }
    }

    /**
     * 移除所有RGB同时保留所有预设
     *
     * @param original 源
     * @return 替换后的
     */
    private fun removeRGB(original: String?): String
    {
        return original!!.replace("\\033\\[38;[25];.*?m".toRegex(), "")
    }

    /**
     * 移除所有颜色
     *
     * @param original 源
     * @return 替换后的
     */
    private fun removeColor(original: String?): String
    {
        return original!!.replace("\\033\\[[0-9;]*?m".toRegex(), "")
    }

    init
    {
        var colorSupportLevel = colorSupportLevel
        if (colorSupportLevel == ColorSupportLevel.OS_DEPENDENT) colorSupportLevel = if (System.getProperty("os.name")
                .toLowerCase().contains("win")
        ) ColorSupportLevel.PRESET_ONLY else ColorSupportLevel.DEFAULT
        this.colorSupportLevel = colorSupportLevel
    }
}
