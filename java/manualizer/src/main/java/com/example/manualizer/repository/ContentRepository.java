package com.example.manualizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.manualizer.entity.Content;

/** Manualテーブル : RepositoryImpl */
public interface ContentRepository extends JpaRepository<Content, Integer>{
	void deleteByMail(String mail);
	Iterable<Content> findAllByMail(String mail);
	Iterable<Content> findByTitleLike(String keyword);
	//Iterable<Content> findByTitleLikeOrfindByWhyLikeOrfindByWhoLikeOrfindByTimeLikeOrfindByContentLike(String keyword);
}
