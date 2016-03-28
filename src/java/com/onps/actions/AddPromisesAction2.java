/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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

/**
 *
 * @author Rex
 */
public class AddPromisesAction2 extends ActionSupport implements SessionAware, ServletRequestAware{
    private String promises;
    HttpServletRequest request;
    SessionMap<String, Object> sessionMap;
    Session s = HibernateUtil.getSessionFactory().openSession();
    
    public String beforeExecute(){
        try{
        //Session s = HibernateUtil.getSessionFactory().openSession();
        List<Candidate> list = s.createCriteria(Candidate.class).add(Restrictions.eq("cid", (String)sessionMap.get("cid"))).list();
        Candidate c = list.get(0);
        promises = c.getPromises();
        s.close();
        return SUCCESS;
        }catch(Exception e){
            s.close();
            return ERROR;
        }
    }

    public String getPromises() {
        return promises;
    }

    public void setPromises(String promises) {
        this.promises = promises;
    }

    public void setSession(Map<String, Object> map) {
        sessionMap = (SessionMap)map;
    }

    public void setServletRequest(HttpServletRequest hsr) {
        request = hsr;
    }
}
