package com.comanda.domain.entity;






import com.comanda.domain.enumerado.Localizacao;
import com.comanda.domain.enumerado.StatusMesa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
@Table(name =  "tab_mesas")
@Getter
@Setter
@Entity

public class Mesa extends GeradorId {

	private static final long serialVersionUID = 1L;
	@NotNull
	@Column
	private Integer numerodaMesa;
	@NotNull
	private Integer capacidade;
	@Column(length = 30)
	@Enumerated(EnumType.STRING)
	private Localizacao loclizacao;
	@Column(length = 30)
	@Enumerated(EnumType.STRING)
	private StatusMesa statusMesa;
//	@Getter(value = AccessLevel.NONE)
//	@Transient
//	private BigDecimal total;
//	public BigDecimal getTotal() {
//		// calcularTotal();
//	        return total;
//	}
//	   @OneToMany(mappedBy = "mesa")
//	    private List<Comanda> comandas = new ArrayList<>();
//	   
//	   private void calcularTotal() {
//	        total = BigDecimal.ZERO;
//	        comandas.stream()
//            .flatMap(comanda -> comanda.getItemsdaComanda().stream())
//            .forEach(item -> total = total.add(item.getSubtotal()));
//	    }
	  
}
