package com.uni.mybatis.board.model.service;

import java.util.ArrayList;

import com.uni.mybatis.board.model.dto.Board;
import com.uni.mybatis.board.model.dto.PageInfo;

public interface BoardService {

	int getListCount() throws Exception;

	ArrayList<Board> selectList(PageInfo pi) throws Exception;

}
