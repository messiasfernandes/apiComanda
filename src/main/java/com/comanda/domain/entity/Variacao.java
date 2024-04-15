package com.comanda.domain.entity;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Variacao implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	//@JsonManagedReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private Caracteristica caracteristica;
//	@NotBlank
//	@Column(length = 80)
//	private String opcao;
	//@JsonManagedReference
	//@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)

	private Combinacao combinacao;
	

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Variacao other = (Variacao) obj;
		return Objects.equals(id, other.id);
	}

}
