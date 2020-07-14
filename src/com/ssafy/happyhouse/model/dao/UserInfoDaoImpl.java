package com.ssafy.happyhouse.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ssafy.happyhouse.HappyHouseException;
import com.ssafy.happyhouse.model.dto.UserInfo;
import com.ssafy.happyhouse.util.DBUtil;

public class UserInfoDaoImpl implements UserInfoDao {

	@Override
	public void addUserInfo(UserInfo userinfo) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DBUtil.getConnection();
			String sql = " insert into userinfo (id, password, name, address, phone)"
					+ "values(?,?,?,?,?)";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, userinfo.getId());
			stmt.setString(2, userinfo.getPassword());
			stmt.setString(3, userinfo.getName());
			stmt.setString(4, userinfo.getAddress());
			stmt.setString(5, userinfo.getPhone());
			int result = stmt.executeUpdate();
			if(result>0) {
				System.out.printf("%d개의 회원정보를 생성했습니다.",result);
			}else {
				throw new HappyHouseException("회원정보를 생성하는데에 실패했습니다.");
			}
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(con);
		}
	}

	@Override
	public void deleteUserInfo(String id) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DBUtil.getConnection();
			String sql = " delete from userinfo where id =?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			
			int result = stmt.executeUpdate();
			if(result>0) {
				System.out.printf("%d개의 회원정보를 삭제했습니다.",result);
			}else {
				throw new HappyHouseException("회원정보를 삭제하는데에 실패했습니다.");
			}
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(con);
		}
	}

//	@Override
//	public void updateUserInfo(String id, String oldPassword, String newPassword, String phone) throws SQLException {
//		Connection con = null;
//		PreparedStatement stmt = null;
//		try {
//			con = DBUtil.getConnection();
//			String sql = " update userinfo set password = ?, phone = ? where id=? and password=?";
//			stmt = con.prepareStatement(sql);
//			stmt.setString(1, newPassword);
//			stmt.setString(2, phone);
//			stmt.setString(3, id);
//			stmt.setString(4, oldPassword);
//			int result = stmt.executeUpdate();
//			if(result>0) {
//				System.out.printf("%d개의 회원정보를 수정했습니다.",result);
//			}else {
//				throw new HappyHouseException("회원정보를 수정하는데에 실패했습니다.");
//			}
//		} finally {
//			DBUtil.close(stmt);
//			DBUtil.close(con);
//		}
//	}

	@Override
	public void updateUserInfo(String uid, int num, String post) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("update userinfo set ");
			if(num == 1) {
				sql.append("name = ? ");
			}else if(num == 2) {
				sql.append("password = ? ");
			}else if(num == 3) {
				sql.append("address = ? ");
			}else if(num == 4) {
				sql.append("phone = ? ");
			}
			sql.append("where id = ?");
			stmt = con.prepareStatement(sql.toString());
			stmt.setString(1,  post);
			stmt.setString(2,  uid);
			stmt.executeUpdate();
		} finally {
			if(stmt != null)
				DBUtil.close(stmt);
			if(con != null)
				DBUtil.close(con);
		}
		System.out.println("update 완료");
	}

	@Override
	public UserInfo searchUserInfo(String id) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getConnection();
			String sql = " select * from userinfo where id =?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			
			rs = stmt.executeQuery();
			if(rs.next()) {
				UserInfo info = new UserInfo();
				info.setId(id);
				info.setName(rs.getString("name"));
				info.setPassword(rs.getString("password"));
				info.setAddress(rs.getString("address"));
				info.setPhone(rs.getString("phone"));
				return info;
			}
			return null;
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(con);
		}
	}


}
