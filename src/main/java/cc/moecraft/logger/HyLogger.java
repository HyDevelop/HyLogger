package cc.moecraft.logger;

import cc.moecraft.logger.environments.LogEnvironment;
import cc.moecraft.logger.text.Paragraph;
import lombok.Getter;

import static cc.moecraft.logger.LogLevel.*;

/**
 * 此类由 Hykilpikonna 在 2018/07/02 创建!
 * Created by Hykilpikonna on 2018/07/02!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class HyLogger
{
    @Getter
    private LoggerInstanceManager instanceManager;

    @Getter
    private String prefix;

    @Getter
    private boolean debug;

    public final FancyLogger fancy;

    protected HyLogger(LoggerInstanceManager instanceManager, String prefix, boolean debug)
    {
        this.instanceManager = instanceManager;
        this.prefix = prefix;
        this.debug = debug;

        this.fancy = new FancyLogger(this);
    }

    private void log(LogLevel level, String message)
    {
        if (checkDebug(level)) return;
        for (LogEnvironment environment : instanceManager.getEnvironments())
            environment.log(instanceManager.getFormat().get(level), prefix, message);
    }

    private void log(LogLevel level, String ... message)
    {
        if (checkDebug(level)) return;
        Paragraph paragraph = new Paragraph(message);
        for (LogEnvironment environment : instanceManager.getEnvironments())
            environment.log(instanceManager.getFormat().get(level), prefix, paragraph);
    }

    private void log(LogLevel level, Paragraph message)
    {
        if (checkDebug(level)) return;
        for (LogEnvironment environment : instanceManager.getEnvironments())
            environment.log(instanceManager.getFormat().get(level), prefix, message);
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
}
