package com.myticketsystem.hys;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.alee.extended.statusbar.WebStatusBar;
import com.alee.extended.statusbar.WebStatusLabel;
import com.alee.laf.button.WebButton;
import com.alee.laf.combobox.WebComboBox;
import com.alee.laf.label.WebLabel;
import com.alee.laf.text.WebPasswordField;
import com.alee.laf.text.WebTextField;

public class LoginWindow extends JFrame {

	private final WebButton btnLogin;
	private final WebComboBox comboType;
	private final JPanel contentPane;
	private final WebPasswordField txtPassword;
	private final WebTextField txtUsername;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) {
	 * 
	 * try {
	 * UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
	 * catch (ClassNotFoundException | InstantiationException |
	 * IllegalAccessException | UnsupportedLookAndFeelException e) {
	 * e.printStackTrace(); }
	 * 
	 * 
	 * EventQueue.invokeLater(new Runnable() { public void run() { try {
	 * WebLookAndFeel.install ();
	 * 
	 * 
	 * LoginWindow frame = new LoginWindow(); frame.setVisible(true); } catch
	 * (Exception e) { e.printStackTrace(); } } }); }
	 */

	/**
	 * Create the frame.
	 */
	public LoginWindow() {
		setResizable(false);
		setTitle("Ticket System Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 501, 375);
		contentPane = new JPanel();
		contentPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		contentPane.setAlignmentY(Component.TOP_ALIGNMENT);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		WebLabel lblAdminLoginHere = new WebLabel("Ticket System Login");
		lblAdminLoginHere.setBounds(22, 11, 425, 38);
		lblAdminLoginHere.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblAdminLoginHere.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdminLoginHere.setFont(new Font("Tahoma", Font.PLAIN, 19));
		contentPane.add(lblAdminLoginHere);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(12, 60, 473, 246);
		contentPane.add(panel);
		panel.setLayout(null);

		WebLabel lblPassword = new WebLabel("Password:", Helper.getIcon("images/bullet_key.png"));
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblPassword.setBounds(33, 131, 89, 34);
		panel.add(lblPassword);

		WebLabel lblUsername = new WebLabel("Username:", Helper.getIcon("images/user.png"));
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblUsername.setBounds(33, 86, 89, 34);
		panel.add(lblUsername);

		txtUsername = new WebTextField();
		txtUsername.setInputPrompt("Username...");
		txtUsername.setInputPromptFont(txtUsername.getFont().deriveFont(Font.ITALIC));
		txtUsername.setBounds(128, 86, 335, 34);
		panel.add(txtUsername);
		txtUsername.setColumns(10);

		txtPassword = new WebPasswordField();
		txtPassword.setInputPrompt("Password...");
		txtPassword.setInputPromptFont(txtPassword.getFont().deriveFont(Font.ITALIC));
		txtPassword.setBounds(128, 131, 335, 34);
		panel.add(txtPassword);

		btnLogin = new WebButton("Login", Helper.getIcon("images/tick.png"));

		btnLogin.setRound(2);

		btnLogin.setBounds(324, 189, 139, 34);
		panel.add(btnLogin);

		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				User usr = DBManager.getUser(txtUsername.getText(), txtPassword.getText());

				int comboIndex = comboType.getSelectedIndex();
				if (usr != null) {
					if (comboIndex == 0) {
						CashierWindow wnd = new CashierWindow(usr);
						setVisible(false);
						Helper.centreWindow(wnd);
						wnd.setVisible(true);
					} else {
						AdminAreaWindow wnd = new AdminAreaWindow(usr);
						setVisible(false);
						Helper.centreWindow(wnd);
						wnd.setVisible(true);
					}
					txtUsername.clear();
					txtPassword.clear();
					
				} else {
					Helper.showMessageBox(getOwner(), "Error!", "User not found!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		WebLabel lblType = new WebLabel("Type:", Helper.getIcon("images/cog.png"));
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblType.setBounds(33, 22, 89, 34);
		panel.add(lblType);

		comboType = new WebComboBox(new String[] { "Cashier", "Administrator" });
		comboType.setBounds(128, 22, 335, 34);
		panel.add(comboType);

		WebStatusBar statusBar = new WebStatusBar();
		statusBar.setBounds(0, 317, 495, 30);
		contentPane.add(statusBar);

		WebStatusLabel webStatusLabel = new WebStatusLabel("Debug Functions ", Helper.getIcon("images/bug.png"));
		webStatusLabel.setBounds(0, 329, 495, 18);
		statusBar.add(webStatusLabel);

		WebButton statusButton1 = new WebButton("Input Admin Data");
		statusButton1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtUsername.setText("administrator");
				txtPassword.setText("12345678");
				comboType.setSelectedIndex(1);
			}
		});
		statusBar.add(statusButton1);

		WebButton statusButton2 = new WebButton("Input Cashier Data");
		statusButton2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtUsername.setText("cashier1");
				txtPassword.setText("12345678");
				comboType.setSelectedIndex(0);
			}
		});
		statusBar.add(statusButton2);

	}
}
