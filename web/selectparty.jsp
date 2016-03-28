<% if(session==null){
    response.sendRedirect("home.jsp");
}%>
<%@taglib prefix="s" uri="/struts-tags" %>
<s:form action="ListOfCandidates">
    <s:select list="{'Bhartiya Janta Party', 'Indian National Congress', 'Communist Party of India(Marxist)', 'Communist Party of India', 'Bahujan Samaj Party', 'Nationalist Congress Party'}" label="Select a party" name="party"/>
    <s:submit value="Submit"/>
</s:form>