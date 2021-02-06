
package frames;



import java.lang.*;
import javax.swing.*;
import java.awt.event.*;
import entity.*;
import repository.*;



public class AllProductFrame extends JFrame implements ActionListener
{
	private JTextField nameTf;
	private JLabel nameLable;
	private JButton getAllBtn,backBtn,nameBtn;
	private JPanel panel;
	private JTable pTable;
	private JScrollPane pTableSP;

	private User user;
	private ProductRepo pr;
	private UserRepo ur;
	


	public AllProductFrame(User user)
	{
		super("All Products");
		this.setSize(800,450);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.user = user;
		
		pr = new ProductRepo();
		ur = new UserRepo();
		
		panel = new JPanel();
		panel.setLayout(null);



		nameLable = new JLabel("Product Name");
		nameLable.setBounds(20,20,100,30);
		panel.add(nameLable);
		nameTf = new JTextField();
		nameTf.setBounds(120,20,100,30);
		panel.add(nameTf);

		nameBtn = new JButton("...");
		nameBtn.setBounds(220,20,50,20);
		nameBtn.addActionListener(this);
		panel.add(nameBtn);

		String data[][] = {{"", "", "", ""}};
		
		String head[] = {"Date", "Product","Quantity", "Total Amount"};
		
		pTable = new JTable(data,head);
		pTableSP = new JScrollPane(pTable);
		pTableSP.setBounds(350, 100, 400, 150);
		pTable.setEnabled(false);
		panel.add(pTableSP);

		getAllBtn = new JButton("Get All Product");
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
			String data[][] = pr.getAllProduct();
			String head[] = {"Id", "Name", "Av. Quantity", "Price"};
			
			panel.remove(pTableSP);
			
			pTable = new JTable(data,head);
			pTable.setEnabled(false);
			pTableSP = new JScrollPane(pTable);
			pTableSP.setBounds(350, 100, 400, 150);
			panel.add(pTableSP);
			
			panel.revalidate();
			panel.repaint();
			
		}

		else if(command.equals(nameBtn.getText()))
		{
			String data[][] = pr.getProductByName(nameTf.getText());
			String head[] = {"Id", "Name", "Av. Quantity", "Price"};

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
			CustomerHome customerHome = new CustomerHome(user);
			customerHome.setVisible(true);
			this.setVisible(false);
		}
		else{}
		
	}
}