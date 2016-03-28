
package com.onps.actions;

import com.onps.pojos.Candidate;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class UpdateCandidateAccount extends ActionSupport implements SessionAware, ServletRequestAware{
    private String oldpassword;
    private String newpassword;
    Session s = HibernateUtil.getSessionFactory().openSession();
    HttpServletRequest request;
    SessionMap<String, Object> sessionMap;

    @Override
    public String execute(){
        //try{
        String cid = (String)sessionMap.get("cid");
        System.out.println(cid);
        
        List<Candidate> list = s.createCriteria(Candidate.class).add(Restrictions.eq("cid", cid)).list();
        Candidate v = list.get(0);
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
//        }catch(Exception e){
//            s.close();
//            return ERROR;
//        }
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
        sessionMap = (SessionMap)map;
    }

    public void setServletRequest(HttpServletRequest hsr) {
        request = hsr;
    }
    
}
