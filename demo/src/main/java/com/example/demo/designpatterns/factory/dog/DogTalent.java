package com.example.demo.designpatterns.factory.dog;

public class DogTalent extends DogFactory {

    @Override
    public Husky createHushy(String name) {
        return new Husky(name);
    }

    @Override
    public Samoye createSamoye(String name) {
        return new Samoye(name);
    }
}
