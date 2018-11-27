package cc.moecraft.logger.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

/**
 * 此类由 Hykilpikonna 在 2018/08/18 创建!
 * Created by Hykilpikonna on 2018/08/18!
 * Github: https://github.com/hykilpikonna
 * QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class ThrowableUtil
{
    public static ArrayList<StackTraceEntry> getStackTrace(Throwable throwable)
    {
        return getStackTrace(throwable, Collections.newSetFromMap(new IdentityHashMap<>()));
    }

    private static ArrayList<StackTraceEntry> getStackTrace(Throwable throwable, Set<Throwable> dejaVu)
    {
        if (dejaVu.contains(throwable))
            return new ArrayList<>(Collections.singletonList(new StackTraceEntry(StackTraceEntryType.CIRCULAR_REFERENCE, null)));
        dejaVu.add(throwable);

        ArrayList<StackTraceEntry> result = new ArrayList<>();

        // Print our stack trace
        StackTraceElement[] trace = throwable.getStackTrace();
        for (StackTraceElement stackTraceElement : trace)
            result.add(new StackTraceEntry(StackTraceEntryType.ELEMENT, stackTraceElement));

        // Print cause, if any
        Throwable cause = throwable.getCause();
        if (cause != null)
        {
            result.add(new StackTraceEntry(StackTraceEntryType.BEGIN_CAUSE, null));
            result.addAll(getStackTrace(cause, dejaVu));
        }

        return result;
    }

    @AllArgsConstructor @Data
    public static class StackTraceEntry
    {
        private final StackTraceEntryType type;
        private final StackTraceElement stackTraceElement;
    }

    public enum StackTraceEntryType
    {
        ELEMENT,
        BEGIN_CAUSE,
        CIRCULAR_REFERENCE
    }
}
