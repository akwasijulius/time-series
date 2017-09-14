/**
 * 
 */
package com.jogsoft.apps.assignment.timeseries.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Julius Oduro
 *
 */
public class InstrumentDto {
	
	private String instrumentName;
	private LocalDate date;
	private BigDecimal value;
	
	public InstrumentDto(String instrumentName, LocalDate date, BigDecimal value){
		this.instrumentName = instrumentName;
		this.date = date;
		this.value = value;
	}
	
	public String getInstrumentName() {
		return instrumentName;
	}
	public void setInstrumentName(String instrumentName) {
		this.instrumentName = instrumentName;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InstrumentDto [instrumentName=").append(instrumentName).append(", date=").append(date)
				.append(", value=").append(value).append("]");
		return builder.toString();
	}

	

}
