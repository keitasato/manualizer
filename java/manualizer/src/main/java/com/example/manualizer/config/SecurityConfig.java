package com.example.manualizer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.boot.autoconfigure.security.servlet.PathRequest;

import com.example.manualizer.service.MemberDetailsServiceImpl;
import com.example.manualizer.service.MemberServiceImpl;
import com.example.manualizer.service.ContentServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.formLogin(login -> login
                .loginProcessingUrl("/sign_in") //ログイン処理のパス
                .loginPage("/login") //ログインページ
                .usernameParameter("mail") //リクエストパラメータのname属性を明示
                .passwordParameter("password")
                .defaultSuccessUrl("/content") //ログイン成功時の遷移先
                .failureUrl("/login?error")
                .permitAll()
        ).logout(logout -> logout
                .logoutSuccessUrl("/logout") //ログアウトのURL
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/content") //ログアウト時の遷移先 POSTでアクセス
        ).authorizeHttpRequests(authz -> authz
                //.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .mvcMatchers("/css/**").permitAll()
                .mvcMatchers("/img/**").permitAll()
                .mvcMatchers("/js/**").permitAll()
                .mvcMatchers("/register/**").permitAll()
                //.mvcMatchers("/").permitAll()
                //.mvcMatchers("/general").hasRole("GENERAL")
                //.mvcMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated()
        );
        return http.build();
    }
	
	/**
	@Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
            .username("user")
            .password("password")
            .roles("USER")
            .build();
        return new InMemoryUserDetailsManager(user);
    }
	*/
	
	@Bean
	public ContentServiceImpl contentService() {
		return new ContentServiceImpl();
	}
	
	@Bean
	public MemberServiceImpl memberService() {
		return new MemberServiceImpl();
	}
	
	@Bean
	public MemberDetailsServiceImpl customUserDetailsService() {
		return new MemberDetailsServiceImpl();
	}
	
	
	@Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }
    
	// UserDetailsベースの認証は、Spring Securityが認証のためにユーザ名/パスワードを受け入れる構成の場合に利用される。
	// UserDetailsService および PasswordEncoder を利用して、ユーザ名とパスワードを認証する AuthenticationProvider
	@Bean
    public DaoAuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService());
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }
	
	
}
