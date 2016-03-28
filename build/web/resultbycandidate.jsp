<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="com.onps.pojos.Votes"%>
<%@page import="java.util.List"%>
<%@page import="com.onps.pojos.Candidate"%>
<%@page import="org.hibernate.Session"%>
<%@page import="com.onps.pojos.HibernateUtil"%>
<%
    Session s = HibernateUtil.getSessionFactory().openSession();
    List<Candidate> candidates = s.createCriteria(Candidate.class).list();
%>
        

<table border="1">
    <thead>
        <tr>
            <th>Party</th>
            <th>Candidate</th>
            <th>No. of Votes</th>
        </tr>
    </thead>
    <tbody>
<%  for(int i=0; i<candidates.size(); i++){
        List<Votes> votesList = s.createCriteria(Votes.class).add(Restrictions.eq("cid", candidates.get(i).getCid())).list();
%>
        <tr>
            <td> <%= candidates.get(i).getParty() %> </td>
            <td> <%= candidates.get(i).getFname() %> <%= candidates.get(i).getLname() %> </td>
            <td> <%= votesList.size() %> </td>
        </tr>
        <% } %>
    </tbody>
</table>