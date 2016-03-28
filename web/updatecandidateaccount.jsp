<% if(session==null){
    response.sendRedirect("home.jsp");
}%>
<%@taglib prefix="s" uri="/struts-tags" %>
<s:form action="FinalizeCandidateAccount">
    <s:password name="oldpassword" label="Enter your old password"/>
    <s:password name="newpassword" label="Enter your new password"/>
    <s:submit value="Submit"/>
</s:form>
${requestScope.msg}
