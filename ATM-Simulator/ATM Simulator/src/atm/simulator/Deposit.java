package atm.simulator;

import javax.swing.*;
import javax.swing.border.LineBorder;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class Deposit extends JFrame implements ActionListener{

	private JPanel mainPanel;
	private JButton deposit, back, exit;
	private JTextField field;
	private JDateChooser chooseDepositDate;
	
	public Deposit() {
		
		super("Deposit Cash");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(297, 80, 600, 500);
		
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBackground(new Color(255, 255, 255));
		this.setContentPane(mainPanel);
		
		JLabel label = new JLabel("ENTER AMOUNT TO DEPOSIT");
		label.setBounds(140, 76, 350, 40);
		label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		mainPanel.add(label);
		
		JLabel rs = new JLabel("Rs.");
		rs.setBounds(136, 126, 350, 40);
		rs.setFont(new Font("Tahoma", Font.BOLD, 20));
		mainPanel.add(rs);
		
		field = new JTextField();
		field.setBounds(190, 136, 200, 25);
		field.setFont(new Font("Cosmic Sans", Font.PLAIN, 15));
		mainPanel.add(field);
		
		chooseDepositDate = new JDateChooser();
		chooseDepositDate.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		chooseDepositDate.setForeground(new Color(105, 105, 105));
		chooseDepositDate.setBounds(400, 138, 100, 20);
		mainPanel.add(chooseDepositDate);
		
		back = new JButton("Back");
		back.setBackground(Color.black);
		back.setForeground(Color.white);
		back.setBounds(170, 201, 100, 30);
		back.setFocusable(false);
		back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		back.setBorder(new LineBorder(new Color(0, 0, 0)));
		back.addActionListener(this);
		mainPanel.add(back);
		
		deposit = new JButton("Deposit");
		deposit.setBackground(Color.black);
		deposit.setForeground(Color.white);
		deposit.setBounds(300, 201, 100, 30);
		deposit.setFocusable(false);
		deposit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		deposit.setBorder(new LineBorder(new Color(0, 0, 0)));
		deposit.addActionListener(this);
		mainPanel.add(deposit);
		
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
		
		if(e.getSource() == deposit) {
			
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

				st3.setString(1, ((JTextField) chooseDepositDate.getDateEditor().getUiComponent()).getText());
				st3.setString(2,  "Credited");
				st3.setInt(3, Integer.parseInt(field.getText() ));
				
				st1.setString(1, Card);
				ResultSet rs = st1.executeQuery();
			
				int j = st3.executeUpdate();
				if(j > 0) {
					JOptionPane.showMessageDialog(null, " Data Inserted Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
				}
				
				if(rs.next()) {
					totalSum = rs.getInt("balance");
					totalSum += Integer.parseInt(field.getText());
					String sql2 = "update balanceenquiry set balance = ? where cardnumber = ? ";
					PreparedStatement st2 = con.conn.prepareStatement(sql2);
					st2.setInt(1, totalSum);
					st2.setString(2, Card);
					
					int i = st2.executeUpdate();
					if(i > 0) {
						JOptionPane.showMessageDialog(null, "Rs. " + field.getText() +  " Deposited Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
						this.setVisible(false);
						new Main().setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Failed To Deposit Amount Successfully", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
				totalSum = Integer.parseInt(field.getText());
				st.setString(1, Card );
				st.setInt(2,  totalSum);
				
				int i = st.executeUpdate();
				if(i > 0) {
					
					JOptionPane.showMessageDialog(null, "Rs. " + field.getText() +  " Deposited Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
					this.setVisible(false);
					new Main().setVisible(true);
					
				}else {
					JOptionPane.showMessageDialog(null, "Failed To Deposit Amount Successfully", "Error", JOptionPane.ERROR_MESSAGE);
				}
				}
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		new Deposit().setVisible(true);
	}
}
