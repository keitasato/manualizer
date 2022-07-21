package com.example.manualizer.form;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Form */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordChangeForm {
	@NotBlank
	private String nickname;
	
	@NotBlank
	private String password;
	
	@NotBlank
	private String password_check;
}
