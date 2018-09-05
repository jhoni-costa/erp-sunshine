package br.com.jhonicosta.erp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jhonicosta.erp.domain.Cliente;
import br.com.jhonicosta.erp.repositories.ClienteRepository;
import br.com.jhonicosta.erp.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		return repository.save(obj);
	}
	
	public Cliente update(Cliente obj) {
		find(obj.getId());
		return repository.save(obj);
	}
}
