package br.com.jhonicosta.erp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jhonicosta.erp.domain.Produto;
import br.com.jhonicosta.erp.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;
	
	public Produto buscar(Integer id) {
		Optional<Produto> obj = repository.findById(id);
		return obj.orElse(null);
	}
	
	public Produto insert(Produto obj) {
		obj.setId(null);
		return repository.save(obj);
	}
	
}
