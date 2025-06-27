package kleberlz.sbootexp_security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kleberlz.sbootexp_security.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
	
	Optional<Usuario> findByLogin(String login);

}
