package com.onps.actions;

import com.onps.pojos.Candidate;
import com.onps.pojos.Voter;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class PollingLoginAction extends ActionSupport implements ServletRequestAware {

    private String vid;
    private String password;
    private String hashpass;
    HttpServletRequest request;
    private List<Candidate> list1=new ArrayList<Candidate>();
    Session s = HibernateUtil.getSessionFactory().openSession();

    public List<Candidate> getList1() {
        return list1;
    }

    public void setList1(List<Candidate> list1) {
        this.list1 = list1;
    }

    @Override
    public String execute() {
        //try{
        List<Voter> list = s.createCriteria(Voter.class).add(Restrictions.eq("vid", vid)).list();
        if(list.isEmpty()){
            request.setAttribute("msg", "Wrong VID");
            return ERROR;
        }else{
            Voter v = list.get(0);
        System.out.println(v.getPassword());
        System.out.println(v.getHashpass());        
            if (password.equals(v.getPassword()) && hashpass.equals(v.getHashpass())) {
                if (v.getHasvoted().equals("no") && v.getApproved().equals('y')) {
                    HttpSession session=request.getSession(true);
                    session.setAttribute("vid", v.getVid());
                    list1=s.createCriteria(Candidate.class).add(Restrictions.eq("constituency", v.getConstituency())).list();
                    return Action.SUCCESS;
                } else {
                    request.setAttribute("msg", "You have already voted or you are not yet approved for voting by the admin.");
                    return Action.ERROR;
                }
            } else {
                request.setAttribute("msg", "Wrong password");
                return Action.ERROR;
            }
        }
//        }catch(Exception e){
//            
//            s.close();
//            return ERROR;
//        }
    }
    
    @Override
    public void validate(){
        if(vid==null||vid.equals(""))
            addFieldError("vid", "Enter Voter ID");
        if(password==null||password.equals(""))
            addFieldError("password", "Enter Password");
        if(hashpass==null||hashpass.equals(""))
            addFieldError("hashpass", "Enter HashPassword");
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHashpass() {
        return hashpass;
    }

    public void setHashpass(String hashpass) {
        this.hashpass = hashpass;
    }

    public void setServletRequest(HttpServletRequest hsr) {
        request = hsr;
    }
}
