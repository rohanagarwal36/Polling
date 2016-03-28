
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

public class CandidateLoginAction extends ActionSupport implements SessionAware, ServletRequestAware{
    private String cid;
    private String password;
    HttpServletRequest request;
    SessionMap<String, Object> sessionMap;
    Session s = HibernateUtil.getSessionFactory().openSession();

    @Override
    public String execute(){
        try{
        List<Candidate> list = s.createCriteria(Candidate.class).add(Restrictions.eq("cid", cid)).list();
        if(list.isEmpty()){
            request.setAttribute("msg", "Wrong CID");
            return ERROR;
        }
        Candidate c = list.get(0);
        if(c!=null){
            if(c.getPassword().equals(password)){
                if(c.getPhoto()==null){
                     sessionMap.put("cid", c.getCid());
                    request.setAttribute("msg", "You have not uploaded your photo yet");
                    s.close();
                    return "nophoto";
                }else{
                    sessionMap.put("cid", c.getCid());
                    request.setAttribute("cid", c.getCid());
                    s.close();
                    return SUCCESS;
                }
            }else{
                request.setAttribute("msg", "Invalid Password");
                s.close();
                return ERROR;
            }
        }else{
            request.setAttribute("msg", "Invalid User");
            s.close();
            return ERROR;
        }
        }catch(Exception e){
            s.close();
            return ERROR;
        }
        
    }
    
    @Override
    public void validate(){
        if(cid==null||cid.equals(""))
            addFieldError("cid", "Enter Candidate ID");
        if(password==null||password.equals(""))
            addFieldError("password", "Enter Password");
    }
    
    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSession(Map<String, Object> map) {
        sessionMap = (SessionMap)map;
    }

    public void setServletRequest(HttpServletRequest hsr) {
        request=hsr;
    }
    
    public String logout(){
        sessionMap.invalidate();
        return SUCCESS;
    }
    
    
}
