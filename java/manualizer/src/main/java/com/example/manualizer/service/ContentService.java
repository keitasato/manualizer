package com.example.manualizer.service;

import java.util.Optional;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.example.manualizer.entity.Content;

@Service
@EnableTransactionManagement
/** Contentサービス処理 */
public interface ContentService {
	/** コンテンツ情報を全件取得 */
	Iterable<Content> selectAll();
	
	//Iterable<Content> selectAllLike(String keyword);
	
	Iterable<Content> selectAllByTitleLike(String keyword);
	
	Iterable<Content> selectAllByWhoLike(String keyword);
	
	Iterable<Content> selectAllByLike(String keyword); 
	
	/** コンテンツ情報をidをキーに１件取得 */
	Optional<Content> selectOneById(Integer id);
	
	/** コンテンツ情報を登録 */
	void insertContent(Content content);
	
	/** コンテンツ情報を更新 */
	void updateContent(Content content);
	
	/** コンテンツ情報を削除 */
	void deleteContentById(Integer id);
	
	/** ユーザの全コンテンツ情報を削除 */
	void deleteContentByMail(String mail);
	
	/** ユーザのコンテンツ情報を全件取得 */
	Iterable<Content> selectAllbyMail(String mail);
	
	void copyContent(Content from, Content to);

}
