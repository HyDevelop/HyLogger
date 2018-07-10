package cc.moecraft.logger.environments;

import cc.moecraft.logger.text.Paragraph;

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

    public void log(String format, String prefix, Paragraph message)
    {
        for (String sentence : replaceVariables(format, prefix, message).getSentences()) logRaw(sentence);
    }

    public static Paragraph replaceVariables(String format, String prefix, Paragraph paragraph)
    {
        Paragraph result = new Paragraph();
        String basicFormat = replaceBasicVariables(format, prefix);

        for (String sentence : paragraph.getSentences())
        {
            result.addSentences(replaceMessage(basicFormat, sentence));
        }

        return result;
    }

    public static String replaceVariables(String format, String prefix, String message)
    {
        return replaceMessage(replaceBasicVariables(format, prefix), message);
    }

    private static String replaceMessage(String basicFormat, String message)
    {
        return basicFormat.replace("{message}",   message);
    }

    private static String replaceBasicVariables(String format, String prefix)
    {

        StackTraceElement stackTrace = Thread.currentThread().getStackTrace()[6];

        String stClass = stackTrace.getClassName();
        String stMethod = stackTrace.getMethodName();
        String stLine = stackTrace.getLineNumber() + "";
        String stFull = stClass + "." + stMethod + ":" + stLine;

        return format
                .replace("{time}",      getCurrentTime())
                .replace("{prefix}",    prefix)
                .replace("{st.class}",  stClass)
                .replace("{st.method}", stMethod)
                .replace("{st.line}",   stLine)
                .replace("{st.full}",   stFull)
        ;
    }
}
