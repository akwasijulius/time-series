/**
 * 
 */
package com.jogsoft.apps.assignment.timeseries.processor;

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
 * @author Julius Oduro
 *
 */
@Component
public class FileProcessor {
	
	private static final Logger LOGGER = Logger.getLogger( FileProcessor.class.getName() );
	
	private static final String TOKEN =  ",";	
	
	@Value("${time.series.data.file}")
	private String fileName;		
	 
	@Autowired
	private DataProcessor dataProcessor;
	
	
	public void processData() throws IOException, URISyntaxException{
		
		this.prompt();
		
		Path path = Paths.get(getClass().getClassLoader().getResource(fileName).toURI());
		
		try( Stream<String> lines = Files.lines(path)){			
			lines.forEach( line -> {
				LOGGER.log( Level.INFO, "Processing data: {0}", line );
				dataProcessor.processLine(line.split(TOKEN));
				} );
			
		}catch(Exception ex){
			throw ex;
		}
		
		dataProcessor.printResults();
		
	}
	
	

	
	
	
	
	private void prompt(){
		LOGGER.log( Level.INFO, "Press \"Enter\" to continue...");				
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		scanner.close();
	}
	
	
	
	


}
