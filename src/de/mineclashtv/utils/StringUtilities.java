package de.mineclashtv.utils;

public class StringUtilities {

    public static String[] fixLeft(String[] s) {
    return fixLeft(s, ' ');
}

    public static String[] fixLeft(String[] s, char repChar) {
        String[] answer = new String[s.length];
        String repString = Character.toString(repChar);
        int max = getMaxLength(s);

        for(int i = 0; i < s.length; i++)
            answer[i] = repString.repeat(max - s[i].length()) + s[i];

        return answer;
    }

    public static String[] fixRight(String[] s) {
        return fixRight(s, ' ');
    }

    public static String[] fixRight(String[] s, char repChar) {
        String[] answer = new String[s.length];
        String repString = Character.toString(repChar);
        int max = getMaxLength(s);

        for(int i = 0; i < s.length; i++)
            answer[i] = s[i] + repString.repeat(max - s[i].length());

        return answer;
    }

    public static String[] center(String[] s) {
        return center(s, ' ');
    }

    public static String[] center(String[] s, char repChar) {
        String[] answer = new String[s.length];
        int max = getMaxLength(s);

        for(int i = 0; i < s.length; i++)
            answer[i] = center(s[i], repChar, max) /*+ " ".repeat(max - s[i].length())*/;

        return answer;
    }

    public static String center(String s, int width) {
        return center(s, ' ', width);
    }

    public static String center(String s, char repChar, int width) {
        if(s.length() > width)
            throw new IllegalArgumentException("String length longer than max width: " + s.length() + " > " + width);

        String repString = Character.toString(repChar);
        int rep = (width - s.length()) / 2;

        return repString.repeat(rep) + s + repString.repeat(rep);
    }

    public static int getMaxLength(String[] s) {
        int max = 0;
        for(String value : s)
            max = Math.max(value.length(), max);

        return max;
    }

}
