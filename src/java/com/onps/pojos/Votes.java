package com.onps.pojos;
// Generated Aug 3, 2014 11:14:04 PM by Hibernate Tools 3.6.0



/**
 * Votes generated by hbm2java
 */
public class Votes  implements java.io.Serializable {


     private String vid;
     private String cid;
     private int constituency;
     private String party;

    public Votes() {
    }

    public Votes(String vid, String cid, int constituency, String party) {
       this.vid = vid;
       this.cid = cid;
       this.constituency = constituency;
       this.party = party;
    }
   
    public String getVid() {
        return this.vid;
    }
    
    public void setVid(String vid) {
        this.vid = vid;
    }
    public String getCid() {
        return this.cid;
    }
    
    public void setCid(String cid) {
        this.cid = cid;
    }
    public int getConstituency() {
        return this.constituency;
    }
    
    public void setConstituency(int constituency) {
        this.constituency = constituency;
    }
    public String getParty() {
        return this.party;
    }
    
    public void setParty(String party) {
        this.party = party;
    }




}

