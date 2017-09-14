package com.jogsoft.apps.assignment;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.jogsoft.apps.assignment.timeseries.dto.InstrumentDto;
import com.jogsoft.apps.assignment.timeseries.processor.DataProcessor;
import com.jogsoft.apps.assignment.timeseries.service.InstrumentEnricherService;
import com.jogsoft.apps.assignment.timeseries.service.InstrumentService;

@RunWith(MockitoJUnitRunner.class)
public class DataProcessorTest {
	
	@Mock
	private InstrumentService service;

	@Mock
	private InstrumentEnricherService enricher;
	
	@InjectMocks
	private DataProcessor processor = new DataProcessor();



	@Test
	public final void testProcessLineDoesNotEnrichDataWhenItsNotABusinessDate() {		
		
		when(service.isBusinessDate(any(LocalDate.class))).thenReturn(false);
		
		processor.processLine(new String[] {"INSTRUMENT1","12-Sep-2017","22.09"});
		
		verify(enricher, never()).enrichInstrumentValue(any(InstrumentDto.class));		
		
	}
	
	
	@Test
	public final void testProcessLineDoesEnrichDataWhenItsABusinessDate() {		
		
		when(service.isBusinessDate(any(LocalDate.class))).thenReturn(true);
		
		processor.processLine(new String[] {"INSTRUMENT1","12-Sep-2017","22.09"});
		
		verify(enricher).enrichInstrumentValue(any(InstrumentDto.class));		
		
	}
	
	
	@Test(expected=Exception.class)
	public final void testProcessLineShouldthrowExecption() {	
		
		processor.processLine(new String[] {"INSTRUMENT1","","22.09"});
	}

}
