<%@ page import="java.util.List" %>
<%@ page import="app.entities.Part" %>
<%@ page import="app.servlets.ListServlet" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Locale" %>
<%@ page import="app.entities.FilterValues" %><%--
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
        <%
            String pname = (String) request.getAttribute("pname");
            String pnumber = (String) request.getAttribute("pnumber");
            String vendor = (String) request.getAttribute("vendor");
            String qty = (String) request.getAttribute("qty");
            String shippedAfter = (String) request.getAttribute("shippedAfter");
            String shippedBefore = (String) request.getAttribute("shippedBefore");
            String receivedAfter = (String) request.getAttribute("receivedAfter");
            String receivedBefore = (String) request.getAttribute("receivedBefore");
            String sortingOrder = (String) request.getAttribute("sortingOrder");
            if (sortingOrder == null) sortingOrder = "";
        %>

        <!-- filter-->
        <form id = "01" method="post">
            <ul>
                <li>
                    <label>PN
                        <%
                            if ((pnumber != null) && (!pnumber.equals("null"))) {
                                out.println("<input value = \"" + pnumber + "\" type=\"text\" name=\"pnumber\"><br />");
                            }
                            else {
                                out.println("<input value = \"\" type=\"text\" name=\"pnumber\"><br />");
                            }
                        %>
                    </label>
                </li>

                <li>
                    <label>Part Name:
                        <%
                            if ((pname != null) && (!pname.equals("null"))) {
                                out.println("<input value = \"" + pname + "\" type=\"text\" name=\"pname\"><br />");
                            }
                            else {
                                out.println("<input value = \"\" type=\"text\" name=\"pname\"><br />");
                            }
                        %>
                    </label>
                </li>

                <li>
                    <label>Vendor:
                        <%
                            if ((vendor != null) && (!vendor.equals("null"))) {
                                out.println("<input value = \"" + vendor + "\" type=\"text\" name=\"vendor\"><br />");
                            }
                            else {
                                out.println("<input value = \"\" type=\"text\" name=\"vendor\"><br />");
                            }
                        %>
                    </label>
                </li>

                <li>
                    <label>Qty:
                        <%
                            if ((qty != null) && (!qty.equals("null"))) {
                                out.println("<input value = \"" + qty + "\" type=\"text\" name=\"qty\"><br />");
                            }
                            else {
                                out.println("<input value = \"\" type=\"text\" name=\"qty\"><br />");
                            }
                        %>
                    </label>
                </li>

                <li>
                    <label>Shipped:
                        <%
                            if ((shippedAfter != null) && (!shippedAfter.equals("null"))) {
                                out.println("after <input size = \"7\" value = \"" + shippedAfter + "\" type=\"text\" name=\"shippedAfter\">");
                            }
                            else {
                                out.println("after <input  size = \"7\" value = \"\" type=\"text\" name=\"shippedAfter\">");
                            }
                            if ((shippedBefore != null) && (!shippedBefore.equals("null"))) {
                                out.println("before<input size = \"7\" value = \"" + shippedBefore + "\" type = \"text\" name = \"shippedBefore\" ><br / >");
                            }
                            else {
                                out.println("before <input  size = \"7\" value = \"\" type=\"text\" name=\"shippedBefore\">");
                            }
                        %>
                    </label>
                </li>

                <li>
                    <label>Received:
                        <%
                            if ((receivedAfter != null) && (!receivedAfter.equals("null")))  {
                                out.println("after <input size = \"7\" value = \"" + receivedAfter + "\" type=\"text\" name=\"receivedAfter\">");
                            }
                            else {
                                out.println("after <input  size = \"7\" value = \"\" type=\"text\" name=\"receivedAfter\">");
                            }
                            if ((receivedBefore != null) && (!receivedBefore.equals("null"))) {
                                out.println("before<input size = \"7\" value = \"" + receivedBefore + "\" type = \"text\" name = \"receivedBefore\" ><br / >");
                            }
                            else {
                                out.println("before <input  size = \"7\" value = \"\" type=\"text\" name=\"receivedBefore\">");
                            }
                        %>
                    </label>
                </li>
            </ul>
            <button type="submit">Filter</button>
        </form>
    </table>
<div>

    <!-- output filtered parts table -->
    <table border="1" width = 40% cellspacing="0" cellpadding="3" >
        <caption></caption>
        <tr>
                <!--sort by column-->
                <th><button type="submit" form = "01" name="sortByColumn" value="1">PN</button></th>
                <th><button type="submit" form = "01" name="sortByColumn" value="2">Part Name</button></th>
                <th><button type="submit" form = "01" name="sortByColumn" value="3">Vendor</button></th>
                <th><button type="submit" form = "01" name="sortByColumn" value="4">Qty</button></th>
                <th><button type="submit" form = "01" name="sortByColumn" value="5">Shipped</button></th>
                <th><button type="submit" form = "01" name="sortByColumn" value="6">Received</button></th>
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
            } else out.println("<p>No results matching your request</p>");
        %>
    </table>
</div>

</body>
</html>