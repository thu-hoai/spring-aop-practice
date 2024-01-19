package com.example.demo.collections;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class TreeSetDemo {

    public static void main(String[] args) {

        User u1 = new User("D", 30);
        User u2 = new User("D", 25);
        User u3 = new User("B", 35);

        Set<User> userSet = new TreeSet<>();
        userSet.add(u1);
        userSet.add(u3);
        userSet.add(u2);

        User u4 = new User("B", 11);
        User u5 = new User("A", 100);
        userSet.add(u4);
        userSet.add(u5);

        System.out.println(userSet.contains(u4));
    }
}

class User implements Comparable<User> {

    private String name;
    private Integer age;

    public User(String name, int age) {
        this.age = age;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "User{" +
            "name='" + name + '\'' +
            ", age=" + age +
            '}';
    }

    @Override
    public int compareTo(User o) {
        return this.age.compareTo(o.getAge());
    }
}
