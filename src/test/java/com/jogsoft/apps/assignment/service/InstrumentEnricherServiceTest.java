package com.jogsoft.apps.assignment.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.jogsoft.apps.assignment.timeseries.dto.InstrumentDto;
import com.jogsoft.apps.assignment.timeseries.entity.InstrumentPriceModifier;
import com.jogsoft.apps.assignment.timeseries.repository.InstrumentPriceModifierRepository;
import com.jogsoft.apps.assignment.timeseries.service.InstrumentEnricherService;
import com.jogsoft.apps.assignment.timeseries.service.impl.InstrumentEnricherServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class InstrumentEnricherServiceTest {
	
	@Mock
	private InstrumentPriceModifierRepository repository;
	
	@InjectMocks
	InstrumentEnricherService enricher = new InstrumentEnricherServiceImpl();



	@Test
	public final void testEnrichInstrumentValueIsEnriched() {
		String instrumentName = "Instrument1";
		BigDecimal price = new BigDecimal(20.05);
		
		InstrumentDto instrument = new InstrumentDto(instrumentName, LocalDate.now(), price);
		
		BigDecimal multiplier = new BigDecimal(0.45);
		InstrumentPriceModifier priceModifier = new InstrumentPriceModifier(instrumentName, multiplier);
		
		when(repository.findByInstrumentName(instrumentName)).thenReturn(priceModifier);
		
		enricher.enrichInstrumentValue(instrument);
		
		assertThat(instrument.getValue().doubleValue(), is(price.multiply(multiplier).doubleValue()));		
	}
	
	@Test
	public final void testEnrichInstrumentValueIsNotEnrichedWhenModifierNotFound() {
		String instrumentName = "Instrument1";
		BigDecimal price = new BigDecimal(20.05);
		
		InstrumentDto instrument = new InstrumentDto(instrumentName, LocalDate.now(), price);
		
		
		when(repository.findByInstrumentName(instrumentName)).thenReturn(null);
		
		enricher.enrichInstrumentValue(instrument);
		
		assertThat(instrument.getValue().doubleValue(), is(price.doubleValue()));		
	}
	
	@Test
	public final void testEnrichInstrumentValueIsNotEnrichedWhenPriceModifierIsNull() {
		String instrumentName = "Instrument1";
		BigDecimal price = new BigDecimal(20.05);
		
		InstrumentDto instrument = new InstrumentDto(instrumentName, LocalDate.now(), price);

		InstrumentPriceModifier priceModifier = new InstrumentPriceModifier(instrumentName, null);
		
		when(repository.findByInstrumentName(instrumentName)).thenReturn(priceModifier);
		
		enricher.enrichInstrumentValue(instrument);
		
		assertThat(instrument.getValue().doubleValue(), is(price.doubleValue()));			
	}
	
	
	@Test
	public final void testEnrichInstrumentValueIsNotEnrichedWhenInstrumentIsNull() {
		InstrumentDto instrument = null;
		
		InstrumentDto returnedInstrument = enricher.enrichInstrumentValue(instrument);
		
		assertThat(returnedInstrument, is(nullValue()));		
	}

}
