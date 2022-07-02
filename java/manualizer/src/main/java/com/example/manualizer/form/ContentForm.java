package com.example.manualizer.form;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Form */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentForm {
	/** 識別ID */
	private Integer id;
	
	/** タイトル */
	@NotBlank
	private String title;
	
	/** 内容 */
	@NotBlank
	private String content;
	
	/** 登録 or 更新フラグ */
	// 登録 : true
	// 更新 : false
	// private boolean isReg;

}
