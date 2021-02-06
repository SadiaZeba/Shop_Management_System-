package repository;

import java.lang.*;
import java.util.*;
import entity.*;
import interfaces.*;

public class ProductRepo implements IProductRepo
{
    DatabaseConnection dbc;

    public ProductRepo()
    {
        dbc = new DatabaseConnection();
    }

    public void insertInDB(Product p1)
    {
        String query = "INSERT INTO products VALUES ('"+p1.getId()+"','"+p1.getName()+"','"+p1.getQuantity()+"',"+p1.getPrice()+");";
        try
        {
            dbc.openConnection();
            dbc.st.execute(query);
            dbc.closeConnection();
        }
        catch(Exception ex){System.out.println(ex.getMessage());}
    }
    public void deleteFromDB(String productId)
    {
        String query = "DELETE from products WHERE id='"+productId+"';";
        try
        {
            dbc.openConnection();
            dbc.st.execute(query);
            dbc.closeConnection();
        }
        catch(Exception e){System.out.println(e.getMessage());}
    }
    public void updateInDB(Product p1)
    {
        String query = "UPDATE products SET Name='"+p1.getName()+"', quantity= '"+p1.getQuantity()+"', price = "+p1.getPrice()+" WHERE id='"+p1.getId()+"'";

        try
        {
            dbc.openConnection();
            dbc.st.executeUpdate(query);
            dbc.closeConnection();
        }
        catch(Exception ex){System.out.println(ex.getMessage());}
    }
    public Product searchProduct(String productId)
    {
        Product p1 = null;
        String query = "SELECT `Name`, `price`, `quantity` FROM `products` WHERE `id`='"+ productId + "'";
        try
        {
            System.out.println(query);
            dbc.openConnection();
            dbc.result = dbc.st.executeQuery(query);

            while(dbc.result.next())
            {

                String name = dbc.result.getString("name");
                double price = dbc.result.getDouble("price");
                Integer quantity = dbc.result.getInt("quantity");

                p1 = new Product();
                p1.setId(productId);
                p1.setName(name);
                p1.setQuantity(quantity);
                p1.setPrice(price);
            }

        }
        catch(Exception ex){System.out.println(ex.getMessage());}
        dbc.closeConnection();
        return p1;
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
    public String[][] getProductByName(String pname)
    {
        ArrayList<Product> ar = new ArrayList<Product>();
        String query = "SELECT * FROM products WHERE name LIKE '%"+ pname+"%';";

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




