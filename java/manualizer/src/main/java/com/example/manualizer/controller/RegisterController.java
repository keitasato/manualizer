package com.example.manualizer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.MailException
//import org.springframework.mail.MailSender

import com.example.manualizer.entity.Content;
import com.example.manualizer.entity.Member;
import com.example.manualizer.form.ContentForm;
import com.example.manualizer.form.MemberForm;
import com.example.manualizer.service.ContentService;
import com.example.manualizer.service.MemberService;

/** Register コントローラ */
@Controller
@RequestMapping("register")

public class RegisterController {
	/** DI対象 */
	@Autowired
	MemberService service;
	
	/** form-backing bean の初期化 */
	@ModelAttribute
	public MemberForm setUpForm() {
		MemberForm form = new MemberForm();
		/** 何かのデフォルト値設定 */
		return form;
	}
	
	
	/** Memberを新規登録 */
	/** http://localhost:8080/register/signup にGETでアクセスすると、以下の処理がされてreturnされたhtmlファイルが読み込まれる */
	@GetMapping("/signup")
	public String signup(Model model) {
		// 表示用Modelへの格納
		model.addAttribute("msg", "Keita!!!");
		return "signup";
	}
	
	/** Memberを新規登録 */
	/** http://localhost:8080/register/mail にPOSTでアクセスすると、以下の処理がされてreturnされたhtmlファイルが読み込まれる */
	@PostMapping("/mail")
	public String mail(@Validated MemberForm memberForm, BindingResult bindingResult, Model model) {
		String mail = memberForm.getUsername();
		boolean isMember = service.existsByUsername(mail);
		// 表示用Modelへの格納
		model.addAttribute("username", mail);
		
		if (isMember) {
			model.addAttribute("msg", "は既に登録されています");
		}else {
			model.addAttribute("msg", "にメールを送信しました");
			
		}
		
		
		
		return "mail";
	}
	
	

}
