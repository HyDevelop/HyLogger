/**
 * 此类由 Hykilpikonna 在 2018/05/04 创建!
 * Created by Hykilpikonna on 2018/05/04!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class ColorTest
{
    public static void main(String[] args)
    {
        for (int i = 0; i < 256; i += 16)
        {
            for (int j = 0; j < 16; j++)
            {
                System.out.print(String.format(" \u001b[38;5;%sm %s \u001b[0m ", i + j, i + j));
            }
        }
    }
}
