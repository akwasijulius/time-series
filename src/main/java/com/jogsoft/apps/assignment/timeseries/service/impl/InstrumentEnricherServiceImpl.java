/**
 * 
 */
package com.jogsoft.apps.assignment.timeseries.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jogsoft.apps.assignment.timeseries.dto.InstrumentDto;
import com.jogsoft.apps.assignment.timeseries.entity.InstrumentPriceModifier;
import com.jogsoft.apps.assignment.timeseries.repository.InstrumentPriceModifierRepository;
import com.jogsoft.apps.assignment.timeseries.service.InstrumentEnricherService;

/**
 * @author Julius Oduro
 *
 */
@Service
public class InstrumentEnricherServiceImpl implements InstrumentEnricherService {
	
	@Autowired
	private InstrumentPriceModifierRepository repository;


	@Override
	public InstrumentDto enrichInstrumentValue(InstrumentDto instrument) {
		if(instrument != null){
			InstrumentPriceModifier modifier = repository.findByInstrumentName(instrument.getInstrumentName());
			if(modifier != null && modifier.getMultiplier() != null){
				BigDecimal enrichedValue = instrument.getValue().multiply(modifier.getMultiplier());
				instrument.setValue(enrichedValue);
			}
		}
		return instrument;
	}

}
