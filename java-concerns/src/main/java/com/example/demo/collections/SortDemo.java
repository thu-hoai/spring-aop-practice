package com.example.demo.collections;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortDemo {

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student(223, "Chaitanya", 26));
        students.add(new Student(245, "Rahul", 24));
        students.add(new Student(209, "Ajeet", 32));

        // Sort
        sortPracticing(students);

        // Convert ArrayList to Array and Array to ArrayList
        List<Student> arrayList = Arrays.asList(new Student(), new Student());
        Student[] array = students.toArray(new Student[students.size()]);
    }

    private static void  sortPracticing(List<Student> students) {
        Collections.sort(students, Comparator.comparing(Student::getStudentName));
        students.stream().forEach(student -> System.out.println(student));
    }
}

class Student {

    private String studentName;
    private int rollNo;
    private int studentAge;

    public Student() {}

    public Student(int rollNo, String studentName, int studentAge) {
        this.rollNo = rollNo;
        this.studentName = studentName;
        this.studentAge = studentAge;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public int getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(int studentAge) {
        this.studentAge = studentAge;
    }

    @Override
    public String toString() {
        return "Student{" +
            "studentName='" + studentName + '\'' +
            ", rollNo=" + rollNo +
            ", studentAge=" + studentAge +
            '}';
    }
}
