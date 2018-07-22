package cc.moecraft.logger.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 此类由 Hykilpikonna 在 2018/07/04 创建!
 * Created by Hykilpikonna on 2018/07/04!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class TimeUtils
{
    public static String getCurrentTime(String pattern)
    {
        return new SimpleDateFormat(pattern).format(Calendar.getInstance().getTime());
    }

    public static String getCurrentTime()
    {
        return getCurrentTime("yy-MM-dd HH:mm:ss");
    }
}
