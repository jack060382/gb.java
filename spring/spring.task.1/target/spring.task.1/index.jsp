<%@ page import="main.servlet.model.Product" %>
<%@ page import="java.util.List" %>
<html>
<body>
<h1>Products:</h1>

<ul>
<% for (Product value : (List<Product>) (request.getAttribute("products"))) { %>
<li>id: <%=value.getId()%>; title: <%=value.getTitle()%>; cost: <%=value.getCost()%></li>
<%}%>
</ul>
</body>
</html>
