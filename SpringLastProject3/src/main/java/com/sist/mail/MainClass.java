package com.sist.mail;
import java.io.FileReader;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MainClass {
	
	
	    public static String style="<style>" + 
	    		"body {" + 
	    		"	  padding:1.5em;" + 
	    		"	  background: #f5f5f5" + 
	    		"	}" + 
	    		"	table {" + 
	    		"	  border: 1px #a39485 solid;" + 
	    		"	  font-size: .9em;" + 
	    		"	  box-shadow: 0 2px 5px rgba(0,0,0,.25);" + 
	    		"	  width: 100%;" + 
	    		"	  border-collapse: collapse;" + 
	    		"	  border-radius: 5px;" + 
	    		"	  overflow: hidden;" + 
	    		"	}" + 
	    		"	th {" + 
	    		"	  text-align: left;" + 
	    		"	}" + 
	    		"	thead {" + 
	    		"	  font-weight: bold;" + 
	    		"	  color: #fff;" + 
	    		"	  background: #73685d;" + 
	    		"	}" + 
	    		"	 td, th {" + 
	    		"	  padding: 1em .5em;" + 
	    		"	  vertical-align: middle;" + 
	    		"	}" + 
	    		"	 td {" + 
	    		"	  border-bottom: 1px solid rgba(0,0,0,.1);" + 
	    		"	  background: #fff;" + 
	    		"	}" + 
	    		"	a {" + 
	    		"	  color: #73685d;" + 
	    		"	}" + 
	    		"	 @media all and (max-width: 768px) {" + 
	    		"	  table, thead, tbody, th, td, tr {" + 
	    		"	    display: block;" + 
	    		"	  }" + 
	    		"	  th {" + 
	    		"	    text-align: right;" + 
	    		"	  }" + 
	    		"	  table {" + 
	    		"	    position: relative; " + 
	    		"	    padding-bottom: 0;" + 
	    		"	    border: none;" + 
	    		"	    box-shadow: 0 0 10px rgba(0,0,0,.2);" + 
	    		"	  }" + 
	    		"	  thead {" + 
	    		"	    float: left;" + 
	    		"	    white-space: nowrap;" + 
	    		"	  }" + 
	    		"	  tbody {" + 
	    		"	    overflow-x: auto;" + 
	    		"	    overflow-y: hidden;" + 
	    		"	    position: relative;" + 
	    		"	    white-space: nowrap;" + 
	    		"	  }" + 
	    		"	  tr {" + 
	    		"	    display: inline-block;" + 
	    		"	    vertical-align: top;" + 
	    		"	  }" + 
	    		"	  th {" + 
	    		"	    border-bottom: 1px solid #a39485;" + 
	    		"	  }" + 
	    		"	  td {" + 
	    		"	    border-bottom: 1px solid #e5e5e5;" + 
	    		"	  }" + 
	    		"	  }" + 
	    		"</style>";
	    

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
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
				 + style
       		     + "</head>"
       		     + "<body>"
       		     + "<table>"
       		     + "<tr>"
       		     + "<th width=15%>회원번호</th>"
       		     + "<td width=85%>1</td>"
       		     + "</tr>"
       		     + "<tr>"
       		     + "<th width=15%>이름</th>"
       		     + "<td width=85%>홍길동</td>"
       		     + "</tr>"
       		     + "<tr>"
       		     + "<th width=15%>성별</th>"
       		     + "<td width=85%>남자</td>"
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

}
