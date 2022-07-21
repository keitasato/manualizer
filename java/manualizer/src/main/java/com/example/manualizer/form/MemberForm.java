package com.example.manualizer.form;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Form */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberForm {
	/** メールアドレス */
	@NotBlank
	private String mail;
	
	@NotBlank
	private String nickname;
	
	@NotBlank
	private String password;

}
