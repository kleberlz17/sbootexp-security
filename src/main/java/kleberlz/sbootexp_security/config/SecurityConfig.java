package kleberlz.sbootexp_security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity // Habilita pra fazer configs do Spring Security
@EnableMethodSecurity(securedEnabled = true)// configurar permissoes nos controllers.(FooController)
public class SecurityConfig { //Aqui é toda a lógica principal da configuração de segurança;
	// AQUI JUNTA TODA A LOGICA FEITA DE SECURITY!!!
	@Bean
	public SecurityFilterChain securityFilterChain(
			HttpSecurity http, 
			SenhaMasterAuthenticationaProvider senhaMasterAuthenticationProvider,
			CustomAuthenticationProvider customAuthenticationProvider,
			CustomFilter customFilter) throws Exception {
		return http
				.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(customizer -> {
					customizer.requestMatchers("/public").permitAll();
					customizer.anyRequest().authenticated();
				})
				
				.httpBasic(Customizer.withDefaults()) //habilita novamente o httpbasic(basic auth) padrão
				.formLogin(Customizer.withDefaults())//habilita novamente o formLogin padrão
				.authenticationProvider(senhaMasterAuthenticationProvider)
				.authenticationProvider(customAuthenticationProvider)
				.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails commonUser = User.builder()
				.username("user")
				.password(passwordEncoder().encode("123"))
				.roles("USER")
				.build();
		
		UserDetails adminUser = User.builder()
				.username("admin")
				.password(passwordEncoder().encode("admin"))
				.roles("USER", "ADMIN")
				.build();
		
		return new InMemoryUserDetailsManager(commonUser, adminUser);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean //Aqui tira a obrigatoriedade do prefixo ROLE_
	public GrantedAuthorityDefaults grantedAuthorityDefaults() {
		return new GrantedAuthorityDefaults("");
	}

}
