package atm.simulator;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.*;

public class MainPage extends JFrame implements ActionListener{

	private JPanel mainPanel;
	private JLabel label;
	private JButton open;
	
	public MainPage() {
		
		super("ATM Simulator");
//		ImageIcon appicon = new ImageIcon(ClassLoader.getSystemResource("atm/simulator/icons/atm.png"));
//		this.setIconImage(appicon.getImage());
		this.setBounds(300, 150, 700, 370);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.white);
		this.setContentPane(mainPanel);
		ImageIcon mainIcon = new ImageIcon(ClassLoader.getSystemResource("atm/simulator/icons/atm.png"));
		Image img = mainIcon.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);
		ImageIcon result = new ImageIcon(img);
		label = new JLabel(result);
		open = new JButton("Open");
		open.setForeground(Color.blue);
		open.setBackground(Color.white);
		open.setBorder(new LineBorder(new Color(255, 255, 255)));
		open.setFocusable(false);
		open.setBounds(200 , 200, 120, 20);
		open.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		open.addActionListener(this);
		mainPanel.add(open);
		mainPanel.add(label);		
		
		
	}
	
	public static void main(String[] args) {
		new MainPage().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == open) {
			this.setVisible(false);
			new Login().setVisible(true);
		}
		
	}
}
