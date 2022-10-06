package br.com.hotel.controller.form;

import java.time.LocalDateTime;

import br.com.hotel.model.Hospedagem;
import br.com.hotel.model.Pessoa;

public class HospedagemCheckInForm {
	private LocalDateTime dataHoraCheckin;
	private boolean vagaGaragem;
	private Long idPessoa;

	public LocalDateTime getDataHoraCheckin() {
		return dataHoraCheckin;
	}
	public void setDataHoraCheckin(LocalDateTime dataHoraCheckin) {
		this.dataHoraCheckin = dataHoraCheckin;
	}
	public boolean isVagaGaragem() {
		return vagaGaragem;
	}
	public void setVagaGaragem(boolean vagaGaragem) {
		this.vagaGaragem = vagaGaragem;
	}
	public Long getIdPessoa() {
		return idPessoa;
	}
	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}

	public Hospedagem converter(Pessoa pessoa) {
		return new Hospedagem(getDataHoraCheckin(), isVagaGaragem(), pessoa);
	
	}

}
