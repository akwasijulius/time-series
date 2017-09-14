package com.jogsoft.apps.assignment.timeseries.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SummationDto {	
	
	private String name;
	private BigDecimal sum;
	private int counter;
	
	public SummationDto(String name) {
		this.name = name;
		this.sum = BigDecimal.ZERO;
		this.counter = 0;
	}

	public String getName() {
		return name;
	}
	
	public BigDecimal getSum() {
		return sum;
	}
	
	public void addtoSum(BigDecimal value) {
		sum = sum.add(value);
		counter += 1;		
	}
	
	public BigDecimal getAverage(){
		return sum.divide(new BigDecimal(counter), RoundingMode.HALF_UP);
	}	

	
}
