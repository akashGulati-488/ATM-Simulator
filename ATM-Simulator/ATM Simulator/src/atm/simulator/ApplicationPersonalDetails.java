package atm.simulator;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class EmailValidator {

    private Pattern pattern;
    private Matcher matcher;

    private static final String EMAIL_PATTERN = 
        "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public EmailValidator() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    public boolean validate(final String hex) {

        matcher = pattern.matcher(hex);
        return matcher.matches();

    }
}
public class ApplicationPersonalDetails extends JFrame implements ActionListener{

	private JPanel mainPanel;
	private JTextField name_field, father_name_field, dob_field, email_field, address_field, 
	city_field, pincode_field, state_field, mobilenumber_field;
	private JLabel name, father_name, dob;
	private JComboBox box1, box2, box3;
	private JRadioButton male, female, single, married;
	private static long rNo;
	private JButton next;
	public ApplicationPersonalDetails() {
		
		super("Application Form - Personal Details");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(280, 21, 800, 650);
		
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBackground(new Color(255, 255, 255));
		this.setContentPane(mainPanel);
		
		Random rand = new Random();
		rNo = Math.abs(rand.nextLong() % 1000);
		
		JLabel label1 = new JLabel("Application Form - " + rNo);
		label1.setBounds(250, 15, 350, 65);
//		label1.setForeground(Color.RED);
		label1.setFont(new Font("Tahoma", Font.BOLD, 20));
		mainPanel.add(label1);
		
		JLabel label2 = new JLabel("Personal Details");
//		label2.setForeground(Color.GREEN);
		label2.setBounds(310, 55, 150, 35);
		label2.setFont(new Font("Cosmic Sans", Font.BOLD, 15));
		mainPanel.add(label2);
		
		name = new JLabel("Name :");
		name.setBounds(150, 113, 120, 25);
		name.setFont(new Font("Tahoma", Font.BOLD, 13));
		mainPanel.add(name);
		
		name_field = new JTextField();
		name_field.setBounds(280, 117, 300, 19);
		mainPanel.add(name_field);
		
		father_name = new JLabel("Father's Name :");
		father_name.setBounds(150, 153, 150, 25);
		father_name.setFont(new Font("Tahoma", Font.BOLD, 13));
		mainPanel.add(father_name);
		
		father_name_field = new JTextField();
		father_name_field.setBounds(280, 157, 300, 19);
		mainPanel.add(father_name_field);
		
		dob = new JLabel("Date Of Birth :");
		dob.setBounds(150, 193, 170, 25);
		dob.setFont(new Font("Tahoma", Font.BOLD, 13));
		mainPanel.add(dob);
		
//		dob_field = new JTextField();
//		dob_field.setBounds(270, 177, 300, 19);
//		mainPanel.add(dob_field);
		
		JLabel date = new JLabel("Date");
		date.setBounds(277, 193, 95, 25);
		date.setFont(new Font("Tahoma", Font.BOLD, 11));
		mainPanel.add(date);
		
		Integer[] dates = {1, 2 , 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20
				, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
		
		box1 = new JComboBox(dates);
		box1.setBounds(310, 197, 40, 15);
		mainPanel.add(box1);
		
		JLabel month = new JLabel("Month");
		month.setBounds(369, 193, 95, 25);
		month.setFont(new Font("Tahoma", Font.BOLD, 11));
		mainPanel.add(month);
		
		String[] months = {"January", "February", "March", "April", "May", "June", "July", "August",
				"September", "October", "November", "December"};
		
		box2 = new JComboBox(months);
		box2.setBounds(410, 197, 120, 17);
		mainPanel.add(box2);
		
		JLabel year = new JLabel("Year");
		year.setBounds(546, 193, 95, 25);
		year.setFont(new Font("Tahoma", Font.BOLD, 11));
		mainPanel.add(year);
		
		Integer[] years = {1998, 1999, 2000, 2001, 2002, 2003, 2004, 2005, 2006, 2007, 2008, 2009,
				2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022};
		
		box3 = new JComboBox(years);
		box3.setBounds(580, 197, 60, 17);
		mainPanel.add(box3);
		
		JLabel gender = new JLabel("Gender :");
		gender.setBounds(150, 229, 100, 25);
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
		ButtonGroup group = new ButtonGroup();
		group.add(male);
		group.add(female);
		mainPanel.add(male);
		mainPanel.add(female);
		
		JLabel email = new JLabel("Email Address :");
		email.setBounds(150, 265, 170, 25);
		email.setFont(new Font("Tahoma", Font.BOLD, 13));
		mainPanel.add(email);
		
		email_field = new JTextField();
		email_field.setBounds(280, 268, 300, 19);
		mainPanel.add(email_field);
		
		JLabel address = new JLabel("Address :");
		address.setBounds(150, 305, 170, 25);
		address.setFont(new Font("Tahoma", Font.BOLD, 13));
		mainPanel.add(address);
		
		address_field = new JTextField();
		address_field.setBounds(280, 310, 300, 19);
		mainPanel.add(address_field);
		
		JLabel city = new JLabel("City :");
		city.setBounds(150, 345, 170, 25);
		city.setFont(new Font("Tahoma", Font.BOLD, 13));
		mainPanel.add(city);
		
		city_field = new JTextField();
		city_field.setBounds(280, 350, 300, 19);
		mainPanel.add(city_field);
		
		JLabel state = new JLabel("State :");
		state.setBounds(150, 385, 170, 25);
		state.setFont(new Font("Tahoma", Font.BOLD, 13));
		mainPanel.add(state);
		
		state_field = new JTextField();
		state_field.setBounds(280, 390, 300, 19);
		mainPanel.add(state_field);
		
		JLabel pincode = new JLabel("PIN Code :");
		pincode.setBounds(150, 425, 170, 25);
		pincode.setFont(new Font("Tahoma", Font.BOLD, 13));
		mainPanel.add(pincode);
		
		pincode_field = new JTextField();
		pincode_field.setBounds(280, 430, 300, 19);
		mainPanel.add(pincode_field);
		
		JLabel mobilenumber = new JLabel("Mobile Number :");
		mobilenumber.setBounds(150, 470, 170, 25);
		mobilenumber.setFont(new Font("Tahoma", Font.BOLD, 13));
		mainPanel.add(mobilenumber);
		
		mobilenumber_field = new JTextField();
		mobilenumber_field.setBounds(280, 470, 300, 19);
		mainPanel.add(mobilenumber_field);
		
		JLabel mar_status = new JLabel("Martial Status :");
		mar_status.setBounds(150, 510, 170, 25);
		mar_status.setFont(new Font("Tahoma", Font.BOLD, 13));
		mainPanel.add(mar_status);
		
//		address_field = new JTextField();
//		address_field.setBounds(280, 310, 300, 19);
//		mainPanel.add(address_field);
		
		married = new JRadioButton("Married");
		married.setBounds(277, 510, 90, 25);
		married.setBackground(Color.white);
		married.setFocusable(false);
		single = new JRadioButton("Single");
		single.setBounds(370, 510, 90, 25);
		single.setBackground(Color.white);
		single.setFocusable(false);
		ButtonGroup group1 = new ButtonGroup();
		group1.add(married);
		group1.add(single);
		mainPanel.add(married);
		mainPanel.add(single);
		
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
		
		EmailValidator emailValidator = new EmailValidator();
		if(!emailValidator.validate(email_field.getText().trim())) {
			JOptionPane.showMessageDialog(null, "Invalid EmailID", "Error", JOptionPane.ERROR_MESSAGE);
			return;
	        
		}
		
		if(e.getSource() == next) {
			
			String gender = "", mar_status = "";
			if(male.isSelected()) {
				gender = "Male";
			}
			if(female.isSelected()) {
				gender = "Female";
			}
			if(single.isSelected()) {
				mar_status = "Single";
			}
			if(married.isSelected()) {
				mar_status = "Married";
			}
			if(name_field.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Please Write Name Field", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(father_name_field.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Please Write Father Name Field", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(email_field.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Please Write Email Field", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(address_field.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Please Write Address Field", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(city_field.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Please Write City Field", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(state_field.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Please Write State Field", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(pincode_field.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Please Write Pincode Field", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(mobilenumber_field.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Please Write Mobile Number Field", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(gender == "") {
				JOptionPane.showMessageDialog(null, "Please Select Gender Field", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(mar_status == "") {
				JOptionPane.showMessageDialog(null, "Please Martial Status Gender Field", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(mobilenumber_field.getText().length() != 10) {
				JOptionPane.showMessageDialog(null, "Invalid Mobile Number", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				Conn con = new Conn();
				String sql = "insert into userPersonalDetails values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement st = con.conn.prepareStatement(sql);
				
				st.setString(1, name_field.getText() );
				st.setString(2, father_name_field.getText() );
				st.setString(3, box1.getSelectedItem() + " " + (String) box2.getSelectedItem() + " " + box3.getSelectedItem() );
				st.setString(4, gender );
				st.setString(5, address_field.getText() );
				st.setString(6, city_field.getText() );
				st.setString(7, state_field.getText() );
				st.setString(8, pincode_field.getText() );
				st.setString(9, mar_status );
				st.setLong(10, rNo);
				st.setString(11, email_field.getText() );
				st.setString(12, mobilenumber_field.getText() );
				
				int rs = st.executeUpdate();
				if(rs > 0) {
					JOptionPane.showMessageDialog(null, "Data Added Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
					this.setVisible(false);
					new ApplicationPersonalAdditionalDetails().setVisible(true);
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
		
		}
		
	}
	public static long getRNum() {
		return rNo;
	}
	public static void main(String[] args) {
		new ApplicationPersonalDetails().setVisible(true);
	}
}
