package cc.moecraft.logger.environments;

import org.fusesource.jansi.AnsiConsole;

import static cc.moecraft.logger.environments.ColorSupportLevel.*;

/**
 * 此类由 Hykilpikonna 在 2018/07/03 创建!
 * Created by Hykilpikonna on 2018/07/03!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
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
        if (colorSupportLevel == FORCED) System.out.println(message);
        else if (colorSupportLevel == PRESET_ONLY) AnsiConsole.out.println(removeRGB(message));
        else AnsiConsole.out.println(message);
    }

    /**
     * 移除所有RGB同时保留所有预设
     * @param original 源
     * @return 替换后的
     */
    public String removeRGB(String original)
    {
        return original.replaceAll("\\033\\[38;2;.*?m", "");
    }
}
