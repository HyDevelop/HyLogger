package cc.moecraft.logger.format;

import static cc.moecraft.logger.format.AnsiConstants.*;
import static cc.moecraft.logger.format.AnsiConstants.PREFIX;
import static cc.moecraft.logger.format.AnsiConstants.SUFFIX;

/**
 * 此类由 Hykilpikonna 在 2018/05/04 创建!
 * Created by Hykilpikonna on 2018/05/04!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public enum AnsiFormat 
{
    RESET("0"),

    HIGH_INTENSITY("1", "l"),
    LOW_INTENSITY("2"),
    ITALIC("3", "o"),
    UNDERLINE("4", "n"),
    BLINK("5"),
    RAPID_BLINK("6"),
    REVERSE_VIDEO("7"),
    INVISIBLE_TEXT("8");

    String code;

    AnsiFormat(String code, String ... placeholders)
    {
        this.code = code;
        formats.add(this);
        for (String placeholder : placeholders) formatsPlaceholderIndex.put(placeholder, this);
    }

    @Override
    public String toString() 
    {
        return PREFIX + code + SUFFIX;
    }
}
