package cc.moecraft.logger.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 此类由 Hykilpikonna 在 2018/07/04 创建!
 * Created by Hykilpikonna on 2018/07/04!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class TimeUtils
{
    public static String getCurrentTime()
    {
        return new SimpleDateFormat("yy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
    }
}
