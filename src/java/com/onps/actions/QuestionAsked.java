package com.onps.actions;

import com.onps.pojos.Questions;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Session;

public class QuestionAsked extends ActionSupport implements SessionAware, ServletRequestAware {

    public String question;
    SessionMap<String, Object> sessionMap;
    HttpServletRequest request;
    Session s = HibernateUtil.getSessionFactory().openSession();

    @Override
    public String execute() {
        try{
        if (!sessionMap.isEmpty()) {
            
            Questions q = new Questions();
            q.setQuestion(question);
            q.setCid((String) sessionMap.get("cid"));
            q.setVid((String) sessionMap.get("vid"));
            s.save(q);
            s.beginTransaction().commit();
            s.close();
            request.setAttribute("quesmsg", "Your Question is submitted. Visit the ask a question link after sometime to see what answer the candidate has given for your question.");
            return SUCCESS;
        } else {
            request.setAttribute("quesmsg", "You must login to ask the question");
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
        if(question==null||question.equals(""))
            addFieldError("question", "Enter a Question");
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setSession(Map<String, Object> map) {
        sessionMap = (SessionMap) map;
    }

    public void setServletRequest(HttpServletRequest hsr) {
        request = hsr;
    }

}
