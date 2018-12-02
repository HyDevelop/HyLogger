package cc.moecraft.logger;

import cc.moecraft.logger.environments.LogEnvironment;
import cc.moecraft.logger.text.Paragraph;
import cc.moecraft.logger.utils.FormatUtils;
import lombok.Getter;

import java.util.ArrayList;

import static cc.moecraft.logger.LogLevel.*;
import static cc.moecraft.logger.format.AnsiColor.RED;
import static cc.moecraft.logger.format.AnsiColor.YELLOW;
import static cc.moecraft.logger.utils.ThrowableUtil.StackTraceEntry;
import static cc.moecraft.logger.utils.ThrowableUtil.getStackTrace;

/**
 * 此类由 Hykilpikonna 在 2018/07/02 创建!
 * Created by Hykilpikonna on 2018/07/02!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
@SuppressWarnings("unused")
public class HyLogger
{
    @Getter
    private LoggerInstanceManager instanceManager;

    @Getter
    private String prefix;

    @Getter
    private boolean debug;

    public final FancyLogger fancy;

    public final TimingLogger timing;

    protected HyLogger(LoggerInstanceManager instanceManager, String prefix, boolean debug)
    {
        this.instanceManager = instanceManager;
        this.prefix = prefix;
        this.debug = debug;

        this.fancy = new FancyLogger(this);
        this.timing = new TimingLogger(this);
    }

    /**
     * Log a message.
     *
     * @param level Level
     * @param message Message
     */
    public void log(LogLevel level, String message)
    {
        if (checkDebug(level)) return;
        for (LogEnvironment environment : instanceManager.getEnvironments())
            environment.log(instanceManager.getFormat().get(level), prefix, message);
    }

    /**
     * Log message with a format.
     *
     * @param level Level
     * @param format Format
     * @param args Arguments
     */
    public void log(LogLevel level, String format, Object ... args)
    {
        log(level, FormatUtils.resolve(format, args));
    }

    /**
     * Log a paragraph of messages.
     *
     * @param level Level
     * @param paragraph Paragraph of messages.
     */
    public void log(LogLevel level, Paragraph paragraph)
    {
        if (checkDebug(level)) return;
        for (LogEnvironment environment : instanceManager.getEnvironments())
            environment.log(instanceManager.getFormat().get(level), prefix, paragraph);
    }

    /**
     * Log a stack trace element.
     *
     * @param level Level
     * @param stackTraceElement Stack trace element.
     */
    public void log(LogLevel level, StackTraceElement stackTraceElement)
    {
        if (checkDebug(level)) return;
        for (LogEnvironment environment : instanceManager.getEnvironments())
            environment.log(instanceManager.getFormat().get(level), prefix, stackTraceElement);
    }

    /**
     * Log a throwable.
     *
     * @param throwable Throwable.
     */
    public void log(Throwable throwable)
    {
        ArrayList<StackTraceEntry> entries = getStackTrace(throwable);

        log(ERROR_STACKTRACE, RED + "Error: " + throwable.getLocalizedMessage());

        for (StackTraceEntry entry : entries)
        {
            switch (entry.getType())
            {
                case ELEMENT:
                {
                    log(ERROR_STACKTRACE, RED + " -> " + YELLOW + entry.getStackTraceElement());
                    break;
                }
                case BEGIN_CAUSE:
                {
                    throwable = throwable.getCause();
                    log(ERROR_STACKTRACE, RED + "Caused By: " + throwable.getLocalizedMessage());
                    break;
                }
            }
        }
    }

    /**
     * Log an empty line.
     */
    public void newLine()
    {
        log(LOG, "");
    }

    /**
     * Returns true when the level is debug and the debug option is false.
     *
     * @param level Level
     * @return True when the level is debug and the debug option is false.
     */
    private boolean checkDebug(LogLevel level)
    {
        return level == DEBUG && !debug;
    }

    public void log(String message)
    {
        log(LOG, message);
    }

    public void debug(String message)
    {
        log(DEBUG, message);
    }

    public void error(String message)
    {
        log(ERROR, message);
    }

    public void warning(String message)
    {
        log(WARNING, message);
    }

    public void log(Paragraph message)
    {
        log(LOG, message);
    }

    public void debug(Paragraph message)
    {
        log(DEBUG, message);
    }

    public void error(Paragraph message)
    {
        log(ERROR, message);
    }

    public void warning(Paragraph message)
    {
        log(WARNING, message);
    }

    public void log(String format, Object ... message)
    {
        log(LOG, format, message);
    }

    public void debug(String format, Object ... message)
    {
        log(DEBUG, format, message);
    }

    public void error(String format, Object ... message)
    {
        log(ERROR, format, message);
    }

    public void warning(String format, Object ... message)
    {
        log(WARNING, format, message);
    }

    public void error(Throwable throwable)
    {
        log(throwable);
    }

    public void error(String message, Throwable throwable)
    {
        error(message);
        error(throwable);
    }
}
