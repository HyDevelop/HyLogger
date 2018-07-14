package cc.moecraft.logger.environments;

import org.fusesource.jansi.AnsiConsole;

import static cc.moecraft.logger.environments.ColorSupportLevel.FORCED;
import static cc.moecraft.logger.environments.ColorSupportLevel.PASSTHROUGH;
import static cc.moecraft.logger.environments.ColorSupportLevel.PRESET_ONLY;

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
