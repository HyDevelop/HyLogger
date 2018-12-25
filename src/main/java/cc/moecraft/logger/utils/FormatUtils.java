package cc.moecraft.logger.utils;

/**
 * 此类由 Hykilpikonna 在 2018/12/02 创建!
 * Created by Hykilpikonna on 2018/12/02!
 * Github: https://github.com/hykilpikonna
 * Meow!
 *
 * @author Hykilpikonna
 */
public class FormatUtils
{
    /**
     * Format a string.
     *
     * @param format Format
     * @param args Args
     * @return Formatted string.
     */
    public static String resolve(String format, Object... args)
    {
        StringBuilder result = new StringBuilder();

        int count = 0;
        for (int i = 0; i < format.length() - 1; i++)
        {
            char charAt = format.charAt(i);
            String twoChars = format.substring(i, i + 2);

            // Ignore \*
            if (charAt == '\\')
            {
                i++;
                if (i < format.length())
                {
                    result.append(format.charAt(i));
                }
                continue;
            }

            // Resolve {}
            if (twoChars.equals("{}"))
            {
                result.append(args[count]);
                count++;
                i++;
                continue;
            }
            result.append(format.charAt(i));
        }

        String last = format.substring(format.length() - 2);
        if (!last.equals("{}")) result.append(last.charAt(1));

        return result.toString();
    }
}
