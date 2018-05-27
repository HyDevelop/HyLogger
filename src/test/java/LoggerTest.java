import cc.moecraft.logger.AnsiColor;
import cc.moecraft.logger.AnsiFormat;
import cc.moecraft.logger.DebugLogger;
import com.sun.org.apache.xerces.internal.dom.AttrNSImpl;

import java.io.File;

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
    private static DebugLogger logger = new DebugLogger("LoggerTest", true, "logs", "log");

    public static void main(String[] args)
    {
        logger.log("一条测试Log消息");
        logger.debug("一条测试Debug消息");
        logger.error("一条测试Error消息");
    }
}
