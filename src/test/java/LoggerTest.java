import cc.moecraft.logger.AnsiColor;
import cc.moecraft.logger.AnsiFormat;
import cc.moecraft.logger.DebugLogger;
import com.sun.org.apache.xerces.internal.dom.AttrNSImpl;

/**
 * 此类由 Hykilpikonna 在 2018/05/04 创建!
 * Created by Hykilpikonna on 2018/05/04!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class LoggerTest
{
    private static DebugLogger logger = new DebugLogger("LoggerTest", true);

    public static void main(String[] args)
    {
        logger.debug(AnsiColor.RED.getBright() + AnsiColor.BLUE.getBackground() + AnsiFormat.HIGH_INTENSITY + " Hi ");
    }
}
