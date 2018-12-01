package cc.moecraft.logger;

import cc.moecraft.logger.environments.LogEnvironment;
import cc.moecraft.logger.format.AnsiColor;
import cc.moecraft.logger.text.Paragraph;
import cc.moecraft.logger.utils.ThrowableUtil;
import lombok.Getter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;

import static cc.moecraft.logger.LogLevel.*;
import static cc.moecraft.logger.format.AnsiColor.RED;
import static cc.moecraft.logger.format.AnsiColor.YELLOW;
import static cc.moecraft.logger.utils.ThrowableUtil.*;

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
    private void log(LogLevel level, String message)
    {
        if (checkDebug(level)) return;
        for (LogEnvironment environment : instanceManager.getEnvironments())
            environment.log(instanceManager.getFormat().get(level), prefix, message);
    }

    /**
     * Log multiple messages
     *
     * @param level Level
     * @param message Messages
     */
    private void log(LogLevel level, String ... message)
    {
        if (checkDebug(level)) return;
        Paragraph paragraph = new Paragraph(message);
        for (LogEnvironment environment : instanceManager.getEnvironments())
            environment.log(instanceManager.getFormat().get(level), prefix, paragraph);
    }

    /**
     * Log message with a format.
     *
     * @param level Level
     * @param format Format
     * @param args Arguments
     */
    private void log(LogLevel level, String format, Object ... args)
    {
        //TODO
    }

    /**
     * Log a paragraph of messages.
     *
     * @param level Level
     * @param paragraph Paragraph of messages.
     */
    private void log(LogLevel level, Paragraph paragraph)
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
    private void log(LogLevel level, StackTraceElement stackTraceElement)
    {
        if (checkDebug(level)) return;
        for (LogEnvironment environment : instanceManager.getEnvironments())
            environment.log(instanceManager.getFormat().get(level), prefix, stackTraceElement);
    }

    /**
     * Log an empty line.
     */
    public void newLine()
    {
        log(LOG, "\n");
    }

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

    public void log(String ... message)
    {
        log(LOG, message);
    }

    public void debug(String ... message)
    {
        log(DEBUG, message);
    }

    public void error(String ... message)
    {
        log(ERROR, message);
    }

    public void warning(String ... message)
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

    public void error(String message, Throwable throwable)
    {
        StringWriter errors = new StringWriter();
        throwable.printStackTrace(new PrintWriter(errors));

        log(ERROR, message + AnsiColor.YELLOW + ": " + throwable.getMessage() + RED + " ->");
        log(ERROR_STACKTRACE, new Paragraph(errors.toString()));
    }

    public void error(Throwable throwable)
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
}
