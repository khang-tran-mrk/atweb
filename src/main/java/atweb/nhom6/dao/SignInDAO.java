package atweb.nhom6.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import atweb.nhom6.model.ACCOUNT; 
public class SignInDAO {

	public int check(ACCOUNT account) {
		String CHECK_USER_SQL = "select username from ACCOUNT where username = ? and password = ?";
		int result = 0;

		// ket noi db
		Connection connection = new ConnectionToDB().getConnect();

		try {
			PreparedStatement preStmt = connection.prepareStatement(CHECK_USER_SQL);

			preStmt.setString(1, account.getUsername());
			preStmt.setString(2, account.getPassword());

			// thuc hien them account vao
			ResultSet rs = preStmt.executeQuery();
			
			if (rs.next()) {
				result = 1;
				System.out.println("numrow = " + rs.getRow());
			}	
			else
				result = 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	//code very random
    public String getRandom() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }
  //gui mail den mail cua user
    public boolean sendEmail(String email, String code) {
        boolean test = false;
        
        String toEmail = email;
        System.out.println("toEmail = " + toEmail );
        try {

        	Properties props = new Properties();
        	props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "587");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.port", "587");
            

            Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                    return new javax.mail.PasswordAuthentication("contact.cgv4@gmail.com", "hoangminhtuan");
                }
            });

            //set email message details
            Message mess = new MimeMessage(session);

    		//set from email address
            mess.setFrom(new InternetAddress("contact.cgv4@gmail.com"));
    		//set to email address or destination email address
            mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
    		
    		//set email subject
            mess.setSubject("User Email Verification");
            System.out.println(code);
    		//set message text
            mess.setText("Khang ngu hello,"
            		+ "Click the link to change the password: "  + 
            		" http://localhost:8080/atweb/doforgot?code=" +code +"&email" +email );
            
            //send the message
            Transport.send(mess);
            
            test=true;
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return test;
    }
    
 // kiem tra sdt co ton tai hay khong 
 		public boolean checkEmail(String email) {
 			try {
 			ResultSet rs=	new ConnectionToDB().selectData("select * from ACCOUNT where email = '" + email + "'");
 			if(rs.next()) {
 				
 				return true;
 			}else {
 				
 				return false;
 			}
 					
 				
 			} catch (Exception e) {
 				return false;
 			}
 		}
 		
 		//cap nhap password khi thay doi
 		public static boolean updatePassword(ACCOUNT u) {
 			try {
 				
 				new  ConnectionToDB().excuteSql("update ACCOUNT set password='"+u.getPassword()+"' where email='"+ u.getEmail()+"'");
 				
 				return true;
 			} catch (Exception e) {
 				System.out.println(e.getMessage());
 				e.printStackTrace();
 			}
 			return false;
 		}
	
}
