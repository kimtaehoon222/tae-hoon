package com.uni.mybatis.member.model.dao;

import org.apache.ibatis.session.SqlSession;

import com.uni.mybatis.member.model.dto.Member;

public class MemberDao {

	public Member loginMember(SqlSession sqlSession, Member m) {
		
		Member loginUser = null;
		
		loginUser = sqlSession.selectOne(null);
		
		return loginUser;
	}

}
