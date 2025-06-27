package kleberlz.sbootexp_security.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@SuppressWarnings("serial")
public class CustomAuthentication implements Authentication {
	
	private final IdentificacaoUsuario identificacaoUsuario;
	
	public CustomAuthentication(IdentificacaoUsuario identificacaoUsuario) { //Constructor modificado.
		if(identificacaoUsuario == null) {
			throw new ExceptionInInitializerError("Não é possivel criar um custom authentication sem a identificação do usuário");
		}
		this.identificacaoUsuario = identificacaoUsuario;
	}
	

	@Override
	public String getName() {
		return this.identificacaoUsuario.getNome(); //modificado para o identificacaoUsuario.
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { //modificado para o identificacaoUsuario.
		return this.identificacaoUsuario
				.getPermissoes()
				.stream()
				.map(perm -> new SimpleGrantedAuthority(perm))
				.collect(Collectors.toList());
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getDetails() {
		return null;
	}

	@Override
	public Object getPrincipal() { //modificado para o identificacaoUsuario.
		return this.identificacaoUsuario;
	}

	@Override
	public boolean isAuthenticated() {
		return true;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		//modificado para o identificacaoUsuario.
		throw new IllegalArgumentException("Não precisa chamar, já estamos autenticados");
	}

}
