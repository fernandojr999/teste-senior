package br.com.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hotel.model.Hospedagem;

public interface HospedagemRepository extends JpaRepository<Hospedagem, Long>{

	List<Hospedagem> findByFinalizada(boolean b);

}
