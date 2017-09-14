/**
 * 
 */
package com.jogsoft.apps.assignment.timeseries.repository;

import org.springframework.data.repository.CrudRepository;

import com.jogsoft.apps.assignment.timeseries.entity.InstrumentPriceModifier;

/**
 * InstrumentPriceModifierRepository is the repository for the InstrumentPriceModifier entity
 * 
 * @author Julius Oduro
 */
public interface InstrumentPriceModifierRepository extends CrudRepository<InstrumentPriceModifier, Long> {
	

	/**
	 * Find and returns the price modifier by the specified instrument name. For the purpose of this
	 * assignment we assume that the instrumentName would only have one unique record.
	 * 
	 * @param instrumentName the instrument name
	 * @return InstrumentPriceModifier
	 */
	InstrumentPriceModifier findByInstrumentName(String instrumentName);
}
