package br.com.hotel.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.hotel.model.Hospedagem;

public class HospedagemDto {
	private Long id;
	private LocalDateTime dataHoraChecking;
	private LocalDateTime dataHoraCheckout;
	private boolean vagaGaragem;
	private boolean finalizada;
	private double valorTotal;
	private String extrato;
	private String detalhePessoa;
	
	public String getExtrato() {
		return extrato;
	}

	public void setExtrato(String extrato) {
		this.extrato = extrato;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public boolean isFinalizada() {
		return finalizada;
	}

	public void setFinalizada(boolean finalizada) {
		this.finalizada = finalizada;
	}

	public String getDetalhePessoa() {
		return detalhePessoa;
	}

	public void setDetalhePessoa(String detalhePessoa) {
		this.detalhePessoa = detalhePessoa;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public HospedagemDto(Hospedagem hospedagem) {
		this.id = hospedagem.getId();
		this.dataHoraChecking = hospedagem.getDataHoraChecking();
		this.dataHoraCheckout = hospedagem.getDataHoraCheckout();
		this.vagaGaragem = hospedagem.isVagaGaragem();
		this.valorTotal = hospedagem.getValorTotal();
		this.finalizada = hospedagem.isFinalizada();
		this.extrato = hospedagem.getExtrato();
		this.detalhePessoa = hospedagem.getPessoa().getId() +" - "+  hospedagem.getPessoa().getNome();
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
	
	public static List<HospedagemDto> converter(List<Hospedagem> asList) {
		return asList.stream().map(HospedagemDto::new).collect(Collectors.toList());
	}
}
