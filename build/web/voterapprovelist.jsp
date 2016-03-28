<% if(session==null){
    response.sendRedirect("home.jsp");
}%>
<%@taglib prefix="s" uri="/struts-tags"%>
<s:form action="VoterVerifyInfo">
    <s:select list="list" label="Select an unapproved voter" listKey="vid" listValue="%{fname+' '+lname}" name="vid"/>
    <s:submit value="Submit"/>
</s:form>
