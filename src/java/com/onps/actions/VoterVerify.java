package com.onps.actions;

import com.onps.pojos.Voter;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class VoterVerify extends ActionSupport implements ServletRequestAware{

    private List<Voter> list;
    private String vid;
    HttpServletRequest request;
    HttpSession session;
    Session s = HibernateUtil.getSessionFactory().openSession();
    
    @Override
    public String execute() {
        
        try{
        Voter v = new Voter();
        list = s.createCriteria(Voter.class).add(Restrictions.or(Restrictions.eq("approved", 'n'),Restrictions.isNull("approved"))).list();
        System.out.println(list);
        s.close();
        return SUCCESS;
        }catch(Exception e){
            s.close();
            return ERROR;
        }
    }

    public String verify() {
        try{
        session=request.getSession(false);
        //Session s = HibernateUtil.getSessionFactory().openSession();
        List<Voter> l = s.createCriteria(Voter.class).add(Restrictions.eq("vid", session.getAttribute("vid"))).list();
        Voter v = new Voter();
        v = l.get(0);
        v.setApproved('y');
        s.update(v);
        s.beginTransaction().commit();
        s.close();
        request.setAttribute("msg", "Voter successfully approved.");
        return SUCCESS;
        }catch(Exception e){
            s.close();
            return ERROR;
        }
    }
    
    
    

    public List<Voter> getList() {
        return list;
    }

    public void setList(List<Voter> list) {
        this.list = list;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public void setServletRequest(HttpServletRequest hsr) {
       request=hsr;
    }
}
