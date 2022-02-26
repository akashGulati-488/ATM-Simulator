package atm.simulator;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.*;
import net.proteanit.sql.DbUtils;

public class Passbook extends JFrame implements ActionListener{

    private JPanel mainPanel;
    private JTable table;
    private JTextField searchText;
    private JButton search, delete, back;
    private JScrollPane scrollPane;

    public Passbook() {
    	
    	super("Passbook");
        this.setBounds(220, 100, 890, 475);
        
        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(220, 220, 220));
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(mainPanel);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(null);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(12, 70, 851, 340);
        scrollPane.setBorder(new LineBorder(new Color(218,165,32), 3, true));
        mainPanel.add(scrollPane);

        table = new JTable();
        table.addMouseListener(new MouseAdapter() {
            	@Override
            	public void mouseClicked(MouseEvent arg0) {
            		int row = table.getSelectedRow();
            		searchText.setText(table.getModel().getValueAt(row, 1).toString());
            	}
        });
        table.setBackground(new Color(240, 248, 255));
        table.setForeground(new Color(50, 205, 50));
        table.setFont(new Font("Cosmic Sans", Font.BOLD, 16));
        scrollPane.setViewportView(table);

        back = new JButton("Back");
        back.setBackground(Color.black);
		back.setForeground(Color.white);
		back.setBounds(10, 25, 100, 30);
		back.setFocusable(false);
		back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		back.setBorder(new LineBorder(new Color(0, 0, 0)));
		back.addActionListener(this);
        mainPanel.add(back);

        mainPanel.setBorder(new TitledBorder(new LineBorder(new Color(95, 158, 160), 3, true), " Passbook ",
    			TitledBorder.CENTER, TitledBorder.TOP, new Font("MV BOLI", Font.BOLD, 25), new Color(102, 205, 170)));
        studentDetails();
    	}
    
    public void studentDetails() {
        try {
            Conn con = new Conn();
            String sql = "select * from passbook";
            PreparedStatement st = con.conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            table.setModel(DbUtils.resultSetToTableModel(rs));
            st.close();
            con.conn.close();
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }

    
    public void actionPerformed(ActionEvent ae){
    	if(ae.getSource() == back) {
    		this.setVisible(false); 
    		new Main().setVisible(true);
    	}
    }
    public static void main(String[] args) {
    	new Passbook().setVisible(true);
        }
}