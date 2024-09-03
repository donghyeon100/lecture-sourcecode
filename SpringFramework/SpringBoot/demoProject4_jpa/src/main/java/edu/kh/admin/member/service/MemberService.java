package edu.kh.admin.member.service;

import java.util.List;

import edu.kh.admin.member.domain.MemberEntity;

public interface MemberService {

	List<MemberEntity> findAll();

}
