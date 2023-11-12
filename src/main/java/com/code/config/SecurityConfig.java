package com.code.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserService UserService;

    @Autowired
    private BCryptPasswordEncoder encodePWD;

    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;


    /*
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(UserService.userDetailsService()).passwordEncoder(encodePWD);

//        String pwd = "123";
//        String encryptPwd = encodePWD.encode(pwd);
//        auth.inMemoryAuthentication().
  //              withUser("leo@gmail.com").password(encryptPwd).roles("ADMIN");

    }
    */
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
            return httpSecurity
                    .httpBasic(Customizer.withDefaults())
                    .cors(AbstractHttpConfigurer::disable)
                    .exceptionHandling(exceptionHandling -> exceptionHandling
                            .authenticationEntryPoint(customAuthenticationEntryPoint)
                    )
                    //.cors(Customizer.withDefaults())
                    //.csrf(AbstractHttpConfigurer::disable)
                    .csrf(csrf -> csrf.disable())
                    .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    //.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                    .authorizeHttpRequests((requests) -> requests
                             .requestMatchers(HttpMethod.GET,"/helpdesk/**").authenticated()
                             .requestMatchers(HttpMethod.GET,"/login").permitAll()
                             .requestMatchers(HttpMethod.GET,"/static/**").permitAll()
                            .requestMatchers(HttpMethod.OPTIONS,"/**").authenticated()
                            .requestMatchers("/**").authenticated()
                            .anyRequest().authenticated()
                    )
                   // .formLogin(Customizer.withDefaults())

                    .formLogin(form -> form
                                        .loginPage("/login")
                             .loginProcessingUrl("/login")
                            //.failureUrl(LOGIN_FAIL_URL)
                            //.usernameParameter(USERNAME)
                            //.passwordParameter(PASSWORD)
                            .defaultSuccessUrl("/home", true)
                    )
                    //.logout((logout) -> logout.permitAll())
                    .logout(Customizer.withDefaults())
                    .authenticationProvider(authenticationProvider())
                    //.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                    .build();

        }


    //@Bean
    //public PasswordEncoder passwordEncoder() {
       // return new BCryptPasswordEncoder();
    //}

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(UserService.userDetailsService());
        authProvider.setPasswordEncoder(encodePWD);
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }

}
