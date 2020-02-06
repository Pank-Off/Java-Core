package com.geekbrains.generic;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Создание пустой коробки?
//Имеет смысл переопределять equals и hashcode?
public class Main {

    public static void main(String[] args) {
        //Задание 1
        Integer[] I = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        System.out.println("Old: " + Arrays.toString(I));
        swap(I, 2, 4);
        System.out.println("New:" + Arrays.toString(I));

        String[] S = {"A", "B", "C", "D", "E", "F"};
        System.out.println("Old: " + Arrays.toString(S));
        swap(S, 2, 4);
        System.out.println("New:" + Arrays.toString(S));

        Double[] D = {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0};
        System.out.println("Old: " + Arrays.toString(D));
        swap(D, 2, 4);
        System.out.println("New:" + Arrays.toString(D));

        //Задание 2
        Integer[] A = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        System.out.println(Arrays.toString(A));
        System.out.println(convert(A));

        //Задание 3
        Box<Orange> orangeBox = new Box<>(new Orange());
        Box<Apple> appleBox = new Box<>(new Apple());

        Box<Apple> box = new Box();

        orangeBox.add(new Orange());
        appleBox.add(new Apple());

        //orangeBox.(new Apple()); так нельзя
        orangeBox.add(new Orange());
        appleBox.add(new Apple());

        orangeBox.add(new Orange());

        System.out.println(orangeBox.getWeight());
        System.out.println(appleBox.getWeight());

        System.out.println(orangeBox.compare(appleBox));

        appleBox.add(new Apple());
        appleBox.add(new Apple());
        appleBox.add(new Apple());

        System.out.println(appleBox.compare(orangeBox));

        System.out.println(appleBox.compare(appleBox));

        appleBox.moveTo(box);

        System.out.println(appleBox.getWeight());
        System.out.println(box.getWeight());

        box.moveTo(box);
        System.out.println(box.getWeight());
    }

    public static <T> void swap(T[] array, int i, int j) {
        T temp;
        temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    public static <T> List<T> convert(T[] array) {
        return new ArrayList<>(Arrays.asList(array));
    }
}
