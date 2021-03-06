package com.example.manualizer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.manualizer.entity.Member;
import com.example.manualizer.repository.MemberRepository;

@Transactional
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberRepository repository;
	
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
	public boolean existsByMail(String mail) {
		return repository.existsById(mail);
	}

}
