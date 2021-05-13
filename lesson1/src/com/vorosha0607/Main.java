package com.vorosha0607;

import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        String[] arr = {"231", "2sded", "adva"};
        changPlaceOfTwoElements(arr, 0, 2);
        System.out.print("Массив после изменений:");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }

        System.out.println();
        ArrayList<String> transformedArr = transformIntoArrayList(arr);
        System.out.println("ArrayList из массива: " + transformedArr);


        Apple apple1 = new Apple();
        Apple apple2 = new Apple();
        Orange orange1 = new Orange();
        Orange orange2 = new Orange();


        Box<Apple> appleBox1 = new Box<>();
        Box<Apple> appleBox2 = new Box<>(apple1, apple2);

        Box<Orange> orangeBox1 = new Box<>();
        Box<Orange> orangeBox2 = new Box<>(orange1, orange2);

        System.out.println("Сравнение appleBox1 с orangeBox1: " + appleBox1.compare(orangeBox1));
        System.out.println("Сравнение appleBox2 с orangeBox2: " + appleBox2.compare(orangeBox2));
        appleBox1.add(apple1);
        appleBox1.add(new Apple());

        System.out.println("Вес appleBox1 до объединения: " + appleBox1.getWeight());
        System.out.println("Вес appleBox2 до объединения: " + appleBox2.getWeight());
        appleBox2.addAnotherBox(appleBox1);
        System.out.println("Вес appleBox1 после объединения: " + appleBox1.getWeight());
        System.out.println("Вес appleBox2 после объединения: " + appleBox2.getWeight());


    }

    public static <T> void changPlaceOfTwoElements (T[] arr, int i, int j){
        T thirdCap = arr[i];
        arr[i] = arr[j];
        arr[j] = thirdCap;
    }

    public static <T> ArrayList<T> transformIntoArrayList(T[] arr){
        return new ArrayList<>(Arrays.asList(arr));
    }
}
