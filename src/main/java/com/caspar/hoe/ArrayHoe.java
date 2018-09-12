package com.caspar.hoe;

/**
 * Array manipulation library
 *
 * @author caspar.chen
 * @date 2018/9/12
 **/
public class ArrayHoe {

    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isNotEmpty(Object[] array) {
        return !isEmpty(array);
    }

    public static boolean contains(Object[] array, Object obj) {
        return indexOf(array, obj) != -1;
    }

    public static int indexOf(Object[] array, Object obj) {
        return indexOf(array, obj, 0);
    }

    public static int indexOf(Object[] array, Object obj, int startIndex) {
        if (isEmpty(array)) {
            return -1;
        } else {
            if (startIndex < 0) {
                startIndex = 0;
            }

            int i;
            if (obj == null) {
                for (i = startIndex; i < array.length; ++i) {
                    if (array[i] == null) {
                        return i;
                    }
                }
            } else if (array.getClass().getComponentType().isInstance(obj)) {
                for (i = startIndex; i < array.length; ++i) {
                    if (obj.equals(array[i])) {
                        return i;
                    }
                }
            }

            return -1;
        }
    }

    /**
     * toString --> [1,2,3,4]
     *
     * @param a
     * @return
     */
    public static String toString(Object[] a) {
        if (a == null) {
            return "null";
        }

        int iMax = a.length - 1;
        if (iMax == -1) {
            return "[]";
        }

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(String.valueOf(a[i]));
            if (i == iMax) {
                return b.append(']').toString();
            }
            b.append(",");
        }
    }
}
