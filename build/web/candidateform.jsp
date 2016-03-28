<%@taglib prefix="s" uri="/struts-tags" %>
Please fill the form below to register. All fields are mandatory. <hr>
<s:form action="CandidateSignup" enctype="multipart/form-data" method="post">
    <s:textfield name="fname" label="First Name" />
    <s:textfield name="lname" label="Last Name" />
    <s:textfield name="fathersname" label="Father's Name" />
    <s:radio list="{'Male', 'Female'}" name="sex" label="Sex"/>
    <s:textfield name="age" label="Age" />
    <s:textfield name="email" label="Email ID"/>
    <s:textfield name="phoneno" label="Phone Number"/>
    <s:textarea name="address" label="Postal Address"/>
    <s:textfield name="cstate" label="State"/>
    <s:textfield name="city" label="City"/>
    <s:textfield name="pincode" label="Pin Code"/>
    <s:textfield name="constituency" label="Constituency Code"/>
    <s:select list="{'Bhartiya Janta Party', 'Indian National Congress', 'Communist Party of India(Marxist)', 'Communist Party of India', 'Bahujan Samaj Party', 'Nationalist Congress Party'}" label="Select a party" name="party"/>
    <s:password name="password" label="Password" />
    <%--<s:password name="password2" label="Confirm Password"/>
    <s:file name="photo" label="Upload your Photo"/>--%>
    <s:submit name="submit" label="Submit" />
    <s:token />
</s:form><br><br>
For help on selection of constituency code, go to this <a href="Constituencies" target="_blank">link</a>.<br>
${requestScope.msg}