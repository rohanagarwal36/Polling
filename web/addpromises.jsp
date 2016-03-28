<% if(session==null){
    response.sendRedirect("home.jsp");
}%>
<%@taglib  prefix="s" uri="/struts-tags" %>
<s:form action="AddPromisesAction">
    <s:textarea name="promises" label="Add your promises" value="%{promises}"/>
    <s:submit value="Submit"/>
</s:form>