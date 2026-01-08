package com.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(AppConfig.class);

		System.out.println("\n---------------- MAIN START ----------------");

		Workplace workplace = context.getBean(Workplace.class);
		Processor processor = context.getBean(Processor.class);

		workplace.printInfo();

		System.out.println("\n---------------- Calling Processor ----------------");

		processor.calculate();

		System.out.println("---------------- MAIN END ----------------\n");

		context.close();
	}
}