import cc.moecraft.logger.AnsiColor;
import cc.moecraft.logger.AnsiFormat;
import cc.moecraft.logger.DebugLogger;
import com.sun.org.apache.xerces.internal.dom.AttrNSImpl;

/**
 * 此类由 Hykilpikonna 在 2018/05/04 创建!
 * Created by Hykilpikonna on 2018/05/04!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class LoggerTest
{
    private static DebugLogger logger = new DebugLogger("LoggerTest", true);

    public static void main(String[] args)
    {
        logger.log("一条测试Log消息");
        logger.debug("一条测试Debug消息");
        logger.error("一条测试Error消息");
    }
}
