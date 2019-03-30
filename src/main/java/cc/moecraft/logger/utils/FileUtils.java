package cc.moecraft.logger.utils;

import java.io.File;
import java.io.IOException;

/**
 * The class {@code FileUtils} is a utility class for file processing.
 * <p>
 * Class created by the HyDEV Team on 2019-03-30!
 *
 * @author HyDEV Team (https://github.com/HyDevelop)
 * @author Hykilpikonna (https://github.com/hykilpikonna)
 * @author Vanilla (https://github.com/VergeDX)
 * @since 2019-03-30 13:22
 */
public class FileUtils
{
    /**
     * Create a file safely
     *
     * @param file File to create
     */
    public static void create(File file)
    {
        if (!file.exists())
        {
            try
            {
                if (!file.createNewFile())
                {
                    exception("File creation failed", file, null);
                }
            }
            catch (IOException e)
            {
                exception("Unknown", file, e);
            }
        }

        if (file.isDirectory())
        {
            exception("File is directory", file, null);
        }

        if (!file.getParentFile().exists())
        {
            if (!file.mkdir())
            {
                exception("Parent directory creation failed", file, null);
            }
        }

        try
        {
            if (!file.createNewFile())
            {
                exception("Unknown", file, null);
            }
        }
        catch (IOException e)
        {
            exception("Unknown", file, e);
        }
    }

    /**
     * Throw an exception
     *
     * @param explanation Why it failed
     * @param file The file that failed to create
     * @param cause Exception that caused it
     */
    private static void exception(String explanation, File file, Throwable cause)
    {
        throw new FileException("Failed to create file : " + explanation + " : " + file.getAbsolutePath(), cause);
    }

    /**
     * Exception related to file processing.
     */
    private static class FileException extends RuntimeException
    {
        private FileException(String message, Throwable cause)
        {
            super(message, cause);
        }
    }
}
