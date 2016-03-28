<%@taglib prefix="s" uri="/struts-tags" %>
<s:head/>
Upload your passport size Photo.
<s:form action="FileUpload" method="post" enctype="multipart/form-data">
    <s:file name="file"/>
    <s:submit value="Upload Photo"/>
    <s:token/>
</s:form>