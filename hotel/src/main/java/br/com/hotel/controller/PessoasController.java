package br.com.hotel.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.hotel.controller.dto.PessoaDto;
import br.com.hotel.controller.form.PessoaForm;
import br.com.hotel.model.Pessoa;
import br.com.hotel.repository.PessoaRepository;

@Controller
@RequestMapping("/pessoas")
public class PessoasController {
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@GetMapping
	@CrossOrigin
	@ResponseBody
	public List<PessoaDto> get(String valor, String chave) {
		List<Pessoa> pessoas = null;
		if(valor == null) {
			pessoas = pessoaRepository.findAll();
		}else {
			if(chave.toUpperCase().equals("NOME")) {
				pessoas = pessoaRepository.findByNome(valor);
			} else if(chave.toUpperCase().equals("TELEFONE")) {
				pessoas = pessoaRepository.findByTelefone(valor);
			} else if(chave.toUpperCase().equals("CPFCNPJ")) {
				pessoas = pessoaRepository.findBycpfcnpj(valor);
			}
			
		}	
		return PessoaDto.converter(pessoas);
	}
	
	@PostMapping
	@CrossOrigin
	public ResponseEntity<PessoaDto> save(@RequestBody PessoaForm pessoaForm, UriComponentsBuilder uriBuilder) {
		Pessoa pessoa = pessoaForm.converter();
		pessoaRepository.save(pessoa);
		
		URI uri = uriBuilder.path("/pessoas/(id)").buildAndExpand(pessoa.getId()).toUri();
		return ResponseEntity.created(uri).body(new PessoaDto(pessoa));
	}
}
