package kleberlz.sbootexp_security.config;

import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class SenhaMasterAuthenticationaProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		//Aqui ocorre a validação dos dados, nesse caso básico, só vai receber acesso  e a role de ADMIN quem logar com o loginMaster e senhaMaster.
		
		var login = authentication.getName();
		var senha = (String) authentication.getCredentials();
		
		String loginMaster = "master";
		String senhaMaster = "@321";
		
		if(loginMaster.equals(login) && senhaMaster.equals(senha)) {
			return new UsernamePasswordAuthenticationToken
					("Sou Master", null, List.of(new SimpleGrantedAuthority("ADMIN")));
		}
		
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}
