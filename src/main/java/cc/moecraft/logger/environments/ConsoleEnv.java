package cc.moecraft.logger.environments;

import static cc.moecraft.logger.utils.AnsiUtils.removeFormat;

/**
 * 此类由 Hykilpikonna 在 2018/07/03 创建!
 * Created by Hykilpikonna on 2018/07/03!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class ConsoleEnv extends LogEnvironment
{
    @Override
    public void logRaw(String message)
    {
        System.out.println(removeFormat(message));
    }
}
