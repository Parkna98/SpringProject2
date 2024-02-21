package com.sist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	private MemberDAO mDao;
	
	@Override
	public int memberIdCount(String userid) {
		// TODO Auto-generated method stub
		return mDao.memberIdCount(userid);
	}

	@Override
	public void memberInsert(MemberVO vo) {
		// TODO Auto-generated method stub
		mDao.memberInsert(vo);
	}

	@Override
	public void memberAuthorityInsert(String userid) {
		// TODO Auto-generated method stub
		mDao.memberAuthorityInsert(userid);
	}

	@Override
	public MemberVO memberLogin(String userId, String userPwd) {
		// TODO Auto-generated method stub
		return mDao.memberLogin(userId, userPwd);
	}

	@Override
	public MemberVO memberSessionData(String userId) {
		// TODO Auto-generated method stub
		return mDao.memberSessionData(userId);
	}

	@Override
	public void lastLoginUpdate(String userId) {
		// TODO Auto-generated method stub
		mDao.lastLoginUpdate(userId);
	}

}
