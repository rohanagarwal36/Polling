package com.onps.actions;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;

public class CandidateVerifyInfo extends ActionSupport implements ServletRequestAware {

    private String cid;
    HttpSession session;
    HttpServletRequest request;

    @Override
    public String execute() {
        session = request.getSession(false);
        session.setAttribute("cid", cid);
        return SUCCESS;
    }
    
    @Override
    public void validate(){
        if(cid==null||cid.equals(""))
            addFieldError("cid", "Select Candidate");
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public void setServletRequest(HttpServletRequest hsr) {
        request = hsr;
    }

}
