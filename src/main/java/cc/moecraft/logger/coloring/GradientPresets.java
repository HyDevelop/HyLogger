package cc.moecraft.logger.coloring;

import java.awt.*;

import static cc.moecraft.logger.coloring.MultiPointLinearGradient.GradientPoint;

/**
 * 此类由 Hykilpikonna 在 18-7-7 创建!
 * Created by Hykilpikonna on 18-7-7!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class GradientPresets
{
    public static final MultiPointLinearGradient RAINBOW;

    static
    {
        // 这个彩虹预设的比例真的不是瞎打出来的... 自然的彩虹就是这样的比例...
        RAINBOW = new MultiPointLinearGradient(
                new Color(255, 0,   0  ),
                new GradientPoint(new Color(255, 0,   255), 15),
                new GradientPoint(new Color(0,   0,   255), 19),
                new GradientPoint(new Color(0,   255, 255), 15),
                new GradientPoint(new Color(0,   255, 0  ), 18),
                new GradientPoint(new Color(255, 255, 0  ), 17),
                new GradientPoint(new Color(255, 0,   0  ), 16)
        );
    }
}
