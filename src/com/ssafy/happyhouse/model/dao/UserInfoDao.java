package com.ssafy.happyhouse.model.dao;

import java.sql.SQLException;

import com.ssafy.happyhouse.model.dto.UserInfo;

public interface UserInfoDao {
// crud(insert, delete, update, select)
	public void addUserInfo(UserInfo userinfo) throws SQLException;
	
	public void deleteUserInfo(String id) throws SQLException;
	
	//public void updateUserInfo(String id, String oldPassword, String newPassword, String phone) throws SQLException;
	public void updateUserInfo(String id, int num, String post) throws SQLException;
	
	public UserInfo searchUserInfo(String id) throws SQLException;
}
/* 1.UserInfo.java
 * 2.UserInfoDao.java
 * 3.UserInfoDaoImpl.java
 * 
 * 4.HouseInfoDaoImpl.java: searchAllHouseInfo()
 * */
 
 //update hehe