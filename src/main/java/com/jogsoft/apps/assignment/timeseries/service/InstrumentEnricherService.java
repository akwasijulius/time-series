package com.jogsoft.apps.assignment.timeseries.service;

import com.jogsoft.apps.assignment.timeseries.dto.InstrumentDto;

public interface InstrumentEnricherService {
	
	/**
	 * Enriches the value of the instrument with the price modify.
	 * 
	 * @param instrument The instrument to enrich
	 * @return The enriched instrument
	 */
	InstrumentDto enrichInstrumentValue(InstrumentDto instrument);

}
