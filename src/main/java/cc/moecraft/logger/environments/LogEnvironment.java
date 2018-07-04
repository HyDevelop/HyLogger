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

    public void log(String prefix, String message)
    {
        logRaw(String.format("%s[%s%s%s] [%s%s%s] %s%s%s", WHITE, PURPLE, getCurrentTime(), WHITE, BLUE, prefix, WHITE, RESET, message, RESET));
    }

    public void debug(String prefix, String message)
    {
        StackTraceElement stackTrace = Thread.currentThread().getStackTrace()[2];
        log(prefix, String.format("%s[%sDEBUG%s(%s%s.%s.%s%s)] %s%s", WHITE, CYAN, WHITE, YELLOW, stackTrace.getClassName(), stackTrace.getMethodName(), stackTrace.getLineNumber(), WHITE, CYAN, message));
    }

    public void error(String prefix, String message)
    {
        StackTraceElement stackTrace = Thread.currentThread().getStackTrace()[2];
        log(prefix, String.format("%s[%sERROR%s(%s%s.%s.%s%s)] %s%s", WHITE, RED, WHITE, YELLOW, stackTrace.getClassName(), stackTrace.getMethodName(), stackTrace.getLineNumber(), WHITE, RED, message));
    }

    public void warning(String prefix, String message)
    {
        log(prefix, String.format("%s[%sWARNING%s] %s%s", WHITE, RED, WHITE, YELLOW, message));
    }
}
