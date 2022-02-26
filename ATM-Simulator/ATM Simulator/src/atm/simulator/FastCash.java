package atm.simulator;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import com.toedter.calendar.JDateChooser;
import java.util.*;

public class FastCash extends JFrame implements ActionListener {

	private JPanel mainPanel;
	private JButton hundred, fivehundred, thousand, twothousand, fivethousand, tenthousand, exit;
	private JDateChooser chooseWithdrawalDate;
	
	
	public FastCash() {
		
		super("Fast Cash");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(370, 63, 550, 550);
		
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBackground(new Color(255, 255, 255));
		this.setContentPane(mainPanel);
		
		JLabel label = new JLabel("Please Select Withdrawal Amount");
		label.setBounds(95, 23, 350, 40);
		label.setFont(new Font("Tahoma", Font.BOLD, 20));
		mainPanel.add(label);
		
		
		hundred = new JButton("Rs. 100");
		hundred.setBackground(Color.black);
		hundred.setForeground(Color.white);
		hundred.setBounds(82, 130, 150, 30);
		hundred.setFocusable(false);
		hundred.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		hundred.setBorder(new LineBorder(new Color(0, 0, 0)));
		hundred.addActionListener(this);
		mainPanel.add(hundred);
		
		fivehundred = new JButton("Rs. 500");
		fivehundred.setBackground(Color.black);
		fivehundred.setForeground(Color.white);
		fivehundred.setBounds(82, 215, 150, 30);
		fivehundred.setFocusable(false);
		fivehundred.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		fivehundred.setBorder(new LineBorder(new Color(0, 0, 0)));
		fivehundred.addActionListener(this);
		mainPanel.add(fivehundred);
		
		thousand = new JButton("Rs. 1000");
		thousand.setBackground(Color.black);
		thousand.setForeground(Color.white);
		thousand.setBounds(82, 295, 150, 30);
		thousand.setFocusable(false);
		thousand.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		thousand.setBorder(new LineBorder(new Color(0, 0, 0)));
		thousand.addActionListener(this);
		mainPanel.add(thousand);
		
		twothousand = new JButton("Rs. 2000");
		twothousand.setBackground(Color.black);
		twothousand.setForeground(Color.white);
		twothousand.setBounds(300, 130, 150, 30);
		twothousand.setFocusable(false);
		twothousand.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		twothousand.setBorder(new LineBorder(new Color(0, 0, 0)));
		twothousand.addActionListener(this);
		mainPanel.add(twothousand);
		
		fivethousand = new JButton("Rs. 5000");
		fivethousand.setBackground(Color.black);
		fivethousand.setForeground(Color.white);
		fivethousand.setBounds(300, 215, 150, 30);
		fivethousand.setFocusable(false);
		fivethousand.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		fivethousand.setBorder(new LineBorder(new Color(0, 0, 0)));
		fivethousand.addActionListener(this);
		mainPanel.add(fivethousand);
		
		tenthousand = new JButton("Rs. 10000");
		tenthousand.setBackground(Color.black);
		tenthousand.setForeground(Color.white);
		tenthousand.setBounds(300, 294, 150, 30);
		tenthousand.setFocusable(false);
		tenthousand.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tenthousand.setBorder(new LineBorder(new Color(0, 0, 0)));
		tenthousand.addActionListener(this);
		mainPanel.add(tenthousand);
		
		chooseWithdrawalDate = new JDateChooser();
		chooseWithdrawalDate.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		chooseWithdrawalDate.setForeground(new Color(105, 105, 105));
		chooseWithdrawalDate.setBounds(400, 358, 100, 20);
		mainPanel.add(chooseWithdrawalDate);
		
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
		
		if(e.getSource() == hundred) {
			withdraw(100);
		}
		
		if(e.getSource() == fivehundred) {
			withdraw(500);
		}
		
		if(e.getSource() == thousand) {
			withdraw(1000);
		}
		
		if(e.getSource() == twothousand) {
			withdraw(2000);
		}
		
		if(e.getSource() == fivethousand) {
			withdraw(5000);
		}
		
		if(e.getSource() == tenthousand) {
			withdraw(10000);
		}
	}
	
	public void withdraw(int cash) {
		try {
			String Card = ApplicationAccountDetails.getCardNumber() != null ? ApplicationAccountDetails.getCardNumber() : Login.getCard();
			int totalSum = 0 ;
			Conn con = new Conn();
			String sql = "insert into balanceenquiry values(?, ?) ";
			String sql1 = "select * from balanceenquiry where cardnumber = ? ";
			String sql3 = "insert into passbook values( ?, ?, ?)";
			
			PreparedStatement st = con.conn.prepareStatement(sql);
			PreparedStatement st1 = con.conn.prepareStatement(sql1);
			PreparedStatement st3 = con.conn.prepareStatement(sql3);

			st3.setString(1, ((JTextField) chooseWithdrawalDate.getDateEditor().getUiComponent()).getText());
			st3.setString(2, "Debited");
			st3.setInt(3, cash);
			
			st1.setString(1, Card);
			ResultSet rs = st1.executeQuery();
			
			int j = st3.executeUpdate();
			if(j > 0) {
				JOptionPane.showMessageDialog(null, " Data Inserted Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
			}
			
			if(rs.next()) {
				if(rs.getInt("balance") >= cash) {
					totalSum = rs.getInt("balance");
					totalSum -= cash;
					String sql2 = "update balanceenquiry set balance = ? where cardnumber = ? ";
					PreparedStatement st2 = con.conn.prepareStatement(sql2);
					st2.setInt(1, totalSum);
					st2.setString(2, Card);
					
					int i = st2.executeUpdate();
					if(i > 0) {
						JOptionPane.showMessageDialog(null, "Rs. " + cash +  " Debited Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
						this.setVisible(false);
						new Main().setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Failed To Debit Amount", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null, "Not Enough Amount In Account To Debit", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new FastCash().setVisible(true);
		}
}
