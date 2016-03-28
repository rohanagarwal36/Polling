package com.onps.actions;

import com.onps.pojos.HibernateUtil;
import com.onps.pojos.Voter;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class VoterRegisterAction extends ActionSupport implements ServletRequestAware, SessionAware{

    HttpServletRequest request;
    

    private String fname;
    private String lname;
    private String fathersname;
    private String sex;
    private Integer age;
    private String address;
    private String city;
    private String state;
    private Integer pincode;
    private String email;
    private Long phoneno;
    private String password;
    private String vid;
    private Integer constituency;
    Session s = HibernateUtil.getSessionFactory().openSession();

    public Integer getConstituency() {
        return constituency;
    }

    public void setConstituency(Integer constituency) {
        this.constituency = constituency;
    }
    SessionMap<String, Object> sessionMap;

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    @Override
    public String execute() {
        try{
        Voter v = new Voter();
        v.setAddress(address);
        v.setAge(age);
        v.setCity(city);
        v.setEmail(email);
        v.setFathersname(fathersname);
        v.setFname(fname);
        v.setLname(lname);
        v.setPassword(password);
        v.setPhoneno(phoneno);
        v.setPincode(pincode);
        v.setVstate(state);
        v.setSex(sex);
        v.setConstituency(constituency);
        v.setHasvoted("no");
        v.setApproved('n');
        s.save(v);
        s.beginTransaction().commit();
        List<Voter> list = s.createCriteria(Voter.class).addOrder(Order.desc("id")).setMaxResults(1).list();
        Voter v2 = list.get(0);
        String fn = v2.getFname();
        String ln = v2.getLname();
        char[] ch = fn.toCharArray();
        fn = new Character(ch[0]).toString().toUpperCase();
        ch = ln.toCharArray();
        ln = new Character(ch[0]).toString().toUpperCase();
        String f = fn + ln + v2.getId();
        v2.setVid(f);
        //HttpSession session = request.getSession(true);
        sessionMap.put("vid", f);
        //session.setAttribute("msg", f);
        s.update(v2);
        s.beginTransaction().commit();
        s.close();
        vid=f;
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
        if(state==null||state.equals(""))
            addFieldError("state", "Enter State");
        if(city==null||city.equals(""))
            addFieldError("city", "Enter City");
        if(pincode==null)
            addFieldError("pincode", "Enter Pincode");
        if(phoneno==null)
            addFieldError("phoneno", "Enter Phone No.");
        if(fathersname==null||fathersname.equals(""))
            addFieldError("fathersname", "Enter City");
        if(fname==null||fname.equals(""))
            addFieldError("fname", "Enter City");
        if(lname==null||lname.equals(""))
            addFieldError("lname", "Enter City");
        if(sex==null||sex.equals(""))
            addFieldError("sex", "Enter City");
        if(age==null)
            addFieldError("age", "Enter City");
        if(password==null||password.equals(""))
            addFieldError("password", "Enter City");
        
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(long phoneno) {
        this.phoneno = phoneno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setServletRequest(HttpServletRequest hsr) {
        request = hsr;
    }

    public void setSession(Map<String, Object> map) {
        sessionMap = (SessionMap)map;
    }
}
