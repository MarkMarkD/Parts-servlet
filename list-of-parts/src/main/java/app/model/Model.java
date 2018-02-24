package app.model;

import app.entities.FilterValues;
import app.entities.Part;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Дмитрий on 23.02.2018.
 */
public class Model {

    private static Model instance = new Model();

    private List<Part> listOfParts;   //промежуточный массив между ДБ и view

    public static Model getInstance() {
        return instance;
    }

    private Model() {
        listOfParts = new ArrayList<Part>();
    }

    //Get filtered positions from DB (if filter == null then get all positions)
    public List<Part> getListOfPartsFromDB(FilterValues filter) throws SQLException, ClassNotFoundException {
        listOfParts.clear();
        ResultSet resultSet = null;
        Connection conn = DBConnUtils.getConnection();
        Statement statement = conn.createStatement();;
        String query = null;
        System.out.println("Connection to db established from method getListOfPartsFromDB");

        if (filter == null) {
            query = "SELECT * FROM public.tableofparts";
        }
        //create query string and apply all the filter fields entered by user
        else {
            query = "SELECT * FROM public.tableofparts WHERE qty >= '0'";
            if (filter.getPartName() != null) {
                query = query + " AND pname LIKE '%" + filter.getPartName() + "%'";
            }
            if (filter.getPartNumber() != null) {
                query = query + " AND pnumber LIKE '%" + filter.getPartNumber() + "%'";
            }
            if (filter.getVendor() != null) {
                query = query + " AND vendor LIKE '%" + filter.getVendor() + "%'";
            }
            if (filter.getQty() >= 0) {
                query = query + " AND qty >= '" + filter.getQty() + "'";
            }
            if (filter.getShippedAfter() != null) {
                query = query + " AND shipped > '" + filter.getShippedAfter() + "'";
            }
            if (filter.getShippedBefore() != null) {
                query = query + " AND shipped < '" + filter.getShippedBefore() + "'";
            }
            if (filter.getReceivedAfter() != null) {
                query = query + " AND received > '" + filter.getReceivedAfter() + "'";
            }
            if (filter.getReceivedBefore() != null) {
                query = query + " AND received < '" + filter.getReceivedBefore() + "'";
            }
            //query = "SELECT * FROM public.tableofparts WHERE pnumber LIKE '%01%' AND pname LIKE '%LCD%'";
        }

        resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            String partName = resultSet.getString("Pname");
            String partNumber = resultSet.getString("Pnumber");
            String vendor = resultSet.getString("Vendor");
            int qty = resultSet.getInt("Qty");
            Date shipped = resultSet.getDate("Shipped");
            Date received = resultSet.getDate("Received");
            Part part = new Part(partName, partNumber, vendor, qty, shipped, received);
            listOfParts.add(part);
        }

        DBConnUtils.closeQuietly(conn);
        System.out.println("Connection to db closed from method getListOfPartsFromDB");
        return listOfParts;
    }

}
