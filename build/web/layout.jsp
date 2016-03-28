<% if(session==null){
    response.sendRedirect("home.jsp");
}%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>ONPS</title>
<style>
table{
	border-collapse: collapse;
	border-width: 0px;
}
table tr td{
}
</style>
</head>

<body>

<table width="100%" border="0" cellspacing="0px">
  <tr>
    <td colspan="2"><tiles:insertAttribute name="header" /></td>
  </tr>
  <tr>
    <td width="200px"><tiles:insertAttribute name="menu"/></td>
    <td width="68%"><tiles:insertAttribute name="body"/></td>
  </tr>
  <tr>
    <td colspan="2"><tiles:insertAttribute name="footer"/></td>
  </tr>
</table>

</body>
</html>





