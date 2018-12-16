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
    public static final MultiPointLinearGradient
            RAINBOW, // 彩虹
            BOP,     // 蓝橙粉
            BPR      // 蓝紫红
    ;

    static
    {
        RAINBOW = new MultiPointLinearGradient(
                new Color(255, 0,   0  ),
                new GradientPoint(new Color(255, 0,   255), 15),
                new GradientPoint(new Color(0,   0,   255), 19),
                new GradientPoint(new Color(0,   255, 255), 15),
                new GradientPoint(new Color(0,   255, 0  ), 18),
                new GradientPoint(new Color(255, 255, 0  ), 17),
                new GradientPoint(new Color(255, 0,   0  ), 16)
        );

        BOP = new MultiPointLinearGradient(
                new Color(64, 224, 208),
                new Color(255, 140, 0),
                new Color(255, 0, 128)
        );

        BPR = new MultiPointLinearGradient(
                new Color(18, 194, 233),
                new Color(196, 113, 237),
                new Color(246, 79, 89)
        );
    }
}
