package frames;

import entity.*;
import repository.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class ChangePasswordForCustomer extends JFrame implements ActionListener,MouseListener
{
	private JLabel idLabel, passLabel,rePassLabel;
	private JTextField idTF;
	private JPasswordField passPF,rePassPF;
	private JButton saveBtn, backBtn,back;

	private JPanel panel;
	private User usr;
	private UserRepo repo;

	public ChangePasswordForCustomer(User u)
	{
		super("Change Password-Customer");
		this.setSize(800,450);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		this.usr = u;
		panel = new JPanel();
		panel.setLayout(null);

		idLabel = new JLabel("Id ");
		idLabel.setBounds(180,120,100,30);
		panel.add(idLabel);

		idTF = new JTextField();
		idTF.setText(usr.getUserId());
		idTF.setBounds(300, 120, 100,30);
		panel.add(idTF);
		idTF.setEditable(false);

		passLabel = new JLabel("New Password: ");
		passLabel.setBounds(180, 170, 100, 30);
		panel.add(passLabel);

		passPF = new JPasswordField();
		passPF.setBounds(300,170, 100, 30);
		panel.add(passPF);

		rePassLabel = new JLabel("Re-Password: ");
		rePassLabel.setBounds(180, 220, 100, 30);
		panel.add(rePassLabel);

		rePassPF= new JPasswordField();
		rePassPF.setBounds(300,220, 100, 30);
		panel.add(rePassPF);

		saveBtn = new JButton("Save");
		saveBtn.setBounds(180, 280, 80, 30);
		saveBtn.addActionListener(this);
		panel.add(saveBtn);

		backBtn = new JButton("Log out");
		backBtn.setBounds(250, 280, 80, 30);
		backBtn.addActionListener(this);
		panel.add(backBtn);
		back= new JButton("Back");
		back.setBounds(250, 340, 80, 30);
		back.addActionListener(this);
		panel.add(back);


		this.add(panel);
	}

	public void actionPerformed(ActionEvent ae)
	{
		String command = ae.getActionCommand();

		if(command.equals(saveBtn.getText()))
		{
			if(!String.valueOf(passPF.getPassword()).equals("") && String.valueOf(passPF.getPassword()).equals(String.valueOf(rePassPF.getPassword()))){
				usr.setPassword(String.valueOf(passPF.getPassword()));
				repo = new UserRepo();
				repo.updateUser(usr);
				passPF.setText("");
				rePassPF.setText("");
				JOptionPane.showMessageDialog(this, "Password updated successfully.");

			}else{
				JOptionPane.showMessageDialog(this, "Password does not match");
			}

		}
		else if(command.equals(backBtn.getText()))
		{
			LoginFrame eh = new LoginFrame();
			eh.setVisible(true);
			this.setVisible(false);
		}

		else if(command.equals(back.getText()))
		{
			CustomerHome ch = new CustomerHome(usr);
			ch.setVisible(true);
			this.setVisible(false);
		}
		else{}

	}
	public void mouseClicked(MouseEvent me){}
	public void mousePressed(MouseEvent me)	{}
	public void mouseReleased(MouseEvent me){}
	public void mouseEntered(MouseEvent me){}
	public void mouseExited(MouseEvent me){}

}
