package cc.moecraft.logger.environments;

import org.fusesource.jansi.AnsiConsole;

import static cc.moecraft.logger.environments.ColorSupportLevel.*;

/**
 * 此类由 Hykilpikonna 在 2018/07/03 创建!
 * Created by Hykilpikonna on 2018/07/03!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class ConsoleColoredEnv extends LogEnvironment
{
    private ColorSupportLevel colorSupportLevel;

    public ConsoleColoredEnv(ColorSupportLevel colorSupportLevel)
    {
        if (colorSupportLevel == OS_DEPENDENT)
            if (System.getProperty("os.name").toLowerCase().contains("win")) colorSupportLevel = PRESET_ONLY;
            else colorSupportLevel = DEFAULT;

        this.colorSupportLevel = colorSupportLevel;
        if (colorSupportLevel != FORCED && colorSupportLevel == PASSTHROUGH) System.getProperties().setProperty("jansi.passthrough", "true");
    }

    @Override
    public void logRaw(String message)
    {
        switch (colorSupportLevel)
        {
            case FORCED:
            {
                System.out.println(message);
                break;
            }
            case PRESET_ONLY:
            {
                AnsiConsole.out.println(removeRGB(message));
                break;
            }
            case DISABLED:
            {
                System.out.println(removeColor(message));
                break;
            }
            default:
            {
                AnsiConsole.out.println(message);
                break;
            }
        }
    }

    /**
     * 移除所有RGB同时保留所有预设
     *
     * @param original 源
     * @return 替换后的
     */
    private String removeRGB(String original)
    {
        return original.replaceAll("\\033\\[38;2;.*?m", "");
    }

    /**
     * 移除所有颜色
     *
     * @param original 源
     * @return 替换后的
     */
    private String removeColor(String original)
    {
        return original.replaceAll("\\033\\[[0-9;]*?m", "");
    }
}
