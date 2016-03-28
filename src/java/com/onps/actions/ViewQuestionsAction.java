
package com.onps.actions;

import com.onps.pojos.Questions;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class ViewQuestionsAction extends ActionSupport implements SessionAware, ServletRequestAware{
    private String question;
    private Integer id;
    private String answer;
    private Questions ques;
    private List<Questions> list = new ArrayList<Questions>();
    SessionMap<String, Object> sessionMap;
    HttpServletRequest request;
    Session s = HibernateUtil.getSessionFactory().openSession();
    
    @Override
    public String execute(){
        try{
        list = s.createCriteria(Questions.class).add(Restrictions.eq("cid", (String)sessionMap.get("cid"))).list();
        if(list.isEmpty()){
            s.close();
            request.setAttribute("noques", "There are no questions for you.");
            return ERROR;
        }else{
            s.close();
            return SUCCESS;
        }
        }catch(Exception e){
            s.close();
            return ERROR;
        }
    }
    
    public String afterExecute1(){
        request.setAttribute("id", id);
        return SUCCESS;
    }
    
    public String afterExecute2(){
        //Session s = HibernateUtil.getSessionFactory().openSession();
        try{
        List<Questions> list1 = s.createCriteria(Questions.class).add(Restrictions.eq("id", sessionMap.get("qid"))).list();
        Questions q = list1.get(0);
        q.setAnswer(answer);
        s.update(q);
        s.beginTransaction().commit();
        s.close();
        request.setAttribute("ansmsg", "You have answered a quesion.");
        return SUCCESS;
        }catch(Exception e){
            s.close();
            return ERROR;
        }
    }
    
    public List<Questions> getList() {
        return list;
    }

    public void setList(List<Questions> list) {
        this.list = list;
    }
    
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
     public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
    public Questions getQues() {
        return ques;
    }

    public void setQues(Questions ques) {
        this.ques = ques;
    }

    public void setSession(Map<String, Object> map) {
        sessionMap = (SessionMap)map;
    }

    public void setServletRequest(HttpServletRequest hsr) {
        request = hsr;
    }
    
}
