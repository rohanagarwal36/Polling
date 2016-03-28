<% if(session==null){
    response.sendRedirect("home.jsp");
}%>
<%@taglib  prefix="s"  uri="/struts-tags"%>
<%@page import="com.onps.pojos.Voter"%>
<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="com.onps.pojos.Questions"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.Session"%>
<%@page import="com.onps.pojos.HibernateUtil"%>
<% 
    Session s = HibernateUtil.getSessionFactory().openSession();
    List<Questions> list = s.createCriteria(Questions.class).add(Restrictions.eq("id", request.getAttribute("id"))).list();
    Questions q = list.get(0);
    List<Voter> list1 = s.createCriteria(Voter.class).add(Restrictions.eq("vid", q.getVid())).list();
    Voter v = list1.get(0);
    Integer id = (Integer)request.getAttribute("id");
    session.setAttribute("qid", id);
%>

Question:  <%= q.getQuestion() %><br>
Asked By:  <%= v.getFname() %> <%= v.getLname() %> <%= v.getVid() %><br><br>

<s:form action="ViewQuestionsAction2">
    <s:textarea name="answer" label="Write your answer" />
    <s:submit value="Submit"/>
    <s:token/>
</s:form>
