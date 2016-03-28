<% if(session==null){
    response.sendRedirect("home.jsp");
}%>
<%@page import="com.onps.pojos.Candidate"%>
<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="java.util.List"%>
<%@page import="com.onps.actions.HibernateUtil"%>
<%@page import="org.hibernate.Session"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
    Session s = HibernateUtil.getSessionFactory().openSession();
    List<Candidate> list = s.createCriteria(Candidate.class).add(Restrictions.eq("cid", (String)session.getAttribute("cid"))).list();
    Candidate c = list.get(0);
    
%>
Following are the details of the voter.<hr><br>
Candidate ID:    <%= c.getCid() %><br><br>
Name:            <%= c.getFname() %> <%= c.getLname() %><br><br>
Father's Name:   <%= c.getFathersname() %><br><br>
Age:             <%= c.getAge() %><br><br>
Sex:             <%= c.getSex() %><br><br>
Email:           <%= c.getEmail() %><br><br>
Phone No.:       <%= c.getPhoneno() %><br><br>
Postal Address:  <%= c.getAddress() %>,
<%= c.getCstate() %>,
<%= c.getCity() %>,
<%= c.getPincode() %><br><br>
Party:           <%= c.getParty() %><br><br>
Constituency:    <%= c.getConstituency() %><br><br><br>
<a href="CandidateApproved">Approve</a><br><br>