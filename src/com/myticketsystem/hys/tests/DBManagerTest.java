package com.myticketsystem.hys.tests;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import com.myticketsystem.hys.Concert;
import com.myticketsystem.hys.DBManager;
import com.myticketsystem.hys.GlobalVariables;
import com.myticketsystem.hys.User;

public class DBManagerTest {

	@Test
	public void testAddConcert() {
		// to test adding, we do what we do in the removal without the final assert
		DBManager.initDB();

		Concert c = DBManager.addConcert(GlobalVariables.DEFAULT_BLANK_CONCERT);
		assertNotNull(c);
		// we get an ID, check if the ID exists
		assertTrue(DBManager.concertExists(c));
		// if it does, remove it
		DBManager.removeConcert(c);
	}

	@Test
	public void testAddTransaction() {
		fail("Not yet implemented");
	}

	@Test
	public void testConcertExists() {
		DBManager.initDB();

		Concert c = DBManager.getConcertByID(3);
		// we get an ID, check if the ID exists
		assertTrue(DBManager.concertExists(c));
	}

	@Test
	public void testExecuteQuery() {
		fail("Not yet implemented");
	}

	@Test
	public void testExecuteUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllConcerts() {
		assertTrue(DBManager.getAllConcerts().size()>3);
	}

	@Test
	public void testGetUser() {
		User usr = DBManager.getUser("cashier1", "12345678");
		assertEquals("cashier",usr.getType());
	}

	@Test
	public void testInitDB() {
		DBManager.initDB();
		assertNotNull(DBManager.connection);
		try {
			assertFalse(DBManager.connection.isClosed());
		}catch (SQLException e) {
			fail(e.getMessage());
		}finally{
			try {
				DBManager.connection.close();
			} catch (SQLException e) {
				fail(e.getMessage());
			}
		}
	}

	@Test
	public void testRemoveConcert() {
		// to test removal, we add one, we remove one
		DBManager.initDB();

		Concert c = DBManager.addConcert(GlobalVariables.DEFAULT_BLANK_CONCERT);
		assertNotNull(c);
		// we get an ID, check if the ID exists
		assertTrue(DBManager.concertExists(c));
		// if it does, remove it
		DBManager.removeConcert(c);
		// check if it does NOT exist
		assertFalse(DBManager.concertExists(c));
	}

	@Test
	public void testSetStatementVars() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateConcert() {
		DBManager.initDB();
		try {
			
			Concert c = DBManager.getConcertByID(3);
			
			String oldTitle = c.getTitle();
				
			c.setTitle(c.getTitle() + "1");
			DBManager.updateConcert(c);
			c = DBManager.getConcertByID(3);
			assertEquals(oldTitle+"1", c.getTitle());
			c.setTitle(oldTitle);
			DBManager.updateConcert(c);
			c = DBManager.getConcertByID(3);
			assertEquals(oldTitle, c.getTitle());

		}finally{
			try {
				DBManager.connection.close();
			} catch (SQLException e) {
				fail(e.getMessage());
			}
		}
		
	}

}
