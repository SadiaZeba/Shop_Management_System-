package frames;

import java.lang.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

import repository.*;
import entity.*;


public class ProductFrame extends JFrame implements ActionListener
{
	private JLabel prIdLabel, prNameLabel, prQuantityLabel, prPriceLabel;
	private JTextField prIdTF, prNameTF, prQuantityTF, prPriceTF;
	private JButton loadBtn, insertBtn, updateBtn, deleteBtn, refreshBtn, getAllBtn, backBtn;
	private JPanel panel;
	private JTable prTable;
	private JScrollPane prTableSP;
	
	private User user;
	private ProductRepo er;
	private UserRepo ur;
	
	
	public ProductFrame(User user)
	{
		super("ProductFrame");
		this.setSize(800,450);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.user = user;
		
		er = new ProductRepo();
		ur = new UserRepo();
		
		panel = new JPanel();
		panel.setLayout(null);
		
		
		
		String data[][] = {{"", "", "", ""}};
		
		String head[] = {"Id", "Name", "Avaliable Quantity", "Price"};
		
		prTable = new JTable(data,head);
		prTableSP = new JScrollPane(prTable);
		prTableSP.setBounds(350, 100, 400, 150);
		prTable.setEnabled(false);
		panel.add(prTableSP);
		
		prIdLabel = new JLabel("ID :");
		prIdLabel.setBounds(100,100,100,30);
		panel.add(prIdLabel);
		
		prIdTF = new JTextField();
		prIdTF.setBounds(220,100,100,30);
		panel.add(prIdTF);
		
		prNameLabel = new JLabel("Name :");
		prNameLabel.setBounds(100,150,100,30);
		panel.add(prNameLabel);
		
		prNameTF = new JTextField();
		prNameTF.setBounds(220,150,100,30);
		panel.add(prNameTF);
		
		prQuantityLabel = new JLabel("Avaliable Quantity: ");
		prQuantityLabel.setBounds(100,200,100,30);
		panel.add(prQuantityLabel);
		
		prQuantityTF = new JTextField();
		prQuantityTF.setBounds(220,200,100,30);
		panel.add(prQuantityTF);
		
		prPriceLabel = new JLabel("Price: ");
		prPriceLabel.setBounds(100,250,100,30);
		panel.add(prPriceLabel);
		
		prPriceTF = new JTextField();
		prPriceTF.setBounds(220,250,100,30);
		panel.add(prPriceTF);
		
		loadBtn = new JButton("Load");
		loadBtn.setBounds(100,300,80,30);
		loadBtn.addActionListener(this);
		panel.add(loadBtn);
		
		insertBtn = new JButton("Insert");
		insertBtn.setBounds(200,300,80,30);
		insertBtn.addActionListener(this);
		panel.add(insertBtn);
		
		updateBtn = new JButton("Update");
		updateBtn.setBounds(300,300,80,30);
		updateBtn.addActionListener(this);
		updateBtn.setEnabled(false);
		panel.add(updateBtn);
		
		deleteBtn = new JButton("Delete");
		deleteBtn.setBounds(400,300,80,30);
		deleteBtn.addActionListener(this);
		deleteBtn.setEnabled(false);
		panel.add(deleteBtn);
		
		refreshBtn = new JButton("Refresh");
		refreshBtn.setBounds(500,300,80,30);
		refreshBtn.addActionListener(this);
		refreshBtn.setEnabled(false);
		panel.add(refreshBtn);
		
		getAllBtn = new JButton("Get All");
		getAllBtn.setBounds(500,260,80,30);
		getAllBtn.addActionListener(this);
		panel.add(getAllBtn);
		
		backBtn = new JButton("Back");
		backBtn.setBounds(600, 350, 80, 30);
		backBtn.addActionListener(this);
		panel.add(backBtn);
		
		this.add(panel);
	}
	public void actionPerformed(ActionEvent ae)
	{
		String command = ae.getActionCommand();
		
		if(command.equals(loadBtn.getText()))
		{
			if(!prIdTF.getText().equals("") || !prIdTF.getText().equals(null))
			{
				Product e = er.searchProduct(prIdTF.getText());
				if(e!= null)
				{
					prNameTF.setText(e.getName());
					prQuantityTF.setText(Integer.toString(e.getQuantity()));
					prPriceTF.setText(Double.toString(e.getPrice()));
					
					prIdTF.setEnabled(false);
					prNameTF.setEnabled(true);
					prQuantityTF.setEnabled(true);
					prPriceTF.setEnabled(true);
					
					updateBtn.setEnabled(true);
					deleteBtn.setEnabled(true);
					refreshBtn.setEnabled(true);
					insertBtn.setEnabled(false);
					loadBtn.setEnabled(false);
				}
				else
				{
					JOptionPane.showMessageDialog(this,"Invaild ID");
				}
			}
		}
		else if(command.equals(insertBtn.getText()))
		{
			Product e = new Product();
			User u = new User();
			Random rd = new Random();
			int x = rd.nextInt(9999999)+10000000;
			
			e.setId(prIdTF.getText());
			e.setName(prNameTF.getText());
			e.setQuantity(Integer.parseInt(prQuantityTF.getText()));
			e.setPrice(Double.parseDouble(prPriceTF.getText()));

			
			er.insertInDB(e);
			ur.insertUser(u);
			
			JOptionPane.showMessageDialog(this, "Inserted, Id: "+ prIdTF.getText()+"and Product Name: "+e.getName());
			
			prIdTF.setText("");
			prNameTF.setText("");
			prQuantityTF.setText("");
			prPriceTF.setText("");
			
			loadBtn.setEnabled(true);
			insertBtn.setEnabled(true);
			updateBtn.setEnabled(false);
			deleteBtn.setEnabled(false);
			refreshBtn.setEnabled(false);
			
		}
		else if(command.equals(refreshBtn.getText()))
		{
			prIdTF.setText("");
			prNameTF.setText("");
			prQuantityTF.setText("");
			prPriceTF.setText("");
			
			prIdTF.setEnabled(true);
			loadBtn.setEnabled(true);
			insertBtn.setEnabled(true);
			updateBtn.setEnabled(false);
			deleteBtn.setEnabled(false);
			refreshBtn.setEnabled(false);
		}
		else if(command.equals(updateBtn.getText()))
		{
			Product e = new Product();
			
			e.setId(prIdTF.getText());
			e.setName(prNameTF.getText());
			e.setPrice(Double.parseDouble(prPriceTF.getText()));
			e.setQuantity(Integer.parseInt(prQuantityTF.getText()));
			
			er.updateInDB(e);
			
			JOptionPane.showMessageDialog(this, "Updated");
			
			prIdTF.setText("");
			prNameTF.setText("");
			prQuantityTF.setText("");
			prPriceTF.setText("");
			
			loadBtn.setEnabled(true);
			insertBtn.setEnabled(true);
			updateBtn.setEnabled(false);
			deleteBtn.setEnabled(false);
			refreshBtn.setEnabled(false);
			
			prIdTF.setEnabled(true);
			prNameTF.setEnabled(true);
			prQuantityTF.setEnabled(true);
			prPriceTF.setEnabled(true);
		}
		else if(command.equals(deleteBtn.getText()))
		{
			er.deleteFromDB(prIdTF.getText());
			ur.deleteUser(prIdTF.getText());
			
			JOptionPane.showMessageDialog(this,"Deleted");
			
			prIdTF.setText("");
			prNameTF.setText("");
			prQuantityTF.setText("");
			prPriceTF.setText("");
			
			prIdTF.setEnabled(true);
			prNameTF.setEnabled(true);
			prQuantityTF.setEnabled(true);
			prPriceTF.setEnabled(true);
	
			loadBtn.setEnabled(true);
			insertBtn.setEnabled(true);
			updateBtn.setEnabled(false);
			deleteBtn.setEnabled(false);
			refreshBtn.setEnabled(false);
		}
		else if(command.equals(getAllBtn.getText()))
		{
			String data[][] = er.getAllProduct();
			String head[] = {"Id", "Name", "Av. Quantity", "Price"};
			
			panel.remove(prTableSP);
			
			prTable = new JTable(data,head);
			prTable.setEnabled(false);
			prTableSP = new JScrollPane(prTable);
			prTableSP.setBounds(350, 100, 400, 150);
			panel.add(prTableSP);
			
			panel.revalidate();
			panel.repaint();
			
		}
		else if(command.equals(backBtn.getText()))
		{
			EmployeeHome eh = new EmployeeHome(user);
			eh.setVisible(true);
			this.setVisible(false);
		}
		else{}
		
	}
}