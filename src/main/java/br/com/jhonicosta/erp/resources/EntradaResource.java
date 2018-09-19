package br.com.jhonicosta.erp.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.jhonicosta.erp.domain.Entrada;
import br.com.jhonicosta.erp.services.EntradaService;

@RestController
@RequestMapping(value = "/entrada")
public class EntradaResource {

	@Autowired
	private EntradaService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Entrada> find(@PathVariable Integer id) {
		Entrada entrada = service.find(id);
		return ResponseEntity.ok().body(entrada);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Entrada>> findAll() {
		List<Entrada> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Entrada obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
