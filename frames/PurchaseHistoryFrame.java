
package frames;



import java.lang.*;
import javax.swing.*;
import java.awt.event.*;


import entity.*;
import repository.*;


public class PurchaseHistoryFrame extends JFrame implements ActionListener
{

	private JButton getAllBtn,backBtn;
	private JPanel panel;
	private JTable pTable;
	private JScrollPane pTableSP;

	private User user;
	private PurchaseRepo pr;
	private UserRepo ur;
	
	CustomerRepo cr = new CustomerRepo();
	Customer c = new Customer();


	public PurchaseHistoryFrame(User user)
	{
		super("Customer Purchase History Frame");
		this.setSize(800,450);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.user = user;
		
		pr = new PurchaseRepo();
		ur = new UserRepo();
		
		panel = new JPanel();
		panel.setLayout(null);
		
		
		
		String data[][] = {{"", "", "", ""}};
		
		String head[] = {"Date", "Product","Quantity", "Total Amount"};
		
		pTable = new JTable(data,head);
		pTableSP = new JScrollPane(pTable);
		pTableSP.setBounds(350, 100, 400, 150);
		pTable.setEnabled(false);
		panel.add(pTableSP);

		getAllBtn = new JButton("Load All");
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

		 if(command.equals(getAllBtn.getText()))
		{
			String data[][] = pr.getAllPUrchaseByCustomer(user.getUserId());
			String head[] = {"Date", "Product","Quantity", "Total Amount"};
			
			panel.remove(pTableSP);
			
			pTable = new JTable(data,head);
			pTable.setEnabled(false);
			pTableSP = new JScrollPane(pTable);
			pTableSP.setBounds(350, 100, 400, 150);
			panel.add(pTableSP);
			
			panel.revalidate();
			panel.repaint();
			
		}
		else if(command.equals(backBtn.getText()))
		{
			CustomerHome ch = new CustomerHome(user);
			ch.setVisible(true);
			this.setVisible(false);
		}
		else{}
		
	}
}