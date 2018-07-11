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
    private HyLogger logger;
    private Long startingTime;

    protected TimingLogger(HyLogger logger)
    {
        this.logger = logger;
    }

    public TimingLogger time()
    {
        logger.log(getMilliseconds() + "ms");
        return this;
    }

    public TimingLogger timeAndReset()
    {
        return time().reset();
    }

    public TimingLogger timeNano()
    {
        logger.log(getTime() + " nanos");
        return this;
    }

    public TimingLogger timeNanoAndReset()
    {
        return timeNano().reset();
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

    public TimingLogger init()
    {
        return reset();
    }

    public void clear()
    {
        startingTime = null;
    }
}
