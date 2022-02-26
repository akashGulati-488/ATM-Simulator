package atm.simulator;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.*;

public class GetOTP extends JFrame implements ActionListener {
	
	private JPanel mainPanel;
	private JTextField text_field;
	private JButton getotp;
	private int otp;
	
	public GetOTP() {
		
		super("Get OTP");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(400, 175, 600, 250);
		
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBackground(Color.WHITE);
		this.setContentPane(mainPanel);
		
		JLabel mobilenumber = new JLabel("Mobile Number : ");
		mobilenumber.setBounds(120, 59, 150, 24);
		mobilenumber.setFont(new Font("Tahoma", Font.BOLD, 15));
		mainPanel.add(mobilenumber);
		
		text_field = new JTextField();
		text_field.setBounds(276, 60, 180, 24);
		text_field.setFont(new Font("Cosmic Sans", Font.PLAIN, 15));
		text_field.setBorder(new LineBorder(new Color(173, 216, 230), 1, true));
		mainPanel.add(text_field);
		
		getotp = new JButton("Get OTP");
		getotp.setBackground(Color.black);
		getotp.setForeground(Color.white);
		getotp.setBounds(240, 140, 130, 30);
		getotp.setFocusable(false);
		getotp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		getotp.setBorder(new LineBorder(new Color(0, 0, 0)));
		getotp.addActionListener(this);
		mainPanel.add(getotp);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == getotp) {
			
			Random rand = new Random();
			otp = rand.nextInt(9999);
			
			try {
				// Construct data
				String apiKey = "apikey=" + "MzE0ZDM1NjM2ZTY5NTU1ODZkNmQ0ODRhNmQ1ODc5NzM=";
				String message = "&message=" + otp + "is your ATM Simulator verification code";
				String sender = "&sender=" + "ATM Simulator";
				String numbers = "&numbers=" + text_field.getText();
				
				// Send data
				HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
				String data = apiKey + numbers + message + sender;
				conn.setDoOutput(true);
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
				conn.getOutputStream().write(data.getBytes("UTF-8"));
				final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				final StringBuffer stringBuffer = new StringBuffer();
				String line;
				while ((line = rd.readLine()) != null) {
					stringBuffer.append(line);
				}
				System.out.println(data +" " + otp);
				rd.close();
				JOptionPane.showMessageDialog(null, "Message Sent Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
//				return stringBuffer.toString();
			} catch (Exception ex) {
//				System.out.println("Error SMS "+ex);
				JOptionPane.showMessageDialog(null, " Error SMS ", "Success", JOptionPane.INFORMATION_MESSAGE);
//				return "Error "+e;
			}
			
		}
	}
	
	public static void main(String[] args) {
		new GetOTP().setVisible(true);
	}
}
