package com.myticketsystem.hys;

import java.util.Date;
import java.util.LinkedHashMap;

public class GlobalVariables {

	private static LinkedHashMap<String, Concert> allConcertsMap = new LinkedHashMap<String, Concert>();
	public static Concert DEFAULT_BLANK_CONCERT = new Concert("New Concert", "", 10.0, new Date(), "", 0);

	public static String DEFAULT_DATE_FORMAT = "dd.MM.yyyy HH:mm";

	public static LoginWindow loginWindow = null;

	public static LinkedHashMap<String, Concert> getAllConcertsMap() {
		if (allConcertsMap.isEmpty()) {
			allConcertsMap = DBManager.getAllConcerts();
		}
		return allConcertsMap;
	}

	public static void setAllConcertsMap(LinkedHashMap<String, Concert> allConcertsMap) {
		GlobalVariables.allConcertsMap = allConcertsMap;
	}

}
