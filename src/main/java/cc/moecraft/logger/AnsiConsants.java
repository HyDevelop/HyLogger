package cc.moecraft.logger;

/**
 * 此类由 Hykilpikonna 在 2018/05/04 创建!
 * Created by Hykilpikonna on 2018/05/04!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class AnsiConsants
{
    public static final String PREFIX = System.getProperty("os.name").toLowerCase().contains("win") ? "\033[0;" : "\u001B[";
    public static final String SUFFIX = "m";
}
