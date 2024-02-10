package com.employee.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private DataSource datasource;
	
	@Autowired
	public void configAuthentication (AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder()).dataSource(datasource) 
		.usersByUsernameQuery("select username, password,enabled from users where username=?")
		.authoritiesByUsernameQuery("select username, role from users where username=?");
	}
 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(requests -> requests
                .antMatchers("/login1").hasAnyRole("ADMIN", "USER")
                .antMatchers("/add", "/edit", "/delete")
                .hasRole("ADMIN").anyRequest().authenticated()).formLogin(login -> login.permitAll()).logout(logout -> logout.permitAll());
        http.formLogin(login -> login.loginPage("/login1"));
        http.formLogin(login -> login.defaultSuccessUrl("/viewhome"))
                .logout(logout -> logout
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessUrl("/login1")
                        .permitAll())

                .sessionManagement(management -> management
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                        .invalidSessionUrl("/login1")
                        .maximumSessions(1));

	}				
		
}
