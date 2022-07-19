package org.algorithm;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SuperTel {
    public static void main(String[] args) {
        System.out.println(method1(new String[]{"1,3-5", "2", "3-4"}));
        System.out.println(method2(new String[]{"1,3-5", "2", "3-4"}));
    }

    // Метод возвращает упорядоченный список чисел
    private static List<Integer> method1(String[] arr) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (!arr[i].contains(",") && !arr[i].contains("-")) {
                list.add(Integer.valueOf(arr[i]));
            } else if (arr[i].contains(",") && !arr[i].contains("-")) {
                list.add(Integer.valueOf(arr[i].split(",")[0]));
                list.add(Integer.valueOf(arr[i].split(",")[1]));
            } else if (arr[i].contains(",") && arr[i].contains("-")) {
                list.add(Integer.valueOf(arr[i].split(",")[0]));
                String s = arr[i].split(",")[1];
                int start = Integer.valueOf(s.split("-")[0]);
                int end = Integer.valueOf(s.split("-")[1]);
                IntStream stream = IntStream.range(start, end + 1);
                stream.forEach(list::add);
            } else if (!arr[i].contains(",") && arr[i].contains("-")) {
                int start = Integer.valueOf(arr[i].split("-")[0]);
                int end = Integer.valueOf(arr[i].split("-")[1]);
                IntStream stream = IntStream.range(start, end + 1);
                stream.forEach(list::add);
            }
        }
        Collections.sort(list);
        return list;
    }


    //
    private static  List<List<Integer>>  method2(String[] arr) {
        List<List<Integer>> list = new ArrayList<>(arr.length);
        for (int i = 0; i < arr.length; i++) {
            if (!arr[i].contains(",") && !arr[i].contains("-")) {
                list.add(List.of(Integer.valueOf(arr[i])));
            } else if (arr[i].contains(",") && !arr[i].contains("-")) {
                list.add(List.of(
                        Integer.valueOf(arr[i].split(",")[0]),
                        Integer.valueOf(arr[i].split(",")[1])));

            } else if (arr[i].contains(",") && arr[i].contains("-")) {
                String s = arr[i].split(",")[1];
                int start = Integer.valueOf(s.split("-")[0]);
                int end = Integer.valueOf(s.split("-")[1]);
                IntStream stream = IntStream.range(start, end + 1);
                List<Integer> list1 = new ArrayList<>();
                list1.add(Integer.valueOf(arr[i].split(",")[0]));
                stream.forEach(list1::add);
                list.add(list1);
            } else if (!arr[i].contains(",") && arr[i].contains("-")) {
                int start = Integer.valueOf(arr[i].split("-")[0]);
                int end = Integer.valueOf(arr[i].split("-")[1]);
                IntStream stream = IntStream.range(start, end + 1);
                List<Integer> list2 = new ArrayList<>();
                stream.forEach(list2::add);
                list.add(list2);
            }

        }
        return cartesianProduct(list);
    }

    // Метод возвращает Декартовое множество
    private static <T> List<List<T>> cartesianProduct(List<List<T>> lists) {
        List<List<T>> resultLists = new ArrayList<List<T>>();
        if (lists.size() == 0) {
            resultLists.add(new ArrayList<T>());
            return resultLists;
        } else {
            List<T> firstList = lists.get(0);
            List<List<T>> remainingLists = cartesianProduct(lists.subList(1, lists.size()));
            for (T condition : firstList) {
                for (List<T> remainingList : remainingLists) {
                    ArrayList<T> resultList = new ArrayList<T>();
                    resultList.add(condition);
                    resultList.addAll(remainingList);
                    resultLists.add(resultList);
                }
            }
        }
        return resultLists;
    }
}
