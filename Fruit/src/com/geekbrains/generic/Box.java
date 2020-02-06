package com.geekbrains.generic;

import java.util.ArrayList;

public class Box <T extends Fruit> {
    private T fruit;
    private ArrayList<T> list;

    Box() {
        list = new ArrayList<>();
    }

    Box(T fruit) {
        this.fruit = fruit;
        list = new ArrayList<>();
        list.add(fruit);
    }

    double getWeight() {

        double w = fruit.getWeight();
        double sum = 0;
        for(T i: list)
            sum += w;
        return sum;
    }

    void add(T fruit) {
        list.add(fruit);
    }

    public boolean compare(Box<?> another) {
        return Math.abs(this.getWeight() - another.getWeight()) < 0.0001;
    }

    public void moveTo(Box<T> another) {
        if(this.equals(another))
            return;
        another.fruit = this.fruit;
        another.list.addAll(list);
        list.clear();
    }

}

