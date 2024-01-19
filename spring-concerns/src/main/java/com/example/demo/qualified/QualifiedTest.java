package com.example.demo.qualified;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QualifiedTest {
    @Autowired CommonInterface implementation1;

}

interface CommonInterface {}

@Component
class Implementation1 implements CommonInterface {}

@Component
class Implementation2 implements CommonInterface {}
