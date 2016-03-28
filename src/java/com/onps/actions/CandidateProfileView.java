
package com.onps.actions;

import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

public class CandidateProfileView extends ActionSupport implements ServletRequestAware{
    private String cid;
   
    HttpServletRequest request;
    
    @Override
    public String execute(){
        request.setAttribute("cid", cid);
        return SUCCESS;   
    }
    
    @Override
    public void validate(){
        if(cid==null||cid.equals(""))
            addFieldError("cid", "Select a Candidate");
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
