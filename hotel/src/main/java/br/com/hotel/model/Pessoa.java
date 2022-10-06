package br.com.hotel.model;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pessoa {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String cpfcnpj;
	private String nome; 
	private String telefone;
	private LocalDateTime dataCadastro;
	
	public Pessoa(){
		
	}
	
	public Pessoa(String cpfcnpj, String nome, String telefone) {
		super();
		this.cpfcnpj = cpfcnpj;
		this.nome = nome;
		this.telefone = telefone;
		this.dataCadastro = LocalDateTime.now();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(cpfcnpj, dataCadastro, id, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(cpfcnpj, other.cpfcnpj) && Objects.equals(dataCadastro, other.dataCadastro)
				&& id == other.id && Objects.equals(nome, other.nome);
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCpfcnpj() {
		return cpfcnpj;
	}

	public void setCpfcnpj(String cpfcnpj) {
		this.cpfcnpj = cpfcnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
}
