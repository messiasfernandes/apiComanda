package com.comanda.model.recorddto;

import java.math.BigDecimal;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.comanda.domain.entity.Preco;

public record DetalharprecoR(Long id,
		@NumberFormat(pattern = "###,###.00", style = Style.CURRENCY) BigDecimal precovenda,

		@NumberFormat(style = Style.CURRENCY) BigDecimal precocusto,

		@NumberFormat(pattern = "#,###.00", style = Style.CURRENCY) BigDecimal customedio) {
        public DetalharprecoR(Preco preco) {
			this(preco.getId(), preco.getPrecovenda(), preco.getPrecocusto(), preco.getCustomedio());
		}
}
