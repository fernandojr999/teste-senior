package br.com.hotel.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.hotel.controller.dto.HospedagemDto;
import br.com.hotel.controller.dto.PessoaDto;
import br.com.hotel.controller.form.HospedagemCheckInForm;
import br.com.hotel.controller.form.HospedagemCheckoutForm;
import br.com.hotel.model.Hospedagem;
import br.com.hotel.model.Pessoa;
import br.com.hotel.repository.HospedagemRepository;
import br.com.hotel.repository.PessoaRepository;

@Controller
public class HospedagemController {
	@Autowired
	private HospedagemRepository hospedagemRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@RequestMapping(value="hospedagens", method = RequestMethod.GET)
	@CrossOrigin
	@ResponseBody
	public List<HospedagemDto> get(String tipo) {
		List<Hospedagem> lista = null;
		
		// Se o tipo estiver nulo, são todas as hospedagens
		if(tipo != null && tipo.toUpperCase().equals("CHECKIN")) {
			lista = hospedagemRepository.findByFinalizada(false);
		} else if (tipo != null && tipo.toUpperCase().equals("CHECKOUT")) {
			lista = hospedagemRepository.findByFinalizada(true);
		} else {
			lista = hospedagemRepository.findAll();
		}
		return HospedagemDto.converter(lista);
	}
	
	@RequestMapping(value="checkin", method = RequestMethod.POST)
	@CrossOrigin
	@ResponseBody
	public ResponseEntity<HospedagemDto> checkin(@RequestBody HospedagemCheckInForm form, UriComponentsBuilder uriBuilder) {
		Hospedagem hospedagem = form.converter(pessoaRepository.getById(form.getIdPessoa()));
		
		hospedagemRepository.save(hospedagem);
		
		URI uri = uriBuilder.path("/hospedagens/(id)").buildAndExpand(hospedagem.getId()).toUri();
		return ResponseEntity.created(uri).body(new HospedagemDto(hospedagem));
	}
	
	@RequestMapping(value="checkout", method = RequestMethod.POST)
	@CrossOrigin
	@ResponseBody
	public ResponseEntity<HospedagemDto> checkout(@RequestBody HospedagemCheckoutForm form, UriComponentsBuilder uriBuilder) {
		Hospedagem hospedagem = form.converter(hospedagemRepository.getById(form.getIdCheckin()));

		hospedagemRepository.save(hospedagem);
		
		URI uri = uriBuilder.path("/hospedagens/(id)").buildAndExpand(hospedagem.getId()).toUri();
		return ResponseEntity.created(uri).body(new HospedagemDto(hospedagem));
	}
}
