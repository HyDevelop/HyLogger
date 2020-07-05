import org.hydev.logger.HyLogger
import org.hydev.logger.format.AnsiColor

/**
 * 此类由 Hykilpikonna 在 2018/12/16 创建!
 * Created by Hykilpikonna on 2018/12/16!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
object BackgroundTest
{
    @JvmStatic
    fun main(args: Array<String>)
    {
        HyLogger("Test")
            .log(AnsiColor.RED.background + AnsiColor.GREEN + "Hello world!")
    }
}
