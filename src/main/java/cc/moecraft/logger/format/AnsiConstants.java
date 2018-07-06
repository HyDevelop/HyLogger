package cc.moecraft.logger.format;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 此类由 Hykilpikonna 在 2018/05/04 创建!
 * Created by Hykilpikonna on 2018/05/04!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class AnsiConstants
{
    public static final String PREFIX;

    public static final String SUFFIX;
    public static final String RGB_FORMAT;

    public static final String FORMAT_PREFIX;

    public static final ArrayList<AnsiColor> colors;
    public static final Map<String, AnsiColor> colorsPlaceholderIndex;
    public static final ArrayList<AnsiFormat> formats;
    public static final Map<String, AnsiFormat> formatsPlaceholderIndex;

    static
    {
        PREFIX = System.getProperty("os.name").toLowerCase().contains("win") ? "\033[" : "\u001B[";

        SUFFIX = "m";
        FORMAT_PREFIX = "&";
        RGB_FORMAT = PREFIX + "38;2;%s;%s;%sm";

        colors = new ArrayList<>();
        formats = new ArrayList<>();
        colorsPlaceholderIndex = new HashMap<>();
        formatsPlaceholderIndex = new HashMap<>();

        // 必须要先加载上才行
        Object temp;
        temp = AnsiColor.RESET;
        temp = AnsiFormat.RESET;
    }
}
