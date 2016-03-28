<%@taglib prefix="s" uri="/struts-tags" %>

User with following details is created.<hr>
Name:            <s:property value="fname"/> <s:property value="lname"/><br><br>
Father's Name:   <s:property value="fathersname"/><br><br>
Age:             <s:property value="age"/><br><br>
Sex:             <s:property value="sex"/><br><br>
Email:           <s:property value="email"/><br><br>
Phone No.:       <s:property value="phoneno"/><br><br>
Postal Address:  <s:property value="address"/>
<s:property value="state"/>
<s:property value="city"/>
<s:property value="pincode"/><br><br>
Constituency:    <s:property value="constituency" /><br>

<br>
Your unique voter ID: ${sessionScope.vid}<br>
<br>
Press next to complete your registration.<br>
<s:form action="VoterPhotoUpload">
    <s:submit value="Next" />
</s:form>
Note: Please remember your Voter ID as it will be used to login into user panel as well as to cast vote.
