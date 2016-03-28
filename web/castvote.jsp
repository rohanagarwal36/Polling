<% if(session==null){
    response.sendRedirect("home.jsp");
}%>
<%@taglib prefix="s" uri="/struts-tags" %>
<s:head/>
<s:form action="MarkVoted">
    <s:select list = "list1" listValue="%{fname+' '+lname+', '+party}" listKey="cid" label="Cast your vote for : " name="cid" />
    <s:submit value="Cast Vote"/>
</s:form>