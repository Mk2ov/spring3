package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Workplace {

    @Value("${workplace.owner}")
    private String owner;

    private Computer computer;

    @Autowired
    public void setComputer(Computer computer) {
        this.computer = computer;
        System.out.println(">> Workplace: Setter called (setComputer)");
    }

    public void printInfo() {
        System.out.println("Workplace Owner: " + owner);
        System.out.println("Equipment: " + computer);
    }
}