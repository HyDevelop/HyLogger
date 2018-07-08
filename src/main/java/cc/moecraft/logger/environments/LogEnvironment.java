package cc.moecraft.logger.environments;

import static cc.moecraft.logger.utils.TimeUtils.getCurrentTime;

/**
 * 此类由 Hykilpikonna 在 2018/07/02 创建!
 * Created by Hykilpikonna on 2018/07/02!
 * Github: https://github.com/hykilpikonna
 * Meow!
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

    public static String replaceVariables(String format, String prefix, String message)
    {
        StackTraceElement stackTrace = Thread.currentThread().getStackTrace()[5];

        String stClass = stackTrace.getClassName();
        String stMethod = stackTrace.getMethodName();
        String stLine = stackTrace.getLineNumber() + "";
        String stFull = stClass + "." + stMethod + ":" + stLine;

        return format
                .replace("{time}",      getCurrentTime())
                .replace("{prefix}",    prefix)
                .replace("{message}",   message)
                .replace("{st.class}",  stClass)
                .replace("{st.method}", stMethod)
                .replace("{st.line}",   stLine)
                .replace("{st.full}",   stFull)
        ;
    }
}
