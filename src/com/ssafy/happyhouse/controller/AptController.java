package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.model.dto.HousePageBean;
import com.ssafy.happyhouse.model.service.HouseService;
import com.ssafy.happyhouse.model.service.HouseServiceImpl;

@WebServlet("*.ado")
public class AptController extends HttpServlet {
	HouseService service;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service = new HouseServiceImpl();
				
		String root = request.getContextPath();
		String reqString = request.getServletPath();
		String page = "";
		
		if("/list.ado".equals(reqString)) {
			boolean[] searchType= new boolean[4];
			String[] checked = request.getParameterValues("checkArray[]");
			String key = request.getParameter("key");
			String word = request.getParameter("word");
			
			if(!"first".equals(request.getParameter("act")) && checked != null){
				for(int i = 0; i < checked.length; i++) {
					if(checked[i].equals("aptdeal")) {
						searchType[0] = true;
					}
					if(checked[i].equals("aptrent")) {
						searchType[1] = true;
					}
					if(checked[i].equals("housedeal")) {
						searchType[2] = true;
					}
					if(checked[i].equals("houserent")) {
						searchType[3] = true;
					}
				}
			}
			
			boolean exist = false;
			for (boolean searchtype : searchType) {
				if(searchtype) {exist = true; break;}
			}
			
			if(!exist) {
				System.out.println("false");
				String msg = "주택 타입은 반드시 한 개 이상을 선택해야 합니다.";
				request.setAttribute("msg", msg);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");//
				dispatcher.forward(request, response);
			}else {
				HousePageBean  bean = new HousePageBean();
				bean.setSearchType(searchType);
				if(key !=null) {
					if(key.equals("dong")) {
						bean.setDong(word);
					}else if(key.equals("name")) {
						bean.setAptname(word);
					}else {
						bean.setDong(word);
						bean.setAptname(word);
					}
				}
				
				List<HouseDeal> list = null;
				if(word == null)
					list = service.searchAll(bean);
				else
					list = service.searchKMP(bean);
				
				int len = Math.min(list.size(), 100);
				if(list!=null){
					int i=0;
					String[][]data = new String[len][5];
					for (HouseDeal deal: list) {
						data[i][0]= ""+deal.getNo();
						data[i][1]= deal.getDong();
						data[i][2]= deal.getAptName();
						data[i][3]= deal.getDealAmount();
						data[i++][4]= deal.getType();
						if(i >= len) {
							break;
						}
					}
				}
				
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				JSONArray arr = new JSONArray();
				int i = 0;
				for(HouseDeal dto : list) {
					JSONObject obj = new JSONObject();
					obj.put("no", dto.getNo());
					obj.put("dong", dto.getDong());
					obj.put("aptname", dto.getAptName());
					obj.put("amount", dto.getDealAmount());
					obj.put("type", dto.getType());
					arr.add(obj);
					i++;
					if(i >= len) {
						break;
					}
				}
				
				out.print(arr.toJSONString());	
			}
		}else if("/detail.ado".equals(reqString)) {
			int no = Integer.parseInt(request.getParameter("no"));
			HouseDeal houseDeal = service.search(no);
			String imgPath = searchImg(houseDeal);
			request.setAttribute("imgPath", imgPath);
			request.setAttribute("houseDeal", houseDeal);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/view/detail.jsp");
			dispatcher.forward(request, response);
		}else if("/findlist.ado".equals(reqString)) {
			String word = request.getParameter("word");
			String key = request.getParameter("key");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			ArrayList<HouseDeal> list = null;
			JSONArray arr = new JSONArray();
			
			try {
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else if("".equals(reqString)) {
			
		}else if("".equals(reqString)) {
			
		}
	}

	private String searchImg(HouseDeal houseDeal) {
		String aptName = houseDeal.getAptName();
		String[] jpgList = {"갑을","건양하늘터","경희궁의아침","경희궁자이","광화문풍림스페이스본","덕산","동성아파트","두산","롯데캐슬천지인",
				"무악동현대","신동아블루아광화문","아남","인왕산아이파크","창신쌍용","현대뜨레비앙"};
		String[] jfifList = {"삼성","삼전솔하임","숭인한양","엘리시아","종로센트레빌","종로중흥S클래스","종로청계힐스테이트","효성쥬얼리시티","CS타워"};
		for (String jpg : jpgList) {
			if(aptName.matches(String.format(".*%s.*", jpg))) {
				return jpg+".jpg";
			}
		}
		for(String jfif : jfifList) {
			if(aptName.matches(String.format(".*%s.*", jfif))) {
				return jfif+".jfif";
			}
		}
		return "다세대주택.jpg";
	}
}
