/**
 * 此类由 Hykilpikonna 在 2018/05/04 创建!
 * Created by Hykilpikonna on 2018/05/04!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
object ColorTest
{
    @JvmStatic
    fun main(args: Array<String>)
    {
        var i = 0
        while (i < 256)
        {
            for (j in 0..15)
            {
                print(String.format(" \u001b[38;5;%sm %s \u001b[0m ", i + j, i + j))
            }
            i += 16
        }
    }
}
