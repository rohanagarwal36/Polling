<%@taglib prefix="s"  uri="/struts-tags"%>
<s:form action="AdminLogin">
    <s:textfield name="username" label="Username" />
    <s:password name="password" label="Password" />
    <s:submit value="Login" />
</s:form>
