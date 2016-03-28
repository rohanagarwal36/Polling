
package com.onps.actions;

import com.onps.pojos.Candidate;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import java.util.Map;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class CandidateProfileEditAction extends ActionSupport implements SessionAware{
    private String email;
    private String address;
    private String cstate;
    private String city;
    private Integer pincode;
    private Long phoneno;
    SessionMap<String, Object> sessionMap;
    Session s = HibernateUtil.getSessionFactory().openSession();

    @Override
    public String execute(){
        try{
        List<Candidate> list = s.createCriteria(Candidate.class).add(Restrictions.eq("cid", (String)sessionMap.get("cid"))).list();
        Candidate c = list.get(0);
        c.setAddress(address);
        c.setEmail(email);
        c.setCstate(cstate);
        c.setCity(city);
        c.setPincode(pincode);
        c.setPhoneno(phoneno);
        s.update(c);
        s.beginTransaction().commit();
        s.close();
        return SUCCESS;
        }catch(Exception e){
            s.close();
            return ERROR;
        }
    }
    
    @Override
    public void validate(){
        if(email==null||email.equals(""))
            addFieldError("email", "Enter Email");
        if(address==null||address.equals(""))
            addFieldError("address", "Enter Address");
        if(cstate==null||cstate.equals(""))
            addFieldError("cstate", "Enter State");
        if(city==null||city.equals(""))
            addFieldError("city", "Enter City");
        if(pincode==null)
            addFieldError("pincode", "Enter Pincode");
        if(phoneno==null)
            addFieldError("phoneno", "Enter Phone No.");
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCstate() {
        return cstate;
    }

    public void setCstate(String cstate) {
        this.cstate = cstate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getPincode() {
        return pincode;
    }

    public void setPincode(Integer pincode) {
        this.pincode = pincode;
    }

    public Long getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(Long phoneno) {
        this.phoneno = phoneno;
    }

    public void setSession(Map<String, Object> map) {
        sessionMap = (SessionMap)map;
    }
    
}
