package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.model.dto.UserInfo;

public interface UserInfoService {
	/**
	 * 새로운 사용자 정보를 db에 넣는 작업을 한다
	 */
	public void insert(UserInfo userinfo);
		
	/**
	 * uid에 해당하는 사용자의 정보를 table에서 제거한다..  
	 * @param uid 삭제할 사용자의 id
	 */
	public void delete(String id, String password);
	
	/**
	 * uid에 해당하는 사용자의 정보를 table에서 제거한다..  
	 * @param uid 수정할 사용자의 id, num 수정할 data의 값(1 : password, 2 : name, 3 : address, 4 : phone)
	 * @return 수정된 사용자의 정보
	 */
	public void update(String uid, int num, String post);
	
	/**
	 * uid에 해당하는 사용자의 정보가 table에 있다면 정보를 반환..  
	 * @param uid 삭제할 사용자의 id
	 * @return 사용자의 userinfo, 없다면 null을 반환한다.
	 */
	public UserInfo select(String uid);

}
