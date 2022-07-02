package com.example.manualizer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.manualizer.entity.Content;
import com.example.manualizer.service.ContentService;

import java.util.Date;

@SpringBootApplication
public class ManualizerApplication {
	/** 起動メソッド */
	public static void main(String[] args) {
		SpringApplication.run(ManualizerApplication.class, args);
		// SpringApplication.run(ManualizerApplication.class, args).getBean(ManualizerApplication.class).execute();
	}
	/** 注入 */
	@Autowired
	ContentService service;
	
	/** 実行メソッド */
	private void execute() {
		// 登録処理
		// setup();
		
		// 全件取得
		// showList();
		
		// 1件取得
		// showOne();
		
		// 更新処理
		// updateContent();
		
		// 削除処理
		// deleteContent();
	}
	/** マニュアルを5件登録します */
	private void setup() {
		System.out.println("--- 登録処理開始 ---");
		
		// エンティティ生成
		Date date1 = new Date();
		long timeInMilliSeconds1 = date1.getTime();
		java.sql.Date sqlDate1 = new java.sql.Date(timeInMilliSeconds1);
		Content content1 = new Content(null, "サンプル01", "マニュアルサンプル1の説明文", sqlDate1, sqlDate1);
		
		Date date2 = new Date();
		long timeInMilliSeconds2 = date2.getTime();
		java.sql.Date sqlDate2 = new java.sql.Date(timeInMilliSeconds2);
		Content content2 = new Content(null, "サンプル02", "マニュアルサンプル2の説明文", sqlDate2, sqlDate2);
		
		Date date3 = new Date();
		long timeInMilliSeconds3 = date3.getTime();
		java.sql.Date sqlDate3 = new java.sql.Date(timeInMilliSeconds2);
		Content content3 = new Content(null, "サンプル03", "マニュアルサンプル3の説明文", sqlDate3, sqlDate3);
		
		// リストにエンティティを格納
		List<Content> contentList = new ArrayList<>();
		Collections.addAll(contentList, content1, content2, content3);
		// 登録実行
		for (Content content : contentList) {
			service.insertContent(content);
		}
		
		System.out.println("--- 登録処理完了 ---");
		
	}

	/** 全件取得 */
	private void showList() {
		System.out.println("--- 全件取得開始 ---");
		// リポジトリを利用して全件取得を実施、結果を取得
		Iterable<Content> contents = service.selectAll();
		for (Content content : contents){
			System.out.println(content);
		}
		System.out.println("--- 全件取得終了 ---");
	}
	
	private void showOne() {
		System.out.println("--- 1件取得開始 ---");
		// リポジトリを利用して1件取得を実施、結果を取得(返り値はOptional)
		Optional<Content> contentOpt = service.selectOneById(1);
		// 値の存在チェック
		if(contentOpt.isPresent()) {
			System.out.println(contentOpt.get());
		} else {
			System.out.println("該当するマニュアルが存在しません。");
		}
		System.out.println("--- 1件取得終了 ---");
	}
	
	/** 更新処理 */
	private void updateContent() {
		System.out.println("--- 更新処理開始 ---");
		// 変更したいエンティティを生成する
		Date date1 = new Date();
		long timeInMilliSeconds1 = date1.getTime();
		java.sql.Date sqlDate1 = new java.sql.Date(timeInMilliSeconds1);
		Content content1 = new Content(1, "サンプル01", "マニュアルサンプル1の説明文,更新済", sqlDate1, sqlDate1);
		// 更新実行
		service.updateContent(content1);
		// 更新確認
		System.out.println("更新したデータは、" + content1 + "です。");
		System.out.println("--- 更新処理終了 ---");
	}
	
	private void deleteContent() {
		System.out.println("--- 削除処理開始 ---");
		// 削除実行
		service.deleteContentById(2);
		System.out.println("--- 削除処理終了 ---");
	}
}