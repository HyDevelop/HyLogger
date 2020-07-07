package color

import org.hydev.logger.coloring.AnsiColorMode.TRUE_COLOR_24BIT
import org.hydev.logger.coloring.AnsiColorMode.XTERM_256_8BIT
import org.hydev.logger.foreground
import org.hydev.logger.to8Bit
import java.awt.Color

/**
 * 此类由 Hykilpikonna 在 2018/12/16 创建!
 * Created by Hykilpikonna on 2018/12/16!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
object ColorConverterTest
{
    @JvmStatic
    fun main(args: Array<String>)
    {
        run {
            var r = 0
            while (r < 256)
            {
                var g = 0
                while (g < 256)
                {
                    var b = 0
                    while (b < 256)
                    {
                        print(Color(r, g, b).to8Bit().toString() + "|")
                        b += 16
                    }
                    print(" ")
                    g += 16
                }
                println()
                r += 16
            }
        }
        run {
            var r = 0
            while (r < 256)
            {
                var g = 0
                while (g < 256)
                {
                    var b = 0
                    while (b < 256)
                    {
                        print(Color(r, r, r).foreground(TRUE_COLOR_24BIT) + "█")
                        b += 16
                    }
                    print(" ")
                    g += 16
                }
                println()
                r += 16
            }
        }
        var r = 0
        while (r < 256)
        {
            var g = 0
            while (g < 256)
            {
                var b = 0
                while (b < 256)
                {
                    print(Color(r, r, r).foreground(XTERM_256_8BIT) + "█")
                    b += 16
                }
                print(" ")
                g += 16
            }
            println()
            r += 16
        }
    }
}
