import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jdom.Document;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.JTable;
import javax.swing.JScrollPane;
 

public class donate extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField surname;
	private JTextField amount;
	private JTextField currency;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					donate frame = new donate();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public donate() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 543, 341);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
	
		
		 
		
		name = new JTextField();
		name.setBounds(94, 37, 96, 19);
		contentPane.add(name);
		name.setColumns(10);
		
		surname = new JTextField();
		surname.setBounds(94, 85, 96, 19);
		contentPane.add(surname);
		surname.setColumns(10);
		
		amount = new JTextField();
		amount.setBounds(94, 130, 96, 19);
		contentPane.add(amount);
		amount.setColumns(10);
		
		currency = new JTextField();
		currency.setBounds(94, 174, 96, 19);
		contentPane.add(currency);
		currency.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(10, 37, 46, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("surname");
		lblNewLabel_1.setBounds(10, 85, 130, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("amount");
		lblNewLabel_2.setBounds(10, 130, 46, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("currency");
		lblNewLabel_3.setBounds(10, 174, 130, 13);
		contentPane.add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("create user");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			try 
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/donations","root","");
				PreparedStatement ps = conn.prepareStatement("insert into users(name,surname,amount,currency) values(?,?,?,?);");
				ps.setString(1, name.getText() );
				ps.setString(2, surname.getText() );
				ps.setString(3, amount.getText() );
				ps.setString(4, currency.getText() );
				int x = ps.executeUpdate();
				if(x>0)
				{
					System.out.println("all ok");
				}
				else
				{
					System.out.println("failed");
				}
			}
			catch(Exception e1) {System.out.println(e1);}
			
			}
		});
		btnNewButton.setBounds(72, 206, 130, 21);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(210, 10, 319, 284);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_1 = new JButton("load data");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				try 
				{
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/donations","root","");
					String query ="select * from users";
					PreparedStatement ps = conn.prepareStatement(query);
					ResultSet rs = ps.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}
				catch(Exception e1)
				{
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(72, 237, 132, 21);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("generate pdf");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			/*	String path ="";
				JFileChooser j = new JFileChooser();
				j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int x = j.showSaveDialog(this);
				
				if(x==JFileChooser.APPROVE_OPTION)
				{
					path = j.getSelectedFile().getPath();
				}
				Document doc = new Document();
				try 
				{
					PdfWriter.getInstance(doc,new FileOutputStream(path+"abc123.pdf"));	
					doc.open();
					
					
					PdfPTable = tbl = new PdfPTable(3);
					
				}catch(Exception e)
				{
					
				}*/
				
			}
		});
		btnNewButton_2.setBounds(72, 268, 130, 21);
		contentPane.add(btnNewButton_2);
	}
}
