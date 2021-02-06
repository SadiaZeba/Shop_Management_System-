package repository;

import java.lang.*;


import java.util.*;
import entity.*;
import interfaces.*;

public class PurchaseRepo implements IPurchaseRepo {
    DatabaseConnection dbc;

    public PurchaseRepo() {
        dbc = new DatabaseConnection();
    }

    public void insertInDB(Purchase p) {
        String query = "INSERT INTO `purchase` VALUES ('" + p.getDate() + "','" + p.getCId() + "','" + p.getPId() + "','" + p.getQuantity() + "','" + p.getAmount() + "')";
        try {
            System.out.println(query);
            dbc.openConnection();
            dbc.st.execute(query);
            dbc.closeConnection();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String[][] getAllPUrchaseByCustomer(String name)
    {
        ArrayList<Purchase> ar = new ArrayList<Purchase>();
        String query = "SELECT `date`,`name`,`purchase`.`quantity`,`amount` from `purchase` INNER JOIN `products` ON `products`.`id` = `purchase`.`p_id` WHERE c_id = '"+name+"';";

        try
        {
            System.out.println(query);
            dbc.openConnection();
            dbc.result = dbc.st.executeQuery(query);


            while(dbc.result.next())
            {
                String date = dbc.result.getString("date");
                String productName = dbc.result.getString("name");
                int availableQuantity = dbc.result.getInt("quantity");
                double price = dbc.result.getDouble("amount");

                Purchase p1 = new Purchase(date,productName,availableQuantity,price);
                ar.add(p1);
            }
        }
        catch(Exception e){System.out.println(e.getMessage());}
        dbc.closeConnection();


        Object obj[] = ar.toArray();
        String data[][] = new String [ar.size()][4];

        for(int i=0; i<obj.length; i++)
        {
            data[i][0] = ((Purchase)obj[i]).getDate();
            data[i][1] = ((Purchase)obj[i]).getPName();
            data[i][2] = ((Purchase)obj[i]).getQuantity()+"";
            data[i][3] = (((Purchase)obj[i]).getAmount())+"";
        }
        return data;
    }
}
