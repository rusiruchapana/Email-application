
package codes;

import java.io.File;
import java.util.Properties;
import  javax.mail.*;




public class SendMail {
        public static void send(String mail, String subject, String message, File[] files){
            
            String userEmail = "rovansyna@gmail.com";
            String userPassword = "rovansynacC@";
            
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            
            Session session = Session.getInstance(props, new Authenticator(){
                    protected PasswordAuthentication getPasswordAuthentication(){
                        return new PasswordAuthentication(userEmail, userPassword);
                    }
            });
            
            
            
            
            
        }
}
