package com.uni.mybatis.board.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.uni.mybatis.board.model.dto.Board;
import com.uni.mybatis.board.model.dto.PageInfo;
import com.uni.mybatis.board.model.dto.SearchCondition;

public class BoardDao {

	public int getListCount(SqlSession sqlSession) throws Exception{
		
		return sqlSession.selectOne("boardMapper.getListCount");
	}

	public ArrayList<Board> selectList(SqlSession sqlSession, PageInfo pi) throws Exception{
		// * offset : 몇개의 게시글을 건너 뛰고 조회할 지에 대해 계산
		// ex) boardLimit : 5
		// currentPage = 1		1~5		0개의 게시글 건너 뛰고 1부터 5개 조회
		// currentPage = 2		6~10	5개의 게시글 건너 뛰고 6부터 5개 조회
		// currentPage = 3		11~15	10개의 게시글 건너 뛰고 11부터 5개 조회
		// currentPage = 4		16~20	15개의 게시글 건너 뛰고 16부터 5개 조회
		
		
		////현재 페이지에 -1 * 게시글 갯수
		int offset = (pi.getCurrentPage()-1)*pi.getBoardLimit();
		
		
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());

		/* 1 페이지 ( 1 ~ 10 )
		 10건 조회
		 10건 조회
		 
		 2 페이지 ( 11 ~ 20 )
		 10건 조회
		 20개 조회 후 10번째 부터 10건
		 
		 3 페이지 ( 21 ~ 30 )
		 10건 조회
		 30개 조회 후 20번째 부터 10건
		.
		.
		.
		 10 페이지 ( 91 ~ 100 )
		 10건 조회
		 100개 조회 후 90번째 부터 10건*/

		
		
		
		
		
		return (ArrayList)sqlSession.selectList("boardMapper.selectList",null, rowBounds);
	}
	
	public int getSearchListCount(SqlSession sqlSession, SearchCondition sc) {
		
		return sqlSession.selectOne("boardMapper.getSearchListCount",sc);
	}

	public ArrayList<Board> selectSearchList(SqlSession sqlSession, SearchCondition sc, PageInfo pi) {
		
		//몇개의 게시글을 건너 뛰고 조회할 지에 대해 계산
		int offset = (pi.getCurrentPage()-1)*pi.getBoardLimit();
		
		//mybatis에서 제공하는 RowBounds : 전체를 조회해와서 몇 개의 게시글을 건너뛰고 조회할지 계산을 내부적으로 해준다.
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());//offset정보와 뿌려질 게시글을 넣어준다.
		
		
		return (ArrayList)sqlSession.selectList("boardMapper.selectSearchList",sc, rowBounds);
	}

	public Board selectBoard(SqlSession sqlSession, int bno) {
		
		return sqlSession.selectOne("boardMapper.selectBoard",bno);
		
	}

	public int updateCount(SqlSession sqlSession, int bno) {
		
		return sqlSession.update("boardMapper.updateCount",bno);
	}

}
