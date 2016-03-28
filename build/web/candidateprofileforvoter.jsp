<% if(session==null){
    response.sendRedirect("home.jsp");
}%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.File"%>
<%@page import="org.apache.struts2.ServletActionContext"%>
<%@page import="com.onps.pojos.Candidate"%>
<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="java.util.List"%>
<%@page import="com.onps.pojos.HibernateUtil"%>
<%@page import="org.hibernate.Session"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
    
    Session s = HibernateUtil.getSessionFactory().openSession();
    List<Candidate> list = s.createCriteria(Candidate.class).add(Restrictions.eq("cid", request.getAttribute("cid"))).list();
    Candidate c = list.get(0);
    byte[] image = c.getPhoto();
    File f = new File("C:\\Users\\Rex\\Documents\\NetBeansProjects\\ONPS\\build\\web\\candidatephoto\\candidate"+c.getCid()+".jpg");
    FileOutputStream fos = new FileOutputStream(f);
    request.setAttribute("candidate", "candidate"+c.getCid()+".jpg");
    request.setAttribute("promises", c.getPromises());
    fos.write(image);
    fos.close();
    s.close();
%>
<img src="candidatephoto/${requestScope.candidate}" height="80" width="80" /><br><br>Name: <%= c.getFname()+" "+c.getLname() %><br>
Age: <%= c.getAge() %><br>
Sex: <%= c.getSex()%><br>
From: <%= c.getCity()+" ,"+c.getCstate()%><br>
Constituency: <%= c.getConstituency()%><br>
<br>
Promises: <% if(c.getPromises()==null) { %>
    No promises added yet.
<%} else{%>
<%= c.getPromises() %>
<%}%>