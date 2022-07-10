package com.example.manualizer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.manualizer.entity.Member;
import com.example.manualizer.repository.MemberRepository;

public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberRepository repository;
	
	@Override
	public Optional<Member> selectOneByMail(String mail) {
		return repository.findById(mail);
	}

	@Override
	public void insertMember(Member member) {
		repository.save(member);
	}

	@Override
	public void updateMember(Member member) {
		repository.save(member);
	}

	@Override
	public void deleteMemberByMail(String mail) {
		repository.deleteById(mail);
	}
	
	@Override
	public boolean existsByUsername(String mail) {
		return repository.existsById(mail);
	}

}