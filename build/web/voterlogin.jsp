<%@taglib prefix="s" uri="/struts-tags" %>
<s:head/>
${requestScope.passchangemsg}
<br>
<s:form action="UserLogin">
    <s:textfield name="vid" label="Voter ID"/>
    <s:password name="password" label="Password"/>
    <s:submit value="Submit"/>
</s:form>
${requestScope.msg}