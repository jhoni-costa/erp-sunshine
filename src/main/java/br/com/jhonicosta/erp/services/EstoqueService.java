package br.com.jhonicosta.erp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jhonicosta.erp.domain.Estoque;
import br.com.jhonicosta.erp.repositories.EstoqueRepository;

@Service
public class EstoqueService {

	@Autowired
	private EstoqueRepository repository;

	public Estoque insert(Estoque obj) {
		return repository.save(obj);
	}
}
