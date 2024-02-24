package com.comanda.model.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SubGrupoInput {
	private Long id;
	@NotNull
	@NotBlank
	private String nomeSubgrupo;                                                                                                                                                                
	
    private GrupoInput grupo= new GrupoInput();


}
