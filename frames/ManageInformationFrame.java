package frames;



import java.lang.*;
import javax.swing.*;
import java.awt.event.*;

import java.util.*;
import entity.*;

import repository.*;

public class ManageInformationFrame extends JFrame implements ActionListener
{
	private JButton backBtn,logoutBtn,submitBtn;
	private JLabel cIdLabel, cNameLabel, cAddressLabel;
	private JTextField cIdTF, cNameTF, cAddressTF;

	private JPanel panel;

	CustomerRepo cr = new CustomerRepo();
	Customer c = new Customer();
	User u = new User();

	public ManageInformationFrame(Customer customer)
	{
		super("Customer Information update");
		this.setSize(800,450);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);


		panel = new JPanel();
		panel.setLayout(null);

		logoutBtn = new JButton("Logout");
		logoutBtn.setBounds(600, 50, 150, 30);
		logoutBtn.addActionListener(this);
		panel.add(logoutBtn);

		submitBtn = new JButton("Update");
		submitBtn.setBounds(500, 50, 150, 30);
		submitBtn.addActionListener(this);
		panel.add(submitBtn);


		backBtn = new JButton("Back");
		backBtn.setBounds(390, 300, 80, 30);
		backBtn.addActionListener(this);
		panel.add(backBtn);

		cIdLabel = new JLabel("ID :");
		cIdLabel.setBounds(100,100,100,30);
		panel.add(cIdLabel);

		cIdTF = new JTextField();
		cIdTF.setText(customer.getId());
		cIdTF.setBounds(220,100,100,30);
		panel.add(cIdTF);

		cIdTF.setEditable(false);
		

		cNameLabel = new JLabel("Name :");
		cNameLabel.setBounds(100,150,100,30);
		panel.add(cNameLabel);

		cNameTF = new JTextField();
		cNameTF.setText(customer.getName());
		cNameTF.setBounds(220,150,100,30);
		panel.add(cNameTF);

		cAddressLabel = new JLabel("Address ");
		cAddressLabel.setBounds(100,250,100,30);
		panel.add(cAddressLabel);

		cAddressTF = new JTextField();
		cAddressTF.setText(customer.getAddress());
		cAddressTF.setBounds(220,250,100,30);
		panel.add(cAddressTF);

		this.c = customer;

		this.add(panel);
	}
	public void actionPerformed(ActionEvent ae)
	{
		String command = ae.getActionCommand();


		if(command.equals(submitBtn.getText()))
		{
			UserRepo ur = new UserRepo();


			Random rd = new Random();
			int x = rd.nextInt(9999999)+10000000;

			c.setId(cIdTF.getText());
			c.setName(cNameTF.getText());
			c.setAddress(cAddressTF.getText());



			cr.updateInDB(c);

			JOptionPane.showMessageDialog(this, "Customer updated , Id: "+cIdTF.getText());
		}
		if(command.equals(logoutBtn.getText()))
		{
			LoginFrame lf = new LoginFrame();
			lf.setVisible(true);
			this.setVisible(false);
		}
		else if(command.equals(backBtn.getText())){
			User usr = new User();
			usr.setUserId(c.getId());
			CustomerHome customerHome = new CustomerHome(usr);
			customerHome.setVisible(true);
			this.setVisible(false);
		}
		else{}
	}
}