package cc.moecraft.logger;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static cc.moecraft.logger.AnsiColor.*;

/**
 * 此类由 Hykilpikonna 在 2018/04/17 创建!
 * Created by Hykilpikonna on 2018/04/17!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 */
public class DebugLogger
{
    private String pre;
    private boolean debug;

    /**
     * 一个萌萌的Logger
     * @param pre 前缀
     * @param debug Debug消息是否开启
     */
    public DebugLogger(String pre, boolean debug)
    {
        this.pre = pre;
        this.debug = debug;
    }

    /**
     * 设置是否Debug
     * @param value 是否Debug
     */
    public void setDebug(boolean value)
    {
        debug = value;
    }

    /**
     * 发送一条log
     * @param s log消息
     */
    public void log(String s)
    {
        System.out.println(String.format("%s[%s%s%s] [%s%s%s] %s%s%s", WHITE, PURPLE, getCurrentTime(), WHITE, BLUE, pre, WHITE, RESET, s, RESET));
    }

    public String getCurrentTime()
    {
        return new SimpleDateFormat("yy-MM-dd HH.mm.ss").format(Calendar.getInstance().getTime());
    }

    /**
     * 发送一条Debug消息
     * @param s Debug消息
     */
    public void debug(String s)
    {
        if (debug)
        {
            StackTraceElement stackTrace = Thread.currentThread().getStackTrace()[2];

            log(String.format("%s[%sDEBUG%s(%s%s.%s.%s%s)] %s%s", WHITE, RED, WHITE, YELLOW, stackTrace.getClassName(), stackTrace.getMethodName(), stackTrace.getLineNumber(), WHITE, CYAN, s));
        }
    }

    /**
     * 发送一条Error消息
     * @param s Error消息
     */
    public void error(String s)
    {
        if (debug)
        {
            StackTraceElement stackTrace = Thread.currentThread().getStackTrace()[2];

            log(String.format("%s[%sERROR%s(%s%s.%s.%s%s)] %s%s", WHITE, RED, WHITE, YELLOW, stackTrace.getClassName(), stackTrace.getMethodName(), stackTrace.getLineNumber(), WHITE, RED, s));
        }
    }
}

