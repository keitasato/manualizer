package com.example.manualizer.entity;

//import org.springframework.data.annotation.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.text.SimpleDateFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/** manualテーブル用 : Entity */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="content")

public class Content {
	/** 識別ID */
	@Id
	@Column(name = "id")
	private Integer id;
	/** タイトル */
	@Column(name = "title")
	private String title;
	/** 内容 */
	@Column(name = "content")
	private String content;
	/** 登録日時 */
	@Column(name = "reg_date")
	private Date reg_date;
	/** 更新日時 */
	@Column(name = "upd_date")
	private Date upd_date;
	
}
