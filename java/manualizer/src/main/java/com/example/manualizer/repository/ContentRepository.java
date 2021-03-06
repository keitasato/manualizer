package com.example.manualizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.manualizer.entity.Content;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/** Manualテーブル : RepositoryImpl */
public interface ContentRepository extends JpaRepository<Content, Integer>{
	void deleteByMail(String mail);
	Iterable<Content> findAllByMail(String mail);
	Iterable<Content> findByTitleLike(String keyword);
	Iterable<Content> findByWhoLike(String keyword);
	//Iterable<Content> findByTitleLikeOrfindByWhoLike(String keyword, String keyword2);
	//Iterable<Content> findByTitleLikeOrfindByWhyLikeOrfindByWhoLikeOrfindByTimeLikeOrfindByContentLike(String keyword);
	
	@Query("SELECT c FROM Content c WHERE c.title LIKE %:keyword% OR c.content LIKE %:keyword% OR c.why LIKE %:keyword% OR c.time LIKE %:keyword% OR c.who LIKE %:keyword%")
    Iterable<Content> searchByLike(@Param("keyword") String keyword);
}
