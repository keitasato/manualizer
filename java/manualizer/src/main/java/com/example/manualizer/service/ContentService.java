package com.example.manualizer.service;

import java.util.Optional;

import com.example.manualizer.entity.Content;

/** Contentサービス処理 */
public interface ContentService {
	/** コンテンツ情報を全件取得 */
	Iterable<Content> selectAll();
	
	/** コンテンツ情報をidをキーに１件取得 */
	Optional<Content> selectOneById(Integer id);
	
	/** コンテンツ情報を登録 */
	void insertContent(Content content);
	
	/** コンテンツ情報を更新 */
	void updateContent(Content content);
	
	/** コンテンツ情報を削除 */
	void deleteContentById(Integer id);

}
