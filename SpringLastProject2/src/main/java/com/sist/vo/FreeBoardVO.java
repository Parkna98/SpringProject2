package com.sist.vo;
import java.util.Date;

import lombok.Data;
@Data
public class FreeBoardVO {
	private int no,hit;
	private String name,subject,content,pwd,dbday;
	private Date regdate;
}
