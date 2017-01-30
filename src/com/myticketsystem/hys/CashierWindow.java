package com.myticketsystem.hys;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import com.alee.extended.breadcrumb.WebBreadcrumb;
import com.alee.extended.breadcrumb.WebBreadcrumbToggleButton;
import com.alee.extended.label.WebHotkeyLabel;
import com.alee.laf.button.WebButton;
import com.alee.laf.combobox.WebComboBox;
import com.alee.laf.label.WebLabel;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.tabbedpane.TabbedPaneStyle;
import com.alee.laf.tabbedpane.WebTabbedPane;
import com.alee.laf.text.WebTextArea;
import com.alee.laf.text.WebTextField;
import com.alee.laf.tree.WebTree;
import com.alee.utils.SwingUtils;

public class CashierWindow extends JFrame {

	private final WebButton btnNext;
	private final WebButton btnPrevious;

	private final WebButton btnPrint;
	private final JPanel contentPane;
	private String CURRENT_SELECTED_SEAT = "_";
	private Concert currentConcert;
	private Double finalTicketPrice;
	private final WebLabel lblFinalTicketPrice;
	private final WebHotkeyLabel lblPrintTicket;
	private final WebHotkeyLabel lblSeatInfo;
	private final SeatTable mainSeatTable;
	private final WebTabbedPane mainTabbedPane;
	private final String SEAT_INFO = "<html>Selected Seat: <b>%s</b><br>Ticket Cost: <b>%s</b></html>";
	private MouseEvent seatTableMouseEvent;
	private final String TICKET_INFO = "<html><b>Ticket System Invoice</b>" + "<br><br><br><br><br>"
			+ "Customer Name: <b>%s</b><br>" + "Concert Name: <b>%s</b><br>" + "Ticket Type: <b>%s</b><br>"
			+ "Concert Date: <b>%s</b><br>" + "Concert Time: <b>%s</b><br>" + "Seat: <b>%s</b><br>" + "<br>"
			+ "Ticket Cost: <b>$ %.02f</b></html>";
	private final WebComboBox ticketTypeCombo;

	private final WebTextField txtConcertDate;
	private final WebTextArea txtConcertDescription;
	private final WebTextField txtConcertName;

	private final WebTextField txtConcertPrice;

	private final WebTextField txtConcertTime;
	private final WebTextField txtConcertTitle;
	private final WebTextField txtConcertVenue;

	private final WebTextField txtCustomerName;

	private final WebTree<DefaultMutableTreeNode> upcomingConcertList;
	private final WebBreadcrumbToggleButton toggleButton1;
	private final WebBreadcrumbToggleButton toggleButton2;
	private final WebBreadcrumbToggleButton toggleButton3;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) {
	 * 
	 * CoreManagers.initialize ();
	 * 
	 * 
	 * try {
	 * UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
	 * catch (ClassNotFoundException | InstantiationException |
	 * IllegalAccessException | UnsupportedLookAndFeelException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); }
	 * 
	 * EventQueue.invokeLater(new Runnable() { public void run() { try {
	 * WebLookAndFeel.install ();
	 * 
	 * CashierWindow frame = new CashierWindow(); frame.setVisible(true); }
	 * catch (Exception e) { e.printStackTrace(); } } }); }
	 */

	/**
	 * Create the frame.
	 */
	public CashierWindow(User usr) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				GlobalVariables.loginWindow.setVisible(true);
			}
		});
		setTitle("Cashier Area - Logged in as " + usr.getUsername());
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 849, 730);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final WebBreadcrumb mainBreadcrumb = new WebBreadcrumb(true);
		mainBreadcrumb.setEncloseLastElement(true);
		mainBreadcrumb.setBounds(10, 11, 823, 42);

		toggleButton1 = new WebBreadcrumbToggleButton("Concerts", Helper.getIcon("images/category.png"));
		toggleButton1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				changeTab(0);
			}
		});

		toggleButton2 = new WebBreadcrumbToggleButton("Select Seat",
				Helper.getIcon("images/table_select.png"));
		toggleButton2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeTab(1);
			}
		});

		toggleButton3 = new WebBreadcrumbToggleButton("Print Ticket",
				Helper.getIcon("images/printer.png"));
		toggleButton3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeTab(2);
			}
		});
		toggleButton1.setSelected(true);

		mainBreadcrumb.add(toggleButton1);
		mainBreadcrumb.add(toggleButton2);
		mainBreadcrumb.add(toggleButton3);

		SwingUtils.groupButtons(mainBreadcrumb);

		contentPane.add(mainBreadcrumb);

		mainTabbedPane = new WebTabbedPane();
		mainTabbedPane.setTabbedPaneStyle(TabbedPaneStyle.attached);
		mainTabbedPane.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				JTabbedPane sourceTabbedPane = (JTabbedPane) arg0.getSource();
				int index = sourceTabbedPane.getSelectedIndex();
				switch (index) {
				case 0:
					toggleButton1.setSelected(true);
					break;
				case 1:
					toggleButton2.setSelected(true);
					break;
				case 2:
					toggleButton3.setSelected(true);
					break;
				}
			}
		});
		mainTabbedPane.setTabPlacement(SwingConstants.RIGHT);
		mainTabbedPane.setBounds(10, 63, 882, 547);
		setBounds(101, 100, 849, 710);

		contentPane.add(mainTabbedPane);

		final JPanel panel1 = new JPanel();
		mainTabbedPane.addTab("Step 1", null, panel1, null);

		final JPanel panel2 = new JPanel();
		mainTabbedPane.addTab("Step 2", null, panel2, null);
		panel2.setLayout(null);

		final WebLabel wblblSelectSeat = new WebLabel("Select Seat:", (Icon) null);
		wblblSelectSeat.setFont(new Font("Tahoma", Font.PLAIN, 11));
		wblblSelectSeat.setBounds(26, 11, 89, 34);
		panel2.add(wblblSelectSeat);

		mainSeatTable = new SeatTable();
		mainSeatTable.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				seatTableMouseEvent = arg0;
				updateStuff();
			}
		});
		mainSeatTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				seatTableMouseEvent = arg0;
				updateStuff();
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				seatTableMouseEvent = arg0;
				updateStuff();
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				seatTableMouseEvent = arg0;
				updateStuff();
			}
		});
		mainSeatTable.setPlainFont(false);
		mainSeatTable.setCellSelectionEnabled(true);
		mainSeatTable.setEditable(false);

		final WebScrollPane scrollPane = new WebScrollPane(mainSeatTable);
		mainSeatTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		scrollPane.setBounds(26, 44, 776, 407);
		panel2.add(scrollPane);

		lblSeatInfo = new WebHotkeyLabel(String.format(SEAT_INFO, "_", String.format("$ %.0f", 0.0)));
		lblSeatInfo.setBounds(664, 464, 138, 51);
		panel2.add(lblSeatInfo);

		final JPanel panel3 = new JPanel();
		mainTabbedPane.addTab("Step 3", null, panel3, null);
		panel3.setLayout(null);

		lblPrintTicket = new WebHotkeyLabel(TICKET_INFO);
		lblPrintTicket.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrintTicket.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblPrintTicket.setBounds(71, 45, 636, 463);
		panel3.add(lblPrintTicket);

		panel1.setLayout(null);

		upcomingConcertList = new WebTree<DefaultMutableTreeNode>(Helper.getAllConcertsByName().toArray(new String[0]));
		upcomingConcertList.setMultiplySelectionAllowed(false);
		upcomingConcertList.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent arg0) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) upcomingConcertList
						.getLastSelectedPathComponent();
				currentConcert = GlobalVariables.getAllConcertsMap().get(node.toString());

				txtConcertTitle.setText(currentConcert.getTitle());
				txtConcertDescription.setText(currentConcert.getDescription());
				txtConcertVenue.setText(currentConcert.getVenue());
				txtConcertDate.setText(Helper.getFormattedDate("dd.MM.yyyy", currentConcert.getcDate()));
				txtConcertTime.setText(Helper.getFormattedDate("HH:mm", currentConcert.getcDate()));
				txtConcertPrice.setText(String.format("$ %.0f", currentConcert.getPrice()));

				txtConcertName.setText(currentConcert.getTitle());

				updateStuff();
			}
		});

		upcomingConcertList.setShowsRootHandles(false);
		// concertList.setVisibleRowCount ( 4 );
		upcomingConcertList.setEditable(false);
		upcomingConcertList.setRowHeight(48);
		upcomingConcertList.setCellRenderer(new CustomTreeCellRenderer());
		upcomingConcertList.setBounds(122, 11, 178, 385);

		final WebScrollPane scollPane1 = new WebScrollPane(upcomingConcertList);
		scollPane1.setPaintButtons(false);
		scollPane1.setBounds(122, 11, 180, 314);
		panel1.add(scollPane1);

		final WebLabel wblblSelectAConcert = new WebLabel("Select a concert:", (Icon) null);
		wblblSelectAConcert.setFont(new Font("Tahoma", Font.PLAIN, 11));
		wblblSelectAConcert.setBounds(10, 11, 102, 34);
		panel1.add(wblblSelectAConcert);

		final WebLabel wblblTitle = new WebLabel("Title:", Helper.getIcon("images/artwork.png"));
		wblblTitle.setFont(new Font("Tahoma", Font.PLAIN, 11));
		wblblTitle.setBounds(347, 11, 89, 34);
		panel1.add(wblblTitle);

		final WebLabel wblblDescription = new WebLabel("Description:", Helper.getIcon("images/text_area.png"));
		wblblDescription.setFont(new Font("Tahoma", Font.PLAIN, 11));
		wblblDescription.setBounds(347, 56, 89, 34);
		panel1.add(wblblDescription);

		txtConcertTitle = new WebTextField();
		txtConcertTitle.setEditable(false);
		txtConcertTitle.setInputPrompt("");
		txtConcertTitle.setColumns(10);
		txtConcertTitle.setBounds(442, 11, 335, 34);
		panel1.add(txtConcertTitle);

		txtConcertDescription = new WebTextArea();
		txtConcertDescription.setEditable(false);
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

		txtConcertDate = new WebTextField();
		txtConcertDate.setEditable(false);
		txtConcertDate.setInputPrompt("");
		txtConcertDate.setColumns(10);
		txtConcertDate.setBounds(442, 156, 335, 34);
		panel1.add(txtConcertDate);

		txtConcertTime = new WebTextField();
		txtConcertTime.setEditable(false);
		txtConcertTime.setInputPrompt("");
		txtConcertTime.setBounds(442, 201, 335, 34);
		panel1.add(txtConcertTime);

		final WebLabel wblblVenue = new WebLabel("Venue:", Helper.getIcon("images/map.png"));
		wblblVenue.setFont(new Font("Tahoma", Font.PLAIN, 11));
		wblblVenue.setBounds(347, 246, 89, 34);
		panel1.add(wblblVenue);

		txtConcertVenue = new WebTextField();
		txtConcertVenue.setEditable(false);
		txtConcertVenue.setInputPrompt("");
		txtConcertVenue.setColumns(10);
		txtConcertVenue.setBounds(442, 246, 335, 34);
		panel1.add(txtConcertVenue);

		final WebLabel wblblPrice = new WebLabel("Ticket Price:", Helper.getIcon("images/money.png"));
		wblblPrice.setFont(new Font("Tahoma", Font.PLAIN, 11));
		wblblPrice.setBounds(347, 291, 89, 34);
		panel1.add(wblblPrice);

		txtConcertPrice = new WebTextField();
		txtConcertPrice.setInputPrompt("");
		txtConcertPrice.setEditable(false);
		txtConcertPrice.setColumns(10);
		txtConcertPrice.setBounds(442, 291, 335, 34);
		panel1.add(txtConcertPrice);

		final JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Customer Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 358, 802, 182);
		panel1.add(panel);
		panel.setLayout(null);

		final WebLabel wblblName = new WebLabel("Name:", (Icon) null);
		wblblName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		wblblName.setBounds(28, 92, 89, 34);
		panel.add(wblblName);

		final WebLabel wblblTicketType = new WebLabel("Ticket Type:", (Icon) null);
		wblblTicketType.setFont(new Font("Tahoma", Font.PLAIN, 11));
		wblblTicketType.setBounds(28, 137, 89, 34);
		panel.add(wblblTicketType);

		txtCustomerName = new WebTextField();
		txtCustomerName.setText("John Doe");
		txtCustomerName.setInputPrompt("");
		txtCustomerName.setColumns(10);
		txtCustomerName.setBounds(123, 92, 335, 34);
		panel.add(txtCustomerName);

		final WebLabel webLabel_2 = new WebLabel("Concert:", (Icon) null);
		webLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		webLabel_2.setBounds(28, 26, 89, 34);
		panel.add(webLabel_2);

		txtConcertName = new WebTextField();
		txtConcertName.setEnabled(false);
		txtConcertName.setInputPrompt("");
		txtConcertName.setColumns(10);
		txtConcertName.setBounds(123, 26, 335, 34);
		panel.add(txtConcertName);
		
		ticketTypeCombo = new WebComboBox(new String[]{"Normal", "Discounted (unemployed, children, students, elderly)"});
		ticketTypeCombo.setBounds(123, 137, 335, 34);

		ticketTypeCombo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateStuff();
			}
		});

		panel.add(ticketTypeCombo);

		lblFinalTicketPrice = new WebLabel();
		lblFinalTicketPrice.setText("Final Ticket Price: $ 0");
		lblFinalTicketPrice.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFinalTicketPrice.setBounds(561, 122, 182, 52);
		panel.add(lblFinalTicketPrice);

		btnNext = new WebButton("Next", Helper.getIcon("images/arrow_right.png"));
		btnNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int index = mainTabbedPane.getSelectedIndex();

				if (index < (mainTabbedPane.getTabCount() - 1)) {
					changeTab(index + 1);
				}
			}
		});
		btnNext.setRound(6);
		btnNext.setTopBgColor(new Color(255, 255, 255));
		btnNext.setRolloverShine(true);
		btnNext.setBounds(714, 621, 108, 36);
		contentPane.add(btnNext);

		btnPrevious = new WebButton("Previous", Helper.getIcon("images/arrow_left.png"));
		btnPrevious.setEnabled(false);
		btnPrevious.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = mainTabbedPane.getSelectedIndex();

				if (index >= 1) {
					changeTab(index - 1);
				}
			}
		});
		btnPrevious.setTopBgColor(Color.WHITE);
		btnPrevious.setRound(6);
		btnPrevious.setRolloverShine(true);
		btnPrevious.setBounds(20, 621, 108, 36);
		contentPane.add(btnPrevious);

		btnPrint = new WebButton("Print", Helper.getIcon("images/printer.png"));
		btnPrint.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Helper.html2pdf(getOwner(), lblPrintTicket.getText());

				// add transaction
				Transaction tr = new Transaction();
				tr.setCustomerName(txtCustomerName.getText());
				tr.setTransactionId("TID-" + Helper.getRandomString(10).toUpperCase());
				tr.setTicketType((ticketTypeCombo.getSelectedIndex() == 0) ? "Normal" : "Discounted");
				tr.setTicketPrice(finalTicketPrice);
				tr.setConcertName(currentConcert.getTitle());
				tr.setConcertSeat(CURRENT_SELECTED_SEAT);
				tr.setConcertVenue(currentConcert.getVenue());
				tr.setTransactionDate(Helper.getFormattedDate(GlobalVariables.DEFAULT_DATE_FORMAT, new Date()));
				tr.setConcertDate(Helper.getFormattedDate(GlobalVariables.DEFAULT_DATE_FORMAT, currentConcert.getcDate()));

				DBManager.addTransaction(tr);
			}
		});
		btnPrint.setTopBgColor(Color.WHITE);
		btnPrint.setRound(6);
		btnPrint.setRolloverShine(true);
		btnPrint.setBounds(371, 621, 108, 36);
		btnPrint.setVisible(false);
		contentPane.add(btnPrint);

	}

	public void changeTab(int wantedIndex) {

		updateStuff();

		final int currIndex = mainTabbedPane.getSelectedIndex();

		if (currentConcert == null) {
			Helper.showMessageBox(this, "Warning", "You have to select a concert before continuing!", 2); // warning
			toggleButton1.setSelected(true);
			return;
		}
		if ((currIndex == 1) && (wantedIndex == 2)) {
			// want to go from tab1 to tab2

			if (CURRENT_SELECTED_SEAT == "_") {
				// you cannot do that
				Helper.showMessageBox(this, "Warning", "You have to select a seat before continuing!", 2); // warning
				toggleButton2.setSelected(true);
				return;
			}
		}

		if (wantedIndex == 0) {
			btnPrint.setVisible(false);
			btnPrevious.setEnabled(false);
			btnNext.setEnabled(true);
			toggleButton1.setSelected(true);
		} else if (wantedIndex == 1) {
			btnPrint.setVisible(false);
			btnPrevious.setEnabled(true);
			btnNext.setEnabled(true);
			toggleButton2.setSelected(true);
		} else if (wantedIndex == 2) {
			btnPrint.setVisible(true);
			btnPrevious.setEnabled(true);
			btnNext.setEnabled(false);
			toggleButton3.setSelected(true);
		}

		mainTabbedPane.setSelectedIndex(wantedIndex);
	}

	public void updateStuff() {

		finalTicketPrice = 0.0;

		if (currentConcert != null) {
			finalTicketPrice = currentConcert.getPrice();
		}

		final String ticketType = (ticketTypeCombo.getSelectedIndex() == 0) ? "Normal" : "Discounted";

		if (ticketType=="Discounted"){
			finalTicketPrice = Helper.getDiscountedPrice(finalTicketPrice);
		}



		if (seatTableMouseEvent != null && currentConcert != null) { 
			final int row = mainSeatTable.rowAtPoint(seatTableMouseEvent.getPoint());
			final int col = mainSeatTable.columnAtPoint(seatTableMouseEvent.getPoint());

			if (mainSeatTable.isSeatAvailable(row, col)) {
				CURRENT_SELECTED_SEAT = String.format("%s%d", (String) mainSeatTable.getModel().getValueAt(row, 0), col);
			} else {
				CURRENT_SELECTED_SEAT = "_";
			}

			lblSeatInfo.setText(String.format(SEAT_INFO, CURRENT_SELECTED_SEAT, "$ " + finalTicketPrice));
		}

		lblFinalTicketPrice.setText("Final Ticket Price: $ " + String.format("%.02f", finalTicketPrice));

		lblPrintTicket.setText(String.format(TICKET_INFO, txtCustomerName.getText(), txtConcertName.getText(), ticketType,
						txtConcertDate.getText(), txtConcertTime.getText(), CURRENT_SELECTED_SEAT, finalTicketPrice));

	}
}