package repository;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


import repository.*;
import entity.*;
import interfaces.*;
public class CustomerRepo implements ICustomerRepo
{
    DatabaseConnection dbc;

    public CustomerRepo()
    {
        dbc = new DatabaseConnection();
    }

    public void insertInDB(Customer c)
    {
        String query = "INSERT INTO customer VALUES ('"+c.getId()+"','"+c.getName()+"','"+c.getAddress()+"');";
        try
        {
            System.out.println(query);
            dbc.openConnection();
            dbc.st.execute(query);
            dbc.closeConnection();
        }
        catch(Exception ex){System.out.println(ex.getMessage());}
    }

    public void updateInDB(Customer c)
    {
        String query = "UPDATE `customer` SET `Name`='"+c.getName()+"', `address` = '"+c.getAddress()+"' WHERE `id`='"+c.getId()+"'";
        System.out.println(query);

        try
        {
            dbc.openConnection();
            dbc.st.executeUpdate(query);
            dbc.closeConnection();
        }
        catch(Exception ex){System.out.println(ex.getMessage());}
    }
    public Customer searchCustomer(String id)
    {
        Customer c = null;
        String query = "SELECT `Name`, `id`, `address` FROM `customer` WHERE `id`='"+ id + "'";
        try
        {
            System.out.println(query);
            dbc.openConnection();
            dbc.result = dbc.st.executeQuery(query);

            while(dbc.result.next())
            {

                String name = dbc.result.getString("name");
                String cid = dbc.result.getString("id");
                String address= dbc.result.getString("address");

                c = new Customer();
                c.setId(cid);
                c.setName(name);
                c.setAddress(address);
            }

        }
        catch(Exception ex){System.out.println(ex.getMessage());}
        dbc.closeConnection();
        return c;
    }
    public String[][] getAllCustomer()
    {
        ArrayList<Customer> ar = new ArrayList<Customer>();
        String query = "SELECT * FROM customer;";

        try
        {
            dbc.openConnection();
            dbc.result = dbc.st.executeQuery(query);


            while(dbc.result.next())
            {
                String id = dbc.result.getString("id");
                String name = dbc.result.getString("name");
                String address=dbc.result.getString("address");

				Customer c = new Customer(id,name,address);
                ar.add(c);
            }
        }
        catch(Exception e){System.out.println(e.getMessage());}
        dbc.closeConnection();

        Object obj[] = ar.toArray();
        String data[][] = new String [ar.size()][4];

        for(int i=0; i<obj.length; i++)
        {
            data[i][0] = ((Customer)obj[i]).getId();
            data[i][1] = ((Customer)obj[i]).getName();
            data[i][2] = ((Customer)obj[i]).getAddress().toString();

        }
        return data;
    }

    public String[][] getCustomerByName(String cname)
    {
        ArrayList<Customer> ar = new ArrayList<Customer>();
        String query = "SELECT * FROM customer WHERE NAME LIKE '%"+ cname +"%';";

        try
        {
            dbc.openConnection();
            dbc.result = dbc.st.executeQuery(query);


            while(dbc.result.next())
            {
                String id = dbc.result.getString("id");
                String name = dbc.result.getString("name");
                String address=dbc.result.getString("address");

                Customer c = new Customer(id,name,address);
                ar.add(c);
            }
        }
        catch(Exception e){System.out.println(e.getMessage());}
        dbc.closeConnection();

        Object obj[] = ar.toArray();
        String data[][] = new String [ar.size()][4];

        for(int i=0; i<obj.length; i++)
        {
            data[i][0] = ((Customer)obj[i]).getId();
            data[i][1] = ((Customer)obj[i]).getName();
            data[i][2] = ((Customer)obj[i]).getAddress().toString();

        }
        return data;
    }


    public String[][] getCustomerById(String cid)
    {
        ArrayList<Customer> ar = new ArrayList<Customer>();
        String query = "SELECT * FROM customer WHERE id LIKE '%"+ cid +"%';";

        try
        {
            dbc.openConnection();
            dbc.result = dbc.st.executeQuery(query);


            while(dbc.result.next())
            {
                String id = dbc.result.getString("id");
                String name = dbc.result.getString("name");
                String address=dbc.result.getString("address");

                Customer c = new Customer(id,name,address);
                ar.add(c);
            }
        }
        catch(Exception e){System.out.println(e.getMessage());}
        dbc.closeConnection();

        Object obj[] = ar.toArray();
        String data[][] = new String [ar.size()][4];

        for(int i=0; i<obj.length; i++)
        {
            data[i][0] = ((Customer)obj[i]).getId();
            data[i][1] = ((Customer)obj[i]).getName();
            data[i][2] = ((Customer)obj[i]).getAddress().toString();

        }
        return data;
    }

        public String[][] getAllProduct()
        {
            ArrayList<Product> ar = new ArrayList<Product>();
            String query = "SELECT * FROM products;";

            try
            {
                dbc.openConnection();
                dbc.result = dbc.st.executeQuery(query);


                while(dbc.result.next())
                {
                    String productId = dbc.result.getString("id");
                    String productName = dbc.result.getString("name");
                    int availableQuantity = dbc.result.getInt("quantity");
                    double price = dbc.result.getDouble("price");

                    Product p1 = new Product(productId,productName,availableQuantity,price);
                    ar.add(p1);
                }
            }
            catch(Exception e){System.out.println(e.getMessage());}
            dbc.closeConnection();



            Object obj[] = ar.toArray();
            String data[][] = new String [ar.size()][4];

            for(int i=0; i<obj.length; i++)
            {
                data[i][0] = ((Product)obj[i]).getId();
                data[i][1] = ((Product)obj[i]).getName();
                data[i][2] = ((Product)obj[i]).getQuantity()+"";
                data[i][3] = (((Product)obj[i]).getPrice())+"";
            }
            return data;


    }
}




