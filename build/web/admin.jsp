<% if(session==null){
    response.sendRedirect("home.jsp");
}%>
Hello Admin! Welcome<hr><br>
${requestScope.msg}