package com.sist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sist.vo.*;
import com.sist.dao.*;

/*
	Service => DAO통합, 실제 데이터 전송 모아서 처리 => Controller
	Controller에서 수정을하면 Controller에 접근이 어렵기 때문에
	Business Logic은 주로 Service에서 작성한다!
	유지보수면에서 Service에 작성하는 것이 더 유리하다!
 */

@Service
public class ReserveServiceImpl implements ReserveService{
	@Autowired
	private ReserveDAO rDao;

	@Override
	public List<FoodVO> foodReserveData(String type) {
		// TODO Auto-generated method stub
		return rDao.foodReserveData(type);
	}

	@Override
	public void foodReserveInsert(ReserveVO vo) {
		// TODO Auto-generated method stub
		rDao.foodReserveInsert(vo);
	}

	@Override
	public List<ReserveVO> reserveMypageData(String userId) {
		// TODO Auto-generated method stub
		return rDao.reserveMypageData(userId);
	}

	@Override
	public void reserveCancel(int rno) {
		// TODO Auto-generated method stub
		rDao.reserveCancel(rno);
	}

	@Override
	public List<ReserveVO> reserveAdminpageData() {
		// TODO Auto-generated method stub
		return rDao.reserveAdminpageData();
	}

	@Override
	public void reserveok(int rno) {
		// TODO Auto-generated method stub
		rDao.reserveok(rno);
	}

	@Override
	public ReserveVO reserveInfoData(int rno) {
		// TODO Auto-generated method stub
		return rDao.reserveInfoData(rno);
	}
	
	
}
