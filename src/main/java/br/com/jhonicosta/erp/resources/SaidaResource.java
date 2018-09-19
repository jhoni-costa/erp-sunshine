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

import br.com.jhonicosta.erp.domain.Saida;
import br.com.jhonicosta.erp.services.SaidaService;

@RestController
@RequestMapping(value = "/saidas")
public class SaidaResource {

	@Autowired
	private SaidaService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Saida> find(@PathVariable Integer id) {
		Saida entrada = service.find(id);
		return ResponseEntity.ok().body(entrada);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Saida>> findAll() {
		List<Saida> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Saida obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
