package com.example.education_management_api;

import java.util.ArrayList;
import java.util.List;

public class ExampleClass {

    public static void main(String[] args) {
        System.out.println("Tìm chỉ số của số lớn nhất trong một mảng");
        List<Integer> list = new ArrayList<>();
        list.add(1); //0
        list.add(5); //1
        list.add(7); //2
        list.add(3); //3
        System.out.println(list.get(0));
        for (int i = 0; i < list.size(); i++) {
            System.out.println("Giá trị của số thứ " + i + "là :" + list.get(i));
        }
        // while do
        // do while
        // if else
        // switch case

        Integer result = getIndexOfBiggestNumberInList(list);
        System.out.println("Index of biggest number: " + result);

    }

    public static Integer getIndexOfBiggestNumberInList(List<Integer> list){
        int indexOfBiggestNumber = 0;
        int biggestNumber = list.get(0); // 1
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) > biggestNumber) { // 5 > 1
                indexOfBiggestNumber = i; // từ 0 -> 1
                biggestNumber = list.get(i); // từ 1 -> 5
            }
        }
        return indexOfBiggestNumber;
    }
}
