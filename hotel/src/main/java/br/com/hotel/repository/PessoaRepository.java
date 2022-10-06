package br.com.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hotel.model.Pessoa;



public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	List<Pessoa> findByNome(String nomePessoa);

	List<Pessoa> findByTelefone(String valor);

	List<Pessoa> findBycpfcnpj(String valor);

}
