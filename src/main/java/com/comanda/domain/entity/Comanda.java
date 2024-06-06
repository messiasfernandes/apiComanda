package com.comanda.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.comanda.domain.enumerado.StatusPagamentoComanda;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "tab_comandas")
public class Comanda implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime data_abertura;
	@Column(length = 20)
	@Enumerated(EnumType.STRING)
	private StatusPagamentoComanda statusPagamentoComanda;
	@JsonIgnore
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn()
	private Mesa mesa;
//	@Getter(value = AccessLevel.NONE)
//	@Transient
//	private BigDecimal total;
	@JsonManagedReference
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "comanda", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemComanda> itemsdaComanda = new ArrayList<>();
//    public BigDecimal getTotal() {
//        calcularTotal();
//        return total;
//    }
//
//    private void calcularTotal() {
//        total = BigDecimal.ZERO;
//        for (ItemComanda item : itemsdaComanda) {
//            total = total.add(item.getSubtotal());
//        }
//    }
}