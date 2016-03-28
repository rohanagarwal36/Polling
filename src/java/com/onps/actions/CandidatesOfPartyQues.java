
package com.onps.actions;

import com.onps.pojos.Candidate;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class CandidatesOfPartyQues extends ActionSupport {
    private String party;
    List<Candidate> list = new  ArrayList<Candidate>();
    Session s = HibernateUtil.getSessionFactory().openSession();
    
    @Override
    public String execute(){
        try{
        list = s.createCriteria(Candidate.class).add(Restrictions.eq("party", party)).add(Restrictions.eq("approved", 'y')).list();
        s.close();
        return SUCCESS;
        }catch(Exception e){
            s.close();
            return ERROR;
        }
    }
    
    @Override
    public void validate(){
        if(party==null||party.equals(""))
            addFieldError("party", "Select Party");
    }

    public List<Candidate> getList() {
        return list;
    }

    public void setList(List<Candidate> list) {
        this.list = list;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }
    
}
