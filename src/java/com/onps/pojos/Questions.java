package com.onps.pojos;
// Generated Aug 3, 2014 11:14:04 PM by Hibernate Tools 3.6.0



/**
 * Questions generated by hbm2java
 */
public class Questions  implements java.io.Serializable {


     private Integer id;
     private String vid;
     private String cid;
     private String question;
     private String answer;

    public Questions() {
    }

    public Questions(String vid, String cid, String question, String answer) {
       this.vid = vid;
       this.cid = cid;
       this.question = question;
       this.answer = answer;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
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
    public String getQuestion() {
        return this.question;
    }
    
    public void setQuestion(String question) {
        this.question = question;
    }
    public String getAnswer() {
        return this.answer;
    }
    
    public void setAnswer(String answer) {
        this.answer = answer;
    }




}

