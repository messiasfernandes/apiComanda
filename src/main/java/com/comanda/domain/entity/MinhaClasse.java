package com.comanda.domain.entity;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MinhaClasse {
	@Size(min = 3, max = 3, message = "o campo de posusuir no maximo {max} e no minimo  {min}")
   private String nome;
}
