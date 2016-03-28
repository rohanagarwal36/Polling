
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

public class VoterLoginAction extends ActionSupport implements SessionAware, ServletRequestAware{
    private String vid;
    private String password;
    HttpServletRequest request;
    SessionMap<String, Object> sessionMap;
    Session s = HibernateUtil.getSessionFactory().openSession();

    @Override
    public String execute(){
        try{
        List<Voter> list = s.createCriteria(Voter.class).add(Restrictions.eq("vid", vid)).list();
        if(list.isEmpty()){
            request.setAttribute("msg", "Wrong VID");
            return ERROR;
        }
        Voter v = list.get(0);
        if(v!=null){
            if(v.getPassword().equals(password)){
                if(v.getPhoto()==null){
                    s.close();
                    sessionMap.put("vid", vid);
                    request.setAttribute("msg", "You have not uploaded your photo yet");
                    return "nophoto";
                }else{
                    s.close();
                    sessionMap.put("vid", v.getVid());
                    request.setAttribute("vid", v.getVid());
                    return SUCCESS;
                }
            }else{
                s.close();
                request.setAttribute("msg", "Invalid Password");
                return ERROR;
            }
        }else{
            s.close();
            request.setAttribute("msg", "Invalid User");
            return ERROR;
        }
        }catch(Exception e){
            request.setAttribute("msg", "catched");
            s.close();
            return ERROR;
        }
    }
    
    @Override
    public void validate(){
        if(vid==null||vid.equals(""))
            addFieldError("vid", "Enter Voter ID");
        if(password==null||password.equals(""))
            addFieldError("password", "Enter Password");
    }
    
    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSession(Map<String, Object> map) {
        sessionMap = (SessionMap)map;
        sessionMap.put("vid", vid);
    }

    public void setServletRequest(HttpServletRequest hsr) {
        request=hsr;
    }
    
}
