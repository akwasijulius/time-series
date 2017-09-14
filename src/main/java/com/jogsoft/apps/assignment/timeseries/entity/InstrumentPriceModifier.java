/**
 * 
 */
package com.jogsoft.apps.assignment.timeseries.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * InstrumentPriceModifier Entity Class
 * 
 * @author Julius Oduro
 */
@Entity
@Table(name="INSTRUMENT_PRICE_MODIFIER")
public class InstrumentPriceModifier {
	
	protected InstrumentPriceModifier() {		
	}
	
	public InstrumentPriceModifier(String instrumentName, BigDecimal multiplier) {
		super();
		this.instrumentName = instrumentName;
		this.multiplier = multiplier;
	}
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name = "NAME")
	private String instrumentName;
	
	@Column(name = "MULTIPLIER")
	private BigDecimal multiplier; 

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getInstrumentName() {
		return instrumentName;
	}
	public void setInstrumentName(String instrumentName) {
		this.instrumentName = instrumentName;
	}
	public BigDecimal getMultiplier() {
		return multiplier;
	}
	public void setMultiplier(BigDecimal multiplier) {
		this.multiplier = multiplier;
	}

}
