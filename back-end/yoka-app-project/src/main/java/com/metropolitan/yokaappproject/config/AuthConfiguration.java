package com.metropolitan.yokaappproject.config;

import com.metropolitan.yokaappproject.domain.Korisnik;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class AuthConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("myUserDetailsService")
    UserDetailsService userDetailsService;

//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private DataSource dataSource;

    private String userQuery ="select email_korisnka, password, active from korisnik where email_korisnka=?";
    private String roleQuery = "select role_korisnka from korisnik where email_korisnka=?";

   @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.jdbcAuthentication()
               .usersByUsernameQuery(userQuery)
               .authoritiesByUsernameQuery(roleQuery)
               .dataSource(dataSource)
               .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/api/korpa/**").permitAll()
                .antMatchers("/api/korisnik/**").permitAll()
                .antMatchers("/api/namestaj/**").permitAll()
                .antMatchers("/api/porudzbina/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic() .and()
                .formLogin()
                .loginPage("/")
                .loginProcessingUrl("/logIn")
                .usernameParameter("email")
                .passwordParameter("password")

                .permitAll()
                .and()
                .logout().permitAll();;
    }
    private AuthenticationSuccessHandler successHandler() {
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                                HttpServletResponse httpServletResponse, Authentication authentication)
                    throws IOException, ServletException {
                httpServletResponse.getWriter().append("OK");
                httpServletResponse.setStatus(200);
            }
        };
    }

    private AuthenticationFailureHandler failureHandler() {
        return new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
                                                HttpServletResponse httpServletResponse, AuthenticationException e)
                    throws IOException, ServletException {
                httpServletResponse.getWriter().append("Authentication failure");
                httpServletResponse.setStatus(401);
            }
        };
    }



//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }
@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}

}
