package kleberlz.sbootexp_security.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class UsuarioGrupo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id; // id do usuariogrupo
	
	@ManyToOne
	@JoinColumn(name = "id_usuario") // id do usuario
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "id_grupo") // id do grupo
	private Grupo grupo;

	
	public UsuarioGrupo(Usuario usuario, Grupo grupo) {
		this.usuario = usuario;
		this.grupo = grupo;
	}
}
