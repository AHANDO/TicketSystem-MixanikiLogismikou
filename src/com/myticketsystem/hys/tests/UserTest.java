package com.myticketsystem.hys.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.myticketsystem.hys.User;

public class UserTest {

	
	@Test
	public void testUsernameGet() {
		User u = new User("username1","12345678","cashier",true);
		
		assertEquals("username1",u.getUsername());
		assertEquals("12345678",u.getPassword());
		assertEquals("cashier",u.getType());
		assertTrue(u.isEnabled());
	}
	
	@Test
	public void testUsernameSet() {
		User u = new User("","","",false);
		u.setUsername("username1");
		u.setPassword("12345678");
		u.setType("cashier");
		u.setEnabled(true);
		
		assertEquals("username1",u.getUsername());
		assertEquals("12345678",u.getPassword());
		assertEquals("cashier",u.getType());
		assertTrue(u.isEnabled());
	}

}
