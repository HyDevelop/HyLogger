package cc.moecraft.logger;

import lombok.Data;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static cc.moecraft.logger.AnsiColor.*;

/**
 * 此类由 Hykilpikonna 在 2018/04/17 创建!
 * Created by Hykilpikonna on 2018/04/17!
 * Github: https://github.com/hykilpikonna
 * Meow!
 */
@Data
public class DebugLogger
{
    private static FileLogger fileLogger;
    private String prefix;
    private boolean debug;

    /**
     * 一个萌萌的Logger
     * @param prefix 前缀
     * @param debug Debug消息是否开启
     */
    public DebugLogger(String prefix, boolean debug)
    {
        this(prefix, debug, null, null);
    }

    /**
     * 一个萌萌的Logger
     * @param prefix 前缀
     * @param debug Debug消息是否开启
     */
    public DebugLogger(String prefix, boolean debug, String filePath, String fileName)
    {
        this.prefix = prefix;
        this.debug = debug;
        if (fileName == null) return;
        if (filePath == null || filePath.isEmpty()) filePath = "./";
        if (!(filePath.endsWith("/") || filePath.endsWith("\\"))) filePath += File.separator;
        try
        {
            fileLogger = new FileLogger(new File(filePath + fileName + "@" + getCurrentTime().replace(":", "-").replace(" ", "-") + ".log"), this);
        }
        catch (IOException e)
        {
            fileLogger = null;
            log("FileLogger启动失败, 不会写入文件");
            e.printStackTrace();
        }
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
        String message = String.format("%s[%s%s%s] [%s%s%s] %s%s%s", WHITE, PURPLE, getCurrentTime(), WHITE, BLUE, prefix, WHITE, RESET, s, RESET);
        if (fileLogger != null) fileLogger.log(message);
        System.out.println(message);
    }

    public String getCurrentTime()
    {
        return new SimpleDateFormat("yy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
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

            log(String.format("%s[%sDEBUG%s(%s%s.%s.%s%s)] %s%s", WHITE, CYAN, WHITE, YELLOW, stackTrace.getClassName(), stackTrace.getMethodName(), stackTrace.getLineNumber(), WHITE, CYAN, s));
        }
    }

    /**
     * 发送一条Error消息
     * @param s Error消息
     */
    public void error(String s)
    {
        StackTraceElement stackTrace = Thread.currentThread().getStackTrace()[2];

        log(String.format("%s[%sERROR%s(%s%s.%s.%s%s)] %s%s", WHITE, RED, WHITE, YELLOW, stackTrace.getClassName(), stackTrace.getMethodName(), stackTrace.getLineNumber(), WHITE, RED, s));
    }
}

