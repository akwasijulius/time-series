/**
 * 
 */
package com.jogsoft.apps.assignment.timeseries.processor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * FileProcessor 
 * @author Julius Oduro
 *
 */
@Component
public class FileProcessor {
	
	private static final Logger LOGGER = Logger.getLogger( FileProcessor.class.getName() );
	
	private static final String TOKEN =  ",";	
	 
	@Autowired
	private DataProcessor dataProcessor;
	
	
	public void processData(String filePath) throws IOException, URISyntaxException{		

		LOGGER.log(Level.INFO, "******* Processing file: {0} *******", filePath);
		
		try(BufferedReader in = new BufferedReader(new FileReader(filePath))) {
		    String line;
		    while ((line = in.readLine()) != null) {
		    	LOGGER.log( Level.INFO, "Processing data: {0}", line );
				dataProcessor.processLine(line.split(TOKEN));
		    }
		    
		    
		    dataProcessor.printResults();	
		}
			
	}
	
	


	
	
	
	


}
