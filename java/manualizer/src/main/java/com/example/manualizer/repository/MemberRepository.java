package com.example.manualizer.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.manualizer.entity.Member;

/** Memberテーブル : RepositoryImpl */
public interface MemberRepository extends CrudRepository<Member, String>{
}
