<%@taglib  prefix="s"  uri="/struts-tags"%>
<s:head/>
<s:form action="PollingLoginAction">
    <s:textfield name="vid" label="Voter ID"/>
    <s:password name="password" label="Password"/>
    <s:password name="hashpass" label="Hash Password"/>
    <s:submit value="Submit"/>
</s:form>

${requestScope.msg}