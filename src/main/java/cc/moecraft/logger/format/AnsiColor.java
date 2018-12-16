package cc.moecraft.logger.format;

import static cc.moecraft.logger.format.AnsiConstants.*;

/**
 * 此类由 Hykilpikonna 在 2018/05/04 创建!
 * Created by Hykilpikonna on 2018/05/04!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public enum AnsiColor
{
    RESET (0, "r"),

    BLACK (30, "0", "8"),
    RED   (31, "4", "c"),
    GREEN (32, "2", "a"),
    YELLOW(33, "6", "e"),
    BLUE  (34, "1", "9"),
    PURPLE(35, "5", "d"),
    CYAN  (36, "3", "b"),
    WHITE (37, "7", "f");

    private int code;

    AnsiColor(int code, String ... placeholders)
    {
        this.code = code;
        colors.add(this);
        for (String placeholder : placeholders) colorsPlaceholderIndex.put(placeholder, this);
    }

    @Override
    public String toString()
    {
        return ESC_PREFIX + code + SUFFIX;
    }

    public String get()
    {
        return toString();
    }

    public String getBright()
    {
        return ESC_PREFIX + code + ";1" + SUFFIX;
    }

    public String getBackground()
    {
        return ESC_PREFIX + (code + 10) + SUFFIX;
    }
    public String getBrightBg()
    {
        return ESC_PREFIX + (code + 70) + SUFFIX;
    }
}
