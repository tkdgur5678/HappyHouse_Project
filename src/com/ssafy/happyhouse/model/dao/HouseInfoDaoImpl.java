package com.ssafy.happyhouse.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.happyhouse.model.dto.HouseInfo;
import com.ssafy.happyhouse.util.DBUtil;

public class HouseInfoDaoImpl implements HouseInfoDao {

	@Override
	public List<HouseInfo> searchAllHouseInfo() throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getConnection();
			String sql = "select * from housedeal";
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			List<HouseInfo> list = new ArrayList<HouseInfo>();
			while(rs.next()) {
				HouseInfo info = new HouseInfo();
				info.setNo(rs.getInt("no"));
				info.setDong(rs.getString("dong"));
				info.setAptName(rs.getString("aptName"));
				info.setCode(rs.getInt("code"));
				info.setBuildYear(rs.getInt("buildYear"));
				info.setJibun(rs.getString("jibun"));
				list.add(info);
			}
			return list;
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(con);
		}
	}

}
