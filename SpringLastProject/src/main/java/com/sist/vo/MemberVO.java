package com.sist.vo;
import java.util.Date;

import lombok.Data;
@Data
public class MemberVO {
	private int enabled;
	private String userId,userName,userPwd,sex,birthday,email,phone,phone1,phone2,post,addr1,addr2,content,
					reg_dbday,mod_dbday,last_dbday; 
	private Date regdate,modifydate,lastLogin;
	private String authority,msg;
}
