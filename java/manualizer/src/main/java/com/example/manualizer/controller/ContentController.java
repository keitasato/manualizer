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

import com.example.manualizer.entity.Content;
import com.example.manualizer.entity.Member;
import com.example.manualizer.form.ContentForm;
import com.example.manualizer.service.ContentService;
import com.example.manualizer.service.MemberService;

import java.util.List;
import java.util.ArrayList;

/** Content コントローラ */
@Controller
@RequestMapping("content")
public class ContentController {
	/** DI対象 */
	@Autowired
	ContentService service;
	@Autowired
	MemberService memberService;
	/** form-backing bean の初期化 */
	@ModelAttribute
	public ContentForm setUpForm() {
		ContentForm form = new ContentForm();
		/** 何かのデフォルト値設定 */
		return form;
	}
	
	/** Contentの一覧を表示 */
	/** http://localhost:8080/content/ にGETでアクセスすると、以下の処理がされてreturnされたhtmlファイルが読み込まれる */
	@GetMapping("")
	public String showList(Model model) {
		// ログイン成功時に呼び出されるメソッドSecurityContextHolderから認証済みユーザの情報を取得しモデルへ追加する
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//Principalからログインユーザの情報を取得
		String mail = auth.getName();
		
		// コンテンツの一覧を取得
		Iterable<Content> list = service.selectAll();
		List<Content> result = new ArrayList<Content>();
		
		// カードに表示する説明文を２０文字以下に抑える
		// メールアドレスの代わりにニックネームを格納する
		int maxLength =20;
		for (Content content : list) {
			Content addContent = new Content();
			
			service.copyContent(content, addContent);
			
			String explain = content.getContent();
			int length = explain.length();
			int cut = Math.min(length, maxLength);
			
			if(length >= maxLength) addContent.setContent(explain.substring(0, cut) + "...");
			else addContent.setContent(explain);
			
			// コンテンツの作成者のニックネームを表示用に格納
			Optional<Member> memberOpt = memberService.selectOneByMail(content.getMail());
			Member member = memberOpt.get();
			addContent.setMail(member.getNickname());
			
			result.add(addContent);
		}
		
		// 表示用Modelへの格納
		model.addAttribute("list", result);
		
		return "index";
	}
	
	/** 検索ワードに合致するContentの一覧を表示 */
	/** http://localhost:8080/content/ にGETでアクセスすると、以下の処理がされてreturnされたhtmlファイルが読み込まれる */
	@GetMapping("search")
	public String showSearchList2(@RequestParam(defaultValue = "NOT PARAM") String keyword, Model model) {
		// ログイン成功時に呼び出されるメソッドSecurityContextHolderから認証済みユーザの情報を取得しモデルへ追加する
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//Principalからログインユーザの情報を取得
		String mail = auth.getName();
		//System.out.println("searching");
		//System.out.println(searchForm.getKeyword());
		
		// コンテンツの一覧を取得
		//Iterable<Content> list = service.selectAllLike(form.getKeyword());
		Iterable<Content> list = service.selectAllByLike("%"+keyword+"%");
		List<Content> result = new ArrayList<Content>();
		
		// カードに表示する説明文を２０文字以下に抑える
		// メールアドレスの代わりにニックネームを格納する
		int maxLength =20;
		for (Content content : list) {
			Content addContent = new Content();
			
			service.copyContent(content, addContent);
			
			String explain = content.getContent();
			int length = explain.length();
			int cut = Math.min(length, maxLength);
			
			if(length >= maxLength) addContent.setContent(explain.substring(0, cut) + "...");
			else addContent.setContent(explain);
			
			// コンテンツの作成者のニックネームを表示用に格納
			Optional<Member> memberOpt = memberService.selectOneByMail(content.getMail());
			Member member = memberOpt.get();
			addContent.setMail(member.getNickname());
			
			result.add(addContent);
		}
		
		// 表示用Modelへの格納
		model.addAttribute("list", result);
		
		return "index";
	}
	
	
	/** ログインユーザのContentの一覧を表示 */
	/** http://localhost:8080/content/myManual にGETでアクセスすると、以下の処理がされてreturnされたhtmlファイルが読み込まれる */
	@GetMapping("myManual")
	public String myManual(Model model, RedirectAttributes redirectAttributes) {
		// ログイン成功時に呼び出されるメソッドSecurityContextHolderから認証済みユーザの情報を取得しモデルへ追加する
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//Principalからログインユーザの情報を取得
		String mail = auth.getName();
		
		// コンテンツの一覧を取得
		Iterable<Content> list = service.selectAllbyMail(mail);
		List<Content> result = new ArrayList<Content>();
		
		// カードに表示する説明文を２０文字以下に抑える
		// メールアドレスの代わりにニックネームを格納する
		int maxLength =20;
		for (Content content : list) {
			Content addContent = new Content();
			
			service.copyContent(content, addContent);
			
			String explain = content.getContent();
			int length = explain.length();
			int cut = Math.min(length, maxLength);
			if(length >= maxLength) addContent.setContent(explain.substring(0, cut) + "...");
			else addContent.setContent(explain);
			Optional<Member> memberOpt = memberService.selectOneByMail(content.getMail());
			Member member = memberOpt.get();
			addContent.setMail(member.getNickname());
			
			result.add(addContent);
		}
		
		model.addAttribute("list", result);
		
		return "myManual";
	}
	
	/** Contentを新規登録 */
	/** http://localhost:8080/content/create にGETでアクセスすると、以下の処理がされてreturnされたhtmlファイルが読み込まれる */
	@GetMapping("create")
	public String create(Model model) {
		return "create";
	}
	
	/** Contentデータを1件挿入 */
	/** http://localhost:8080/content/insert にPOSTでアクセスすると、以下の処理がされてreturnされたhtmlファイルが読み込まれる */
	@PostMapping("insert")
	public String insert(@Validated ContentForm contentForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		// FormからEntityへの詰め替え
		Content content = new Content();
		content.setTitle(contentForm.getTitle());
		content.setContent(contentForm.getContent());
		content.setWho(contentForm.getWho());
		content.setWhy(contentForm.getWhy());
		content.setTime(contentForm.getTime());
		
		// 日付をEntityへ格納
		Date date = new Date();
		content.setReg_date(date);
		content.setUpd_date(date);
		
		// ログイン成功時に呼び出されるメソッドSecurityContextHolderから認証済みユーザの情報を取得しモデルへ追加する
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//Principalからログインユーザの情報を取得
		String mail = auth.getName();
		content.setMail(mail);
		
		// 入力チェック
		if (!bindingResult.hasErrors()) {
			service.insertContent(content);
			redirectAttributes.addFlashAttribute("complete", "登録が完了しました");
			return "redirect:/content";
		} else {
			// エラーがある場合は、一覧表示処理を呼び出す
			return showList(model);
		}
		
	}
	
	/** Contentを参照 */
	/** http://localhost:8080/content/show にGETでアクセスすると、以下の処理がされてreturnされたhtmlファイルが読み込まれる */
	@GetMapping("/show_{id}")
	public String show(@PathVariable Integer id, Model model) {
		// ログイン成功時に呼び出されるメソッドSecurityContextHolderから認証済みユーザの情報を取得しモデルへ追加する
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//Principalからログインユーザの情報を取得
		String mail = auth.getName();
		
		// Contentを取得(Optionalでラップ)
		Optional<Content> contentOpt = service.selectOneById(id);
		if (contentOpt.isPresent()) {
			Content content = contentOpt.get();
			model.addAttribute("content", content);
			
			// 自分の作成したマニュアルか否かのフラグ
			boolean own = content.getMail().equals(mail);
			model.addAttribute("own", own);
		}
		
		return "show";
	}
	
	
	/** Contentを参照 */
	/** http://localhost:8080/content/show にGETでアクセスすると、以下の処理がされてreturnされたhtmlファイルが読み込まれる */
	@GetMapping("/edit_{id}")
	public String edit(@PathVariable Integer id, Model model) {
		// Contentを取得(Optionalでラップ)
		Optional<Content> contentOpt = service.selectOneById(id);
		if (contentOpt.isPresent()) {
			Content content = contentOpt.get();
			model.addAttribute("content", content);
		}
		return "update";
	}
	
	/** idをKeyにしてデータを更新する */
	/** http://localhost:8080/content/update にPOSTでアクセスすると、以下の処理がされてreturnされたhtmlファイルが読み込まれる */
	@PostMapping("update")
	public String update(@Validated ContentForm contentForm, BindingResult bindingResult,
			Model model, RedirectAttributes redirectAttributes) {
		// コンテンツ情報を1件更新してリダイレクト
		Optional<Content> contentOpt = service.selectOneById(contentForm.getId());
		if (contentOpt.isPresent()) {
			Content content = contentOpt.get();
			content.setTitle(contentForm.getTitle());
			content.setContent(contentForm.getContent());
			service.updateContent(content);
			redirectAttributes.addFlashAttribute("updcomplete", "更新が完了しました");
			return "redirect:/content";
		} else {
			redirectAttributes.addFlashAttribute("updcomplete", "更新に失敗しました");
			return "redirect:/content";
		}
	}
	
	/** idをKeyにしてデータを削除する */
	/** http://localhost:8080/content/delete にPOSTでアクセスすると、以下の処理がされてreturnされたhtmlファイルが読み込まれる */
	@PostMapping("delete")
	public String delete(@RequestParam("id") String id, Model model, RedirectAttributes redirectAttributes) {
		// コンテンツ情報を1件削除してリダイレクト
		service.deleteContentById(Integer.parseInt(id));
		redirectAttributes.addFlashAttribute("delcomplete", "削除が完了しました");
		return "redirect:/content";
	}
	
	// 以下はFormとDomainObjectに詰め直しメソッド
	
	/** ContentFormからContentに詰め直して戻り値として返す */
	private Content makeContent(ContentForm contentForm) {
		Content content = new Content();
		content.setId(contentForm.getId());
		content.setTitle(contentForm.getTitle());
		content.setContent(contentForm.getContent());
		Date date = new Date();
		long timeInMilliSeconds = date.getTime();
		java.sql.Date sqlDate = new java.sql.Date(timeInMilliSeconds);
		content.setUpd_date(date);
		
		return content;
	}
	
	/** ContentからContentFormに詰め直して戻り値として返す */
	private ContentForm makeContentForm(Content content) {
		ContentForm form = new ContentForm();
		form.setId(content.getId());
		form.setTitle(content.getTitle());
		form.setContent(content.getContent());
		
		return form;
	}
}
