package com.example.manualizer.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.manualizer.entity.Member;

/** Contentテーブル : RepositoryImpl */
public interface MemberRepository extends CrudRepository<Member, String>{
	public Member findByMail(String email);
}
