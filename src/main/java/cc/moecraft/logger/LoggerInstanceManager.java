package cc.moecraft.logger;

import cc.moecraft.logger.environments.LogEnvironment;
import lombok.Getter;

import java.util.*;

/**
 * 此类由 Hykilpikonna 在 2018/07/04 创建!
 * Created by Hykilpikonna on 2018/07/04!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class LoggerInstanceManager
{
    @Getter
    private Map<String, HyLogger> cachedInstance = new HashMap<>();

    @Getter
    private List<LogEnvironment> environments;

    public LoggerInstanceManager(LogEnvironment... environments)
    {
        this.environments = Arrays.asList(environments);
    }

    public LoggerInstanceManager addEnvironment(LogEnvironment ... environments)
    {
        this.environments.addAll(Arrays.asList(environments));
        return this;
    }

    public HyLogger getLoggerInstance(String prefix, boolean debug)
    {
        if (cachedInstance.containsKey(prefix)) return cachedInstance.get(prefix);

        HyLogger logger = new HyLogger(this, prefix, debug);
        cachedInstance.put(prefix, logger);
        return logger;
    }
}
