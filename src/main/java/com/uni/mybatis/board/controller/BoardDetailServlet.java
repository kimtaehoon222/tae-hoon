package com.uni.mybatis.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uni.mybatis.board.model.dto.Board;
import com.uni.mybatis.board.model.service.BoardService;
import com.uni.mybatis.board.model.service.BoardServiceImpl;

/**
 * Servlet implementation class BoardDetailServlet
 */
@WebServlet("/detailBoard.do")
public class BoardDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터 값을 변수에 넣어줌, String 타입으로 넘어오니 형변환
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		Board b;
		try {
			b = boardService.selectBoard(bno);
			
			
			//DetailView에서 b로 받아서 사용함. 그래서 키값을 b로 넘겨준다
			request.setAttribute("b", b);
			
			request.getRequestDispatcher("WEB-INF/views/board/boardDetailView.jsp").forward(request, response);
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
