package cc.moecraft.logger

/**
 * 此类由 Hykilpikonna 在 2018/07/10 创建!
 * Created by Hykilpikonna on 2018/07/10!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
class TimingLogger(private val logger: HyLogger)
{
    private var startingTime: Long? = null
    fun time(): TimingLogger
    {
        logger.log(milliseconds.toString() + "ms")
        return this
    }

    fun timeAndReset(): TimingLogger
    {
        return time().reset()
    }

    fun timeNano(): TimingLogger
    {
        logger.log("$time nanos")
        return this
    }

    fun timeNanoAndReset(): TimingLogger
    {
        return timeNano().reset()
    }

    val time: Long
        get() = System.nanoTime() - startingTime!!

    val milliseconds: Double
        get() = time.toDouble() / 1000000.0

    fun reset(): TimingLogger
    {
        startingTime = System.nanoTime()
        return this
    }

    fun init(): TimingLogger
    {
        return reset()
    }

    fun clear()
    {
        startingTime = null
    }
}
