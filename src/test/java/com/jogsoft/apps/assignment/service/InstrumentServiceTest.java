/**
 * 
 */
package com.jogsoft.apps.assignment.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Before;
import org.junit.Test;

import com.jogsoft.apps.assignment.timeseries.service.InstrumentService;
import com.jogsoft.apps.assignment.timeseries.service.impl.InstrumentServiceImpl;

/**
 * @author Julius Oduro
 */
public class InstrumentServiceTest {
	
	InstrumentService service;
	
	@Before
	public void setUp(){
		service = new InstrumentServiceImpl();		
	}

	/**
	 * Test method for {@link com.jogsoft.apps.assignment.timeseries.service.impl.InstrumentServiceImpl#isBusinessDate(java.time.LocalDate)}.
	 */
	@Test
	public void testIsBusinessDateShouldReturnTrueForWeekday() {
		// 11th Sept 2017 is Monday
		LocalDate date = LocalDate.of(2017, Month.SEPTEMBER, 11);
		boolean results = service.isBusinessDate(date);
		assertThat(results, is(true));
	}
	
	@Test
	public void testIsBusinessDateShouldReturnFalseForSaturday() {
		// 09th Sept 2017 is Saturday
		LocalDate date = LocalDate.of(2017, Month.SEPTEMBER, 9);
		boolean results = service.isBusinessDate(date);
		assertThat(results, is(false));
	}
	
	@Test
	public void testIsBusinessDateShouldReturnFalseForSunday() {
		// 10th Sept 2017 is Sunday
		LocalDate date = LocalDate.of(2017, Month.SEPTEMBER, 10);
		boolean results = service.isBusinessDate(date);
		assertThat(results, is(false));
	}
	
	
	@Test
	public void testIsBusinessDateShouldReturnFalseForNullDate() {
		boolean results = service.isBusinessDate(null);
		assertThat(results, is(false));
	}



	@Test
	public void testIsAfterFilterDateShouldReturnTrue() {
		LocalDate date = LocalDate.of(2014, Month.NOVEMBER, 1);
		boolean results = service.isAfterFilterDate(date);
		assertThat(results, is(true));
	}
	
	@Test
	public void testIsAfterFilterDateShouldReturnFalse() {
		LocalDate date = LocalDate.of(2014, Month.OCTOBER, 31);
		boolean results = service.isAfterFilterDate(date);
		assertThat(results, is(false));
	}
	
	@Test
	public void testIsAfterFilterDateShouldReturnFalseForNullDate() {
		boolean results = service.isAfterFilterDate(null);
		assertThat(results, is(false));
	}

}
