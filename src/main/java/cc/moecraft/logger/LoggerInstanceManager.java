package cc.moecraft.logger;

import cc.moecraft.logger.environments.LogEnvironment;
import cc.moecraft.logger.format.AnsiFormat;
import lombok.Getter;

import java.util.*;

import static cc.moecraft.logger.LogLevel.*;

/**
 * 此类由 Hykilpikonna 在 2018/07/04 创建!
 * Created by Hykilpikonna on 2018/07/04!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class LoggerInstanceManager
{
    @Getter
    private Map<String, HyLogger> cachedInstance = new HashMap<>();

    @Getter
    private List<LogEnvironment> environments;

    @Getter
    private Map<LogLevel, String> format = new HashMap<>();

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

    public void setFormat(LogLevel logLevel, String newFormat)
    {
        format.put(logLevel, AnsiFormat.replaceAllFormatWithANSI(newFormat));
    }
}
