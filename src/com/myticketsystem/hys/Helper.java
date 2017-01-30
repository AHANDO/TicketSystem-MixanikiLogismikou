package com.myticketsystem.hys;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.security.SecureRandom;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.alee.laf.filechooser.WebFileChooser;
import com.alee.laf.tree.WebTree;
import com.alee.managers.notification.NotificationManager;
import com.itextpdf.text.Document;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;

public class Helper {

	public static DefaultMutableTreeNode addNodeToTree(WebTree<DefaultMutableTreeNode> tree, String text) {
		final DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
		final DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
		final DefaultMutableTreeNode temp = new DefaultMutableTreeNode(text);
		root.add(temp);
		model.reload(root);

		return temp;
	}

	public static void centreWindow(Window frame) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		final int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		final int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
	}

	public static Color darkenColor(Color color, float amount) {
		int red = (int) Math.round(color.getRed() * (1.0 - amount));
		int green = (int) Math.round(color.getGreen() * (1.0 - amount));
		int blue = (int) Math.round(color.getBlue() * (1.0 - amount));

		if (red < 0) {
			red = 0;
		} else if (red > 255) {
			red = 255;
		}
		if (green < 0) {
			green = 0;
		} else if (green > 255) {
			green = 255;
		}
		if (blue < 0) {
			blue = 0;
		} else if (blue > 255) {
			blue = 255;
		}

		int alpha = color.getAlpha();

		return new Color(red, green, blue, alpha);
	}
	

	public static List<String> getAllConcertsByName() {
		final List<String> conTitles = new ArrayList<String>();
		for (String key : GlobalVariables.getAllConcertsMap().keySet()) {
			conTitles.add(GlobalVariables.getAllConcertsMap().get(key).getTitle());
		}

		return conTitles;
	}

	public static List<Object> getAllRowsByColumn(String sql, String colName, String colType) {
		// coltype = double, string, boolean, etc
		// gets all CELLS in that COLUMN of the resulting ResultSet

		final List<Object> stuff = new ArrayList<Object>();
		ResultSet rs = DBManager.executeQuery(sql, new Object[] {});

		try {
			while (rs.next()) {
				Object obj;
				if (colType == "string") {
					obj = rs.getString(colName);
				} else if (colType == "double") {
					obj = rs.getDouble(colName);
				} else if (colType == "integer") {
					obj = rs.getInt(colName);
				} else if (colType == "boolean") {
					obj = rs.getBoolean(colName);
				} else {
					obj = rs.getObject(colName);
				}
				stuff.add(obj);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return stuff;
	}

	public static List<String> getAllUsers() {
		final List<String> users = new ArrayList<String>();
		ResultSet rs = DBManager.executeQuery("SELECT * FROM users WHERE ENABLED=?", new Object[] { 1 });

		try {
			while (rs.next()) {
				users.add(rs.getString("username") + " - " + rs.getString("user_type"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
	}

	public static Double getDiscountedPrice(Double currPrice) {
		return currPrice * 0.50;
	}

	public static String getFormattedDate(String pattern, Date date) {
		return (new SimpleDateFormat(pattern)).format(date);
	}

	public static ImageIcon getIcon(String resURL) {
		// resURL = images/icon.png
		try {
			ImageIcon i = new ImageIcon(ImageIO.read(ClassLoader.getSystemResource(resURL)));
			return i;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Double getListAverage(List<Object> list) {
		Double total = 0.0;
		for (int i = 0; i < list.size(); i++) {
			total += (Double)list.get(i);
		}
		return total / list.size();
	}

	public static Double getListSum(List<Object> list) {
		Double total = 0.0;
		for (int i = 0; i < list.size(); i++) {
			total += (Double) list.get(i);
		}
		return total;
	}

	public static boolean getRandomBool() {
		return Math.random() > 0.5f;
	}

	public static String getRandomString(int len) {
		final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		final SecureRandom rnd = new SecureRandom();
		StringBuilder stringBuilder = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			stringBuilder.append(AB.charAt(rnd.nextInt(AB.length())));
		}
		return stringBuilder.toString();
	}

	public static String getSeatFillStatus() {
		// randomize taken/free seats, since this is a demo application and will never be used in real life! #yolo
		
		return (Math.random() > 0.7f) ? "taken" : "free";
	}

	public static List<String> getUpcomingConcerts() {
		final List<String> concerts = new ArrayList<String>();
		ResultSet rs = DBManager.executeQuery("SELECT * FROM concerts", new Object[] {});

		try {
			while (rs.next()) {
				SimpleDateFormat dt = new SimpleDateFormat(GlobalVariables.DEFAULT_DATE_FORMAT);

				try {
					Date tempFinalDate = dt.parse(rs.getString("date"));
					if (tempFinalDate.after(new Date())) {
						concerts.add(rs.getString("title"));
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return concerts;
	}

	public static void html2pdf(Component owner, String html) {
		try {
			WebFileChooser fileChooser = new WebFileChooser();

			fileChooser.setMultiSelectionEnabled(false);
			File tempFile = null;
			if (fileChooser.showSaveDialog(owner) == JFileChooser.APPROVE_OPTION) {
				tempFile = fileChooser.getSelectedFile();

			}

			if (tempFile == null) {
				// no file selected, cancel
				return;
			}

			OutputStream file = new FileOutputStream(tempFile);
			Document document = new Document();
			PdfWriter.getInstance(document, file);
			document.open();
			HTMLWorker htmlWorker = new HTMLWorker(document);
			htmlWorker.parse(new StringReader(html));
			document.close();
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void log(String message) {
		NotificationManager.showNotification(message);

		System.out.println(message);
	}

	public static void reloadConcertTree(WebTree<DefaultMutableTreeNode> tree) {
		GlobalVariables.getAllConcertsMap().clear(); // clear to be empty, for
														// it to get the new
														// complete concert list
														// from the DB
		List<String> list = Helper.getAllConcertsByName();

		DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
		String sel = "";
		if (selectedNode != null) {
			sel = selectedNode.toString();
		}
		Helper.log(sel);
		root.removeAllChildren();

		DefaultMutableTreeNode newSelectedNode = null;

		for (int i = 0; i < list.size(); i++) {
			DefaultMutableTreeNode n = addNodeToTree(tree, list.get(i));

			if (n.toString() == sel) {
				newSelectedNode = n;
			}
		}

		if (newSelectedNode != null) {
			tree.setSelectedNode(newSelectedNode);
		}

		// model.reload();
	}

	public static void setPanelEnabled(JPanel panel, Boolean isEnabled) {
		if (panel == null) {
			return;
		}

		panel.setEnabled(isEnabled);

		Component[] components = panel.getComponents();

		for (int i = 0; i < components.length; i++) {
			if (components[i].getClass().getName() == "javax.swing.JPanel") {
				setPanelEnabled((JPanel) components[i], isEnabled);
			}

			components[i].setEnabled(isEnabled);
		}
	}

	public static void showMessageBox(Component owner, String title, String message, int type) {
		JOptionPane.showMessageDialog(owner, message, title, type);
	}
}
