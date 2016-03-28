
package com.onps.actions;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;

public class VoterVerifyInfo extends ActionSupport implements ServletRequestAware{
    private String vid;
    HttpSession session;
    HttpServletRequest request;
    
    @Override
    public String execute(){
        session=request.getSession(false);
        session.setAttribute("vid", vid);
        return SUCCESS;   
    }
    
    @Override
    public void validate(){
        if(vid==null||vid.equals(""))
            addFieldError("vid", "Select Voter");
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
