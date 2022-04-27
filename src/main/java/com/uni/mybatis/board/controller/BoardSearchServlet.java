package com.uni.mybatis.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uni.mybatis.board.model.dto.Board;
import com.uni.mybatis.board.model.dto.PageInfo;
import com.uni.mybatis.board.model.dto.SearchCondition;
import com.uni.mybatis.board.model.service.BoardService;
import com.uni.mybatis.board.model.service.BoardServiceImpl;
import com.uni.mybatis.common.Pagination;

/**
 * Servlet implementation class BoardSearchServlet
 */
@WebServlet("/searchBoard.do")
public class BoardSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BoardService boardService = new BoardServiceImpl();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//파라미터 값을 변수에 담아준다.
		String condition = request.getParameter("condition");
		String search = request.getParameter("search");
		
		//SearchCondition 객체 생성
		SearchCondition sc = new SearchCondition();
		
		switch(condition) {
		case "writer" : 
			sc.setWriter(search);
			break;
		case "title" : 
			sc.setTitle(search);
			break;
		case "content" : 
			sc.setContent(search);
			break;
		}
		//해당 카테고리에 맞게 총 게시글 개수를 가지고 온다.
		try {
			//총 게시글 
			int listCount = boardService.getSearchListCount(sc);
			
			//페이지 처리
			//현재 페이지 초기값 1로 설정
			int currentPage = 1;
			if(request.getParameter("currentPage") != null) {
				//값이 있으면 파라미터 값을 변수에 담아준다. 
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			}
			
			//페이지 하단에 보여질 최대 개수
			int pageLimit = 10;
			//보여지는 게시글 개수
			int boardLimit = 5;
			
			//메소드를 호출한다.
			PageInfo pi = Pagination.getPageInfo(listCount, currentPage, pageLimit, boardLimit);
			
			
			ArrayList<Board> list = boardService.selectSearchList(sc, pi);
			
			//값을 제대로 가져오면 넘겨준다.
			request.setAttribute("list", list);
			request.setAttribute("pi", pi);
			request.setAttribute("search", search);
			request.setAttribute("condition", condition);
			
			request.getRequestDispatcher("WEB-INF/views/board/boardListView.jsp").forward(request, response);

			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
