package atm.simulator;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class ApplicationPersonalAdditionalDetails extends JFrame implements ActionListener{

	private JPanel mainPanel;
	private JTextField occupation_field, aadharNo_field, panNo_field, email_field, address_field, 
	city_field, pincode_field, state_field;
	private JLabel religion, category, edu_qual;
	private JButton next;
	private JComboBox religionBox, categoriesBox, qualificationBox;
	private JRadioButton account_yes, account_no, senior_no, senior_yes, male, female, others;
	
	public ApplicationPersonalAdditionalDetails() {
		
		super("ApplicationForm - Additional Details");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(280, 21, 800, 650);
		
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBackground(new Color(255, 255, 255));
		this.setContentPane(mainPanel);
		
		Random rand = new Random();
		long rNo = rand.nextLong() % 1000;
		
		JLabel label1 = new JLabel("Application Form - " + "Personal Details");
		label1.setBounds(170, 15, 430, 65);
//		label1.setForeground(Color.RED);
		label1.setFont(new Font("Tahoma", Font.BOLD, 20));
		mainPanel.add(label1);
		
		JLabel label2 = new JLabel("Additional Details");
//		label2.setForeground(Color.GREEN);
		label2.setBounds(310, 55, 150, 35);
		label2.setFont(new Font("Cosmic Sans", Font.BOLD, 15));
		mainPanel.add(label2);
		
		religion = new JLabel("Religion :");
		religion.setBounds(90, 113, 120, 25);
		religion.setFont(new Font("Tahoma", Font.BOLD, 13));
		mainPanel.add(religion);
		
		String[] religion = {"Hindu", "Muslim", "Christian", "Buddhism", "Others"};
		religionBox = new JComboBox(religion);
		religionBox.setBounds(277, 113, 180, 20);
		mainPanel.add(religionBox);
		
		category = new JLabel("Category :");
		category.setBounds(90, 153, 150, 25);
		category.setFont(new Font("Tahoma", Font.BOLD, 13));
		mainPanel.add(category);
		
		String[] categories = {"General", "SC", "ST", "OBC"};
		categoriesBox = new JComboBox(categories);
		categoriesBox.setBounds(277, 153, 180, 20);
		mainPanel.add(categoriesBox);
		
		edu_qual = new JLabel("Educational Qualification :");
		edu_qual.setBounds(90, 193, 180, 25);
		edu_qual.setFont(new Font("Tahoma", Font.BOLD, 13));
		mainPanel.add(edu_qual);
		
		String[] qualification = {"U.G", "P.G", "Metric", "Higher Secondary"};
		qualificationBox = new JComboBox(qualification);
		qualificationBox.setBounds(277, 193, 180, 20);
		mainPanel.add(qualificationBox);
		
		JLabel gender = new JLabel("Gender :");
		gender.setBounds(90, 229, 100, 25);
		gender.setFont(new Font("Tahoma", Font.BOLD, 13));
		mainPanel.add(gender);
		
		male = new JRadioButton("Male");
		male.setBounds(277, 229, 90, 25);
		male.setBackground(Color.white);
		male.setFocusable(false);
		female = new JRadioButton("Female");
		female.setBounds(370, 229, 90, 25);
		female.setBackground(Color.white);
		female.setFocusable(false);
		others = new JRadioButton("Others");
		others.setBounds(463, 229, 90, 25);
		others.setBackground(Color.white);
		others.setFocusable(false);
		ButtonGroup group1 = new ButtonGroup();
		group1.add(male);
		group1.add(female);
		group1.add(others);
		mainPanel.add(male);
		mainPanel.add(female);
		mainPanel.add(others);
	
		JLabel occupation = new JLabel("Occupation :");
		occupation.setBounds(90, 265, 170, 25);
		occupation.setFont(new Font("Tahoma", Font.BOLD, 13));
		mainPanel.add(occupation);
		
		occupation_field = new JTextField();
		occupation_field.setBounds(280, 268, 300, 19);
		mainPanel.add(occupation_field);
		
		JLabel aadharNo = new JLabel("Aadhar Number :");
		aadharNo.setBounds(90, 305, 170, 25);
		aadharNo.setFont(new Font("Tahoma", Font.BOLD, 13));
		mainPanel.add(aadharNo);
		
		aadharNo_field = new JTextField();
		aadharNo_field.setBounds(280, 310, 300, 19);
		mainPanel.add(aadharNo_field);
		
		JLabel panNo = new JLabel("PAN Number :");
		panNo.setBounds(90, 345, 170, 25);
		panNo.setFont(new Font("Tahoma", Font.BOLD, 13));
		mainPanel.add(panNo);
		
		panNo_field = new JTextField();
		panNo_field.setBounds(280, 350, 300, 19);
		mainPanel.add(panNo_field);
		
		JLabel existingAccount = new JLabel("Existing Account :");
		existingAccount.setBounds(90, 385, 170, 25);
		existingAccount.setFont(new Font("Tahoma", Font.BOLD, 13));
		mainPanel.add(existingAccount);
		
		account_yes = new JRadioButton("Yes");
		account_yes.setBounds(277, 385, 90, 25);
		account_yes.setBackground(Color.white);
		account_yes.setFocusable(false);
		account_no = new JRadioButton("No");
		account_no.setBounds(370, 385, 90, 25);
		account_no.setBackground(Color.white);
		account_no.setFocusable(false);
		ButtonGroup group = new ButtonGroup();
		group.add(account_yes);
		group.add(account_no);
		mainPanel.add(account_yes);
		mainPanel.add(account_no);
		
		JLabel seniorCitizen = new JLabel("Senior Citizen :");
		seniorCitizen.setBounds(90, 425, 170, 25);
		seniorCitizen.setFont(new Font("Tahoma", Font.BOLD, 13));
		mainPanel.add(seniorCitizen);
		
		senior_yes = new JRadioButton("Yes");
		senior_yes.setBounds(277, 425, 90, 25);
		senior_yes.setBackground(Color.white);
		senior_yes.setFocusable(false);
		senior_no = new JRadioButton("No");
		senior_no.setBounds(370, 425, 90, 25);
		senior_no.setBackground(Color.white);
		senior_no.setFocusable(false);
		ButtonGroup group2 = new ButtonGroup();
		group2.add(senior_yes);
		group2.add(senior_no);
		mainPanel.add(senior_yes);
		mainPanel.add(senior_no);
		
		next = new JButton("Next");
		next.setBackground(Color.black);
		next.setForeground(Color.white);
		next.setBounds(600, 540, 95, 30);
		next.setFocusable(false);
		next.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		next.setBorder(new LineBorder(new Color(0, 0, 0)));
		next.addActionListener(this);
		mainPanel.add(next);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == next) {
			
			String choosenGender = "";
			String existingAccount = "";
			String seniorCitizen = "";
			
			
			if(male.isSelected()) {
				choosenGender = "Male";
			}
			if(female.isSelected()) {
				choosenGender = "Female";
			}
			if(others.isSelected()) {
				choosenGender = "Others";
			}
			if(account_yes.isSelected()) {
				existingAccount = "Yes";
			}
			if(account_no.isSelected()) {
				existingAccount = "No";
			}
			if(senior_yes.isSelected()) {
				seniorCitizen = "Yes";
			}
			if(senior_no.isSelected()) {
				seniorCitizen = "No";
			}
			
			if(choosenGender == "") {
				JOptionPane.showMessageDialog(null, "Please Select Gender", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(existingAccount == "") {
				JOptionPane.showMessageDialog(null, "Please Select Existing Account", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(seniorCitizen == "") {
				JOptionPane.showMessageDialog(null, "Please Select Senior Citizen", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(occupation_field.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Please Write Occupation Field", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(aadharNo_field.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Please Write Aadhar Number Field", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(panNo_field.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Please Write Pan Number Field", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				Conn con = new Conn();
				String sql = "insert into userPersonalAdditionalDetails values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement st = con.conn.prepareStatement(sql);
				
				st.setString(1, (String) religionBox.getSelectedItem() );
				st.setString(2, (String) categoriesBox.getSelectedItem() );
				st.setString(3, (String) qualificationBox.getSelectedItem() );
				st.setString(4, choosenGender );
				st.setString(5, occupation_field.getText() );
				st.setString(6, aadharNo_field.getText() );
				st.setString(7, panNo_field.getText() );
				st.setString(8, existingAccount );
				st.setString(9, seniorCitizen );
				st.setLong(10,  ApplicationPersonalDetails.getRNum());
				int rs = st.executeUpdate();
				if(rs > 0) {
					JOptionPane.showMessageDialog(null, "Data Added Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
					this.setVisible(false);
//					new ApplicationForm2().setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(null, "Failed To Add Data Successfully", "Error", JOptionPane.ERROR_MESSAGE);
				}
				st.close();
				con.conn.close();
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
			this.setVisible(false);
			new ApplicationAccountDetails().setVisible(true); 
		}
	}
	
	public static void main(String[] args) {
		new ApplicationPersonalAdditionalDetails().setVisible(true);
	}
}
