package com.example.demo.java8;

import java.util.Optional;

public class OptionalPractices {

    public static void main(String[] args) {
    }

    private String getProvinceRefactor() {
        Student student = getStudent();
        return Optional.ofNullable(student)
                .map(Student::getAddress)
                .map(Address::getProvince)
                .orElse("not specified");
    }

    private String getProvince() {
        Student student = getStudent();
        if (student != null) {
            Address address = student.getAddress();
            if (address != null) {
                String province = address.getProvince();
                if (province != null) {
                    return province;
                }
            }
        }
        return "not specified";
    }

    private Student getStudent() {
        return new Student(1);
    }
}

class Student {

    public Student(Integer id) {
        this.id = id;
    }

    private Integer id;
    private Address address;

    public Address getAddress() {
        return address;
    }
}

class Address {
    private String province;

    public String getProvince() {
        return province;
    }
}