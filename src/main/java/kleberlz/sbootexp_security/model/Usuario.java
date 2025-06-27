package kleberlz.sbootexp_security.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Data
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	private String login;
	
	private String senha;
	
	private String nome;
	
	@Transient //Pra ignorar como coluna de banco de dados.
	private List<String> permissoes;

}
