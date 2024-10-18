package org.serratec.agencia.model;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity 
@Table (name = "viagens")
public class Viagem {
	@Id 
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String local;
	private LocalDate dataViagem;
	private double valorUnitario;
	@Enumerated (EnumType.STRING)
	private StatusViagem statusViagem;
	@OneToOne (cascade = CascadeType.ALL)
	private Voo voo;
	
	
	
	public Voo getVoo() {
		return voo;
	}
	public void setVoo(Voo voo) {
		this.voo = voo;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public LocalDate getDataViagem() {
		return dataViagem;
	}
	public void setDataViagem(LocalDate dataViagem) {
		this.dataViagem = dataViagem;
	}
	public double getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	public StatusViagem getStatusViagem() {
		return statusViagem;
	}
	public void setStatusViagem(StatusViagem statusViagem) {
		this.statusViagem = statusViagem;
	}
	
}
