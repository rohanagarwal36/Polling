package com.onps.actions;

import com.mchange.v2.c3p0.impl.C3P0Defaults;
import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

public class AdminLogin extends ActionSupport implements ServletRequestAware {

    HttpServletRequest request;
    private String username;
    private String password;
    HttpSession session;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String execute() {
        session=request.getSession(true);
        if (username.equals("admin") && password.equals("onps")) {
            return SUCCESS;
        } else {
            request.setAttribute("msg", "Invalid username or password");
            return ERROR;
        }
    }
    
    @Override
    public void validate(){
        if(username==null||username.equals(""))
            addFieldError("username", "Enter Username");
        if(password==null||password.equals(""))
            addFieldError("password", "Enter Password");
    }

    public void setServletRequest(HttpServletRequest hsr) {
        request = hsr;
    }
}
