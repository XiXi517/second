/**
 *
 */
package com.senlang.sdip.util;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author Mc.D
 */
public final class StringHelper {
    public final static String Empty = "";

    public static boolean isNullOrEmpty(String s) {
        return s == null || s.equals("");
    }

    public static boolean isNullOrWhiteSpace(String s) {
        return s == null || s.trim().equals("");
    }

    private String str;

    public StringHelper(String s) {
        this.str = s;
    }

    public Optional<Integer> tryToInt() {
        try {
            return Optional.of(Integer.parseInt(str));
        } catch (Exception e) {
            return null;
        }
    }

    public int tryToInt(int defaultValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static String join(String s, Iterable<String> strings) {

        List<String> l = new ArrayList<>();
        strings.forEach(v -> l.add(v));

        String split = "";

        if (s != null) {
            split = s;
        }

        if (l.isEmpty()) {
            return Empty;
        }
        if (l.size() == 1) {
            return l.get(0);
        }

        return l.stream().reduce((c, v) -> c + s + v).get();
    }

    public static String join(String s, String[] strings) {
        String split = "";

        if (s != null) {
            split = s;
        }
        if (strings.length < 1) {
            return Empty;
        }
        if (strings.length == 1) {
            return strings[0];
        }
        return Arrays.asList(strings).stream().reduce((c, v) -> c + s + v).get();
    }
}
