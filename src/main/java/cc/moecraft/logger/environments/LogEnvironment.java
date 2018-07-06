package cc.moecraft.logger.environments;

import static cc.moecraft.logger.format.AnsiColor.*;
import static cc.moecraft.logger.format.AnsiColor.RESET;
import static cc.moecraft.logger.utils.TimeUtils.getCurrentTime;

/**
 * 此类由 Hykilpikonna 在 2018/07/02 创建!
 * Created by Hykilpikonna on 2018/07/02!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public abstract class LogEnvironment
{
    public abstract void logRaw(String message);

    public void log(String format, String prefix, String message)
    {
        logRaw(replaceVariables(format, prefix, message));
    }

    {
        StackTraceElement stackTrace = Thread.currentThread().getStackTrace()[2];


    }
}
