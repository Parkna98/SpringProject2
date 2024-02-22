package com.sist.manager;

import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.util.*;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sist.mail.*;
import com.sist.vo.MemberVO;


@Component
public class MailManager {
	public void mailMemberSender(MemberVO vo) {
	
		String temp="";
		try
		{
            FileReader fr=new FileReader("c:\\springDev\\mail.txt");
            int i=0;
            while((i=fr.read())!=-1)
            {
            	temp+=String.valueOf((char)i);
            }
		}catch(Exception ex) {}
		
		String host="smtp.naver.com";
		String user="pkpphn@naver.com";
		String password="";
		Properties props=new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", 587);
		props.put("mail.smtp.auth", true);
		Session session=Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
		
		try {
			MimeMessage message=new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress("pkpphn@naver.com"));
			// 보낼이메일주소 매개변수
			message.setSubject("[Test]테스트 입니다."); // 메일 제목
			// 메일내용
			
			
			
			String html="<html>"
       		     + "<head>"
       		     + "</head>"
       		     + "<body>"
       		     + "<table width=450>"
       		     + "<tr>"
       		     + "<th width=15%>이름</th>"
       		     + "<td width=85%>"+vo.getUserName()+"</td>"
       		     + "</tr>"
       		     + "<tr>"
       		     + "<th width=15%>성별</th>"
       		     + "<td width=85%>"+vo.getSex()+"</td>"
       		     + "</tr>"
       		     + "<tr>"
       		     + "<th width=15%>이메일</th>"
       		     + "<td width=85%>"+vo.getEmail()+"</td>"
       		     + "</tr>"
       		     + "<tr>"
       		     + "<th width=15%>주소</th>"
       		     + "<td width=85%>"+vo.getAddr1()+" "+vo.getAddr2()+"</td>"
       		     + "</tr>"
       		     + "<tr>"
       		     + "<th width=15%>전화</th>"
       		     + "<td width=85%>"+vo.getPhone()+"</td>"
       		     + "</tr>"
       		     + "<tr>"
       		     + "<td colspan=2>"
       		     + "<img src=\"https://recipe1.ezmember.co.kr/cache/data/goods/23/04/16/1000035599/1000035599_list_08.jpg\" style=\"width:300px;height=300px\">"
       		     + "</td>"
       		     + "</tr>"
       		     + "</table>"
       		     + "</body>"
       		     + "</html>";
			
			message.setContent(html,"text/html;charset=UTF-8"); // send the message
			Transport.send(message);
			System.out.println("Message Send Success!");
		}catch(MessagingException e) {
			e.printStackTrace();
		}
	}
	public void mailReserveSender() {
		
	}
}
