package com.example.demo.designpattern.factorymethod;


public class PetFactory {

    public Pet createPet(String animalType) {
        return switch (animalType.toLowerCase()) {
            case "dog" -> new Dog();
            case "cat" -> new Cat();
            default -> throw new UnsupportedOperationException("Unknown animal type");
        };
    }
}
