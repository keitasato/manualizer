package com.example.manualizer.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.manualizer.entity.Content;

/** Manualテーブル : RepositoryImpl */
public interface ManualRepository extends CrudRepository<Content, Integer>{
}