package com.onps.actions;

import com.onps.pojos.Voter;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class HashPassword extends ActionSupport implements ServletRequestAware, SessionAware {

    SessionMap<String, Object> sessionMap;
    HttpServletRequest request;
    Session session = HibernateUtil.getSessionFactory().openSession();

    @Override
    public String execute() {
        try{
        List<Voter> list = session.createCriteria(Voter.class).add(Restrictions.eq("vid", sessionMap.get("vid"))).list();
        Voter v = list.get(0);
        String pass;
        if (v.getHashpass() == null) {
            int n = (int) (Math.random() * 100);
            int m = (int) (Math.random() * 100);
            String s = "&Qaz#Ws&aXe#Dcr$fVt@gbY&hAnu#jMik$loPqAw@ea#SdzXc#rFv&bGtay$hNmj@uIkol@pqS&aWeaf@drTahGy@UkjIo@lpz$Cx%vbMn";
            pass = s.substring(n, n + 6);
            pass = (n % 10) + pass;
            pass = pass.substring(0, 3) + (n / 10) + pass.substring(3);
            pass = pass.substring(0, 4) + (m / 10) + pass.substring(4, 6) + (m % 10) + pass.substring(6);
            v.setHashpass(pass);
            session.update(v);
            session.beginTransaction().commit();
            session.close();
        } else {
            pass = v.getHashpass();
        }
        pass = "Your hash password is " + pass;
        request.setAttribute("msg", "Please do not share your hash password with anyone. On the day of voting, login with your hash password to cast your vote.");
        request.setAttribute("quesmsg", pass);
        return Action.SUCCESS;
        }catch(Exception e){
            session.close();
            return ERROR;
        }
    }

    public void setServletRequest(HttpServletRequest hsr) {
        request = hsr;
    }

    public void setSession(Map<String, Object> map) {
        sessionMap = (SessionMap) map;
    }
}
