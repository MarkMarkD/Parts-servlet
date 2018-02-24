package app.servlets;

import app.entities.FilterValues;
import app.entities.Part;
import app.model.Model;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Дмитрий on 22.02.2018.
 */
public class ListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FilterValues filter = null;
        Model model = Model.getInstance();
        List<Part> parts = null;
        try {
            parts = model.getListOfPartsFromDB(filter);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        req.setAttribute("listOfParts", parts);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/list.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Get entity parameters from POST request
        String partName = req.getParameter("pname");
        String partNumber = req.getParameter("pnumber");
        String vendor = req.getParameter("vendor");
        String quantity = req.getParameter("qty");
        int qty = 0;
        String shpdAfter = req.getParameter("shippedAfter");
        Date shippedAfter = null;
        String shpdBefore = req.getParameter("shippedBefore");
        Date shippedBefore = null;
        String rcvdAfter = req.getParameter("receivedAfter");
        Date receivedAfter = null;
        String rcvdBefore = req.getParameter("receivedBefore");
        Date receivedBefore = null;

        //parse from String into Integer and Date
        try {
            if (!quantity.equals("")) qty = Integer.parseInt(quantity);
            if (!shpdAfter.equals("")) shippedAfter = parseMyDate(shpdAfter);
            if (!shpdBefore.equals("")) shippedBefore = parseMyDate(shpdBefore);
            if (!rcvdAfter.equals("")) receivedAfter = parseMyDate(rcvdAfter);
            if (!rcvdBefore.equals("")) receivedBefore = parseMyDate(rcvdBefore);
        } catch (Exception e) {e.printStackTrace();}

        //creates new entity FilterValues with all the filter values entered by user
        FilterValues filter = new FilterValues (partName, partNumber, vendor, qty, shippedAfter, shippedBefore,
                receivedAfter, receivedBefore);

        //send filter info to Model and get list of filtered parts
        Model model = Model.getInstance();
        List<Part> parts = null;
        try {
            parts = model.getListOfPartsFromDB(filter);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        req.setAttribute("listOfParts", parts);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/list.jsp");
        requestDispatcher.forward(req, resp);
    }

    private Date parseMyDate(String someDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy", Locale.US);
        try {
            return sdf.parse(someDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("Incorrect date entered");
        return null;
    }
}
