package frames;


import java.lang.*;
import javax.swing.*;
import java.awt.event.*;
import entity.*;

import repository.*;


public class CustomerHome extends JFrame implements ActionListener
{
	JButton logoutBtn, manageBtn, purchasedProductBtn, changePasswordBtn,productBtn;
	JPanel panel;

	User user;
	CustomerRepo customerRepo;
	UserRepo userRepo;

	public CustomerHome(User user)
	{
		super("Welcome Customer");
		this.setSize(800,450);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.user = user;
		this.customerRepo = new CustomerRepo();
		this.userRepo = new UserRepo();
		
		panel = new JPanel();
		panel.setLayout(null);
		
		logoutBtn = new JButton("Logout");
		logoutBtn.setBounds(600, 50, 150, 30);
		logoutBtn.addActionListener(this);
		panel.add(logoutBtn);
		
		changePasswordBtn = new JButton("Change Password");
		changePasswordBtn.setBounds(600, 100, 150, 30);
		changePasswordBtn.addActionListener(this);
		panel.add(changePasswordBtn);
		
		manageBtn = new JButton("Manage Information");
		manageBtn.setBounds(50, 100, 150, 30);
		manageBtn.addActionListener(this);
		panel.add(manageBtn);
		
		purchasedProductBtn = new JButton("Purchased Product");
		purchasedProductBtn.setBounds(225, 100, 150, 30);
		purchasedProductBtn.addActionListener(this);
		panel.add(purchasedProductBtn);

		productBtn = new JButton("All Product");
		productBtn.setBounds(400, 100, 150, 30);
		productBtn.addActionListener(this);
		panel.add(productBtn);
		this.add(panel);
	}
	public void actionPerformed(ActionEvent ae)
	{
		String command = ae.getActionCommand();
		
		if(command.equals(logoutBtn.getText()))
		{
			LoginFrame lf = new LoginFrame();
			lf.setVisible(true);
			this.setVisible(false);
		}
		else if(command.equals(manageBtn.getText()))
		{
			user = userRepo.getUserInfo(user.getUserId());
			if(user.getStatus()==2)
			{
				Customer customer = customerRepo.searchCustomer(user.getUserId());
				ManageInformationFrame mf = new ManageInformationFrame(customer);
				mf.setVisible(true);
				this.setVisible(false);
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Access Denied");
			}
		}
		else if(command.equals(purchasedProductBtn.getText())) {
			PurchaseHistoryFrame ph = new PurchaseHistoryFrame(user);
			ph.setVisible(true);
			this.setVisible(false);
		}

			else if(command.equals(productBtn.getText()))
		{
			AllProductFrame pf = new AllProductFrame(user);
			pf.setVisible(true);
			this.setVisible(false);
		}
		else if(command.equals(changePasswordBtn.getText())){
			user = userRepo.getUserInfo(user.getUserId());
			ChangePasswordForCustomer changePassword = new ChangePasswordForCustomer(user);
			changePassword.setVisible(true);
			this.setVisible(false);
		}


		else{}
	}
}