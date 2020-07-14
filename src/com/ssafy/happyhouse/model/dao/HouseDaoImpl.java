package com.ssafy.happyhouse.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.model.dto.HouseInfo;
import com.ssafy.happyhouse.model.dto.HousePageBean;
import com.ssafy.happyhouse.util.DBUtil;

public class HouseDaoImpl implements HouseDao{
	private Map<String, HouseInfo> houseInfo;
	private Map<String, List<HouseDeal>> deals;
	private int size;
	private List<HouseDeal>search;
	private String[] searchType= {HouseDeal.APT_DEAL, HouseDeal.APT_RENT, HouseDeal.HOUSE_DEAL, HouseDeal.HOUSE_RENT};
	public HouseDaoImpl() {
	}
	/**
	 * 아파트 정보와 아파트 거래 정보를  xml 파일에서 읽어온다.
	 */
	public void loadData() { }
	
	/**
	 * 검색 조건(key) 검색 단어(word)에 해당하는 아파트 거래 정보(HouseInfo)를  검색해서 반환.  
	 * @param bean  검색 조건과 검색 단어가 있는 객체
	 * @return 조회한 식품 목록
	 */
	public List<HouseDeal> searchAll(HousePageBean  bean) throws SQLException{
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getConnection();
			//and type in (1,2)
			StringBuilder sql = new StringBuilder(100);
			sql.append(" select * from housedeal where 1=1 ");
			boolean[] type = bean.getSearchType();
			sql.append(" and type in (");
			for (int i = 0, len = type.length, cnt = 0; i < len; ++i) {
	            if (type[i]) {
	               if (cnt > 0) sql.append(",");
	               sql.append(i+1);
	               ++cnt;
	            }
	         }
			sql.append(")");
			String dong = bean.getDong();
			String aptName = bean.getAptname();
			if(dong != null && !dong.trim().equals("")) {
				sql.append(" and dong like ? ");				
			}else if(aptName != null && !aptName.trim().equals("")) {
				sql.append(" and aptname like ? ");
			}
			stmt = con.prepareStatement(sql.toString());
			if(dong != null && !dong.trim().equals("")) {
				stmt.setString(1, "%"+dong+"%");				
			}else if(aptName != null && !aptName.trim().equals("")) {
				stmt.setString(1, "%"+aptName+"%");				
			}
			sql.append("LIMIT 100");
			rs = stmt.executeQuery();
			List<HouseDeal> list = new LinkedList<HouseDeal>();
			while(rs.next()) {				
				HouseDeal deal = new HouseDeal();
				deal.setNo(rs.getInt("no"));
				deal.setDong(rs.getString("dong"));
				deal.setAptName(rs.getString("aptName"));
				deal.setCode(rs.getInt("code"));
				deal.setDealAmount(rs.getString("dealAmount"));
				deal.setBuildYear(rs.getInt("buildYear"));
				deal.setDealYear(rs.getInt("dealYear"));
				deal.setDealMonth(rs.getInt("dealMonth"));
				deal.setDealDay(rs.getInt("dealDay"));
				deal.setArea(rs.getDouble("area"));
				deal.setFloor(rs.getInt("floor"));
				deal.setJibun(rs.getString("jibun"));
				deal.setType(rs.getString("type"));
				deal.setRentMoney(rs.getString("rentMoney"));
				list.add(deal);
			}
			return list;
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(con);
		}
	}
	
	/**
	 * 주택 거래 식별 번호에 해당하는 아파트 거래 정보를 검색해서 반환한다.<br/>
	 * @param no	검색할 아파트 식별 번호
	 * @return		아파트 식별 번호에 해당하는 아파트 거래 정보를 찾아서 리턴한다, 없으면 null이 리턴됨
	 */
	public HouseDeal search(int no) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getConnection();
			String sql = " select * from housedeal where no =? ";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, no);
			
			rs = stmt.executeQuery();
			if(rs.next()) {
				HouseDeal deal = new HouseDeal();
				deal.setNo(rs.getInt("no"));
				deal.setDong(rs.getString("dong"));
				deal.setAptName(rs.getString("aptName"));
				deal.setCode(rs.getInt("code"));
				deal.setDealAmount(rs.getString("dealAmount"));
				deal.setBuildYear(rs.getInt("buildYear"));
				deal.setDealYear(rs.getInt("dealYear"));
				deal.setDealMonth(rs.getInt("dealMonth"));
				deal.setDealDay(rs.getInt("dealDay"));
				deal.setArea(rs.getDouble("area"));
				deal.setFloor(rs.getInt("floor"));
				deal.setJibun(rs.getString("jibun"));
				deal.setType(rs.getString("type"));
				deal.setRentMoney(rs.getString("rentMoney"));
				return deal;
			}
			return null;
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(con);
		}
	}
	
	public static void main(String[] args) {
		HouseDao dao = new HouseDaoImpl();
	}
	
	
	/**
	 * 검색 조건(key) 검색 단어(word)에 해당하는 아파트 거래 정보(HouseInfo)를  검색해서 반환.  
	 * @param bean  검색 조건과 검색 단어가 있는 객체
	 * @return 조회한 식품 목록
	 */
	public List<HouseDeal> searchAllforKMP(HousePageBean  bean) throws SQLException{
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getConnection();
			//and type in (1,2)
			StringBuilder sql = new StringBuilder(100);
			sql.append(" select * from housedeal where 1=1 ");
			boolean[] type = bean.getSearchType();
			sql.append(" and type in (");
			for (int i = 0, len = type.length, cnt = 0; i < len; ++i) {
	            if (type[i]) {
	               if (cnt > 0) sql.append(",");
	               sql.append(i+1);
	               ++cnt;
	            }
	         }
			sql.append(")");
			String dong = bean.getDong();
			String aptName = bean.getAptname();
			
			if(dong== null && aptName==null)
				sql.append("LIMIT 1000");

			stmt = con.prepareStatement(sql.toString());
			
			rs = stmt.executeQuery();
			List<HouseDeal> list = new LinkedList<HouseDeal>();
			while(rs.next()) {				
				HouseDeal deal = new HouseDeal();
				deal.setNo(rs.getInt("no"));
				deal.setDong(rs.getString("dong"));
				deal.setAptName(rs.getString("aptName"));
				deal.setCode(rs.getInt("code"));
				deal.setDealAmount(rs.getString("dealAmount"));
				deal.setBuildYear(rs.getInt("buildYear"));
				deal.setDealYear(rs.getInt("dealYear"));
				deal.setDealMonth(rs.getInt("dealMonth"));
				deal.setDealDay(rs.getInt("dealDay"));
				deal.setArea(rs.getDouble("area"));
				deal.setFloor(rs.getInt("floor"));
				deal.setJibun(rs.getString("jibun"));
				deal.setType(rs.getString("type"));
				deal.setRentMoney(rs.getString("rentMoney"));
				list.add(deal);
			}
			return list;
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(con);
		}
	}

}





