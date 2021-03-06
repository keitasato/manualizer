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
	
	/**
	@Override
	public Iterable<Content> selectAllLike(String keyword){
		return repository.findByTitleLikeOrfindByWhyLikeOrfindByWhoLikeOrfindByTimeLikeOrfindByContentLike(keyword);
	}
	*/
	
	@Override
	public Iterable<Content> selectAllByTitleLike(String keyword){
		return repository.findByTitleLike(keyword);
	}
	
	@Override
	public Iterable<Content> selectAllByWhoLike(String keyword){
		return repository.findByWhoLike(keyword);
	}
	
	@Override
	public Iterable<Content> selectAllByLike(String keyword){
		//return repository.findByTitleLike(keyword);
		return repository.searchByLike(keyword);
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
	
	@Override
	public void deleteContentByMail(String mail) {
		repository.deleteByMail(mail);
	}
	
	@Override
	public Iterable<Content> selectAllbyMail(String mail) {
		return repository.findAllByMail(mail);
	}
	
	@Override
	public void copyContent(Content from, Content to) {
		to.setId(from.getId());
		to.setMail(from.getMail());
		to.setTitle(from.getTitle());
		to.setWhy(from.getWhy());
		to.setWho(from.getWho());
		to.setTime(from.getTime());
		to.setContent(from.getContent());
		to.setReg_date(from.getReg_date());
		to.setUpd_date(from.getUpd_date());
	}
}