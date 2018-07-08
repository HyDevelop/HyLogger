package cc.moecraft.logger.coloring;

import lombok.Data;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * 此类由 Hykilpikonna 在 18-7-7 创建!
 * Created by Hykilpikonna on 18-7-7!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 
 */
public class MultiPointLinearGradient
{
    private final ArrayList<GradientPoint> colors;
    private final int total;

    public MultiPointLinearGradient(GradientPoint color1, GradientPoint color2, ArrayList<GradientPoint> colors)
    {
        this.colors = colors;
    }
}
