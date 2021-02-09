package member.handler;

import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import member.service.IMemberService;
import member.service.MemberServiceImpl;
import vo.MemberVO;

public class PassEmail {

	public void sendMail(String memId, String memEmail) {

		// db에있는 email

		System.out.println(memEmail);
		String host = "smtp.naver.com";
		final String user = "email";
		final String password = "password";

		String to = memEmail;

		// Get the session object
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		// Compose the message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
//			Random rnd = new Random();
//			String randomPass = String.valueOf((char) ((int) (rnd.nextInt(26)) + 97));
			
			
			StringBuffer randomPass = new StringBuffer();
			Random rnd = new Random();
			for (int i = 0; i < 7; i++) {
			    int rIndex = rnd.nextInt(3);
			    switch (rIndex) {
			    case 0:
			        // a-z
			    	randomPass.append((char) ((int) (rnd.nextInt(26)) + 97));
			        break;
			    case 1:
			        // A-Z
			    	randomPass.append((char) ((int) (rnd.nextInt(26)) + 65));
			        break;
			    case 2:
			        // 0-9
			    	randomPass.append((rnd.nextInt(10)));
			        break;
			    }
			}
			
			String temporaryPass = randomPass.toString();
			
			IMemberService memberService = MemberServiceImpl.getInstance();
			
			MemberVO mv = new MemberVO();
			mv.setMemId(memId);
			mv.setMemPass(temporaryPass);
			
			int cnt = memberService.updatePass(mv);
			System.out.println("cnt = "+cnt);
			// Subject
			message.setSubject("CCNEE 임시 비밀번호 입니다.");

			// Text
			message.setText(temporaryPass+"로 로그인 후 마이페이지에서 비밀번호를 변경해 주십시오.");
			
			// send the message
			Transport.send(message);
			System.out.println("이메일 전송 성공!");
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
