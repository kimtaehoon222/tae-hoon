package com.uni.mybatis.member.model.service;

import com.uni.mybatis.member.model.dto.Member;

public interface MemberService {

	Member loginMember(Member m) throws Exception;

	void insertMember(Member m) throws Exception;

}
