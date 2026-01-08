package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component("powerfulProcessor")
public class Processor {

    public void calculate() {
        System.out.println("Processor: Calculating complex data...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Value("${cpu.model}")
    private String model;

    @Value("${cpu.frequency}")
    private double frequency;

    public Processor() {
        System.out.println(">> Processor: Constructor called");
    }

    @PostConstruct
    public void init() {
        System.out.println(">> Processor: init method (@PostConstruct) - Ready to calculate");
    }

    @PreDestroy
    public void cleanup() {
        System.out.println(">> Processor: destroy method (@PreDestroy) - Cooling down");
    }

    @Override
    public String toString() {
        return "Processor model=" + model + ", freq=" + frequency;
    }
}