package atm.simulator;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class Main extends JFrame implements ActionListener {

	private JPanel mainPanel;
	private JButton cashwithdrawal, deposit, pinchange, checkbalance, passbook, fastcash, exit;
	
	public Main() {
		
		super("Main Section");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(370, 63, 550, 550);
		
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBackground(new Color(255, 255, 255));
		this.setContentPane(mainPanel);
		
		JLabel label = new JLabel("Please Select Your Option");
		label.setBounds(95, 23, 350, 40);
		label.setFont(new Font("Tahoma", Font.BOLD, 25));
		mainPanel.add(label);
		
		
		cashwithdrawal = new JButton("Withdraw Cash");
		cashwithdrawal.setBackground(Color.black);
		cashwithdrawal.setForeground(Color.white);
		cashwithdrawal.setBounds(82, 130, 150, 30);
		cashwithdrawal.setFocusable(false);
		cashwithdrawal.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cashwithdrawal.setBorder(new LineBorder(new Color(0, 0, 0)));
		cashwithdrawal.addActionListener(this);
		mainPanel.add(cashwithdrawal);
		
		deposit = new JButton("Deposit");
		deposit.setBackground(Color.black);
		deposit.setForeground(Color.white);
		deposit.setBounds(82, 215, 150, 30);
		deposit.setFocusable(false);
		deposit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		deposit.setBorder(new LineBorder(new Color(0, 0, 0)));
		deposit.addActionListener(this);
		mainPanel.add(deposit);
		
		checkbalance = new JButton("Check Balance");
		checkbalance.setBackground(Color.black);
		checkbalance.setForeground(Color.white);
		checkbalance.setBounds(82, 295, 150, 30);
		checkbalance.setFocusable(false);
		checkbalance.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		checkbalance.setBorder(new LineBorder(new Color(0, 0, 0)));
		checkbalance.addActionListener(this);
		mainPanel.add(checkbalance);
		
		pinchange = new JButton("Change PIN");
		pinchange.setBackground(Color.black);
		pinchange.setForeground(Color.white);
		pinchange.setBounds(300, 130, 150, 30);
		pinchange.setFocusable(false);
		pinchange.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pinchange.setBorder(new LineBorder(new Color(0, 0, 0)));
		pinchange.addActionListener(this);
		mainPanel.add(pinchange);
		
		passbook = new JButton("Passbook");
		passbook.setBackground(Color.black);
		passbook.setForeground(Color.white);
		passbook.setBounds(300, 215, 150, 30);
		passbook.setFocusable(false);
		passbook.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		passbook.setBorder(new LineBorder(new Color(0, 0, 0)));
		passbook.addActionListener(this);
		mainPanel.add(passbook);
		
		fastcash = new JButton("Fast Cash");
		fastcash.setBackground(Color.black);
		fastcash.setForeground(Color.white);
		fastcash.setBounds(300, 294, 150, 30);
		fastcash.setFocusable(false);
		fastcash.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		fastcash.setBorder(new LineBorder(new Color(0, 0, 0)));
		fastcash.addActionListener(this);
		mainPanel.add(fastcash);
		
		exit = new JButton("Exit");
		exit.setBackground(Color.black);
		exit.setForeground(Color.white);
		exit.setBounds(201, 399, 150, 30);
		exit.setFocusable(false);
		exit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exit.setBorder(new LineBorder(new Color(0, 0, 0)));
		exit.addActionListener(this);
		mainPanel.add(exit);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == exit) {
			System.exit(ABORT);
		}
		
		if(e.getSource() == checkbalance) {
			try {
				String Card = ApplicationAccountDetails.getCardNumber() != null ? ApplicationAccountDetails.getCardNumber() : Login.getCard();
				int totalSum = 0 ;
				Conn con = new Conn();
				String sql1 = "select * from balanceenquiry where cardnumber = ? ";
				PreparedStatement st1 = con.conn.prepareStatement(sql1);
				
				st1.setString(1, Card);
				ResultSet rs = st1.executeQuery();
				
				if(rs.next()) {
						JOptionPane.showMessageDialog(null, "Available Balance : " + rs.getInt("balance"), "Success", JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "Error In Fetching Balance", "Error", JOptionPane.ERROR_MESSAGE);
				}
					
				}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		
		if(e.getSource() == deposit) {
			this.setVisible(false);
			new Deposit().setVisible(true);
		}
		
		if(e.getSource() == passbook) {
			this.setVisible(false);
			new Passbook().setVisible(true);
		}
		
		if(e.getSource() == pinchange) {
			this.setVisible(false);
			new ChangePIN().setVisible(true);
		}
		
		if(e.getSource() == cashwithdrawal) {
			this.setVisible(false);
			new Withdrawal().setVisible(true);
		}
		
		if(e.getSource() == fastcash) {
			this.setVisible(false);
			new FastCash().setVisible(true);
		}
	}
	
	public static void main(String[] args) {
		new Main().setVisible(true);
		}
}
