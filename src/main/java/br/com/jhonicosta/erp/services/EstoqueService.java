package br.com.jhonicosta.erp.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.jhonicosta.erp.domain.Categoria;
import br.com.jhonicosta.erp.domain.Estoque;
import br.com.jhonicosta.erp.repositories.EstoqueRepository;
import br.com.jhonicosta.erp.services.exceptions.DataIntegrityException;
import br.com.jhonicosta.erp.services.exceptions.ObjectNotFoundException;

@Service
public class EstoqueService {

	@Autowired
	private EstoqueRepository repository;

	public Estoque find(Integer id) {
		Optional<Estoque> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Item de estoque não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	public List<Estoque> findAll() {
		return repository.findAll();
	}

	@Transactional
	public Estoque insert(Estoque obj) {
		return repository.save(obj);
	}

	public Estoque update(Estoque obj) {
		Estoque estoque = find(obj.getId());
		updateData(estoque, obj);
		return repository.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir porque ha entidades relacionadas");
		}
	}

	private void updateData(Estoque estoque, Estoque obj) {
		estoque.setProduto(obj.getProduto());
		estoque.setQuantidade(obj.getQuantidade());
	}
}
