package com.onps.actions;

import com.onps.pojos.Candidate;
import com.onps.pojos.Voter;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class CandidateVerify extends ActionSupport implements ServletRequestAware{

    private List<Voter> list;
    private String cid;
    HttpServletRequest request;
    HttpSession session;
    Session s = HibernateUtil.getSessionFactory().openSession();
    
    @Override
    public String execute() {
        
        try{
        Candidate c= new Candidate();
        list = s.createCriteria(Candidate.class).add(Restrictions.or(Restrictions.eq("approved", 'n'),Restrictions.isNull("approved"))).list();
        s.close();
        return SUCCESS;
        }catch(Exception e){
            s.close();
            return ERROR;
        }
    }

    public String verify() {
        session=request.getSession(false);
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Candidate> l = s.createCriteria(Candidate.class).add(Restrictions.eq("cid", session.getAttribute("cid"))).list();
        Candidate c= new Candidate();
        c = l.get(0);
        c.setApproved('y');
        s.update(c);
        s.beginTransaction().commit();
        request.setAttribute("msg", "Candidate successfully approved.");
        return SUCCESS;
    }
    
   

    public List<Voter> getList() {
        return list;
    }

    public void setList(List<Voter> list) {
        this.list = list;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public void setServletRequest(HttpServletRequest hsr) {
       request=hsr;
    }
}
