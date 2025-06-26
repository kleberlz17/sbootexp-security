package kleberlz.sbootexp_security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kleberlz.sbootexp_security.model.Grupo;

public interface GrupoRepository extends JpaRepository<Grupo, String> {
	
	Optional<Grupo> findByNome(String nome);

}
