package com.myticketsystem.hys.tests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;

import org.junit.Test;

import com.alee.laf.tree.WebTree;
import com.myticketsystem.hys.GlobalVariables;
import com.myticketsystem.hys.Helper;

public class HelperTest {

	@Test
	public void testAddNodeToTree() {
		String text = "Test Text";
		assertEquals(Helper.addNodeToTree(new WebTree<DefaultMutableTreeNode>(), text).toString(), text );
	}

	@Test
	public void testDarkenColor() {
		Color original = Color.decode("#A1A1A1");
		Color darker = Helper.darkenColor(original, 0.1f);
		Color wanted = new Color(145, 145, 145, 255);
		assertEquals(darker, wanted);
	}

	@Test
	public void testGetAllConcertsByName() {
		assertTrue(Helper.getAllConcertsByName().size()>0);
	}

	@Test
	public void testGetAllRowsByColumn() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllUsers() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDiscountedPrice() {
		// since our current discount is 50%
		assertEquals(20.0, Helper.getDiscountedPrice(40.0),0.0);
	}

	@Test
	public void testGetFormattedDate() {
		Date d = new Date(2001-1900,8,11,7,59); // starts the years from 1900 and goes up! Also months is ZERO based, so -1 to get the real one
		assertEquals("11.09.2001 07:59" , Helper.getFormattedDate(GlobalVariables.DEFAULT_DATE_FORMAT, d) );
	}

	@Test
	public void testGetIcon() {
		Icon i = Helper.getIcon("images/cog.png");
		assertNotNull(i);
	}

	@Test
	public void testGetListAverage() {
		List<Object> list = new ArrayList<Object>();
		list.add(10.0);
		list.add(20.0);
		list.add(30.0);
		list.add(40.0);
		list.add(50.0);
		
		assertEquals(30.0, Helper.getListAverage(list),0.0);
	}

	@Test
	public void testGetListSum() {
		List<Object> list = new ArrayList<Object>();
		list.add(10.0);
		list.add(20.0);
		list.add(30.0);
		list.add(40.0);
		list.add(50.0);
		
		assertEquals(150.0 ,Helper.getListSum(list) ,0.0);
	}

	@Test
	public void testGetRandomBool() {
		int trueCount = 0;
		int falseCount = 0;
		for (int i = 0; i < 1000; i++) {
			if (Helper.getRandomBool()){
				trueCount++;
			}else{
				falseCount++;
			}
			
		}
		
		assertTrue(trueCount>200);
		assertTrue(falseCount>200);
		
		// stupid, I know, but you gotta experiment
	}

	@Test
	public void testGetRandomString() {
		assertEquals(10, Helper.getRandomString(10).length());
	}

	@Test
	public void testGetSeatFillStatus() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetUpcomingConcerts() {
		assertTrue(Helper.getUpcomingConcerts().size()>0);
	}

	@Test
	public void testSetPanelEnabled() {
		JPanel panel = new JPanel();
		JLabel lbl =  new JLabel("test label to see if it was disabled");
		lbl.setEnabled(true);
		panel.add(lbl);
		Helper.setPanelEnabled(panel, false);
		
		assertFalse(lbl.isEnabled());
	}

	@Test
	public void testCentreWindow(){
		JFrame w = new JFrame();
		w.setLocation(0, 0);
		Helper.centreWindow(w);

		int x = (int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth() - w.getWidth()) / 2);
		int y = (int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight() - w.getHeight()) / 2);
		
		assertEquals(x,w.getLocation().getX(),0.0);
		assertEquals(y,w.getLocation().getY(),0.0);
	}
}
