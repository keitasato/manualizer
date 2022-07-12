package com.example.manualizer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.manualizer.entity.Content;
import com.example.manualizer.repository.ContentRepository;

@Transactional
public class ContentServiceImpl implements ContentService {
	/** Repository 注入 */
	@Autowired
	ContentRepository repository;

	@Override
	public Iterable<Content> selectAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Content> selectOneById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public void insertContent(Content content) {
		repository.save(content);
	}

	@Override
	public void updateContent(Content content) {
		repository.save(content);
	}

	@Override
	public void deleteContentById(Integer id) {
		repository.deleteById(id);
	}

}
