package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.HappyHouseException;
import com.ssafy.happyhouse.model.dao.NoticeDao;
import com.ssafy.happyhouse.model.dao.NoticeDaoImpl;
import com.ssafy.happyhouse.model.dto.Notice;
import com.ssafy.happyhouse.util.PageNavigation;

public class NoticeServiceImpl implements NoticeService {
	
	private NoticeDao dao;
	
	public NoticeServiceImpl() {
		dao = new NoticeDaoImpl();
	}
	
	@Override
	public void writeNotice(Notice notice) throws Exception {
		if(notice.getTitle()==null || notice.getContent()==null) {
			throw new HappyHouseException("제목과 내용은 필수 값입니다.");
		}
		dao.writeNotice(notice);
	}

	@Override
	public List<Notice> listNotice(int currentPage, int sizePerPage, String key, String word) throws Exception {
		key = key == null ? "" : key;
		word = word == null ? "" : word;
		return dao.listNotice(currentPage, sizePerPage, key, word);
	}

	@Override
	public Notice getNotice(int id) throws Exception {
		return dao.getNotice(id);
	}

	@Override
	public void modifyNotice(Notice notice) throws Exception {
		dao.modifyNotice(notice);
	}

	@Override
	public void deleteNotice(int id) throws Exception {
		dao.deleteNotice(id);
	}

	@Override
	public PageNavigation makePageNavigation(int currentPage, int sizePerPage) throws Exception {
	      PageNavigation pageNavigation = new PageNavigation();
	      int naviSize = 10;//페이지 갯수
	      pageNavigation.setCurrentPage(currentPage);
	      pageNavigation.setNaviSize(naviSize);
	      int totalCount = dao.getTotalCount();//총 게시글 수
	      pageNavigation.setTotalCount(totalCount);
	      int totalPageCount = (totalCount-1) / sizePerPage + 1;//총 페이지 수
	      pageNavigation.setTotalPageCount(totalPageCount);
	      boolean startRange = currentPage <= naviSize;//startRange true : 이전X false: 이전O
	      pageNavigation.setStartRange(startRange);
	      boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;//endRange true: 다음 X false: 다음 O
	      pageNavigation.setEndRange(endRange);
	      pageNavigation.makeNavigator();
	      return pageNavigation;
	}

}
