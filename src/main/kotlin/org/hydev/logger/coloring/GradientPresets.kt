package org.hydev.logger.coloring

import java.awt.Color

object GradientPresets
{
    // 彩虹
    var RAINBOW = MultiPointLinearGradient(
        GradientPoint(Color(255, 0, 0), 0),
        GradientPoint(Color(255, 0, 255), 15),
        GradientPoint(Color(0, 0, 255), 34),
        GradientPoint(Color(0, 255, 255), 50),
        GradientPoint(Color(0, 255, 0), 68),
        GradientPoint(Color(255, 255, 0), 85),
        GradientPoint(Color(255, 0, 0), 100)
    )

    // 蓝橙粉
    var BOP = MultiPointLinearGradient(
        Color(64, 224, 208),
        Color(255, 140, 0),
        Color(255, 0, 128)
    )

    // 蓝紫红
    var BPR = MultiPointLinearGradient(
        Color(18, 194, 233),
        Color(196, 113, 237),
        Color(246, 79, 89)
    )
}
