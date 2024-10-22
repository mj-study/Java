package com.example.effectivejava.section05.item30;

import java.lang.reflect.Array;
import java.util.List;

public class UseGenericListToArray {

    public static <T> T[] listToArray(List<T> list, T[] array) {
        if (array.length < list.size()) {
            T[] newArray = (T[]) Array.newInstance(array.getClass().getComponentType(),
                list.size());
            return list.toArray(newArray);
        }
        return list.toArray(array);
    }


    public static void main(String[] args) {
        List<String> list = List.of("사랑", "낭만", "자객");
        String[] strings = listToArray(list, new String[0]);

        StringBuilder sb = new StringBuilder();
        for (String string : strings) {
            sb.append(string).append(' ');
        }

        System.out.println(sb);
    }

}
