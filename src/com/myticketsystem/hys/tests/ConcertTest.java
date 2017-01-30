package com.myticketsystem.hys.tests;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import com.myticketsystem.hys.Concert;
import com.myticketsystem.hys.GlobalVariables;
import com.myticketsystem.hys.Helper;
import com.myticketsystem.hys.User;

public class ConcertTest {

	@Test
	public void testConcert() {
		Concert c = new Concert("MF Doom", "Greatest rapper of all time", 250.0, new Date(2017-1900,8,11,7,59), "Trump Tower", 14);
		
		assertEquals("MF Doom",c.getTitle());
		assertEquals("Greatest rapper of all time",c.getDescription());
		assertEquals(250.0,c.getPrice(),0.0);
		assertEquals("11.09.2017 07:59", Helper.getFormattedDate(GlobalVariables.DEFAULT_DATE_FORMAT, c.getcDate()));
		assertEquals("Trump Tower",c.getVenue());
		assertEquals(14,c.getId());
		
		Concert c2 = new Concert();
		
		c2.setTitle("MF Doom");		
		c2.setDescription("Greatest rapper of all time");
		c2.setPrice(250.0);
		c2.setcDate(new Date(2017-1900,8,11,7,59));
		c2.setVenue("Trump Tower");
		c2.setId(14);
		
		assertEquals("MF Doom",c2.getTitle());
		assertEquals("Greatest rapper of all time",c2.getDescription());
		assertEquals(250.0,c2.getPrice(),0.0);
		assertEquals("11.09.2017 07:59", Helper.getFormattedDate(GlobalVariables.DEFAULT_DATE_FORMAT, c2.getcDate()));
		assertEquals("Trump Tower",c2.getVenue());
		assertEquals(14,c2.getId());
	}

}
