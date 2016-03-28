
package com.onps.actions;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

public class Logout extends ActionSupport implements SessionAware{
    SessionMap<String, Object> sessionMap;
    
    @Override
    public String execute(){
        sessionMap.clear();
        sessionMap.invalidate();
        return SUCCESS;
    }

    public void setSession(Map<String, Object> map) {
        sessionMap = (SessionMap)map;
    }
}
