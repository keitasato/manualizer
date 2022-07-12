package com.example.manualizer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.manualizer.entity.Content;

/** Manualテーブル : RepositoryImpl */
public interface ContentRepository extends JpaRepository<Content, Integer>{
}