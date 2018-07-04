package cc.moecraft.logger.environments;

import org.fusesource.jansi.AnsiConsole;

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
    @Override
    public void logRaw(String message)
    {
        AnsiConsole.out.println(message);
    }
}
