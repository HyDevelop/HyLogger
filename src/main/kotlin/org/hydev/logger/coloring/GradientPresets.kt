package org.hydev.logger.coloring

import org.hydev.logger.coloring.MultiPointLinearGradient.GradientPoint
import java.awt.Color

object GradientPresets
{
    // 彩虹
    var RAINBOW = MultiPointLinearGradient(
        Color(255, 0, 0),
        GradientPoint(Color(255, 0, 255), 15),
        GradientPoint(Color(0, 0, 255), 19),
        GradientPoint(Color(0, 255, 255), 15),
        GradientPoint(Color(0, 255, 0), 18),
        GradientPoint(Color(255, 255, 0), 17),
        GradientPoint(Color(255, 0, 0), 16)
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
