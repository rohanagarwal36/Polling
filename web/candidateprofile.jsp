<% if(session==null){
    response.sendRedirect("home.jsp");
}%>
<%@page import="com.onps.pojos.Candidate"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="org.hibernate.Session"%>
<%@page import="com.onps.pojos.HibernateUtil"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%

    Session s = HibernateUtil.getSessionFactory().openSession();
    List<Candidate> list = s.createCriteria(Candidate.class).add(Restrictions.eq("cid", (String)session.getAttribute("cid"))).list();
    Candidate c = list.get(0);
    byte[] image = c.getPhoto();
    File f = new File("C:\\Users\\Rex\\Documents\\NetBeansProjects\\ONPS\\build\\web\\candidatephoto\\candidate"+c.getCid()+".jpg");
    FileOutputStream fos = new FileOutputStream(f);
    request.setAttribute("candidate", "candidate"+c.getCid()+".jpg");
    fos.write(image);
    fos.close();
    s.close();

%>
<img src="candidatephoto/${requestScope.candidate}" height="80" width="80" /><br><br>
Welcome <%= c.getFname() %> <%= c.getLname() %>
<br><br>
${requestScope.padded}
${requestScope.ansmsg}
${requestScope.noques}