
package com.onps.actions;

import com.onps.pojos.Voter;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class FileUpload extends ActionSupport implements ServletRequestAware, SessionAware{
    private File file;
    private String fileFileName;
    private String fileContentType;
    private byte[] photo;
    HttpServletRequest request;
    SessionMap<String, Object> sessionMap;
    Session s = HibernateUtil.getSessionFactory().openSession();
    
    
    @Override
    public String execute() throws IOException {
        try{
        String path = request.getSession().getServletContext().getRealPath("/").concat("voterphoto");
        File f = new File(path, fileFileName);
        FileUtils.copyFile(this.file, f);
        photo = new byte[(int)f.length()];
        FileInputStream fis = new FileInputStream(f);
        fis.read(photo);
        List<Voter> list = s.createCriteria(Voter.class).add(Restrictions.eq("vid", (String)sessionMap.get("vid"))).list();
        Voter v = list.get(0);
        v.setPhoto(photo);
        s.update(v);
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
        if(file==null)
            addFieldError("file", "Select a File");
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public void setServletRequest(HttpServletRequest hsr) {
        request = hsr;
    }

    public void setSession(Map<String, Object> map) {
        sessionMap = (SessionMap)map;
    }
}
