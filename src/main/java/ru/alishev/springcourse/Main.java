package ru.alishev.springcourse;

import java.util.Arrays;
import java.util.HashMap;

class Main {

    public static void main(String[] args) {
        System.out.println(solution(22));
    }

    public static int solution(int number) {
        int res = 0;
        int count = 1;
        if (number < 3) {
            return 0;
        }
        while (3 * count < number) {
            if(3 * count % 5 != 0) {
                res = res + 3 * count;
            }
            count++;
        }
        count = 1;
        while (5 * count < number) {
                res = res + 5 * count;
            count++;
        }
        return res;
    }
}
