package com.example.manualizer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	/** タイトル */
	@Column(name = "title")
	private String title;
	
	/** 理由 */
	@Column(name = "why")
	private String why;
	
	/** タイミング */
	@Column(name = "time")
	private String time;
	
	/** 誰が */
	@Column(name = "who")
	private String who;
	
	/** 内容 */
	@Column(name = "content")
	private String content;
	
	/** 登録日時 */
	@Column(name = "reg_date")
	private Date reg_date;
	
	/** 更新日時 */
	@Column(name = "upd_date")
	private Date upd_date;
	
	/** メールアドレス(ユーザID) */
	@Column(name = "mail")
	private String mail;
	
}
