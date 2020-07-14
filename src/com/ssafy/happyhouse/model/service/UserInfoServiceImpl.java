package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;

import com.ssafy.happyhouse.HappyHouseException;
import com.ssafy.happyhouse.model.dao.UserInfoDao;
import com.ssafy.happyhouse.model.dao.UserInfoDaoImpl;
import com.ssafy.happyhouse.model.dto.UserInfo;

public class UserInfoServiceImpl implements UserInfoService {
	private UserInfoDao dao;
	
	public UserInfoServiceImpl() {
		dao = new UserInfoDaoImpl();
	}
	
	@Override
	public void insert(UserInfo userinfo) {
		try {
			dao.addUserInfo(userinfo);
		} catch (SQLException e) {
			throw new HappyHouseException("user 정보 삽입 중 문제가 발생하였습니다.");
		}		
	}

	@Override
	public void delete(String uid, String password) {
		try {
			dao.deleteUserInfo(uid);
		} catch (SQLException e) {
			throw new HappyHouseException("user 정보 삭제 중 문제가 발생");
		}		
	}

	@Override
	public void update(String uid, int num, String post) {
		try {
			dao.updateUserInfo(uid, num, post);
		} catch (SQLException e) {
			throw new HappyHouseException("user 정보 수정 중 문제가 발생");
		}				
	}

	@Override
	public UserInfo select(String uid) {
		try {
			return dao.searchUserInfo(uid);
		} catch (SQLException e) {
			throw new HappyHouseException("user 정보 읽는 중 문제가 발생");
		}	
	}

}
