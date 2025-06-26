package kleberlz.sbootexp_security.dto;

import java.util.List;

import kleberlz.sbootexp_security.model.Usuario;
import lombok.Data;

@Data
public class UsuarioDTO {
	
	private Usuario usuario;
	private List<String> permissoes;

}
