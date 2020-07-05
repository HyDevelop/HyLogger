package color

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
object GrayScaleTest
{
    @JvmStatic
    fun main(args: Array<String>)
    {
        run {
            var r = 0
            while (r < 256)
            {
                print(Color(r, r, r).toAnsi8() + "█")
                r += 16
            }
        }
        println("")
        var r = 0
        while (r < 256)
        {
            print("\u001b[38;2;" + r + ";" + r + ";" + r + "m█")
            r += 16
        }
    }
}
