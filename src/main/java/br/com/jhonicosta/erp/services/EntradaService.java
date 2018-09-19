package br.com.jhonicosta.erp.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.jhonicosta.erp.domain.Entrada;
import br.com.jhonicosta.erp.repositories.EntradaRepository;
import br.com.jhonicosta.erp.services.exceptions.DataIntegrityException;
import br.com.jhonicosta.erp.services.exceptions.ObjectNotFoundException;

@Service
public class EntradaService {

	@Autowired
	private EntradaRepository repository;

	public Entrada find(Integer id) {
		Optional<Entrada> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Item de estoque não encontrado! Id: " + id + ", Tipo: " + Entrada.class.getName()));
	}

	public List<Entrada> findAll() {
		return repository.findAll();
	}

	@Transactional
	public Entrada insert(Entrada obj) {
		return repository.save(obj);
	}

	public Entrada update(Entrada obj) {
		Entrada entrada = find(obj.getId());
		updateData(entrada, obj);
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

	private void updateData(Entrada entrada , Entrada obj) {
		entrada.setData(obj.getData());
		entrada.setFornecedor(obj.getFornecedor());
		entrada.setProdutos(obj.getProdutos());
		entrada.setTotal(obj.getTotal());
	}
}
