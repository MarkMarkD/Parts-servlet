<%@ page import="java.util.List" %>
<%@ page import="app.entities.Part" %>
<%@ page import="app.servlets.ListServlet" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Locale" %><%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 23.02.2018
  Time: 18:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of parts</title>
</head>
<body>
<div>

</div>
    <!-- filter table -->
    <table border="1" width = 40% cellspacing="0" cellpadding="3" >
        <caption></caption>
        <form method="post">
            <ul>
                <li>
                    <label>PN
                        <input type="text" name="pnumber"><br />
                    </label>
                </li>

                <li>
                    <label>Part Name:
                        <input type="text" name="pname"><br />
                    </label>
                </li>

                <li>
                    <label>Vendor:
                        <input type="text" name="vendor" ><br />
                    </label>
                </li>

                <li>
                    <label>Qty:
                        <input type="text" name="qty"><br />
                    </label>
                </li>

                <li>
                    <label>Shipped:
                        after <input size = "6" type="text" name="shippedAfter">
                        before <input size = "6" type="text" name="shippedBefore"><br />
                    </label>
                </li>

                <li>
                    <label>Received:
                        after <input size = "6" type="text" name="receivedAfter">
                        before <input size = "6" type="text" name="receivedBefore"><br />
                    </label>
                </li>

                <button type="submit">Filter</button>
            </ul>
        </form>
    </table>

<div>
    <!-- output filtered parts table -->
    <table border="1" width = 40% cellspacing="0" cellpadding="3" >
        <caption></caption>
        <tr>
            <th>PN</th>
            <th>Part Name</th>
            <th>Vendor</th>
            <th>Qty</th>
            <th>Shipped</th>
            <th>Received</th>
        </tr>


        <%
            List<Part> parts = (List<Part>) request.getAttribute("listOfParts");
            String formattedShipped = null;     //Shipped date formatted in (MMM dd, yyyy)
            String formattedReceived = null;    //Received date formatted in (MMM dd, yyyy)


            if (parts != null && !parts.isEmpty()) {
                for (Part s : parts) {

                    //format output date
                    if (s.getShipped() != null) {
                        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy", Locale.US);
                        formattedShipped = sdf.format(s.getShipped());
                    }
                    else formattedShipped = "";
                    if (s.getReceived() != null) {
                        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy", Locale.US);
                        formattedReceived = sdf.format(s.getReceived());
                    }
                    else formattedReceived = "";

                    //fill output table
                    out.println("<tr><td>" + s.getPartNumber() + "</td>" +
                            "<td>" + s.getPartName() + "</td>" +
                            "<td>" + s.getVendor() + "</td>" +
                            "<td>" + s.getQty() + "</td>" +
                            "<td>" + formattedShipped + "</td>" +
                            "<td>" + formattedReceived + "</td></tr>");
                }
            } else out.println("<p>You don't have any parts!! Or check your DB connection</p>");
        %>
    </table>
</div>

</body>
</html>