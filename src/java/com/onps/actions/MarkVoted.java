
package com.onps.actions;

import com.onps.pojos.Candidate;
import com.onps.pojos.Voter;
import com.onps.pojos.Votes;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class MarkVoted extends ActionSupport implements ServletRequestAware{
    HttpServletRequest request;
    private String cid;
    Session s = HibernateUtil.getSessionFactory().openSession();
        Session s1 = HibernateUtil.getSessionFactory().openSession();

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
    
    @Override
    public String execute(){
        try{
        HttpSession session = request.getSession(false);
        
        List<Candidate> list = s.createCriteria(Candidate.class).add(Restrictions.eq("cid", cid)).list();
        Candidate c = list.get(0);
        Votes vs = new Votes();
        vs.setCid(cid);
        vs.setVid((String)session.getAttribute("vid"));
        vs.setParty(c.getParty());
        vs.setConstituency(c.getConstituency());
        s.save(vs);
        s.beginTransaction().commit();
        List<Voter> list1 = s1.createCriteria(Voter.class).add(Restrictions.eq("vid", (String)session.getAttribute("vid"))).list();
        Voter v = list1.get(0);
        v.setHasvoted("yes");
        s1.update(v);
        s1.beginTransaction().commit();
        request.setAttribute("voter", v.getFname()+" "+v.getLname());
        session.invalidate();
        s.close();
        s1.close();
        return SUCCESS;
        }catch(Exception e ){
            s.close();
            s1.close();
            return ERROR;
        }
    }

    public void setServletRequest(HttpServletRequest hsr) {
        request=hsr;
    }
}
