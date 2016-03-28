<% if(session==null){
    response.sendRedirect("home.jsp");
}%>
<%@taglib prefix="s" uri="/struts-tags"%>
<s:form action="CandidateVerifyInfo">
    <s:select list="list" label="Select an unapproved candidate" listKey="cid" listValue="%{fname+' '+lname}" name="cid"/>
    <s:submit value="Select"/>
</s:form>