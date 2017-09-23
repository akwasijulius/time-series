package com.jogsoft.apps.assignment.timeseries;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.jogsoft.apps.assignment.timeseries.processor.FileProcessor;

@SpringBootApplication
public class Application {

	
	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
				
		String filePath = args[0];
		FileProcessor processor =  context.getBean(FileProcessor.class);
		processor.processData(filePath);		   
	}


}
