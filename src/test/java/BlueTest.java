/**
 * 此类由 Hykilpikonna 在 2018/05/04 创建!
 * Created by Hykilpikonna on 2018/05/04!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class BlueTest
{
    public static void main(String[] args)
    {
        for (int i = 0; i < 256; i += 1)
        {
            System.out.print(String.format(" \u001b[38;2;0;0;%sm %s \u001b[0m ", i, i));
        }
    }
}
