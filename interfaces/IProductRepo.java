package interfaces;

import java.lang.*;

import entity.*;

public interface IProductRepo
{
	public void insertInDB(Product e);
	public void deleteFromDB(String productId);
	public void updateInDB(Product e);
	public Product searchProduct(String productId);
	public String[][] getAllProduct();
}