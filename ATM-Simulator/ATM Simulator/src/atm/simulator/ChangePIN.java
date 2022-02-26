package atm.simulator;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class ChangePIN extends JFrame implements ActionListener{

	private JPanel mainPanel;
	private JButton change, back;
	private JPasswordField currentpin, newpin, reenterpin;
	
	public ChangePIN() {
		
		super("Change PIN");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(297, 80, 600, 450);
		
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBackground(new Color(255, 255, 255));
		this.setContentPane(mainPanel);
		
		JLabel label = new JLabel("CHANGE YOUR PIN");
		label.setBounds(210, 56, 350, 40);
		label.setFont(new Font("Tahoma", Font.PLAIN, 25));
		mainPanel.add(label);
		
		JLabel label1 = new JLabel("Current PIN");
		label1.setBounds(160, 126, 150, 40);
		label1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		mainPanel.add(label1);
		
		currentpin = new JPasswordField();
		currentpin.setBounds(270, 136, 100, 20);
		mainPanel.add(currentpin);
		
		JLabel label2 = new JLabel("New PIN");
		label2.setBounds(160, 166, 150, 40);
		label2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		mainPanel.add(label2);
		
		newpin = new JPasswordField();
		newpin.setBounds(270, 176, 100, 20);
		mainPanel.add(newpin);
		
		JLabel label3 = new JLabel("Re-Enter PIN");
		label3.setBounds(160, 206, 150, 40);
		label3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		mainPanel.add(label3);
		
		reenterpin = new JPasswordField();
		reenterpin.setBounds(270, 216, 100, 20);
		mainPanel.add(reenterpin);
		
		back = new JButton("Back");
		back.setBackground(Color.black);
		back.setForeground(Color.white);
		back.setBounds(170, 286, 100, 30);
		back.setFocusable(false);
		back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		back.setBorder(new LineBorder(new Color(0, 0, 0)));
		back.addActionListener(this);
		mainPanel.add(back);
		
		change = new JButton("Change");
		change.setBackground(Color.black);
		change.setForeground(Color.white);
		change.setBounds(296, 286, 100, 30);
		change.setFocusable(false);
		change.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		change.setBorder(new LineBorder(new Color(0, 0, 0)));
		change.addActionListener(this);
		mainPanel.add(change);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == back) {
			this.setVisible(false);
			new Main().setVisible(true);
		}
		
		if(e.getSource() == change) {
			try {
				Conn con = new Conn();
				String Card = ApplicationAccountDetails.getCardNumber() != null ? ApplicationAccountDetails.getCardNumber() : Login.getCard();
				String sql1 = "update account set pin = ? where cardnumber = ? ";
				String sql2 = "select * from account where cardnumber = ? and pin = ? ";
				PreparedStatement st = con.conn.prepareStatement(sql2);
				st.setString(1,  Card);
				st.setString(2, currentpin.getText());
				
				ResultSet rs = st.executeQuery();
				if(rs.next()) {
					if(newpin.getText().equals(reenterpin.getText())) {
					PreparedStatement s = con.conn.prepareStatement(sql1);
					s.setString(1, newpin.getText() );
					s.setString(2,  Card);
					
					int i = s.executeUpdate();
					if(i > 0) {
						JOptionPane.showMessageDialog(null, "Pin Changed Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
						this.setVisible(false);
						new Main().setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Error Occured", "Error", JOptionPane.ERROR_MESSAGE);
					}
					}else {
						JOptionPane.showMessageDialog(null, "Reenter Pin Doesn't Match", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null, "Current Pin Doesn't Match", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		new ChangePIN().setVisible(true);
	}
}
