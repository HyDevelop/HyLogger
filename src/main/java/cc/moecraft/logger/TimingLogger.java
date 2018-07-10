package cc.moecraft.logger;

/**
 * 此类由 Hykilpikonna 在 2018/07/10 创建!
 * Created by Hykilpikonna on 2018/07/10!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class TimingLogger
{
    private long startingTime;

    public TimingLogger()
    {
        reset();
    }

    public TimingLogger time(HyLogger logger)
    {
        logger.log(getMilliseconds() + "ms");
        return this;
    }

    public TimingLogger timeNano(HyLogger logger)
    {
        logger.log(getTime() + " nanos");
        return this;
    }


    public long getTime()
    {
        return System.nanoTime() - startingTime;
    }

    public double getMilliseconds()
    {
        return ((double) getTime()) / 1000000d;
    }

    public TimingLogger reset()
    {
        startingTime = System.nanoTime();
        return this;
    }
}
