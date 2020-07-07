import org.hydev.logger.utils.FormatUtils;

/**
 * TODO: Write a description for this class!
 *
 * @author HyDEV Team (https://github.com/HyDevelop)
 * @author Hykilpikonna (https://github.com/hykilpikonna)
 * @author Vanilla (https://github.com/VergeDX)
 * @since 2020-07-05 19:28
 */
public class FormatUtilsTest
{
    public static void main(String[] args)
    {
        System.out.println(FormatUtils.resolve("\\ab\\c{}\\as\\{}df{}", 2, 5));
        System.out.println(FormatUtils.resolve("{}{}{}", 2, 5));
        System.out.println(FormatUtils.resolve("{}{}w", 2, 5));
        System.out.println(FormatUtils.resolve("{}{}w", 2));
        System.out.println(FormatUtils.resolve("{}\\{w", 2));
        System.out.println(FormatUtils.resolve("}w", 2));
    }
}
