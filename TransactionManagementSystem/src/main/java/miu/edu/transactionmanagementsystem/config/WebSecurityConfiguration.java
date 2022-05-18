package miu.edu.transactionmanagementsystem.config;

import miu.edu.transactionmanagementsystem.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtFilter jwtFilter;



    @Override
    protected void configure(HttpSecurity http) throws Exception{
            http.csrf().disable()
                    .addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class)
                    .authorizeHttpRequests()
                    .antMatchers("/").permitAll()
                    .antMatchers("/eureka/css/**","/eureka/images/**","/eureka/fonts/**", "/eureka/js/**").permitAll()
                    .anyRequest()
                    .authenticated()
                    .and()
                    .httpBasic();

    }

    // configure AuthenticationManager so that it knows from where to load
    // user for matching credentials
    // Use BCryptPasswordEncoder
//    @Override
//    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }


}


