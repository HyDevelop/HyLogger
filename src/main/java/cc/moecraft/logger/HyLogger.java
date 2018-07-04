package cc.moecraft.logger;

import cc.moecraft.logger.environments.LogEnvironment;
import cc.moecraft.logger.format.AnsiConstants;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Random;

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

    protected HyLogger(LoggerInstanceManager instanceManager, String prefix, boolean debug)
    {
        this.instanceManager = instanceManager;
        this.prefix = prefix;
        this.debug = debug;
    }

    public void log(String message)
    {
        for (LogEnvironment environment : instanceManager.getEnvironments()) environment.log(prefix, message);
    }

    public void debug(String message)
    {
        if (debug) for (LogEnvironment environment : instanceManager.getEnvironments()) environment.debug(prefix, message);
    }

    public void error(String message)
    {
        for (LogEnvironment environment : instanceManager.getEnvironments()) environment.error(prefix, message);
    }

    public void warning(String message)
    {
        for (LogEnvironment environment : instanceManager.getEnvironments()) environment.warning(prefix, message);
    }

    public void logRAINBOW(String message)
    {
        StringBuilder newMessage = new StringBuilder();

        Random random = new Random();

        for (char c : message.toCharArray())
        {
            newMessage.append(AnsiConstants.colors.get(random.nextInt(AnsiConstants.colors.size()))).append(c);
        }

        log(newMessage.toString());
    }
}
