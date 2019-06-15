package ru.pavel2107.otus.hw13.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    public void configure(WebSecurity webSecurity){
        webSecurity.ignoring().antMatchers( "/");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers( "/").permitAll()
                    .antMatchers( HttpMethod.GET,   "/rest/books/**").permitAll()
                    .antMatchers( HttpMethod.GET,   "/rest/authors/**").hasAnyRole( "USER", "ADMIN")
                    .antMatchers( HttpMethod.GET,   "/rest/genres/**").hasAnyRole( "USER", "ADMIN")
                    .antMatchers( HttpMethod.GET,   "/rest/comments/**").hasAnyRole( "USER", "ADMIN")
                    .antMatchers( HttpMethod.POST,  "/rest/books/**").hasRole( "ADMIN")
                    .antMatchers( HttpMethod.POST,  "/rest/authors/**").hasRole( "ADMIN")
                    .antMatchers( HttpMethod.POST,  "/rest/genres/**").hasRole( "ADMIN")
                    .antMatchers( HttpMethod.DELETE,"/rest/books/**").hasRole( "ADMIN")
                    .antMatchers( HttpMethod.DELETE, "/rest/authors/**").hasRole( "ADMIN")
                    .antMatchers( HttpMethod.DELETE, "/rest/genres/**").hasRole( "ADMIN")
                    .antMatchers( HttpMethod.POST,   "/rest/comments/**").hasAnyRole( "USER", "ADMIN")
                .and()
                .formLogin()
                .and()
                .httpBasic()
                .and()
                .anonymous().principal(
                new UserDetails() {
                    @Override
                    public Collection<? extends GrantedAuthority> getAuthorities() {
                        List<GrantedAuthority> authorities = new ArrayList<>();
                        authorities.add( new SimpleGrantedAuthority( ""));
                        return authorities;
                    }

                    @Override
                    public String getPassword() {
                        return "guest";
                    }

                    @Override
                    public String getUsername() {
                        return "guest";
                    }

                    @Override
                    public boolean isAccountNonExpired() {
                        return true;
                    }

                    @Override
                    public boolean isAccountNonLocked() {
                        return true;
                    }

                    @Override
                    public boolean isCredentialsNonExpired() {
                        return true;
                    }

                    @Override
                    public boolean isEnabled() {
                        return true;
                    }
                }
        );

    }


    @SuppressWarnings("deprecation")
    @Bean
    public PasswordEncoder passwordEncoder(){ return NoOpPasswordEncoder.getInstance(); }


    @Bean
    @SuppressWarnings("deprecation")
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withDefaultPasswordEncoder().username( "user").password("user").roles( "USER").build());
        manager.createUser(User.withDefaultPasswordEncoder().username( "admin").password("admin").roles( "ADMIN").build());

        return manager;
    }

}
