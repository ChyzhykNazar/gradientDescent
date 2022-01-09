package com.company;

import static java.lang.Math.pow;
import static java.lang.StrictMath.abs;

class function{
    public static double func1(double x1, double x2){
        return (x1-15)*(x1-15) + (3 * x2 - 15)*(3 * x2 - 15);
    }
    public static double func2(double x1, double x2){
        return 5 * pow(2 * 15 * x2 - pow(x1, 2),2) + pow((15 - x1), 2);
    }
}

class gold_section {
    static final double PHI = (1 + Math.sqrt(5)) / 2;

    public static double goldSectionFunc1ForX(double a, double b, double y, double e) {
        double x1, x2;
        while (true){
            x1 = b - (b - a) / PHI;
            x2 = a + (b - a) / PHI;
            if (function.func1(x1,y) >= function.func1(x2,y))
                a = x1;
            else
                b = x2;
            if (Math.abs(b - a) < e)
                break;
        }
        return (a + b) / 2;
    }
    public static double goldSectionFunc1ForY(double a, double b, double x, double e) {
        double x1, x2;
        while (true){
            x1 = b - (b - a) / PHI;
            x2 = a + (b - a) / PHI;
            if (function.func1(x, x1) >= function.func1(x,x2))
                a = x1;
            else
                b = x2;
            if (Math.abs(b - a) < e)
                break;
        }
        return (a + b) / 2;
    }
    public static double goldSectionFunc2ForX(double a, double b, double y, double e) {
        double x1, x2;
        while (true){
            x1 = b - (b - a) / PHI;
            x2 = a + (b - a) / PHI;
            if (function.func2(x1,y) >= function.func2(x2,y))
                a = x1;
            else
                b = x2;
            if (Math.abs(b - a) < e)
                break;
        }
        return (a + b) / 2;
    }

    public static double goldSectionFunc2ForY(double a, double b, double x, double e) {
        double x1, x2;
        while (true){
            x1 = b - (b - a) / PHI;
            x2 = a + (b - a) / PHI;
            if (function.func2(x, x1) >= function.func2(x,x2))
                a = x1;
            else
                b = x2;
            if (Math.abs(b - a) < e)
                break;
        }
        return (a + b) / 2;
    }
}

public class Main {

    public static void main(String[] args) {
        double eps = 0.001;
        int N = 15;
        int kmax = 20;
        int k = 0;
        double sumx = 0;
        double sumz = 1;
        double x = -1.3 * N;
        double y = 1.7 * N;
        while (abs(sumz - sumx) > eps && k < kmax){
            double a = -2 * N;
            double b = 2 * N;
            double newX = x;
            double newY = y;
            System.out.println("x = " + x);
            System.out.println("y = " + y);
            System.out.println("func = " + function.func1(x,y));
            x = gold_section.goldSectionFunc1ForX(a, b, y, eps);
            System.out.println("x = " + x);
            System.out.println("y = " + y);
            System.out.println("func = " + function.func1(x,y));
            y = gold_section.goldSectionFunc1ForY(a, b, x, eps);
            System.out.println("x = " + x);
            System.out.println("y = " + y);
            System.out.println("func = " + function.func1(x,y));
            sumx = function.func1(x,y);
            sumz = function.func1(newX,newY);
            k++;
        }
        System.out.println("K = " + k);
        sumx = 0;
        sumz = 1;
        x = -1.3 * N;
        y = 1.7 * N;
        k = 0;
        System.out.println("\n2 Function\n");
        while (abs(sumz - sumx)> eps && k < kmax){
            double a = -2 * N;
            double b = 2 * N;
            double a1 = -2 * N;
            double a2 = 2 * N;
            double newX = x;
            double newY = y;
            System.out.println("x = " + x);
            System.out.println("y = " + y);
            System.out.println("func = " + function.func2(x,y));
            x = gold_section.goldSectionFunc2ForX(a, b, y, eps);
            System.out.println("x = " + x);
            System.out.println("y = " + y);
            System.out.println("func = " + function.func2(x,y));
            y = gold_section.goldSectionFunc2ForY(a1, a2, x, eps);
            sumx = function.func2(x,y);
            sumz = function.func2(newX,newY);
            System.out.println("sumx = " + sumx);
            System.out.println("sumz = " + sumz);
            System.out.println("\u001B[31m" + "sumz - sumx = " + (sumz - sumx) + "\u001B[0m");
            k++;
        }
        System.out.println("K = " + k);
    }
}
