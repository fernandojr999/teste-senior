package br.com.hotel.controller.form;

import br.com.hotel.model.Pessoa;

public class PessoaForm {
	private String nome;
	private String cpfcnpj;
	private String telefone;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpfcnpj() {
		return cpfcnpj;
	}

	public void setCpfcnpj(String cpfcnpj) {
		this.cpfcnpj = cpfcnpj;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Pessoa converter() {
		return new Pessoa(getCpfcnpj(), getNome(), getTelefone());
	}
}
