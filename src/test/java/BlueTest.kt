/**
 * 此类由 Hykilpikonna 在 2018/05/04 创建!
 * Created by Hykilpikonna on 2018/05/04!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
object BlueTest
{
    @JvmStatic
    fun main(args: Array<String>)
    {
        var i = 0
        while (i < 256)
        {
            print(String.format(" \u001b[38;2;0;0;%sm %s \u001b[0m ", i, i))
            i += 1
        }
    }
}
