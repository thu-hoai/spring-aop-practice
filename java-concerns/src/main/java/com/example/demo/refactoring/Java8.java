package com.example.demo.refactoring;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Java8 {

  public static void main(String... args) throws IOException {
    String oneLine = processFile(BufferedReader::readLine);
    System.out.println(oneLine);
  }

  public static String processFile(BufferedReaderProcessor p) throws IOException {
    try (BufferedReader br = new BufferedReader(new FileReader("lambdasinaction/chap3/data.txt"))) {
      return p.process(br);
    }
  }
}

@FunctionalInterface
interface BufferedReaderProcessor {
  String process(BufferedReader b) throws IOException;
}

interface ValidationStrategy {
  boolean execute(String s);
}

class IsAllLowerCase implements ValidationStrategy {
  public boolean execute(String s) {
    return s.matches("[a-z]+");
  }
}

class IsNumeric implements ValidationStrategy {
  public boolean execute(String s) {
    return s.matches("\\d+");
  }
}

class Validator {
  private final ValidationStrategy strategy;

  public Validator(ValidationStrategy v) {
    this.strategy = v;
  }

  public boolean validate(String s) {
    return strategy.execute(s);
  }

  Validator numericValidator = new Validator(new IsNumeric());
  boolean b1 = numericValidator.validate("aaaa");
  Validator numericValidator2 = new Validator((String s) -> s.matches("[a-z]+"));
  boolean b11 = numericValidator2.validate("aaaa");
}