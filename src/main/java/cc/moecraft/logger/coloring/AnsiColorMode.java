package cc.moecraft.logger.coloring;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 此类由 Hykilpikonna 在 2018/12/16 创建!
 * Created by Hykilpikonna on 2018/12/16!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
@AllArgsConstructor
@Getter
public enum AnsiColorMode
{
    COLOR_RGB("2;%s;%s;%s"),
    COLOR_8BIT("5;%s");
    
    private final String format;
}
