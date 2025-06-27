package kleberlz.sbootexp_security.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kleberlz.sbootexp_security.model.Grupo;
import kleberlz.sbootexp_security.model.Usuario;
import kleberlz.sbootexp_security.model.UsuarioGrupo;
import kleberlz.sbootexp_security.repository.GrupoRepository;
import kleberlz.sbootexp_security.repository.UsuarioGrupoRepository;
import kleberlz.sbootexp_security.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {
	
	private final UsuarioRepository usuarioRepository;
	private final GrupoRepository grupoRepository;
	private final UsuarioGrupoRepository usuarioGrupoRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Transactional
	public Usuario salvar(Usuario usuario, List<String> grupos) {
		String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
		usuario.setSenha(senhaCriptografada);
		usuarioRepository.save(usuario);
		
		List<UsuarioGrupo> listaUsuarioGrupo = grupos.stream().map(nomeGrupo -> {		
			Optional<Grupo> possivelGrupo = grupoRepository.findByNome(nomeGrupo);
			if(possivelGrupo.isPresent()) {
				Grupo grupo = possivelGrupo.get();
				return new UsuarioGrupo(usuario, grupo);
			}
			return null;
		})
				.filter(grupo -> grupo != null)
				.collect(Collectors.toList());
		
		usuarioGrupoRepository.saveAll(listaUsuarioGrupo);
		
		return usuario;
	}
	
	public Usuario obterUsuarioComPermissoes(String login) {
		Optional<Usuario> usuarioOptional = usuarioRepository.findByLogin(login);
		if(usuarioOptional.isEmpty()) {
			return null;
		}
		
		Usuario usuario = usuarioOptional.get();
		List<String> permissoes = usuarioGrupoRepository.findPermissoesByUsuario(usuario);
		usuario.setPermissoes(permissoes);
		
		return usuario;
	}

}
