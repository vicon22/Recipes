package com.example.java_spring;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        BaseNumberGenerator generator = new MagicNumberGenerator(20);
        System.out.println(generator.generate());
        System.out.println(LocalDateTime.now());


    }
}
