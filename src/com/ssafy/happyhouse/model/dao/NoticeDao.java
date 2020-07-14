package com.ssafy.happyhouse.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.Notice;

public interface NoticeDao {

	public void writeNotice(Notice notice) throws Exception;
	public List<Notice> listNotice(int currentPage, int sizePerPage, String key, String word) throws Exception;
	
	public Notice getNotice(int id) throws Exception;
	public void modifyNotice(Notice notice) throws Exception;
	public void deleteNotice(int id) throws Exception;
	public int getTotalCount() throws SQLException;
}
