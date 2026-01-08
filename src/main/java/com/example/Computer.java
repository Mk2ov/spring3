package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Computer {

    private String caseType;
    private Processor processor;

    @Autowired
    public Computer(@Value("${pc.case}") String caseType,
                    @Qualifier("powerfulProcessor") Processor processor) {
        this.caseType = caseType;
        this.processor = processor;
        System.out.println(">> Computer: Constructor called");
    }

    @Override
    public String toString() {
        return "Computer [case=" + caseType + ", " + processor + "]";
    }
}