
package frames;


import java.lang.*;
import javax.swing.*;
import java.awt.event.*;
import entity.*;
import repository.*;


public class AllCustomerFrame extends JFrame implements ActionListener
{   private JLabel nameLabel,idLabel;
    private JTextField nameTf,idTf;
	private JButton getAllBtn,backBtn,nameBtn,idBtn;
	private JPanel panel;
	private JTable pTable;
	private JScrollPane pTableSP;

	private User user;
	private UserRepo ur;
	private CustomerRepo cr;


	public AllCustomerFrame(User user)
	{
		super("Search Customer Frame");
		this.setSize(800,450);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.user = user;
		
		cr= new CustomerRepo();
		ur = new UserRepo();
		
		panel = new JPanel();
		panel.setLayout(null);
		
		
		
		String data[][] = {{"", "", "", ""}};
		
		String head[] = {"Id", "Name", "Address"};
		
		pTable = new JTable(data,head);
		pTableSP = new JScrollPane(pTable);
		pTableSP.setBounds(350, 100, 400, 150);
		pTable.setEnabled(false);
		panel.add(pTableSP);

		getAllBtn = new JButton("Get All Product");
		getAllBtn.setBounds(500,260,80,30);
		getAllBtn.addActionListener(this);
		panel.add(getAllBtn);

		nameLabel = new JLabel("Customer Name");
		nameLabel.setBounds(20,20,100,30);
		panel.add(nameLabel);

		nameTf = new JTextField();
		nameTf.setBounds(120,20,100,30);
		panel.add(nameTf);

		idLabel = new JLabel("Customer Id");
		idLabel.setBounds(20,50,100,30);
		panel.add(idLabel);

		idTf = new JTextField();
		idTf.setBounds(120,50,100,30);
		panel.add(idTf);

		nameBtn = new JButton("...");
		nameBtn.setBounds(220,20,50,20);
		nameBtn.addActionListener(this);
		panel.add(nameBtn);

		idBtn = new JButton("..");
		idBtn.setBounds(220,50,50,20);
		idBtn.addActionListener(this);
		panel.add(idBtn);
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
			String data[][] = cr.getAllCustomer();
			String head[] = {"Id", "Name", "Address"};
			
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
			EmployeeHome eh = new EmployeeHome(user);
			eh.setVisible(true);
			this.setVisible(false);
		}
		else if(command.equals(nameBtn.getText())){
			 String data[][] = cr.getCustomerByName(nameTf.getText());
			 String head[] = {"Id", "Name", "Address"};

			 panel.remove(pTableSP);

			 pTable = new JTable(data,head);
			 pTable.setEnabled(false);
			 pTableSP = new JScrollPane(pTable);
			 pTableSP.setBounds(350, 100, 400, 150);
			 panel.add(pTableSP);

			 panel.revalidate();
			 panel.repaint();

		 }
		 else if(command.equals(idBtn.getText()))
		 {
			 String data[][] = cr.getCustomerById(idTf.getText());
			 String head[] = {"Id", "Name", "Address"};

			 panel.remove(pTableSP);

			 pTable = new JTable(data,head);
			 pTable.setEnabled(false);
			 pTableSP = new JScrollPane(pTable);
			 pTableSP.setBounds(350, 100, 400, 150);
			 panel.add(pTableSP);

			 panel.revalidate();
			 panel.repaint();

		 }
		else{}
		
	}
}