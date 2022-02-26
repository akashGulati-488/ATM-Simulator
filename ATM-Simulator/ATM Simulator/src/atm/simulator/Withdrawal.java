package atm.simulator;

import javax.swing.*;
import javax.swing.border.LineBorder;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class Withdrawal extends JFrame implements ActionListener{

	private JPanel mainPanel;
	private JButton withdraw, back, exit;
	private JTextField field;
	private JDateChooser chooseWithdrawalDate;
	
	public Withdrawal() {
		
		super("Withdraw Cash");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(297, 80, 600, 450);
		
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBackground(new Color(255, 255, 255));
		this.setContentPane(mainPanel);
		
		JLabel label = new JLabel("MAXIMUM CASH WITHDRAWAL IS RS. 10,000 / DAY");
		label.setBounds(90, 10, 450, 40);
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		mainPanel.add(label);
		
		JLabel label1 = new JLabel("ENTER AMOUNT TO WITHDRAW");
		label1.setBounds(160, 86, 350, 40);
		label1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		mainPanel.add(label1);
		
		JLabel rs = new JLabel("Rs.");
		rs.setBounds(136, 126, 350, 40);
		rs.setFont(new Font("Tahoma", Font.BOLD, 20));
		mainPanel.add(rs);
		
		field = new JTextField();
		field.setBounds(190, 136, 200, 25);
		field.setFont(new Font("Cosmic Sans", Font.PLAIN, 15));
		mainPanel.add(field);
		
		back = new JButton("Back");
		back.setBackground(Color.black);
		back.setForeground(Color.white);
		back.setBounds(170, 201, 100, 30);
		back.setFocusable(false);
		back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		back.setBorder(new LineBorder(new Color(0, 0, 0)));
		back.addActionListener(this);
		mainPanel.add(back);
		
		withdraw = new JButton("Withdraw");
		withdraw.setBackground(Color.black);
		withdraw.setForeground(Color.white);
		withdraw.setBounds(300, 201, 100, 30);
		withdraw.setFocusable(false);
		withdraw.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		withdraw.setBorder(new LineBorder(new Color(0, 0, 0)));
		withdraw.addActionListener(this);
		mainPanel.add(withdraw);
		
		chooseWithdrawalDate = new JDateChooser();
		chooseWithdrawalDate.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		chooseWithdrawalDate.setForeground(new Color(105, 105, 105));
		chooseWithdrawalDate.setBounds(400, 138, 100, 20);
		mainPanel.add(chooseWithdrawalDate);
		
		exit = new JButton("Exit");
		exit.setBackground(Color.black);
		exit.setForeground(Color.white);
		exit.setBounds(240, 300, 100, 30);
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
		
		if(e.getSource() == back) {
			this.setVisible(false);
			new Main().setVisible(true);
		}
		
		if(e.getSource() == withdraw) {	
			try {
				String Card = ApplicationAccountDetails.getCardNumber() != null ? ApplicationAccountDetails.getCardNumber() : Login.getCard();
				int totalSum = 0 ;
				Conn con = new Conn();
				String sql = "insert into balanceenquiry values(?, ?) ";
				String sql1 = "select * from balanceenquiry where cardnumber = ? ";
				String sql3 = "insert into passbook values( ?, ?, ?)";
				String sql4 = "select sum(amount) as sum from passbook where date = ? and way = ?";
				
				PreparedStatement st = con.conn.prepareStatement(sql);
				PreparedStatement st1 = con.conn.prepareStatement(sql1);
				PreparedStatement st3 = con.conn.prepareStatement(sql3);
				PreparedStatement st4 = con.conn.prepareStatement(sql4);
				
				st4.setString(1, ((JTextField) chooseWithdrawalDate.getDateEditor().getUiComponent()).getText() );
				st4.setString(2,  "Debited");
				ResultSet res = st4.executeQuery();
				if(res.next()) {
					if((res.getInt("sum") + Integer.parseInt(field.getText() )) <= 10000 ) {
						st1.setString(1, Card);
						ResultSet rs = st1.executeQuery();
						st3.setString(1, ((JTextField) chooseWithdrawalDate.getDateEditor().getUiComponent()).getText());
						st3.setString(2,  "Debited");
						st3.setInt(3, Integer.parseInt(field.getText() ));
						
						int j = st3.executeUpdate();
						if(j > 0) {
							JOptionPane.showMessageDialog(null, " Data Inserted Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
						}
						
						if(rs.next()) {
							if(rs.getInt("balance") >= Integer.parseInt(field.getText())) {
								totalSum = rs.getInt("balance");
								totalSum -= Integer.parseInt(field.getText());
								String sql2 = "update balanceenquiry set balance = ? where cardnumber = ? ";
								PreparedStatement st2 = con.conn.prepareStatement(sql2);
								st2.setInt(1, totalSum);
								st2.setString(2, Card);
								
								int i = st2.executeUpdate();
								if(i > 0) {
									JOptionPane.showMessageDialog(null, "Rs. " + field.getText() +  " Debited Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
									this.setVisible(false);
									new Main().setVisible(true);
								}else {
									JOptionPane.showMessageDialog(null, "Failed To Debit Amount", "Error", JOptionPane.ERROR_MESSAGE);
								}
							}else {
								JOptionPane.showMessageDialog(null, "Not Enough Amount In Account To Debit", "Error", JOptionPane.ERROR_MESSAGE);
							}
							
						}
					}else {
						JOptionPane.showMessageDialog(null, "Amount Limit Exceeded For Today", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}else {
					st1.setString(1, Card);
					ResultSet rs = st1.executeQuery();
					st3.setString(1, ((JTextField) chooseWithdrawalDate.getDateEditor().getUiComponent()).getText());
					st3.setString(2,  "Debited");
					st3.setInt(3, Integer.parseInt(field.getText() ));
					
					int j = st3.executeUpdate();
					if(j > 0) {
						JOptionPane.showMessageDialog(null, " Data Inserted Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
					}
					
					if(rs.next()) {
						if(rs.getInt("balance") >= Integer.parseInt(field.getText())) {
							totalSum = rs.getInt("balance");
							totalSum -= Integer.parseInt(field.getText());
							String sql2 = "update balanceenquiry set balance = ? where cardnumber = ? ";
							PreparedStatement st2 = con.conn.prepareStatement(sql2);
							st2.setInt(1, totalSum);
							st2.setString(2, Card);
							
							int i = st2.executeUpdate();
							if(i > 0) {
								JOptionPane.showMessageDialog(null, "Rs. " + field.getText() +  " Debited Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
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
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		new Withdrawal().setVisible(true);
	}
}
