<%@page import="com.onps.pojos.Votes"%>
<%@page import="com.onps.pojos.HibernateUtil"%>
<%@page import="org.hibernate.Session"%>
<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="com.onps.pojos.Candidate"%>
<%@page import="java.util.List"%>
<%
    Session s = HibernateUtil.getSessionFactory().openSession();
    String parties[]=new String[]{"BJP","INC"};
%>
<table border="1">
    <thead>
        <tr>
            <th>Party</th>
            <th>Votes</th>
        </tr>
    </thead>
    <tbody>
        <%for (int i = 0; i < parties.length; i++) {
        List<Votes> sum=s.createCriteria(Votes.class).add(Restrictions.eq("party", parties[i])).list(); %>     
        <tr>
            <td><%= parties[i] %></td>
            <td><%= sum.size() %></td>
        </tr>
        <%}%>
    </tbody>
</table>
