package kleberlz.sbootexp_security.config;

import java.io.IOException;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kleberlz.sbootexp_security.security.CustomAuthentication;
import kleberlz.sbootexp_security.security.IdentificacaoUsuario;

@Component
public class CustomFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain)
			throws ServletException, IOException { 
		
		//Nesse caso básico aqui em específico, cria um usuário além do ADMIN normal, utilizando do header definido abaixo com seu value.
		String secretHeader = request.getHeader("x-secret");
		
		if(secretHeader != null) {
			if(secretHeader.equals("secr3t")) {
				var identificacaoUsuario = new IdentificacaoUsuario(
						"id-secret",
						"Muito Secreto",
						"x-secret",
						List.of("USER"));
				
				Authentication authentication = new CustomAuthentication(identificacaoUsuario);
				
				SecurityContext securityContext = SecurityContextHolder.getContext();
				securityContext.setAuthentication(authentication);
			}
		}
		
		filterChain.doFilter(request, response); 
		//Se não por doFilter vai quebrar a cadeia de filtros e qualquer um vai conseguir acessar, porém sem respostas.
		

		
	}

}
