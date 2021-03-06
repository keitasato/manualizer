package com.example.manualizer.controller;

//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.mail.MailException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.MailSender;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

import com.example.manualizer.entity.Member;
import com.example.manualizer.form.MemberForm;
import com.example.manualizer.service.MemberService;

/** Register コントローラ */
@Controller
@RequestMapping("register")

public class RegisterController {
	/** DI対象 */
	@Autowired
	MemberService service;
	@Autowired
	MailSender mailSender;
	
	private static String secret = "1357";
	private static String salt = "2468";
	
	/** form-backing bean の初期化 */
	@ModelAttribute
	public MemberForm setUpForm() {
		MemberForm form = new MemberForm();
		/** 何かのデフォルト値設定 */
		return form;
	}
	
	
	/** Memberを新規登録する画面に遷移するだけ */
	@GetMapping("/signup")
	public String signup(Model model) {
		return "signup";
	}
	
	/** Memberを新規登録 */
	@PostMapping("/mail")
	public String mail(@Validated MemberForm memberForm, BindingResult bindingResult, Model model) {
		String mail = memberForm.getMail();
		boolean isMember = service.existsByMail(mail);
		// 表示用Modelへの格納
		
		model.addAttribute("mail", mail);
		if (isMember) {
			model.addAttribute("msg", "は既に登録されています");
		}else {
			model.addAttribute("msg", "にメールを送信しました");
			
			TextEncryptor encryptor3 = Encryptors.text(secret, salt);
		    String secretMail = encryptor3.encrypt(mail);
			
	         String from = "manualizer.register@gmail.com";
	         String title = "Manualizer アカウント登録の確認";
	         String content = mail + "さん" + "\n" + "\n" + "以下のリンクにアクセスしてパスワードを設定し、アカウントを認証してください" + "\n"
	        		 //+"http://" + IPadnPort
	                 //+ "/validate"+ "?id=" + vali ;
	                 +"http://localhost:8080/register/mailLink"
	                 + "?mail=" + secretMail;    
	         
	         try {
	                SimpleMailMessage msg = new SimpleMailMessage();
	                msg.setFrom(from);
	                msg.setTo(mail);
	                msg.setSubject(title);// タイトルの設定
	                msg.setText(content); // 本文の設定
	                mailSender.send(msg);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
		}
		
		return "mail";
	}
	
	@GetMapping("/mailLink")
	public String password(@RequestParam(name = "mail") String secretMail, Model model, RedirectAttributes redirectAttributes) {
		TextEncryptor encryptor3 = Encryptors.text(secret, salt);
		String mail = encryptor3.decrypt(secretMail);
		
		if (service.existsByMail(mail)) {
			redirectAttributes.addFlashAttribute("decomplete", mail + "は既に登録されています");
			return "redirect:/login";
		}else {
			model.addAttribute("mail", mail);
			model.addAttribute("complete", "登録が完了しました");
			return "password";
		}
	}
	
	@PostMapping("/done")
	public String done(@Validated MemberForm memberForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		String mail = memberForm.getMail();
		String nickname = memberForm.getNickname();
		String password = memberForm.getPassword();
		
		Member member = new Member();
		member.setMail(mail);
		member.setNickname(nickname);
		member.setPassword(password);
		// 日付をEntityへ格納
		Date date = new Date();
		member.setReg_date(date);
		member.setUpd_date(date);
		
		// 入力チェック
		if (!bindingResult.hasErrors()) {
			service.insertMember(member);
			redirectAttributes.addFlashAttribute("complete", "登録が完了しました");
			return "redirect:/login";
		}
		
		return "/login";
	}
	

}
