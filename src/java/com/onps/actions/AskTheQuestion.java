
package com.onps.actions;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

public class AskTheQuestion extends ActionSupport implements SessionAware{
    public String cid;
    SessionMap<String, Object> sessionMap;
    
    @Override
    public String execute(){
        sessionMap.put("cid", cid);
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

    public void setSession(Map<String, Object> map) {
        sessionMap=(SessionMap)map;
    }
}
