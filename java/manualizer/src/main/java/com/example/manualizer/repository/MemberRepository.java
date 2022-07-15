package com.example.manualizer.repository;

import com.example.manualizer.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

/** Memberテーブル : RepositoryImpl */
public interface MemberRepository extends JpaRepository<Member, String>{
}
