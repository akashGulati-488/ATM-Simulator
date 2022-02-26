package atm.simulator;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class ApplicationAccountDetails extends JFrame implements ActionListener{
	
	private JPanel mainPanel;
	private JLabel services, accounttype;
	private JButton next, exit;
	private JCheckBox accept, netbanking, mobilebanking, chequebook, atmcard, smsalert;
	private JRadioButton savingaccount, currentaccount, fdaccount;
	private static String cardNumber, pin;
	
	public ApplicationAccountDetails() {
		
		super("ApplicationForm - Account Details");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(280, 21, 800, 650);
		
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBackground(new Color(255, 255, 255));
		this.setContentPane(mainPanel);
		
		Random rand = new Random();
		long rNo = rand.nextLong() % 1000;
		
		JLabel label1 = new JLabel("Application Form - " + "Account Details");
		label1.setBounds(189, 15, 430, 65);
		label1.setFont(new Font("Tahoma", Font.BOLD, 20));
		mainPanel.add(label1);
		
		services = new JLabel("Services Required");
		services.setBounds(90, 113, 150, 25);
		services.setFont(new Font("Tahoma", Font.BOLD, 13));
		mainPanel.add(services);
		
		JLabel star1 = new JLabel("*");
		star1.setBounds(220, 113, 300, 25);
		star1.setFocusable(false);
		star1.setBackground(Color.WHITE);
		star1.setForeground(Color.red);
		mainPanel.add(star1);
		
		netbanking = new JCheckBox("Net Banking");
		netbanking.setBounds(130, 160, 120, 17);
		netbanking.setFont(new Font("Tahoma", Font.PLAIN, 13));
		netbanking.setBackground(Color.WHITE);
		netbanking.setFocusable(false);
		
		mobilebanking = new JCheckBox("Mobile Banking");
		mobilebanking.setBounds(300, 160, 120, 17);
		mobilebanking.setFont(new Font("Tahoma", Font.PLAIN, 13));
		mobilebanking.setBackground(Color.WHITE);
		mobilebanking.setFocusable(false);
		
		chequebook = new JCheckBox("Cheque Book");
		chequebook.setBounds(470, 160, 120, 17);
		chequebook.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chequebook.setBackground(Color.WHITE);
		chequebook.setFocusable(false);
		
		atmcard = new JCheckBox("ATM Card");
		atmcard.setBounds(130, 190, 120, 17);
		atmcard.setFont(new Font("Tahoma", Font.PLAIN, 13));
		atmcard.setBackground(Color.WHITE);
		atmcard.setFocusable(false);
		
		smsalert = new JCheckBox("SMS Alerts");
		smsalert.setBounds(300, 190, 120, 17);
		smsalert.setFont(new Font("Tahoma", Font.PLAIN, 13));
		smsalert.setBackground(Color.WHITE);
		smsalert.setFocusable(false);
		
		mainPanel.add(smsalert);
		mainPanel.add(atmcard);
		mainPanel.add(chequebook);
		mainPanel.add(mobilebanking);
		mainPanel.add(netbanking);
		
		accounttype = new JLabel("Account Type");
		accounttype.setBounds(90, 233, 130, 25);
		accounttype.setFont(new Font("Tahoma", Font.BOLD, 13));
		mainPanel.add(accounttype);
		
		JLabel star2 = new JLabel("*");
		star2.setBounds(186, 233, 300, 25);
		star2.setFocusable(false);
		star2.setBackground(Color.WHITE);
		star2.setForeground(Color.red);
		mainPanel.add(star2);
		
		savingaccount = new JRadioButton("Saving Account");
		savingaccount.setBounds(130, 280, 120, 17);
		savingaccount.setFont(new Font("Tahoma", Font.PLAIN, 13));
		savingaccount.setBackground(Color.WHITE);
		savingaccount.setFocusable(false);
		
		currentaccount = new JRadioButton("Current Account");
		currentaccount.setBounds(300, 280, 120, 17);
		currentaccount.setFont(new Font("Tahoma", Font.PLAIN, 13));
		currentaccount.setBackground(Color.WHITE);
		currentaccount.setFocusable(false);
		
		fdaccount = new JRadioButton("Fixed Deposit Account");
		fdaccount.setBounds(470, 280, 170, 17);
		fdaccount.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fdaccount.setBackground(Color.WHITE);
		fdaccount.setFocusable(false);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(savingaccount);
		bg.add(currentaccount);
		bg.add(fdaccount);
		
		mainPanel.add(savingaccount);
		mainPanel.add(currentaccount);
		mainPanel.add(fdaccount);
	
		JLabel cardnumlabel = new JLabel("Card Number (Your 16 digits card number) :");
		cardnumlabel.setBounds(90, 330, 300, 25);
		cardnumlabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		mainPanel.add(cardnumlabel);
		
		JLabel pinlabel = new JLabel("PIN (Your 4 digits pin is) :");
		pinlabel.setBounds(90, 413, 300, 25);
		pinlabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		mainPanel.add(pinlabel);
		
		accept = new JCheckBox("I agree to Terms & conditions");
		accept.setBounds(90, 520, 200, 25);
		accept.setFocusable(false);
		accept.setBackground(Color.WHITE);
		accept.setForeground(Color.blue);
		mainPanel.add(accept);
		
		JLabel star3 = new JLabel("*");
		star3.setBounds(289, 520, 300, 25);
		star3.setFocusable(false);
		star3.setBackground(Color.WHITE);
		star3.setForeground(Color.red);
		mainPanel.add(star3);
		
		next = new JButton("Next");
		next.setBackground(Color.black);
		next.setForeground(Color.white);
		next.setBounds(600, 540, 95, 30);
		next.setFocusable(false);
		next.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		next.setBorder(new LineBorder(new Color(0, 0, 0)));
		next.addActionListener(this);
		mainPanel.add(next);
		
		exit = new JButton("Exit");
		exit.setBackground(Color.black);
		exit.setForeground(Color.white);
		exit.setBounds(480, 540, 95, 30);
		exit.setFocusable(false);
		exit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exit.setBorder(new LineBorder(new Color(0, 0, 0)));
		exit.addActionListener(this);
		mainPanel.add(exit);
		
		generateNumbers();
	}
	
	public void generateNumbers() {
		
		Random rd = new Random();
		cardNumber = Math.abs(rd.nextInt() + 1000000000) + "";
		pin = Math.abs(rd.nextInt() % 10000 + 1234) + "" ;
		
		JLabel cardnum = new JLabel();
		cardnum.setText(cardNumber);
		cardnum.setBounds(130, 367, 300, 25);
		cardnum.setForeground(Color.green);
		cardnum.setFont(new Font("Tahoma", Font.BOLD, 13));
		mainPanel.add(cardnum);
		
		JLabel pin1 = new JLabel();
		pin1.setText(pin);
		pin1.setBounds(130, 453, 300, 25);
		pin1.setForeground(Color.green);
		pin1.setFont(new Font("Tahoma", Font.BOLD, 13));
		mainPanel.add(pin1);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == next) {
			
			if(accept.isSelected()) {
				
				String requiredServices = "";
				String account = "";
				String seniorCitizen = "";
				
				if(netbanking.isSelected()) {
					requiredServices += "NetBanking ";
				}
				if(mobilebanking.isSelected()) {
					requiredServices += "MobileBanking ";
				}
				if(chequebook.isSelected()) {
					requiredServices += "ChequeBook ";
				}
				if(atmcard.isSelected()) {
					requiredServices += "ATMCard ";
				}
				if(smsalert.isSelected()) {
					requiredServices += "SMSAlert ";
				}
				if(savingaccount.isSelected()) {
					account = "Saving Account";
				}
				if(currentaccount.isSelected()) {
					account = "Current Account";
				}
				if(fdaccount.isSelected()) {
					account = "Fixed Deposit Account";
				}
				if(requiredServices == "") {
					JOptionPane.showMessageDialog(null, "Please Select Services Required Field", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(account == "") {
					JOptionPane.showMessageDialog(null, "Please Select Account Type", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				try {
					
					Conn con = new Conn();
					String sql = "insert into useraccountdetails values (?, ?, ?, ?, ?)";
					String sql1 = "insert into account values (?, ?)";
					PreparedStatement st = con.conn.prepareStatement(sql);
					PreparedStatement st1 = con.conn.prepareStatement(sql1);
					st1.setString(1, cardNumber );
					st1.setString(2, pin );
					
					int i = st1.executeUpdate();
					if(i > 0) {
						JOptionPane.showMessageDialog(null, "Info Added Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
					}
					st.setString(1, requiredServices );
					st.setString(2, account );
					st.setString(3, cardNumber );
					st.setString(4, pin );
					st.setLong(5,  ApplicationPersonalDetails.getRNum());
					
					int rs = st.executeUpdate();
					if(rs > 0) {
						JOptionPane.showMessageDialog(null, "Data Added Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
						this.setVisible(false);
					}
					else {
						JOptionPane.showMessageDialog(null, "Failed To Add Data Successfully", "Error", JOptionPane.ERROR_MESSAGE);
					}
					st.close();
					con.conn.close();
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
					this.setVisible(false);
					new Main().setVisible(true);
				}
			else{
				JOptionPane.showMessageDialog(null, "Please Accept Our Terms And Conditions ", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		
		if(e.getSource() == exit) {
			System.exit(ABORT);
		}
	}
	
	public static String getCardNumber() {
		return cardNumber;
	}
	
	public static void main(String[] args) {
		new ApplicationAccountDetails().setVisible(true);
	}
}
