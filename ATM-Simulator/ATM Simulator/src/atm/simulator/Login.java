package atm.simulator;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener{

	private JPanel mainPanel;
	private JButton lgn_btn, sgnup_btn, forgotpin;
	private static JTextField text_field;
	private JPasswordField password_field;
	
	public Login() {
		super("ATM Simulator");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(400, 175, 600, 400);
	
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBackground(new Color(173, 216, 230));
		this.setContentPane(mainPanel);
	
		JLabel symbolLabel = new JLabel();
		symbolLabel.setText("Welcome To ATM Simulator");
		symbolLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		symbolLabel.setBounds(170, 49, 270, 24);
		symbolLabel.setForeground(Color.MAGENTA);
		mainPanel.add(symbolLabel);
	
		JLabel label1 = new JLabel();
		label1.setText("Card Number");
		label1.setBounds(120, 89, 130, 24);
		label1.setFont(new Font("Tahoma", Font.BOLD, 15));
		mainPanel.add(label1);
	
		JLabel label2 = new JLabel();
		label2.setText("PIN");
		label2.setBounds(120, 128, 95, 24);
		label2.setFont(new Font("Tahoma", Font.BOLD, 15));
		mainPanel.add(label2);
	
		text_field = new JTextField();
		text_field.setBounds(236, 90, 180, 24);
		text_field.setFont(new Font("Cosmic Sans", Font.PLAIN, 15));
		text_field.setBorder(new LineBorder(new Color(173, 216, 230), 1, true));
		mainPanel.add(text_field);
	
		password_field = new JPasswordField();
		password_field.setBounds(236, 128, 180, 24);
		password_field.setBorder(new LineBorder(new Color(173, 216, 230), 1, true));
		mainPanel.add(password_field);
	
		lgn_btn = new JButton("Login");
		lgn_btn.setBackground(new Color(255, 253, 208));
		lgn_btn.setForeground(new Color(50,205,50));
		lgn_btn.setBorder(new LineBorder(new Color(173, 216, 230)));
		lgn_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lgn_btn.setBounds(160, 181, 113, 30);
		lgn_btn.setFocusable(false);
		lgn_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lgn_btn.addActionListener(this);
		mainPanel.add(lgn_btn);
	
		sgnup_btn = new JButton("Sign Up");
		sgnup_btn.setBackground(new Color(255, 253, 208));
		sgnup_btn.setForeground(new Color(255, 165, 0));
		sgnup_btn.setBorder(new LineBorder(new Color(173, 216, 230)));
		sgnup_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sgnup_btn.setBounds(280, 181, 113, 30);
		sgnup_btn.setFocusable(false);
		sgnup_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sgnup_btn.addActionListener(this);
		mainPanel.add(sgnup_btn);
		
		forgotpin = new JButton("Forgot PIN");
		forgotpin.setBackground(new Color(255, 253, 208));
		forgotpin.setForeground(new Color(255, 0, 0));
		forgotpin.setBorder(new LineBorder(new Color(173, 216, 230)));
		forgotpin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		forgotpin.setBounds(225, 221, 113, 30);
		forgotpin.setFocusable(false);
		forgotpin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		forgotpin.addActionListener(this);
		mainPanel.add(forgotpin);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == lgn_btn) {
			
			if(text_field.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Please Write CardNumber", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if(password_field.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Please Write PIN", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			try {
				Conn con = new Conn();
				String sql = "select * from account where cardnumber = ? and pin = ?";
				PreparedStatement st = con.conn.prepareStatement(sql);
			
				st.setString(1, text_field.getText());
				st.setString(2, password_field.getText());
			
				ResultSet res_set = st.executeQuery();
				if(res_set.next()) {
					this.setVisible(false);
					new Main().setVisible(true);
				}else {
				JOptionPane.showMessageDialog(null, "Invalid Login!!", "Please Login Again", JOptionPane.ERROR_MESSAGE);
				}
				st.close();
				con.conn.close();
			}
			catch(Exception exc) {
				exc.printStackTrace();
			}
	
	}
	
	if(e.getSource() == sgnup_btn) {
		this.setVisible(false);
		new ApplicationPersonalDetails().setVisible(true);
		
	}
	
	if(e.getSource() == forgotpin) {
		this.setVisible(false);
		new GetOTP().setVisible(true);
	}
}
	
	public static String getCard() {
		return text_field.getText();
	}
	
public static void main(String[] args) {
	new Login();
}

}
