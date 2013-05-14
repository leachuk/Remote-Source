package au.com.entitysolutions.taskflows.model.utils;

import java.io.PrintStream;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Session;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;

public class EntityEmail {
  
    private HtmlEmail email;
    private static final String SMTP_JNDI="smtp/mail";   //JNDI of Mail Sessions in Weblogic
    private static final String DEFAULT_FROM="no-reply@entitysolutions.com.au";
    
    public EntityEmail() {
      init();
    }
    
    
    
    private void init() {
      
      InitialContext ic=null;
      this.email=new HtmlEmail();
      try {
        ic = new InitialContext();
        Session session = (Session) ic.lookup(SMTP_JNDI);
        
        if(session==null)
          throw new NamingException("JNDI not found in mail session of weblogic");
        
        this.email.setMailSession(session);
        setFrom(DEFAULT_FROM);//setting default send from.
      } catch (NamingException e) {
        System.out.println("Error getting mail session from JNDI "+SMTP_JNDI);
        e.printStackTrace();
      }   
    }
    
    public void addReplyTo(String address){
      try {
        this.email.addReplyTo(address);
      } catch (EmailException e) {
        e.printStackTrace();
      }
    }
    
    public void addTo(String address){
      try {    
        String[] addresses=this.getEmailAddresses(address);       
        if(addresses!=null){
          for(String addTo : addresses){
            if(isValidEmail(addTo))
              this.email.addTo(addTo);                      
          }
        }        
      } catch (EmailException e) {
        e.printStackTrace();
      }
    }
    
    public void addCc(String address){
      try {
        String[] addresses=this.getEmailAddresses(address);
        
        if(addresses!=null){
          for(String addCc : addresses){   
            if(isValidEmail(addCc))
             this.email.addCc(addCc);
          }
        }        
      } catch (EmailException e) {
        e.printStackTrace();
      }
    }
    
    public void addBcc(String address){
      try {
        String[] addresses=this.getEmailAddresses(address);
        
        if(addresses!=null){
          for(String addBcc : addresses){ 
            if(isValidEmail(addBcc))
             this.email.addBcc(addBcc);
          }
        }        
      } catch (EmailException e) {
        e.printStackTrace();
      }
    }
    
    public void addHeader(String name, String value){
      this.email.addHeader(name,value);
    }
    
    public void setFrom(String from){
      try {
        this.email.setFrom(from);
      } catch (EmailException e) {
        e.printStackTrace();
      }
    }
    
    public void setSubject(String subject){
      this.email.setSubject(subject);
    }
    
    public void setMsg(String msg){
    try {
      msg="<body style='font-family: Verdana; font-size: 10pt;'>"+msg+"</body>";
      
      this.email.setHtmlMsg(msg);
    } catch (EmailException e) {
      e.printStackTrace();
    }
  }
    
    public void addAttachment(String filePath){
      String filePattern="(.+\\/)?(.+)\\.(.+)";
      Pattern pattern=Pattern.compile(filePattern);
      Matcher m = pattern.matcher(filePath);
      
      String fileName="attachments.zip";
      
      if(m.find()){
        fileName=m.group(2)+"."+m.group(3);
      }
      
      EmailAttachment attachment=new EmailAttachment();
      attachment.setPath(filePath);
      attachment.setDisposition(EmailAttachment.ATTACHMENT);      
      attachment.setName(fileName);
    try {
      this.email.attach(attachment);
    } catch (EmailException e) {
      e.printStackTrace();
    }
  }
    
    public String send() {
      String result=null;
      try {
        result=this.email.send();
      } catch (EmailException e) {
        e.printStackTrace();
      }
      
      return result;
    }
    
    private String[] getEmailAddresses(String address){
      String [] addresses;
      
      if(StringUtils.isEmpty(address)){
        return null;
      }
      addresses=StringUtils.split(StringUtils.replace(address,";",","),",");        
      
      return addresses;
    }
    
    private boolean isValidEmail(String address){
      String  emailPattern="^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
      Pattern pattern = Pattern.compile(emailPattern);
      Matcher matcher = pattern.matcher(address);
      return matcher.matches();   
    }
    


}
