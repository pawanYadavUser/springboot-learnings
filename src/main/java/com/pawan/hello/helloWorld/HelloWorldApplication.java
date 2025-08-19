package com.pawan.hello.helloWorld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class HelloWorldApplication {

	public static void main(String[] args) {
//		SpringApplication.run(HelloWorldApplication.class, args);
		ApplicationContext ctx = SpringApplication.run(HelloWorldApplication.class, args);

		System.out.println("==== All Beans in ApplicationContext ====");
		String[] beanNames = ctx.getBeanDefinitionNames();
		for (String beanName : beanNames) {
			System.out.println(beanName);
		}
	}

}

