<% if(session.getAttribute("vid")==null){
    response.sendRedirect("home.jsp");
}%>

<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="com.onps.pojos.Voter"%>
<%@page import="org.hibernate.Session"%>
<%@page import="com.onps.pojos.HibernateUtil"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%

    Session s = HibernateUtil.getSessionFactory().openSession();
    List<Voter> list = s.createCriteria(Voter.class).add(Restrictions.eq("vid", session.getAttribute("vid"))).list();
    if(list.isEmpty()){
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }else{
    Voter v = list.get(0);
    byte[] image = v.getPhoto();
    File f = new File("C:\\Users\\Rex\\Documents\\NetBeansProjects\\ONPS\\build\\web\\voterphoto\\voter"+v.getVid()+".jpg");
    FileOutputStream fos = new FileOutputStream(f);
    request.setAttribute("voter", "voter"+v.getVid()+".jpg");
    fos.write(image);
    fos.close();
    s.close();
    

%>
<img src="voterphoto/${requestScope.voter}" height="80" width="80" /><br><br>
Welcome <%= v.getFname() %> <%= v.getLname() %>  ${sessionScope.vid}<br><br>
 ${requestScope.quesmsg}
 <br>
 ${requestScope.msg}

<% }%>