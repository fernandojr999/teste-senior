package br.com.hotel.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Hospedagem {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime dataHoraChecking;
	private LocalDateTime dataHoraCheckout;
	private boolean vagaGaragem;
	private double valorTotal;
	private boolean finalizada;
	
	@Column(nullable=true, length=5000)
	private String extrato;
	
	@ManyToOne
	private Pessoa pessoa;
	
	public boolean isFinalizada() {
		return finalizada;
	}

	public void setFinalizada(boolean finalizada) {
		this.finalizada = finalizada;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getExtrato() {
		return extrato;
	}

	public void setExtrato(String extrato) {
		this.extrato = extrato;
	}

	public Hospedagem(){
		
	}
	
	public Hospedagem(LocalDateTime dataHoraChecking, boolean vagaGaragem, Pessoa pessoa) {
		super();
		this.dataHoraChecking = dataHoraChecking;
		this.vagaGaragem = vagaGaragem;
		this.pessoa = pessoa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataHoraChecking() {
		return dataHoraChecking;
	}

	public void setDataHoraChecking(LocalDateTime dataHoraChecking) {
		this.dataHoraChecking = dataHoraChecking;
	}

	public LocalDateTime getDataHoraCheckout() {
		return dataHoraCheckout;
	}

	public void setDataHoraCheckout(LocalDateTime dataHoraCheckout) {
		this.dataHoraCheckout = dataHoraCheckout;
	}

	public boolean isVagaGaragem() {
		return vagaGaragem;
	}

	public void setVagaGaragem(boolean vagaGaragem) {
		this.vagaGaragem = vagaGaragem;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
}
