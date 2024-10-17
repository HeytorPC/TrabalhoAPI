package org.serratec.agencia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table (name = "voo")
public class Voo {
	@Id 
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String compania;
	@Enumerated (EnumType.STRING)
	private StatusVoo statusVoo;
	@Enumerated (EnumType.STRING)
	private Reserva reserva;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCompania() {
		return compania;
	}
	public void setCompania(String compania) {
		this.compania = compania;
	}
	public StatusVoo getStatusVoo() {
		return statusVoo;
	}
	public void setStatusVoo(StatusVoo statusVoo) {
		this.statusVoo = statusVoo;
	}
	public Reserva getReserva() {
		return reserva;
	}
	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
	
	
}
