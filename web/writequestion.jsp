<% if(session.getAttribute("vid")==null){
    response.sendRedirect("home.jsp");
}%>

<%@page import="com.onps.pojos.Candidate"%>
<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="java.util.List"%>
<%@page import="com.onps.pojos.Questions"%>
<%@page import="org.hibernate.Session"%>
<%@page import="com.onps.pojos.HibernateUtil"%>
<%@taglib  prefix="s" uri="/struts-tags" %>
<%
    Session s = HibernateUtil.getSessionFactory().openSession();
    List<Questions> list = s.createCriteria(Questions.class).add(Restrictions.eq("vid", session.getAttribute("vid"))).list();
    if(list.isEmpty()) {%>
        No. Questions Asked yet.<br><br>
    <% }else {
    for(int i=0; i<list.size(); i++) {
    List<Candidate> list1 = s.createCriteria(Candidate.class).add(Restrictions.eq("cid", list.get(i).getCid())).list(); %>
    Question to <%= list1.get(0).getFname() %> <%= list1.get(0).getLname() %>: <%= list.get(i).getQuestion() %><br><br>
    Answer: <% if(list.get(i).getAnswer()==null) {%>
            Not Answered Yet.<br><br>
            <% }else {%>
            <%= list.get(0).getAnswer() %><br><br>
    <% }%>
    <% }%>
    <% }%>
<s:form action="QuestionAsked">
    <s:textarea name="question" label="Type your question here and Submit" />
    <s:submit value="Submit"/>
    <s:token />
</s:form>