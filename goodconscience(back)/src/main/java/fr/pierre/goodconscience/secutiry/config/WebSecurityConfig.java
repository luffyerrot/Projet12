package fr.pierre.goodconscience.secutiry.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import fr.pierre.goodconscience.secutiry.filter.JwtFilter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JwtFilter jwtFilter;
	
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
		.authorizeRequests()
			.antMatchers("/login/", "/user/create", "/enterprise/create", "/enterprise/createinfos", "/enterprise/", "/enterprise/id/**", "/product/", 
					"/giftbasket/date", "/giftbasket/id/**").permitAll()
			.anyRequest().authenticated()
		.and()
			.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        http.cors().and().csrf().disable();
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
    
    PersistentTokenRepository persistentTokenRepository(){
     JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
     tokenRepositoryImpl.setDataSource(dataSource);
     return tokenRepositoryImpl;
    }
}
