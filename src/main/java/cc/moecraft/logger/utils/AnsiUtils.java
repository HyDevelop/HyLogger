package cc.moecraft.logger.utils;

/**
 * 此类由 Hykilpikonna 在 2018/07/04 创建!
 * Created by Hykilpikonna on 2018/07/04!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AnsiUtils
{
    public static String removeFormat(String original)
    {
        return original.replaceAll("\u001B\\[[;\\d]*m", "");
    }
}
