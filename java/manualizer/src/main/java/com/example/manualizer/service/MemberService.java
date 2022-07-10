package com.example.manualizer.service;

import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.manualizer.entity.Content;
import com.example.manualizer.entity.Member;

/** Contentサービス処理 */
public interface MemberService {
	//* メンバー情報をmail(メールアドレス)をキーに、検索
	//UserDetails loadMemberByMail(String mail);
	
	/** コンテンツ情報をmailをキーに１件取得 */
	Optional<Member> selectOneByMail(String mail);
	
	/** コンテンツ情報を登録 */
	void insertMember(Member member);
	
	/** コンテンツ情報を更新 */
	void updateMember(Member member);
	
	/** コンテンツ情報を削除 */
	void deleteMemberByMail(String mail);
	
	// 登録済のメールアドレスか否かを判定
	boolean existsByUsername(String mail);
	
}
