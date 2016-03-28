
package com.onps.actions;

import com.onps.pojos.Candidate;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class AddPromisesAction extends ActionSupport implements SessionAware, ServletRequestAware{
    private String promises;
    HttpServletRequest request;
    SessionMap<String, Object> sessionMap;
    Session s = HibernateUtil.getSessionFactory().openSession();

    @Override
    public String execute(){
        try{
        List<Candidate> list = s.createCriteria(Candidate.class).add(Restrictions.eq("cid", (String)sessionMap.get("cid"))).list();
        Candidate c = list.get(0);
        c.setPromises(promises);
        s.update(c);
        s.beginTransaction().commit();
        s.close();
        request.setAttribute("padded", "Your Promises were added");
        return SUCCESS;
        }catch(Exception e){
            s.close();
            return ERROR;
        }
    }
    
    public String beforeExecute(){
        try{
        //Session s = HibernateUtil.getSessionFactory().openSession();
        List<Candidate> list = s.createCriteria(Candidate.class).add(Restrictions.eq("cid", (String)sessionMap.get("cid"))).list();
        Candidate c = list.get(0);
        promises = c.getPromises();
        s.close();
        return SUCCESS;
        }catch(Exception e){
            s.close();
            return ERROR;
        }
    }
    
    @Override
    public void validate(){
        if(promises==null||promises.equals(""))
            addFieldError("promises", "Enter Promises");
    }
    
    public String getPromises() {
        return promises;
    }

    public void setPromises(String promises) {
        this.promises = promises;
    }

    public void setSession(Map<String, Object> map) {
        sessionMap = (SessionMap)map;
    }

    public void setServletRequest(HttpServletRequest hsr) {
        request=hsr;
    }
    
}
