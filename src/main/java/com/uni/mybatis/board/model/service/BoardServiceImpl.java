package com.uni.mybatis.board.model.service;

import static com.uni.mybatis.common.Template.getSqlSession;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import com.uni.mybatis.board.model.dao.BoardDao;
import com.uni.mybatis.board.model.dto.Board;
import com.uni.mybatis.board.model.dto.PageInfo;

public class BoardServiceImpl implements BoardService {
	private BoardDao boardDao = new BoardDao();
	@Override
	public int getListCount() throws Exception{
		SqlSession sqlSession = getSqlSession();
		int listCount = boardDao.getListCount(sqlSession);
		System.out.println("impl listCount : " + listCount);
		sqlSession.close();
		return listCount;
	}
	@Override
	public ArrayList<Board> selectList(PageInfo pi) throws Exception {
		SqlSession sqlSession = getSqlSession();
		ArrayList<Board> list = boardDao.selectList(sqlSession, pi);
		sqlSession.close();
		
		
		return list;
	}

}
