package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.Notice;
import com.ssafy.happyhouse.util.PageNavigation;

public interface NoticeService {
	public void writeNotice(Notice notice) throws Exception;
	public List<Notice> listNotice(int currentPage, int sizePerPage, String key, String word) throws Exception;
	
	public Notice getNotice(int id) throws Exception;
	public void modifyNotice(Notice notice) throws Exception;
	public void deleteNotice(int id) throws Exception;
	
	public PageNavigation makePageNavigation(int pg, int sizePerPage) throws Exception;
}
