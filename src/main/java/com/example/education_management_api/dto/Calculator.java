package com.example.education_management_api.dto;

import java.util.List;

public class Calculator {

    public int add(int a, int b) {
        return a + b;
    }

    public int divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("b must not be zero");
        }
        return a / b;
    }

    public boolean isEvenNumber(int a) {
        return true;
    }

    public int countAmountEvenNumber(List<Integer> numbers) {
        int count = 0;
        for (Integer number : numbers) {
            if (number % 2 == 0) {
                count++;
            }
        }
        return count;
    }
}
