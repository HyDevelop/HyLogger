
import org.hydev.logger.background
import org.hydev.logger.foreground
import org.hydev.logger.format.AnsiColor.RESET
import java.awt.Color
import java.awt.Color.LIGHT_GRAY

/**
 * 此类由 Hykilpikonna 在 2018/05/04 创建!
 * Created by Hykilpikonna on 2018/05/04!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
object BlueTest
{
    @JvmStatic
    fun main(args: Array<String>)
    {
        for (b in 0..255)
        {
            if (b % 16 == 0) println()

            print(LIGHT_GRAY.foreground() + Color(0,0,b).background() + " " + b.toString().padStart(3, ' ') + " " + RESET + " ")
        }
    }
}
