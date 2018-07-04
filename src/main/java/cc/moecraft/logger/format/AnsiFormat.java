package cc.moecraft.logger.format;

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
public enum AnsiFormat 
{
    RESET("0"),

    HIGH_INTENSITY("1"),
    LOW_INTENSITY("2"),
    ITALIC("3"),
    UNDERLINE("4"),
    BLINK("5"),
    RAPID_BLINK("6"),
    REVERSE_VIDEO("7"),
    INVISIBLE_TEXT("8");

    String code;

    AnsiFormat(String code) 
    {
        this.code = code;
    }

    @Override
    public String toString() 
    {
        return PREFIX + code + SUFFIX;
    }
}
