package interfaces;

import entity.*;

public interface ICustomerRepo
{
	public void insertInDB(Customer e);
	public void updateInDB(Customer e);
	public Customer searchCustomer(String id);
	public String[][] getAllCustomer();
}