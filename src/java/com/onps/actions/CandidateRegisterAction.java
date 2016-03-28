package com.onps.actions;

import com.onps.pojos.Candidate;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import com.onps.pojos.HibernateUtil;import java.util.Map;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
;

public class CandidateRegisterAction extends ActionSupport implements ServletRequestAware, SessionAware{

    
    HttpServletRequest request;
    SessionMap<String, Object> sessionMap;
    private String fname;
    private String lname;
    private String fathersname;
    private String sex;
    private Integer age;
    private String email;
    private String address;
    private String cstate;
    private String city;
    private Integer pincode;
    private String password;
    private Long phoneno;
    private Integer constituency;
    private String party;
    Session s = HibernateUtil.getSessionFactory().openSession();
    
    @Override
    public String execute() {
        try{
        Candidate c = new Candidate();
        c.setAddress(address);
        c.setAge(age);
        c.setCity(city);
        c.setConstituency(constituency);
        c.setCstate(cstate);
        c.setEmail(email);
        c.setFathersname(fathersname);
        c.setFname(fname);
        c.setLname(lname);
        c.setPhoneno(phoneno);
        c.setPassword(password);
        c.setPincode(pincode);
        c.setSex(sex);
        c.setParty(party);
        c.setApproved('n');
        s.save(c);
        s.beginTransaction().commit();
        List<Candidate> list = s.createCriteria(Candidate.class).addOrder(Order.desc("id")).setMaxResults(1).list();
        Candidate c2 = list.get(0);
        String fn = c2.getFname();
        String ln = c2.getLname();
        char[] ch = fn.toCharArray();
        fn = new Character(ch[0]).toString().toUpperCase();
        ch = ln.toCharArray();
        ln = new Character(ch[0]).toString().toUpperCase();
        String f = fn + ln + c2.getId();
        c2.setCid(f);
        HttpSession session = request.getSession(true);
        sessionMap.put("cid", f);
        //session.setAttribute("msg", f);
        s.update(c2);
        s.beginTransaction().commit();
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
        if(fathersname==null||fathersname.equals(""))
            addFieldError("fathersname", "Enter Father's name");
        if(fname==null||fname.equals(""))
            addFieldError("fname", "Enter First Name");
        if(lname==null||lname.equals(""))
            addFieldError("lname", "Enter Last Name");
        if(sex==null||sex.equals(""))
            addFieldError("sex", "Select Sex");
        if(age==null)
            addFieldError("age", "Enter Age");
        if(password==null||password.equals(""))
            addFieldError("password", "Enter Password");
        if(constituency==null)
            addFieldError("constituency", "Enter Constituency");
        if(party==null||party.equals(""))
            addFieldError("party", "Enter Party");
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getFathersname() {
        return fathersname;
    }

    public void setFathersname(String fathersname) {
        this.fathersname = fathersname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(Long phoneno) {
        this.phoneno = phoneno;
    }

    public Integer getConstituency() {
        return constituency;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public void setConstituency(Integer constituency) {
        this.constituency = constituency;
    }

    public void setServletRequest(HttpServletRequest hsr) {
        request=hsr;
    }

    public void setSession(Map<String, Object> map) {
        sessionMap = (SessionMap)map;
    }
}
