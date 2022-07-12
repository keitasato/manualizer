package com.example.manualizer.controller;

import java.util.Date;
import java.util.Optional;

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
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

import com.example.manualizer.entity.Content;
import com.example.manualizer.entity.Member;
import com.example.manualizer.form.PasswordChangeForm;
import com.example.manualizer.form.MemberForm;
import com.example.manualizer.service.MemberService;

/** Account コントローラ */
@Controller
@RequestMapping("account")

public class AccountController {
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
	
	/** Memberを更新 */
	@GetMapping("/edit")
	public String edit(Model model) {
		// 表示用Modelへの格納
		model.addAttribute("msg", "Keita!!!");
		return "edit";
	}
	
	/** idをKeyにしてパスワードを更新する */
	@PostMapping("password_change")
	public String update(@Validated PasswordChangeForm passwordForm, BindingResult bindingResult,
			Model model, RedirectAttributes redirectAttributes) {
		// コンテンツ情報を1件更新してリダイレクト
		// ログイン成功時に呼び出されるメソッドSecurityContextHolderから認証済みユーザの情報を取得しモデルへ追加する
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//Principalからログインユーザの情報を取得
		String mail = auth.getName();
		String password = passwordForm.getPassword();
		String password_check = passwordForm.getPassword_check();
		
		Optional<Member> memberOpt = service.selectOneByMail(mail);
		if (memberOpt.isPresent() && password.equals(password_check) ) {
			Member member = memberOpt.get();
			member.setMail(mail);
			member.setPassword(password);
			
			// 日付をEntityへ格納
			Date date = new Date();
			long timeInMilliSeconds = date.getTime();
			java.sql.Date sqlDate = new java.sql.Date(timeInMilliSeconds);
			member.setUpd_date(date);
			
			service.updateMember(member);
			
			redirectAttributes.addFlashAttribute("updcomplete", "更新が完了しました");
			return "redirect:/content";
		} else {
			// redirectAttributes.addFlashAttribute("updcomplete", "更新に失敗しました");
			return "edit";
		}
	}
	
	/** EmailをKeyにしてデータを削除する */
	/** http://localhost:8080/account/delete にPOSTでアクセスすると、以下の処理がされてreturnされたhtmlファイルが読み込まれる */
	@PostMapping("delete")
	public String delete(Model model, RedirectAttributes redirectAttributes) {
		// コンテンツ情報を1件削除してリダイレクト
		// ログイン成功時に呼び出されるメソッドSecurityContextHolderから認証済みユーザの情報を取得しモデルへ追加する
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//Principalからログインユーザの情報を取得
		String mail = auth.getName();
		service.deleteMemberByMail(mail);
		redirectAttributes.addFlashAttribute("delcomplete", "削除が完了しました");
		return "login";
	}
	
}