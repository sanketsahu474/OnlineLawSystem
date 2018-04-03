package com.example.demo.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private DataSource dataSource;
	@Value("${spring.queries.users-query}")
	private String usersQuery;

	@Value("${spring.queries.roles-query}")
	private String rolesQuery;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().usersByUsernameQuery(usersQuery).authoritiesByUsernameQuery(rolesQuery)
				.dataSource(dataSource).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/login").permitAll()
				.antMatchers("/registration").permitAll().antMatchers("/forgot").permitAll().antMatchers("/reset").permitAll()
				.antMatchers("/admin/AddIpcCode*", "/admin/AdminHome*", "/admin/ShowIpcCode*", "/admin/UpdateIpcCode*",
						"/admin/UserInfo*","/admin/UpdateUser*")
				.hasAuthority("admin")
				.antMatchers("/lawyer/LawyerChat*", "/lawyer/Details*", "/lawyer/LawyerHome*", "/lawyer/SearchIpc*",
						"/lawyer/ShowCases*","/lawyer/UpdateInfo*")
				.hasAuthority("lawyer")
				.antMatchers("/client/Cases*", "/client/ClientHome*", "/client/SearchLawyer", "/client/SearchLawyers*",
						"/client/SearchIpcCode*", "/client/SearchLawyer*","/client/AddCase*","/client/UpdateCase*","/client/UpdateInfo*")
				.hasAuthority("user").anyRequest()

				.authenticated().and().csrf().disable().formLogin().loginPage("/login").failureUrl("/login?error=true")
				.defaultSuccessUrl("/default").usernameParameter("email").passwordParameter("password").and().logout()
				.logoutSuccessUrl("/").and().exceptionHandling().accessDeniedPage("/access-denied");

	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}

}
