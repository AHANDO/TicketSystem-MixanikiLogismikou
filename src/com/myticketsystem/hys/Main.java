package com.myticketsystem.hys;

import com.alee.laf.WebLookAndFeel;
import com.alee.managers.CoreManagers;

public class Main {

	public static void main(String[] args) {
		CoreManagers.initialize();

		WebLookAndFeel.install();

		LoginWindow frame = new LoginWindow();
		GlobalVariables.loginWindow = frame; // keep instance for later
		Helper.centreWindow(frame);
		frame.setVisible(true);
	}

}
