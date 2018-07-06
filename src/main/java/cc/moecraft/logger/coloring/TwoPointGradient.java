package cc.moecraft.logger.coloring;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.*;

/**
 * 此类由 Hykilpikonna 在 2018/07/05 创建!
 * Created by Hykilpikonna on 2018/07/05!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
@Data @AllArgsConstructor
public class TwoPointGradient
{
    private AnsiRGB color1; // 从什么颜色开始渐变
    private AnsiRGB color2; // 渐变到什么颜色

    public TwoPointGradient(Color color1, Color color2)
    {
        this(new AnsiRGB(color1), new AnsiRGB(color2));
    }
}
