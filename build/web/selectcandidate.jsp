<% if(session==null){
    response.sendRedirect("home.jsp");
}%>
<%@taglib prefix="s" uri="/struts-tags" %>
<s:head/>
<s:form action="CandidateProfileView">
    <s:select list = "list" listValue="%{fname+' '+lname+', '+constituency}" listKey="cid" label="Select a Candidate" name="cid"/>
    <s:submit value="Submit"/>
</s:form>