package com.myticketsystem.hys.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.myticketsystem.hys.Transaction;

public class TransactionTest {

	@Test
	public void testTransaction() {
		Transaction c = new Transaction();
		
		c.setConcertDate("11.09.2017 07:59");
		c.setConcertName("Chance The Rapper");
		c.setConcertSeat("F12");
		c.setConcertVenue("O2 Arena");
		c.setCustomerName("Euaggelos Kosmatos");
		c.setTicketPrice(49.99);
		c.setTicketType("Normal");
		c.setTransactionDate("06.09.2017 07:59");
		c.setTransactionId("TSID-A78F69C98");
		
		assertEquals("11.09.2017 07:59",c.getConcertDate());
		assertEquals("Chance The Rapper",c.getConcertName());
		assertEquals("F12",c.getConcertSeat());
		assertEquals("O2 Arena", c.getConcertVenue());
		assertEquals("Euaggelos Kosmatos",c.getCustomerName());
		assertEquals(49.99,c.getTicketPrice(),0.0);
		assertEquals("Normal",c.getTicketType());
		assertEquals("06.09.2017 07:59",c.getTransactionDate());
		assertEquals("TSID-A78F69C98",c.getTransactionId());
		
		c = new Transaction("Euaggelos Kosmatos", "11.09.2017 07:59", "Chance The Rapper", "F12",
				"O2 Arena", 49.99, "Normal", "06.09.2017 07:59", "TSID-A78F69C98");
		
		assertEquals("11.09.2017 07:59",c.getConcertDate());
		assertEquals("Chance The Rapper",c.getConcertName());
		assertEquals("F12",c.getConcertSeat());
		assertEquals("O2 Arena", c.getConcertVenue());
		assertEquals("Euaggelos Kosmatos",c.getCustomerName());
		assertEquals(49.99,c.getTicketPrice(),0.0);
		assertEquals("Normal",c.getTicketType());
		assertEquals("06.09.2017 07:59",c.getTransactionDate());
		assertEquals("TSID-A78F69C98",c.getTransactionId());
	}

}
