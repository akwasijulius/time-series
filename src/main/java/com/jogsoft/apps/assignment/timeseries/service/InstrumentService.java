package com.jogsoft.apps.assignment.timeseries.service;

import java.time.LocalDate;

public interface InstrumentService {

	/**
	 * Check and returns true or false if a given date is business date. That is 
	 * if date is not a weekend
	 * 
	 * @param date the date to be checked
	 * @return true if the date is a weekday otherwise false
	 */
	boolean isBusinessDate(LocalDate date);
	
	
	/**
	 * Checks if the specified date is after the set filter date
	 * 
	 * @param date the date to be checked
	 * @return true if the date is after or false
	 */
	boolean isAfterFilterDate(LocalDate date);

}