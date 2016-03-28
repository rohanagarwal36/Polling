<% if(session==null){
    response.sendRedirect("home.jsp");
}%>
<%@taglib prefix="s" uri="/struts-tags" %>
Edit your profile.<hr><br>
<s:form action="CandidateProfileEditAction" enctype="multipart/form-data" method="post">
    <s:textfield name="email" label="Email ID" value="%{email}"/>
    <s:textfield name="phoneno" label="Phone Number" value="%{phoneno}"/>
    <s:textarea name="address" label="Postal Address" value="%{address}"/>
    <s:textfield name="cstate" label="State" value="%{cstate}"/>
    <s:textfield name="city" label="City" value="%{city}"/>
    <s:textfield name="pincode" label="Pin Code" value="%{pincode}"/>
    <s:submit name="submit" label="Submit" />
    <s:token />
</s:form>