package color

import org.hydev.logger.coloring.AnsiColorMode.TRUE_COLOR_24BIT
import org.hydev.logger.coloring.AnsiColorMode.XTERM_256_8BIT
import org.hydev.logger.foreground
import java.awt.Color

/**
 * 此类由 Hykilpikonna 在 2018/12/16 创建!
 * Created by Hykilpikonna on 2018/12/16!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
object GrayScaleTest
{
    @JvmStatic
    fun main(args: Array<String>)
    {
        run {
            for (r in 0..255 step 16)
            {
                print(Color(r, r, r).foreground(XTERM_256_8BIT) + "█")
            }
        }
        println("")
        for (r in 0..255 step 16)
        {
            print(Color(r, r, r).foreground(TRUE_COLOR_24BIT) + "█")
        }
    }
}
