import cc.moecraft.logger.coloring.MultiPointLinearGradient;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 此类由 Hykilpikonna 在 18-7-7 创建!
 * Created by Hykilpikonna on 18-7-7!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
class MultiPointLinearGradientTest
{
    @Test
    void testMapping()
    {
        List<MultiPointLinearGradient.GradientPoint> list = new ArrayList<>();

        list.add(new MultiPointLinearGradient.GradientPoint(Color.RED));
        list.add(new MultiPointLinearGradient.GradientPoint(Color.BLUE));
        list.add(new MultiPointLinearGradient.GradientPoint(Color.ORANGE));
        list.add(new MultiPointLinearGradient.GradientPoint(Color.PINK, 50));

        List<Map.Entry<Integer, MultiPointLinearGradient.GradientPoint>> mappedSizes = MultiPointLinearGradient.mapSizes(list);

        System.out.println(mappedSizes);

        List<Map.Entry<Integer, MultiPointLinearGradient.GradientPoint>> scaledSizes = MultiPointLinearGradient.scaleSizes(mappedSizes, 7);

        System.out.println(scaledSizes);

        List<Map.Entry<Integer, MultiPointLinearGradient.GradientPoint>> nearestTwo = MultiPointLinearGradient.getNearestTwoColors(scaledSizes, 3);

        System.out.println(nearestTwo);
    }
}
