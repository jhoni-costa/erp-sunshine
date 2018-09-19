package br.com.jhonicosta.erp.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.jhonicosta.erp.domain.Saida;
import br.com.jhonicosta.erp.repositories.SaidaRepository;
import br.com.jhonicosta.erp.services.exceptions.DataIntegrityException;
import br.com.jhonicosta.erp.services.exceptions.ObjectNotFoundException;

@Service
public class SaidaService {

	@Autowired
	private SaidaRepository repository;

	public Saida find(Integer id) {
		Optional<Saida> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Item de estoque não encontrado! Id: " + id + ", Tipo: " + Saida.class.getName()));
	}

	public List<Saida> findAll() {
		return repository.findAll();
	}

	@Transactional
	public Saida insert(Saida obj) {
		return repository.save(obj);
	}

	public Saida update(Saida obj) {
		Saida entrada = find(obj.getId());
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

	private void updateData(Saida entrada, Saida obj) {
		entrada.setData(obj.getData());
		entrada.setCliente(obj.getCliente());
		entrada.setProdutos(obj.getProdutos());
		entrada.setTotal(obj.getTotal());
	}
}
