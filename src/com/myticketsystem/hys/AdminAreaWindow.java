package com.myticketsystem.hys;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import com.alee.extended.date.WebDateField;
import com.alee.extended.label.WebHotkeyLabel;
import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.spinner.WebSpinner;
import com.alee.laf.tabbedpane.TabbedPaneStyle;
import com.alee.laf.tabbedpane.WebTabbedPane;
import com.alee.laf.text.WebTextArea;
import com.alee.laf.text.WebTextField;
import com.alee.laf.tree.WebTree;

public class AdminAreaWindow extends JFrame {

	/**
	 * 
	 */
	private final JPanel contentPane;
	private Concert currentConcert;
	private final WebHotkeyLabel lblAdminStats;
	private final JPanel panelConcertButtons;
	private final JPanel panelEditButtons;
	private final WebDateField txtConcertDate;
	private final WebTextArea txtConcertDescription;
	private final WebTextField txtConcertTicketPrice;
	private final WebSpinner txtConcertTime;
	private final WebTextField txtConcertTitle;
	private final WebTextField txtConcertVenue;
	private final WebTree<DefaultMutableTreeNode> upcomingConcertList;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { WebLookAndFeel.install ();
	 * 
	 * AdminAreaWindow frame = new AdminAreaWindow(); frame.setVisible(true); }
	 * catch (Exception e) { e.printStackTrace(); } } }); }
	 */

	/**
	 * Create the frame.
	 */
	public AdminAreaWindow(User usr) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				GlobalVariables.loginWindow.setVisible(true);
			}
		});

		setTitle("Admin Area - Logged in as " + usr.getUsername());
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 826, 574);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final WebTabbedPane tabbedPane1 = new WebTabbedPane();
		tabbedPane1.setTabbedPaneStyle(TabbedPaneStyle.attached);
		tabbedPane1.setBounds(0, 0, 843, 682);
		setBounds(101, 100, 849, 710);
		tabbedPane1.setTabPlacement(SwingConstants.TOP);

		final JPanel panel1 = new JPanel();
		tabbedPane1.addTab("Concerts", Helper.getIcon("images/artwork.png"), panel1, null);
		panel1.setLayout(null);

		upcomingConcertList = new WebTree<DefaultMutableTreeNode>(Helper.getAllConcertsByName().toArray(new String[0]));
		upcomingConcertList.setMultiplySelectionAllowed(false);
		upcomingConcertList.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent arg0) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) upcomingConcertList.getLastSelectedPathComponent();
				if (node == null) {
					return;
				}
				currentConcert = GlobalVariables.getAllConcertsMap().get(node.toString());

				txtConcertTitle.setText(currentConcert.getTitle());
				txtConcertDescription.setText(currentConcert.getDescription());
				txtConcertVenue.setText(currentConcert.getVenue());
				txtConcertDate.setDate(currentConcert.getcDate());

				txtConcertTime.setValue(currentConcert.getcDate());

				txtConcertTicketPrice.setText(String.format("%.0f", currentConcert.getPrice()));
			}
		});

		upcomingConcertList.setShowsRootHandles(false);
		upcomingConcertList.setEditable(false);
		upcomingConcertList.setRowHeight(48);
		upcomingConcertList.setCellRenderer(new CustomTreeCellRenderer());
		upcomingConcertList.setBounds(122, 11, 178, 385);

		final WebScrollPane scollPane1 = new WebScrollPane(upcomingConcertList);
		scollPane1.setPaintButtons(false);
		scollPane1.setBounds(122, 11, 180, 314);
		panel1.add(scollPane1);

		final WebLabel lblSelectConcert = new WebLabel("Select a concert:", (Icon) null);
		lblSelectConcert.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblSelectConcert.setBounds(10, 11, 102, 34);
		panel1.add(lblSelectConcert);

		final WebLabel wblblTitle = new WebLabel("Title:", Helper.getIcon("images/artwork.png"));
		wblblTitle.setFont(new Font("Tahoma", Font.PLAIN, 11));
		wblblTitle.setBounds(347, 11, 89, 34);
		panel1.add(wblblTitle);

		final WebLabel wblblDescription = new WebLabel("Description:", Helper.getIcon("images/text_area.png"));
		wblblDescription.setFont(new Font("Tahoma", Font.PLAIN, 11));
		wblblDescription.setBounds(347, 56, 89, 34);
		panel1.add(wblblDescription);

		txtConcertTitle = new WebTextField();
		txtConcertTitle.setInputPrompt("");
		txtConcertTitle.setColumns(10);
		txtConcertTitle.setBounds(442, 11, 335, 34);
		panel1.add(txtConcertTitle);

		txtConcertDescription = new WebTextArea();
		txtConcertDescription.setLineWrap(true);
		txtConcertDescription.setWrapStyleWord(true);
		txtConcertDescription.setBounds(442, 11, 335, 34);

		final WebScrollPane areaScroll = new WebScrollPane(txtConcertDescription);
		areaScroll.setPreferredSize(new Dimension(200, 150));
		areaScroll.setBounds(442, 56, 335, 89);
		panel1.add(areaScroll);

		final WebLabel wblblDate = new WebLabel("Date:", Helper.getIcon("images/date.png"));
		wblblDate.setFont(new Font("Tahoma", Font.PLAIN, 11));
		wblblDate.setBounds(347, 156, 89, 34);
		panel1.add(wblblDate);

		final WebLabel wblblTime = new WebLabel("Time:", Helper.getIcon("images/time.png"));
		wblblTime.setFont(new Font("Tahoma", Font.PLAIN, 11));
		wblblTime.setBounds(347, 201, 89, 34);
		panel1.add(wblblTime);

		txtConcertDate = new WebDateField();
		txtConcertDate.setColumns(10);
		txtConcertDate.setBounds(442, 156, 335, 34);
		panel1.add(txtConcertDate);

		final SpinnerDateModel model1 = new SpinnerDateModel();
		txtConcertTime = new WebSpinner(model1);

		txtConcertTime.setEditor(new JSpinner.DateEditor(txtConcertTime, "HH:mm"));
		txtConcertTime.setBounds(442, 201, 78, 34);
		panel1.add(txtConcertTime);

		final WebLabel wblblVenue = new WebLabel("Venue:", Helper.getIcon("images/map.png"));
		wblblVenue.setFont(new Font("Tahoma", Font.PLAIN, 11));
		wblblVenue.setBounds(347, 246, 89, 34);
		panel1.add(wblblVenue);

		txtConcertVenue = new WebTextField();
		txtConcertVenue.setInputPrompt("");
		txtConcertVenue.setColumns(10);
		txtConcertVenue.setBounds(442, 246, 335, 34);
		panel1.add(txtConcertVenue);

		final WebLabel wblblPrice = new WebLabel("Ticket Price:", Helper.getIcon("images/money.png"));
		wblblPrice.setFont(new Font("Tahoma", Font.PLAIN, 11));
		wblblPrice.setBounds(347, 291, 89, 34);
		panel1.add(wblblPrice);

		txtConcertTicketPrice = new WebTextField();
		txtConcertTicketPrice.setColumns(10);
		txtConcertTicketPrice.setBounds(442, 291, 335, 34);
		panel1.add(txtConcertTicketPrice);

		panelEditButtons = new JPanel();
		panelEditButtons.setBounds(597, 366, 180, 36);
		panel1.add(panelEditButtons);
		panelEditButtons.setLayout(null);

		final WebButton btnSave = new WebButton("Save", Helper.getIcon("images/diskette.png"));
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final String tempTitle = txtConcertTitle.getText();
				final String tempDescription = txtConcertDescription.getText();
				final String tempVenue = txtConcertVenue.getText();
				final Date tempDate = txtConcertDate.getDate();
				final Date tempTime = (Date) txtConcertTime.getValue();
				final Double tempPrice = Double.parseDouble(txtConcertTicketPrice.getText());
				tempDate.setHours(tempTime.getHours());
				tempDate.setMinutes(tempTime.getMinutes());

				currentConcert.setTitle(tempTitle);
				currentConcert.setDescription(tempDescription);
				currentConcert.setVenue(tempVenue);
				currentConcert.setcDate(tempDate);
				currentConcert.setPrice(tempPrice);

				DBManager.updateConcert(currentConcert);

				updateEditable(false);
			}
		});
		btnSave.setEnabled(false);
		btnSave.setBounds(92, 0, 88, 36);
		panelEditButtons.add(btnSave);
		btnSave.setTopBgColor(Color.WHITE);
		btnSave.setRound(6);
		btnSave.setRolloverShine(true);

		final WebButton btnCancel = new WebButton("Cancel", Helper.getIcon("images/cancel.png"));
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateEditable(false);
			}
		});
		btnCancel.setEnabled(false);
		btnCancel.setBounds(0, 0, 88, 36);
		panelEditButtons.add(btnCancel);
		btnCancel.setTopBgColor(Color.WHITE);
		btnCancel.setRound(6);
		btnCancel.setRolloverShine(true);

		panelConcertButtons = new JPanel();
		panelConcertButtons.setBounds(10, 366, 448, 36);
		panel1.add(panelConcertButtons);
		panelConcertButtons.setLayout(null);

		final WebButton btnAddNewConcert = new WebButton("Add Concert", Helper.getIcon("images/add.png"));
		btnAddNewConcert.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Concert temp = new Concert(GlobalVariables.DEFAULT_BLANK_CONCERT);
				GlobalVariables.getAllConcertsMap().put(temp.getTitle(), temp);
				currentConcert = temp;
				final DefaultMutableTreeNode node = Helper.addNodeToTree(upcomingConcertList, temp.getTitle());

				upcomingConcertList.setSelectedNode(node);

				editConcert();
			}
		});
		btnAddNewConcert.setBounds(0, 0, 135, 36);
		panelConcertButtons.add(btnAddNewConcert);
		btnAddNewConcert.setTopBgColor(Color.WHITE);
		btnAddNewConcert.setRound(6);
		btnAddNewConcert.setRolloverShine(true);

		final WebButton btnRemoveConcert = new WebButton("Remove Concert", Helper.getIcon("images/delete.png"));
		btnRemoveConcert.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DBManager.removeConcert(currentConcert);
				Helper.reloadConcertTree(upcomingConcertList);
			}
		});
		btnRemoveConcert.setBounds(313, 0, 135, 36);
		panelConcertButtons.add(btnRemoveConcert);
		btnRemoveConcert.setTopBgColor(Color.WHITE);
		btnRemoveConcert.setRound(6);
		btnRemoveConcert.setRolloverShine(true);

		final WebButton btnEditConcert = new WebButton("Edit Concert", Helper.getIcon("images/picture_edit.png"));
		btnEditConcert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				editConcert();
			}
		});
		btnEditConcert.setBounds(157, 0, 135, 36);
		panelConcertButtons.add(btnEditConcert);
		btnEditConcert.setTopBgColor(Color.WHITE);
		btnEditConcert.setRound(6);
		btnEditConcert.setRolloverShine(true);

		final JPanel panel2 = new JPanel();
		tabbedPane1.addTab("Users", Helper.getIcon("images/user.png"), panel2, null);
		panel2.setLayout(null);

		final WebTree<DefaultMutableTreeNode> userList = new WebTree<DefaultMutableTreeNode>(Helper.getAllUsers().toArray(new String[0]));
		WebScrollPane scrollPane = new WebScrollPane(userList);
		scrollPane.setBounds(168, 11, 166, 335);
		panel2.add(scrollPane);

		final JLabel lblAllUsers = new JLabel("All Users:");
		lblAllUsers.setBounds(10, 11, 148, 27);
		panel2.add(lblAllUsers);

		final WebHotkeyLabel lblUsersLabel = new WebHotkeyLabel(
				"<html>If you need to add/edit/remove users, please contact the administrator.</html>");
		lblUsersLabel.setBounds(357, 47, 402, 119);
		lblUsersLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsersLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel2.add(lblUsersLabel);

		userList.setMultiplySelectionAllowed(false);

		userList.setShowsRootHandles(false);
		userList.setEditable(false);
		userList.setRowHeight(48);
		userList.setCellRenderer(new CustomTreeCellRenderer());

		final JPanel panel3 = new JPanel();
		tabbedPane1.addTab("Statistics", Helper.getIcon("images/report.png"), panel3, null);
		panel3.setLayout(null);

		lblAdminStats = new WebHotkeyLabel("");
		lblAdminStats.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdminStats.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblAdminStats.setBounds(10, 11, 823, 639);
		panel3.add(lblAdminStats);
		
		
		contentPane.add(tabbedPane1);
		updateStats();
		updateEditable(false);
	}

	private void editConcert() {
		if (currentConcert == null){
			Helper.showMessageBox(getOwner(), "Info", "You have to select a concert to edit.", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		Helper.setPanelEnabled(panelConcertButtons, false);
		Helper.setPanelEnabled(panelEditButtons, true);
		updateEditable(true);
	}

	private void updateEditable(final boolean isEditable) {
		// upcomingConcertList.setSelectorEnabled(!isEditable);
		Helper.setPanelEnabled(panelConcertButtons, !isEditable);
		Helper.setPanelEnabled(panelEditButtons, isEditable);

		txtConcertTitle.setEditable(isEditable);
		txtConcertDescription.setEditable(isEditable);
		txtConcertTicketPrice.setEditable(isEditable);
		txtConcertTime.setEnabled(isEditable);
		((DefaultEditor) txtConcertTime.getEditor()).getTextField().setEditable(isEditable);
		txtConcertDate.setEditable(isEditable);
		txtConcertVenue.setEditable(isEditable);
	}

	private void updateStats() {
		String finalStats;

		finalStats = "<html><b>Ticket System Statistics</b>" + "<br><br><br><br><br>" + "Concerts: <b>%d</b><br>"
				+ "Upcoming Concerts: <b>%d</b><br>" + "Tickets Sold: <b>%d</b><br>" + "Unique Customers: <b>%d</b><br>"
				+ "Average Ticket Cost: <b>$ %.02f</b><br>" + "Total Earnings from Tickets: <b>$ %.02f</b><br></html>";

		final int concertCount = Helper.getAllRowsByColumn("select * from concerts", "title", "string").size();
		final int upcomingConcerts = Helper.getUpcomingConcerts().size();
		final int ticketSoldCount = Helper.getAllRowsByColumn("select * from transactions", "transaction_id", "string").size();
		final int uniqueCustomerCount = Helper.getAllRowsByColumn("select distinct customer_name from transactions", "customer_name", "string").size();
		final Double averageTicketCost = Helper.getListAverage(Helper.getAllRowsByColumn("select * from transactions", "ticket_price", "double"));
		final Double totalTicketEarnings = Helper.getListSum((Helper.getAllRowsByColumn("select * from transactions", "ticket_price", "double")));

		finalStats = String.format(finalStats, concertCount, upcomingConcerts, ticketSoldCount, uniqueCustomerCount, averageTicketCost, totalTicketEarnings);

		lblAdminStats.setText(finalStats);

	}
}
