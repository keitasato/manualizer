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
	
	/** 理由 */
	@NotBlank
	private String why;
	
	/** タイミング */
	@NotBlank
	private String time;
	
	/** 誰が */
	@NotBlank
	private String who;
	
	/** 内容 */
	@NotBlank
	private String content;

}
