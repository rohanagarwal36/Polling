
package com.onps.actions;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

public class ViewResultAction extends ActionSupport implements SessionAware, ServletRequestAware{
    private String resultsby;
    HttpServletRequest request;
    SessionMap<String, Object> sessionMap;
    
    @Override
    public String execute(){
        if(resultsby.equals("Party")){
            return "party";
        }
        else{
            return "candidate";
        }
    }
    
    @Override
    public void validate(){
        if(resultsby==null||resultsby.equals(""))
            addFieldError("resultsby", "Select an option");
    }

    public String getResultsby() {
        return resultsby;
    }

    public void setResultsby(String resultsby) {
        this.resultsby = resultsby;
    }

    public void setSession(Map<String, Object> map) {
        sessionMap = (SessionMap)map;
    }

    public void setServletRequest(HttpServletRequest hsr) {
        request = hsr;
    }
    
}
