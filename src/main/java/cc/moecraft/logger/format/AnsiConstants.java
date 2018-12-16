package cc.moecraft.logger.format;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 此类由 Hykilpikonna 在 2018/05/04 创建!
 * Created by Hykilpikonna on 2018/05/04!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
@SuppressWarnings("WeakerAccess")
public class AnsiConstants
{
    public static final String ESC_PREFIX = "\033[";
    public static final String SUFFIX = "m";
    
    public static final String FORMAT_PREFIX = "&";
    
    public static final String FOREGROUND = "38;";
    public static final String BACKGROUND = "48;";

    public static final ArrayList<AnsiColor> colors;
    public static final Map<String, AnsiColor> colorsPlaceholderIndex;
    public static final ArrayList<AnsiFormat> formats;
    public static final Map<String, AnsiFormat> formatsPlaceholderIndex;

    static
    {
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
