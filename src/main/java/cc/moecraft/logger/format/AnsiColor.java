package cc.moecraft.logger.format;

import static cc.moecraft.logger.format.AnsiConstants.*;
import static cc.moecraft.logger.format.AnsiConstants.PREFIX;
import static cc.moecraft.logger.format.AnsiConstants.SUFFIX;

/**
 * 此类由 Hykilpikonna 在 2018/05/04 创建!
 * Created by Hykilpikonna on 2018/05/04!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public enum AnsiColor
{
    RESET(0),

    BLACK(30),
    RED(31),
    GREEN(32),
    YELLOW(33),
    BLUE(34),
    PURPLE(35),
    CYAN(36),
    WHITE(37);

    int code;

    AnsiColor(int code, String ... placeholders)
    {
        this.code = code;
        colors.add(this);
        for (String placeholder : placeholders) colorsPlaceholderIndex.put(placeholder, this);
    }

    @Override
    public String toString()
    {
        return PREFIX + code + SUFFIX;
    }

    public String get()
    {
        return toString();
    }

    public String getBright()
    {
        return PREFIX + code + ";1" + SUFFIX;
    }

    public String getBackground()
    {
        return PREFIX + (code + 10) + SUFFIX;
    }
}
