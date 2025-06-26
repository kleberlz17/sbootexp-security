package kleberlz.sbootexp_security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kleberlz.sbootexp_security.dto.UsuarioDTO;
import kleberlz.sbootexp_security.model.Usuario;
import kleberlz.sbootexp_security.service.UsuarioService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
	
	private final UsuarioService usuarioService;
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Usuario> salvar(@RequestBody UsuarioDTO usuarioDTO) {
		Usuario usuarioSalvo = usuarioService.salvar(usuarioDTO.getUsuario(), usuarioDTO.getPermissoes());
		return ResponseEntity.ok(usuarioSalvo);
		
	}

}
