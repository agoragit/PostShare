package util;

public class StringUtils {

    public static boolean isNullOrEmpty( String s )
    {
        return s == null || s.length() == 0;
    }
    public static boolean isNotNull( String s )
    {
        return s != null && s.length() > 0;
    }

    public static boolean isNull( String s )
    {
        return s == null || s.length() == 0;
    }

}
