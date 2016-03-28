
package com.onps.actions;

import com.onps.pojos.Voter;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class UpdateVoterAccount extends ActionSupport implements SessionAware,ServletRequestAware{
    private String oldpassword;
    private String newpassword;
    SessionMap<String, Object> sessionMap;
    HttpServletRequest request;
    Session s = HibernateUtil.getSessionFactory().openSession();

    @Override
    public String execute(){
        try{
        String vid = (String)sessionMap.get("vid");
        System.out.println(vid);
        
        List<Voter> list = s.createCriteria(Voter.class).add(Restrictions.eq("vid", vid)).list();
        Voter v = list.get(0);
        if(v.getPassword().equals(oldpassword)){
            v.setPassword(newpassword);
            s.update(v);
            s.beginTransaction().commit();
            s.close();
            //sessionMap.invalidate();
            request.setAttribute("passchangemsg", "Password Successfully Changed");
            return SUCCESS;
        }
        else{
            //sessionMap.invalidate();
            s.close();
            request.setAttribute("passchangemsg", "Invalid Password");
            return ERROR;
        }
        }catch(Exception e){
            s.close();
            return ERROR;
        }
    }
    
    @Override
    public void validate(){
        if(oldpassword==null||oldpassword.equals(""))
            addFieldError("oldpassword", "Enter Password");
        if(newpassword==null||newpassword.equals(""))
            addFieldError("newpassword", "Enter Password");
    }
    
    public String getOldpassword() {
        return oldpassword;
    }

    public void setOldpassword(String oldpassword) {
        this.oldpassword = oldpassword;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    public void setSession(Map<String, Object> map) {
        sessionMap=(SessionMap<String, Object>)map;
    }

    public void setServletRequest(HttpServletRequest hsr) {
         request=hsr;
    }
    
}
