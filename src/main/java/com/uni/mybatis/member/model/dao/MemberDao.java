package com.uni.mybatis.member.model.dao;

import org.apache.ibatis.session.SqlSession;

import com.uni.mybatis.member.model.dto.Member;

public class MemberDao {

	public Member loginMember(SqlSession sqlSession, Member m) throws Exception{
		
		Member loginUser = null;
		
		loginUser = sqlSession.selectOne("memberMapper.loginMember", m);
		
		return loginUser;
	}

	public int insertMember(SqlSession sqlSession, Member m) throws Exception{
		// TODO Auto-generated method stub
		return sqlSession.insert("memberMapper.insertMember", m);
	}

}
