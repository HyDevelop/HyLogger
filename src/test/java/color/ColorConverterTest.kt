package color

import org.hydev.to8Bit
import org.hydev.toAnsi8
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
                        print(Color(r, r, r).toAnsi8() + "█")
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
                    print("\u001b[38;2;" + r + ";" + g + ";" + b + "m█")
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
