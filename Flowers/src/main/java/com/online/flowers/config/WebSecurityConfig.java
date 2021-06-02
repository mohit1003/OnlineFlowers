package com.online.flowers.config;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.online.flowers.filters.JwtFilter;
import com.online.flowers.service.CustomUserAuthService;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomUserAuthService customUserDetailService;
	
	@Autowired
	private JwtFilter jwtFilter;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
        .csrf().disable().authorizeRequests()
         .antMatchers("/h2-console/**").permitAll()
         .antMatchers("/authenticate").permitAll()
         .antMatchers("/register").permitAll()
         .antMatchers("/getAllFlowers").permitAll()
         .antMatchers("/getAllShops").permitAll()
         .anyRequest()
         .authenticated()
         .and().exceptionHandling().and().sessionManagement()
         .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
         
    	http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

    	http.headers().frameOptions().sameOrigin();
////         /all requests are authenticated
        
        http.cors();
    }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailService);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
    
    
    
    
}