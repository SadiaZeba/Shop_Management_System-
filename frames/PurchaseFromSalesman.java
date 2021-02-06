
package frames;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;


import repository.*;
import entity.*;
import interfaces.*;
public class PurchaseFromSalesman extends JFrame implements ActionListener
{
	private JLabel cId, cName, pId,pName, pQuantity,p_Price,totalAmount,available;
	private JTextField cIdTf,cNameTf,pIdTf,pNameTf,pQuantityTf,pPriceTf,availableTf,totalAmountTf;
	private JPanel panel;
	private JButton confirmBtn,backBtn,cIdBtn,pIdBtn,calPriceBtn;
	private ProductRepo pr;
	private CustomerRepo cr;
	private PurchaseRepo purRepo;
	private User user;

	public PurchaseFromSalesman( User user)
	{
		super("Shop Management System :: Purchase For Salesman");
		this.setSize(800,450);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		panel = new JPanel();
		panel.setLayout(null);

		cId = new JLabel("Customer Id");
		cId.setBounds(200,60,100,30);
		panel.add(cId);
        this.user = user;
		pr = new ProductRepo();
		cr = new CustomerRepo();
		purRepo = new PurchaseRepo();


		cName = new JLabel("Customer Name");
		cName.setBounds(200,95,100,30);
		panel.add(cName);



		pId = new JLabel("Product Id");
		pId.setBounds(200,130,100,30);
		panel.add(pId);

		pName = new JLabel("Product Name");
		pName.setBounds(200,165,100,30);
		panel.add(pName);

		p_Price= new JLabel("Price per unit");
		p_Price.setBounds(200,200,100,30);
		panel.add(p_Price);



		available= new JLabel("Available Quantity");
		available.setBounds(200,235,100,30);
		panel.add(available);
		
		pQuantity= new JLabel("Purchase Quantity");
		pQuantity.setBounds(200,270,100,30);
		panel.add(pQuantity);

		totalAmount= new JLabel("Total Amount");
		totalAmount.setBounds(200,305,100,30);
		panel.add(totalAmount);

		cIdTf = new JTextField();
		cIdTf.setBounds(300, 60, 160,30);
		panel.add(cIdTf);

		cIdBtn = new JButton("..");
		cIdBtn.setBounds(460,60,50,20);
		cIdBtn.addActionListener(this);
		panel.add(cIdBtn);


		cNameTf = new JTextField();
		cNameTf.setBounds(300, 95, 160,30);
		panel.add(cNameTf);
		//cNameTf.setEnabled(false);
		cNameTf.setEditable(false);

		pIdTf = new JTextField();
		pIdTf.setBounds(300, 130, 160,30);
		panel.add(pIdTf);

		pIdBtn = new JButton("...");
		pIdBtn.setBounds(460,130,50,20);
		pIdBtn.addActionListener(this);
		panel.add(pIdBtn);

		pNameTf = new JTextField();
		pNameTf.setBounds(300, 165, 160,30);
		panel.add(pNameTf);
		pNameTf.setEditable(false);

		availableTf = new JTextField();
		availableTf.setBounds(300, 235, 160,30);
		panel.add(availableTf);
		availableTf.setEditable(false);
		
		pQuantityTf = new JTextField();
		pQuantityTf.setBounds(300, 270, 160,30);
		panel.add(pQuantityTf);

		pPriceTf = new JTextField();
		pPriceTf.setBounds(300, 200, 160,30);
		panel.add(pPriceTf);
		pPriceTf.setEditable(false);

		totalAmountTf = new JTextField();
		totalAmountTf.setBounds(300, 305, 160,30);
		panel.add(totalAmountTf);

		calPriceBtn = new JButton("Calculate Price");
		calPriceBtn.setBounds(460,270,150,30);
		calPriceBtn.addActionListener(this);
		panel.add(calPriceBtn);

		confirmBtn = new JButton("Confirm");
		confirmBtn.setBounds(200,340,80,30);
		confirmBtn.addActionListener(this);
		panel.add(confirmBtn);

		backBtn = new JButton("	Back");
		backBtn.setBounds(280,340,80,30);
		backBtn.addActionListener(this);
		panel.add(backBtn);

		this.add(panel);
	}

	public void actionPerformed(ActionEvent ae)
	{
		String command = ae.getActionCommand();
		Customer c;
		Product p;
		if(command.equals(cIdBtn.getText()))
		{
			if(!cIdTf.getText().equals(""))
			{
				c= cr.searchCustomer(cIdTf.getText());
				if(c == null)
				{
					JOptionPane.showMessageDialog(this, "Please provide valid customer id.");
				}
				else
				{
					cNameTf.setText(c.getName());
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Please provide customer id");
			}
		}
		else if(command.equals(pIdBtn.getText()))
		{
			if(!pIdTf.getText().equals(""))
			{
				p= pr.searchProduct(pIdTf.getText());
				if(p == null)
				{
					JOptionPane.showMessageDialog(this, "Please provide valid product id.");
				}
				else
				{
					pNameTf.setText(p.getName());
					pPriceTf.setText(Double.toString(p.getPrice()));
					availableTf.setText(Integer.toString(p.getQuantity()));

				}
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Please provide product id");
			}
		}
		else if(command.equals(confirmBtn.getText()))
		{
			if(!totalAmountTf.getText().equals("") && !pIdTf.getText().equals("") && !pQuantityTf.getText().equals(""))
			{
				try
				{
					Date date = new Date();
					SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

					Purchase pur = new Purchase();
					pur.setDate(formatter.format(date));
					pur.setCId(cIdTf.getText());
					pur.setPId(pIdTf.getText());

					double price;
					int quantity;
					try
					{
						price = Double.valueOf(pPriceTf.getText()).doubleValue();
						quantity = Integer.valueOf(pQuantityTf.getText()).intValue();

						totalAmountTf.setText(String.valueOf(price*quantity));
					}
					catch (Exception e)
					{
						JOptionPane.showMessageDialog(this,e.getMessage());
					}


					pur.setQuantity(Integer.parseInt(pQuantityTf.getText()));
					pur.setAmount(Double.parseDouble(totalAmountTf.getText()));
					Product pd = pr.searchProduct(pur.getPId());

					int inventoryQuantity = pd.getQuantity() - pur.getQuantity();

					if(pur.getQuantity()<=0){
						JOptionPane.showMessageDialog(this,"Purchase quantity must not be zero or negative.");

					}
					else if(inventoryQuantity>0)
					{
						purRepo.insertInDB(pur);
						pd.setQuantity(inventoryQuantity);
						pr.updateInDB(pd);
						JOptionPane.showMessageDialog(this, "Purchase successful.");


					}
					else
					{
						JOptionPane.showMessageDialog(this,"Insufficient inventory!!!");
					}

				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(this,e.getMessage());
				}
			}
			else
				{
					JOptionPane.showMessageDialog(this,"Invalid Purchase");
				}
		}
		else if(command.equals(calPriceBtn.getText())){
			if(!pPriceTf.getText().equals("") && !pQuantityTf.getText().equals(""))
			{
				double price;
				int quantity;
				try
				{
					 price = Double.valueOf(pPriceTf.getText()).doubleValue();
					quantity = Integer.valueOf(pQuantityTf.getText()).intValue();

					if(quantity<=0){
						JOptionPane.showMessageDialog(this,"Purchase Quantity must be grater than zero");
					}
					totalAmountTf.setText(String.valueOf(price*quantity));
				}
				catch (Exception e)
				{
					JOptionPane.showMessageDialog(this,e.getMessage());
				}
			}
			else{
				JOptionPane.showMessageDialog(this,"Invalid Number!!!");
			}
		}
		else if(command.equals(backBtn.getText()))
		{
			EmployeeHome h = new EmployeeHome(user);
			h.setVisible(true);
			this.setVisible(false);
		}
	}

}