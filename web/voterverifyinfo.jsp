<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="java.util.List"%>
<%@page import="com.onps.pojos.Voter"%>
<%@page import="com.onps.actions.HibernateUtil"%>
<%@page import="org.hibernate.Session"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
    Session s = HibernateUtil.getSessionFactory().openSession();
    List<Voter> list = s.createCriteria(Voter.class).add(Restrictions.eq("vid", (String)session.getAttribute("vid"))).list();
    Voter v = list.get(0);
    
%>
Following are the details of the voter.<hr><br>
Voter ID:    <%= v.getVid() %><br><br>
Name:            <%= v.getFname() %> <%= v.getLname() %><br><br>
Father's Name:   <%= v.getFathersname() %><br><br>
Age:             <%= v.getAge() %><br><br>
Sex:             <%= v.getSex() %><br><br>
Email:           <%= v.getEmail() %><br><br>
Phone No.:       <%= v.getPhoneno() %><br><br>
Postal Address:  <%= v.getAddress() %>,
<%= v.getVstate() %>,
<%= v.getCity() %>,
<%= v.getPincode() %><br><br>
Constituency:    <%= v.getConstituency() %><br><br><br>
<a href="VoterApproved">Approve</a><br><br>