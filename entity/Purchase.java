package entity;

import java.util.*;

public class Purchase
{
private String pId;
private String pName;
private String date;
private String cId;
private String cName;
private int quantity;
private Double amount;


public Purchase(){}
public Purchase(String date, String pName, int quantity, double totalAmount)
  {
	this.pName = pName;
	this.quantity =quantity;
	this.amount = totalAmount;
	this.date =date;
  }
	
public void setPId(String pId)
{
	this.pId = pId;
}
public void setPName(String name)
{
	this.pName = pName;
}

public void setCId(String cid)
{
	this.cId = cid;
}
public void setCName(String Cname)
{
	this.cName = cName;
}

public void setDate(String date)
{
	this.date = date;
}
public void setAmount(Double amount)
{
	this.amount = amount;
}
public void setQuantity(int quantity)
{
	this.quantity = quantity;
}

	
public String getPId()
{
	return pId;
}
public String getPName()
{
	return pName;
}
public String getDate()
{
	return date;
}

public String getCId()
{
	return cId;
}
public String getCName()
{
	return cName;
}
public int getQuantity()
{
	return quantity;
}
public Double getAmount()
{
	return amount;
}

}