package com.example.manualizer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/** manualテーブル用 : Entity */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="member")

public class Member {
	/** メールアドレス */
	@Id
	@Column(name = "mail")
	private String mail;
	/** パスワード */
	@Column(name = "password")
	private String password;
	/** 登録日時 */
	@Column(name = "reg_date")
	private Date reg_date;
	/** 更新日時 */
	@Column(name = "upd_date")
	private Date upd_date;
	/** ニックネーム */
	@Column(name = "nickname")
	private String nickname;
}
