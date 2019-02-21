package com.example.carloscabello;

public class Pet {
    private String name;
    private int age;
    private float weight;

    public Pet(String name, int age, float weight){
        this.name = name;
        this.age = age;
        this.weight = weight;
    }

    public String getName(){
        return this.name;
    }

    public int getAge(){
        return this.age;
    }

    public float getWeight(){
        return this.weight;
    }
}
