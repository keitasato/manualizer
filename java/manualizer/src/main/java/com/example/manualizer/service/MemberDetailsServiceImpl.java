package com.example.manualizer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.manualizer.entity.Member;
import com.example.manualizer.repository.MemberRepository;


// UserDetailsServiceはユーザ名とパスワードを使用して認証するためにDaoAuthenticationProviderによって使用される
// 他の属性を取得するために、定義する
// Spring Securityは、UserDetailsServiceのJDBCを提供する
// JDBCとはJava Database Connectivityの省略で、リレーショナル・データベースにアクセスするための、標準Java API
// カスタム(自作の) UserDetailsService を Bean として公開することにより、カスタム認証を定義できる
@Service
@Transactional
public class MemberDetailsServiceImpl implements UserDetailsService {
	
	// UserDetailsはUserDetailsServiceによって返される
	// DaoAuthenticationProvider は UserDetails を検証する
	// 検証後に、UserDetailsService によって返された UserDetails であるプリンシパルを持つ Authentication を返す
	
	@Autowired
	private MemberRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException{
		Optional<Member> memberOpt = repository.findById(mail);
		if (!memberOpt.isPresent()) {
			throw new UsernameNotFoundException("Mail address : " + mail + " was not found in the database");
		}
		
		Member member = memberOpt.get();
		
		//権限のリスト
        //AdminやUserなどが存在するが、今回は利用しないのでUSERのみを仮で設定
        //権限を利用する場合は、DB上で権限テーブル、ユーザ権限テーブルを作成し管理が必要
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		GrantedAuthority authority = new SimpleGrantedAuthority("USER");
		grantList.add(authority);
		
		//rawDataのパスワードを暗号化
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		//MemberはインターフェースなのでMemberクラスのコンストラクタで生成したユーザオブジェクトをキャスト
		UserDetails userDetails = (UserDetails)new User(member.getMail(), encoder.encode(member.getPassword()),grantList);
		return userDetails;
	}
	
	
	
	

}
