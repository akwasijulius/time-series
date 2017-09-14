/**
 * 
 */
package com.jogsoft.apps.assignment.timeseries.service.impl;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.jogsoft.apps.assignment.timeseries.service.InstrumentService;

/**
 * @author Julius Oduro
 *
 */
@Service
public class InstrumentServiceImpl implements InstrumentService {
	
	/*Filter for dates from Nov 2015 */
	private static final LocalDate fromDate = LocalDate.of(2014, 10, 31);
	
	
	
	@Override
	public boolean isBusinessDate(LocalDate date){	
		return date != null && date.getDayOfWeek() != SATURDAY && date.getDayOfWeek() != SUNDAY;
	}

	@Override
	public boolean isAfterFilterDate(LocalDate date) {
		return date != null && date.isAfter(fromDate);
	}
	
	

}
