package cc.moecraft.logger.format;

import java.util.ArrayList;

/**
 * 此类由 Hykilpikonna 在 2018/05/04 创建!
 * Created by Hykilpikonna on 2018/05/04!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class AnsiConstants
{
    public static final String PREFIX = System.getProperty("os.name").toLowerCase().contains("win") ? "\033[0;" : "\u001B[";
    public static final String SUFFIX = "m";
}
