package com.example.startdemo;

import com.example.startdemo.common.utils.SpringBeanUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class StartDemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(StartDemoApplication.class, args);
		SpringBeanUtils.setApplicationContext(context);
//		String[] beanDefinitionNames = context.getBeanDefinitionNames();
//		for (String beanDefinitionName : beanDefinitionNames) {
//			System.out.println(context.getBean(beanDefinitionName));
//		}

	}

}
