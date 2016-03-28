<%@taglib prefix="s" uri="/struts-tags" %>
<s:head/>
${requestScope.passchangemsg}
<s:form action="CandidateUserLogin">
    <s:textfield name="cid" label="Candidate ID"/>
    <s:password name="password" label="Password"/>
    <s:submit value="Submit"/>
</s:form>
${requestScope.msg}