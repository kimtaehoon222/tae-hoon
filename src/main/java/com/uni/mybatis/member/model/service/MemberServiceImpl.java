package com.uni.mybatis.member.model.service;

import org.apache.ibatis.session.SqlSession;
import static com.uni.mybatis.common.Template.getSqlSession;

import com.uni.mybatis.member.model.dao.MemberDao;
import com.uni.mybatis.member.model.dto.Member;

public class MemberServiceImpl implements MemberService {
	//서비스 안에 추상메소드를 여기서 구현한다.(MemberService에 구현체)
	
	private MemberDao memberDao = new MemberDao() ;
	@Override
	public Member loginMember(Member m) throws Exception{
		
		SqlSession sqlSession = getSqlSession();
		
		Member loginUser = memberDao.loginMember(sqlSession,m);
		
		sqlSession.close();
		return loginUser;
	}
	@Override
	public void insertMember(Member m) throws Exception {
		SqlSession sqlSession = getSqlSession();
		
		int result = memberDao.insertMember(sqlSession,m);
		
		if(result > 0) {
			sqlSession.commit();
		}else {
			sqlSession.rollback();
			throw new Exception();
		}
		sqlSession.close();
		
	}


}
