package com.example.manualizer.entity;

import org.springframework.data.annotation.Id;
import java.text.SimpleDateFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/** manualテーブル用 : Entity */
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Content {
	/** 識別IDE */
	@Id
	private Integer id;
	/** タイトル */
	private String title;
	/** 内容 */
	private String content;
	/** 登録日時 */
	private Date reg_date;
	/** 更新日時 */
	private Date upd_date;
	
}
