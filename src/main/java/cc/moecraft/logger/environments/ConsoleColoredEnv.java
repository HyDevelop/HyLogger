package cc.moecraft.logger.environments;

import lombok.NoArgsConstructor;
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
@NoArgsConstructor
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
        // TODO: PRESET_ONLY
        if (colorSupportLevel == FORCED) System.out.println(message);
        else AnsiConsole.out.println(message);
    }
}
