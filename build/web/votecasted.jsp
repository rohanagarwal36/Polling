<% if(session==null){
    response.sendRedirect("home.jsp");
}%>
${requestScope.voter}
Congratulations..!! You have casted your Vote.
Keep visiting for more updates.