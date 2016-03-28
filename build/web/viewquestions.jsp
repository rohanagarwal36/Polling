<% if(session==null){
    response.sendRedirect("home.jsp");
}%>
<%@taglib  prefix="s" uri="/struts-tags" %>
<s:form action="ViewQuestionsAction1">
    <s:iterator value="list" var="ques">
        <s:if test="#ques.answer==null||#ques.answer==''">
        <s:radio list="top" name="id" theme="simple" listValue="%{question}" listKey="%{id}" /> <br><br>
        </s:if>
        <s:else>
           
        </s:else>
    </s:iterator>
    <s:submit value="Answer This" />
    <s:token/>
</s:form>