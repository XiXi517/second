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
public final class ListHelper {
    public final static String Empty = "";

    public static <T> List<T> toList(Iterable<? extends T> iter) {
        List<T> l = new ArrayList<>();
        iter.forEach(v -> l.add(v));
        return l;
    }
}
