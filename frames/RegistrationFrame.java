package frames;



import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import entity.*;

import repository.*;


public class RegistrationFrame extends JFrame implements ActionListener
{
	private JButton submitBtn, backBtn;



	private JLabel cIdLabel, cNameLabel, cAddressLabel;
	private JTextField cIdTF, cNameTF, cAddressTF;

	private JPanel panel;

	public RegistrationFrame(LoginFrame lf)
	{
		super("Register Now !!!");
		this.setSize(800,450);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);


		panel = new JPanel();
		panel.setLayout(null);


		submitBtn = new JButton("Submit");
		submitBtn.setBounds(300, 300, 80, 30);
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
		cIdTF.setBounds(220,100,100,30);
		panel.add(cIdTF);

		cNameLabel = new JLabel("Name :");
		cNameLabel.setBounds(100,150,100,30);
		panel.add(cNameLabel);

		cNameTF = new JTextField();
		cNameTF.setBounds(220,150,100,30);
		panel.add(cNameTF);


		cAddressLabel = new JLabel("Address ");
		cAddressLabel.setBounds(100,250,100,30);
		panel.add(cAddressLabel);

		cAddressTF = new JTextField();
		cAddressTF.setBounds(220,250,100,30);
		panel.add(cAddressTF);


		this.add(panel);
	}
	public void actionPerformed(ActionEvent ae)
	{
		String command = ae.getActionCommand();
		
		if(command.equals(submitBtn.getText()))
		{
			//write codes for Customer Insertion Here
			CustomerRepo cr = new CustomerRepo();
			UserRepo ur = new UserRepo();
			Customer c = new Customer();
			User u = new User();
			Random rd = new Random();
			int x = rd.nextInt(9999999)+10000000;

			c.setId(cIdTF.getText());
			c.setName(cNameTF.getText());
			c.setAddress(cAddressTF.getText());


			u.setUserId(cIdTF.getText());
			u.setPassword(x+"");
			u.setStatus(2);


			cr.insertInDB(c);
			ur.insertUser(u);

			JOptionPane.showMessageDialog(this, "Inserted, Id: "+cIdTF.getText()+"and Password: "+x);

			cIdTF.setText("");
			cNameTF.setText("");
			cAddressTF.setText("");

		}

		else if(command.equals(backBtn.getText()))
		{
			LoginFrame rf = new LoginFrame();
			rf.setVisible(true);
			this.setVisible(false);
		}
		else{}
	}
}