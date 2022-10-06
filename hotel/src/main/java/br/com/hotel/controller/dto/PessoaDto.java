package br.com.hotel.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.hotel.model.Pessoa;

public class PessoaDto {
	private Long id;
	private String cpfcnpj;
	private String nome;
	private String telefone;
	private LocalDateTime dataCadastro;
	
	public PessoaDto(Pessoa pessoa) {
		this.id = pessoa.getId();
		this.cpfcnpj = pessoa.getCpfcnpj();
		this.nome = pessoa.getNome();
		this.dataCadastro = pessoa.getDataCadastro();
		this.telefone = pessoa.getTelefone();
	}
	
	public Long getId() {
		return id;
	}
	public String getCpfcnpj() {
		return cpfcnpj;
	}
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getNome() {
		return nome;
	}
	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setCpfcnpj(String cpfcnpj) {
		this.cpfcnpj = cpfcnpj;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public static List<PessoaDto> converter(List<Pessoa> asList) {
		return asList.stream().map(PessoaDto::new).collect(Collectors.toList());
	}
} 
