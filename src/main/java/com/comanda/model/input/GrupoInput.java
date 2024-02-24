package com.comanda.model.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data

public class GrupoInput {
	private Long id;
	@NotBlank
	private String nomeGrupo;                                                                                                                                                                

		
}
