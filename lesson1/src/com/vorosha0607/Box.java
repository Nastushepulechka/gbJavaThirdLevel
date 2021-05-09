package com.vorosha0607;

import java.util.ArrayList;
import java.util.Arrays;

public class Box <T extends Fruit>{

    private ArrayList<T> boxWithFruits;

    public Box(T... fruits) {
        this.boxWithFruits = new ArrayList(Arrays.asList(fruits));
    }

    public Box() {
        this.boxWithFruits = new ArrayList();
    }

    public float  getWeight(){
        float w = 0.0f;
        for (T fruit : boxWithFruits){
            w += fruit.getWeight();
        }
        return w;
    }


    public boolean compare(Box<?> o) {
        return Math.abs(this.getWeight()-o.getWeight()) < 0.00001;
    }

    public void add (T item){
        boxWithFruits.add(item);
    }

    public void addAnotherBox (Box<T> anotherBox){
        if (this == anotherBox){
            return;
        }
        anotherBox.boxWithFruits.addAll(this.boxWithFruits);
        this.boxWithFruits.clear();
    }
}
