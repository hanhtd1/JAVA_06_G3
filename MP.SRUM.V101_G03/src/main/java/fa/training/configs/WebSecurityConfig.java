package fa.training.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import fa.training.services.implement.UserLoginService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  
  @Autowired
  UserLoginService userlogin;
  
  
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userlogin).passwordEncoder(passwordEncoder());
  }
  
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    
//    http.authorizeRequests().antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')");
    http.authorizeRequests().antMatchers("/trainer/**").access("hasRole('ROLE_TRAINER')");
//    http.authorizeRequests().antMatchers("/trainee/**").access("hasAnyRole('ROLE_TRAINEE')");
    http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

    http.authorizeRequests().antMatchers("/admin/**").permitAll();//TODO for test 
    http.authorizeRequests().antMatchers("/trainee/**").permitAll();
    
    
    
    http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().formLogin().loginPage("/login")
    .usernameParameter("account")
    .defaultSuccessUrl("/index").failureUrl("/login?error=true").permitAll()
    .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login?logout").permitAll(); 
  }
  
  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/assets/**");
  }
  
  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
