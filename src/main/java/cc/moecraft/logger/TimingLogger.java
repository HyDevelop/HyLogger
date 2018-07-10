package cc.moecraft.logger;

/**
 * 此类由 Hykilpikonna 在 2018/07/10 创建!
 * Created by Hykilpikonna on 2018/07/10!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class TimingLogger
{
    private long startingTime;

    public TimingLogger reset()
    {
        startingTime = System.nanoTime();
        return this;
    }
}
