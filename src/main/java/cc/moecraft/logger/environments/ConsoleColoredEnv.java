package cc.moecraft.logger.environments;

import lombok.NoArgsConstructor;
import org.fusesource.jansi.AnsiConsole;

/**
 * 此类由 Hykilpikonna 在 2018/07/03 创建!
 * Created by Hykilpikonna on 2018/07/03!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
@NoArgsConstructor
public class ConsoleColoredEnv extends LogEnvironment
{
    public ConsoleColoredEnv(boolean passThrough)
    {
        System.getProperties().setProperty("jansi.passthrough", String.valueOf(passThrough));
    }

    @Override
    public void logRaw(String message)
    {
        AnsiConsole.out.println(message);
    }
}
