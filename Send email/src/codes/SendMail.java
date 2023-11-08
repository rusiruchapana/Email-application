
package codes;

import java.io.File;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import  javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;




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
            
            
            try {
                    Message emailMessage = new MimeMessage(session);
                    emailMessage.setFrom(new InternetAddress(userEmail));
                    emailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
                    emailMessage.setSubject(subject);
            
                    Multipart multipart = new MimeMultipart();
                    
                    BodyPart messageBodyPart = new MimeBodyPart();
                    messageBodyPart.setText(message);
                    multipart.addBodyPart(messageBodyPart);
                    
                    for(File file : files){
                        BodyPart attachBodyPart = new MimeBodyPart();
                        
                        DataSource source = new FileDataSource(file);
                        attachBodyPart.setDataHandler(new DataHandler(source));
                        attachBodyPart.setFileName(file.getName());
                        
                        multipart.addBodyPart(attachBodyPart);
                    }
                    
                    emailMessage.setContent(multipart);
                    Transport.send(emailMessage);
                    System.out.println("Email sent successfully.");
                    
                    
                    
            } catch (Exception e) {
                     e.printStackTrace();
                     System.out.println("Failed to send email.");
            }
            
        }
}
