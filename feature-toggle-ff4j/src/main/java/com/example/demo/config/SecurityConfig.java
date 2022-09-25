package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    // TODO: dummy approach - need Security config later
    http.csrf(AbstractHttpConfigurer::disable)
        .authenticationManager(passwordAuthenticationManager(passwordEncoder()))
        .httpBasic(Customizer.withDefaults())
        .authorizeHttpRequests(
            authorizeRequest ->
                authorizeRequest.antMatchers("/ff4j-web-console/**").authenticated())
        .formLogin();
    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  private AuthenticationManager passwordAuthenticationManager(PasswordEncoder passwordEncode) {
    UserDetails userDetails =
        User.withUsername("admin")
            .password("$2a$12$fhSMUTRYfqn0TVjSOtGtj./LCMcLJvUnKrkQ8bjhFimDR.7U2Eox6")
            .roles("admin")
            .build();

    UserDetailsService userDetailsService = new InMemoryUserDetailsManager(userDetails);
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setPasswordEncoder(passwordEncode);
    authenticationProvider.setUserDetailsService(userDetailsService);

    return new ProviderManager(authenticationProvider);
  }
}
