<% if(session==null){
    response.sendRedirect("home.jsp");
}%>
<%@taglib  prefix="s" uri="/struts-tags" %>
Select a criteria to view results<br>
<s:form action="ViewResultAction">
    <s:radio name="resultsby" list="{'Party', 'Candidate'}"  />
    <s:submit value="Submit" />
</s:form>