package com.i2gether.beximcom.bkashpayment.bkashpayment.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebsecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
	    auth.inMemoryAuthentication()
	        .withUser("user1").password(passwordEncoder().encode("user1Pass")).roles("USER")
	        .and()
	        .withUser("user2").password(passwordEncoder().encode("user2Pass")).roles("USER")
	        .and()
	        .withUser("admin").password(passwordEncoder().encode("adminPass")).roles("ADMIN");
	}

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
        	.anonymous().and()
        	//.authorizeRequests().antMatchers("/webhook**").permitAll()
	        //.and()
        	.authorizeRequests().anyRequest().authenticated()
            .and()
	        .formLogin()
          		.defaultSuccessUrl("/dashboard",true);
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers("/webhook**");
    }
    
    @Bean 
    public PasswordEncoder passwordEncoder() { 
        return new BCryptPasswordEncoder(); 
    }
    
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
