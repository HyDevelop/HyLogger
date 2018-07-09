package cc.moecraft.logger;

import cc.moecraft.logger.environments.LogEnvironment;
import cc.moecraft.logger.text.Paragraph;
import lombok.Getter;

import static cc.moecraft.logger.LogLevel.*;

/**
 * 此类由 Hykilpikonna 在 2018/07/02 创建!
 * Created by Hykilpikonna on 2018/07/02!
 * Github: https://github.com/hykilpikonna
 * Meow!
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
        if (level == DEBUG && !debug) return;
        for (LogEnvironment environment : instanceManager.getEnvironments())
            environment.log(instanceManager.getFormat().get(level), prefix, message);
    }

    private void log(LogLevel level, Paragraph message)
    {
        if (level == DEBUG && !debug) return;
        for (LogEnvironment environment : instanceManager.getEnvironments())
            environment.log(instanceManager.getFormat().get(level), prefix, message);
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
}
