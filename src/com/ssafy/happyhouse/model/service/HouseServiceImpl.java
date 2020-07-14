package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.happyhouse.HappyHouseException;
import com.ssafy.happyhouse.model.dao.HouseDao;
import com.ssafy.happyhouse.model.dao.HouseDaoImpl;
import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.model.dto.HousePageBean;

public class HouseServiceImpl implements HouseService{
	private HouseDao dao;

	public HouseServiceImpl() {
		 dao =new HouseDaoImpl();
	}
	/**
	 * 검색 조건(key) 검색 단어(word)에 해당하는 아파트 거래 정보(HouseInfo)를  검색해서 반환.  
	 * @param bean  검색 조건과 검색 단어가 있는 객체
	 * @return 조회한 식품 목록
	 */
	public List<HouseDeal> searchAll(HousePageBean  bean){
		try {
			boolean[] types = bean.getSearchType();
			int cnt = 0;
			for(boolean t : types) {
				if(t) {
					cnt++;
				}
			}
			if(cnt == 0) {
				throw new HappyHouseException("주택 타입은 반드시 한 개 이상을 선택해야 합니다.");
			}
			return dao.searchAll(bean);
		} catch (SQLException e) {
			throw new HappyHouseException("주택 정보 조회 중 오류 발생");
		}
	}
	
	/**
	 * 아파트 식별 번호에 해당하는 아파트 거래 정보를 검색해서 반환. 
	 * @param no	검색할 아파트 식별 번호
	 * @return		아파트 식별 번호에 해당하는 아파트 거래 정보를 찾아서 리턴한다, 없으면 null이 리턴됨
	 */
	public HouseDeal search(int no) {
		try {
			HouseDeal deal = dao.search(no);
			if(deal == null) {
				throw new HappyHouseException(String.format("거래번호 %d번에 해당하는 주택거래 정보가 존재하지 않습니다.", no));
			}
			return deal;
		} catch (SQLException e) {
			throw new HappyHouseException("주택 정보 조회 중 오류 발생");
		}
	}
	
	@Override
	public List<HouseDeal> searchKMP(HousePageBean bean) {
		List<HouseDeal> list = null;
		List<HouseDeal> result = new ArrayList();
		try {
			list = dao.searchAllforKMP(bean);
			String dong = bean.getDong();
			String aptName = bean.getAptname();
			if(dong != null && !dong.trim().equals("")&&aptName != null && !aptName.trim().equals("")) {
				//둘다 입력되었을때(동, 아파트이름 선택안 되었을때)
				prepareKmp(dong);
				for(HouseDeal hd : list) {
					if(kmp(hd.getDong()) || kmp(hd.getAptName())) {
						HouseDeal deal = hd;
						result.add(deal);
					}
				}
			}else if(dong != null && !dong.trim().equals("")) {
				prepareKmp(dong);
				for(HouseDeal hd : list) {
					if(kmp(hd.getDong())) {
						HouseDeal deal = hd;
						result.add(deal);
					}
				}								
			}else if(aptName != null && !aptName.trim().equals("")) {
				prepareKmp(aptName);
				for(HouseDeal hd : list) {
					if(kmp(hd.getAptName())) {
						HouseDeal deal = hd;
						result.add(deal);
					}
				}
			}else {
				return list;
			}
			return result;
		} catch (SQLException e) {
			throw new HappyHouseException("주택 정보 조회(KMP) 중 오류 발생");
		}
	}
	
	char[] pattern;
	int[] pi;
	
	private void prepareKmp(String input) {
		pattern = input.toCharArray();
		pi = new int[input.length()];
		
		int j = 0;
		for(int i =1 ; i < pattern.length; i++) {
			while(j > 0 && pattern[i] != pattern[j]){
				 j = pi[j-1];
			}
			if(pattern[i] == pattern[j]) {
				pi[i] = ++j;
			}
		}
	}
	
	private boolean kmp(String input) {
		int j = 0;
		char[] origin = input.toCharArray();
		
		for(int i = 0; i < origin.length; i++) {
			while(j > 0 && origin[i] != pattern[j]) {
				j = pi[j-1];
			}
			
			if(pattern[j] == origin[i]) {
				if(j == pattern.length-1) {
					return true;
				}else {
					j++;
				}
			}
		}
		return false;
	}	
	
}




