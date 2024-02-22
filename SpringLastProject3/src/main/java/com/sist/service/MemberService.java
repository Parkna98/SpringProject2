package com.sist.service;

import com.sist.vo.MemberVO;

public interface MemberService {
	public int memberIdCount(String userid);
	public void memberInsert(MemberVO vo);
	public void memberAuthorityInsert(String userid);
	public MemberVO memberLogin(String userId,String userPwd);
	public MemberVO memberSessionData(String userId);
	public void lastLoginUpdate(String userId);
	public MemberVO memberSessionInfoData(String userId);
}
