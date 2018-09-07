package br.com.jhonicosta.erp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.jhonicosta.erp.domain.Produto;
import br.com.jhonicosta.erp.dto.ProdutoDTO;
import br.com.jhonicosta.erp.repositories.ProdutoRepository;
import br.com.jhonicosta.erp.services.exceptions.DataIntegrityException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;

	public Produto find(Integer id) {
		Optional<Produto> obj = repository.findById(id);
		return obj.orElse(null);
	}

	public List<Produto> findAll() {
		return repository.findAll();
	}

	public Produto insert(Produto obj) {
		obj.setId(null);
		return repository.save(obj);
	}
	
	public Produto update(Produto obj) {
		Produto fornecedor = find(obj.getId());
		updateData(fornecedor, obj);
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

	public Page<Produto> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}

	public Produto fromDTO(ProdutoDTO objDto) {
		return new Produto(objDto.getId(), objDto.getNome(), objDto.getDescricao(), objDto.getBarCode(), objDto.getPreco());
	}

	private void updateData(Produto fornecedor, Produto obj) {
		fornecedor.setNome(obj.getNome());
		fornecedor.setDescricao(obj.getDescricao());
		fornecedor.setBarCode(obj.getBarCode());
		fornecedor.setPreco(obj.getPreco());
	}

}
