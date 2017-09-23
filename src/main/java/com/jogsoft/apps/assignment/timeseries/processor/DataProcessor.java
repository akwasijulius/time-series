/**
 * 
 */
package com.jogsoft.apps.assignment.timeseries.processor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jogsoft.apps.assignment.timeseries.dto.SummationDto;
import com.jogsoft.apps.assignment.timeseries.dto.InstrumentDto;
import com.jogsoft.apps.assignment.timeseries.service.InstrumentEnricherService;
import com.jogsoft.apps.assignment.timeseries.service.InstrumentService;

/**
 * Service class responsible for processing the line records
 * @author Julius Oduro
 */
@Service
public class DataProcessor {
	private static Logger LOGGER = Logger.getLogger(DataProcessor.class.getName());

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MMM-yyyy");

	@Autowired
	private InstrumentService service;

	@Autowired
	private InstrumentEnricherService enricher;

	/* DTO for the summation of instruments */
	private SummationDto averageSummation;

	/* DTO for the summation of instruments filtered by date */
	private SummationDto dateLimitSummation;

	public void processLine(String[] values) {
		LocalDate date = LocalDate.parse(values[1].trim(), FORMATTER);
		if (service.isBusinessDate(date)) {
			InstrumentDto instrument = new InstrumentDto(values[0].trim(), date, new BigDecimal(values[2].trim()));

			// enrich the instrument price value
			enricher.enrichInstrumentValue(instrument);

			// add to the mean counter if its instrument 1
			if (instrument.getInstrumentName().equals("INSTRUMENT1")) {
				if (averageSummation == null) {
					averageSummation = new SummationDto(instrument.getInstrumentName());
				}
				averageSummation.addtoSum(instrument.getValue());
			}

			// add to average holder for instrument 2 from Nov 2014
			else if (instrument.getInstrumentName().equals("INSTRUMENT2")
					&& service.isAfterFilterDate(instrument.getDate())) {
				if (dateLimitSummation == null) {
					dateLimitSummation = new SummationDto(instrument.getInstrumentName());
				}
				dateLimitSummation.addtoSum(instrument.getValue());
			}
		}
	}

	

	public void printResults() {
		LOGGER.log( Level.INFO, "");
		LOGGER.log( Level.INFO, "******************** Results ********************");
		LOGGER.log( Level.INFO, "{0} average: {1}",  new Object[]{ averageSummation.getName(),  averageSummation.getAverage() } );
		LOGGER.log( Level.INFO, "{0} average since November 2014: {1}", new Object[]{ dateLimitSummation.getName(), dateLimitSummation.getAverage() } );
		LOGGER.log( Level.INFO, "*************************************************");
	}

}
