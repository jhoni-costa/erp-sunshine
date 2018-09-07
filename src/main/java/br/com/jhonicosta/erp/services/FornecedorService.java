package br.com.jhonicosta.erp.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.jhonicosta.erp.domain.Categoria;
import br.com.jhonicosta.erp.domain.Cidade;
import br.com.jhonicosta.erp.domain.Endereco;
import br.com.jhonicosta.erp.domain.Fornecedor;
import br.com.jhonicosta.erp.domain.enuns.TipoEndereco;
import br.com.jhonicosta.erp.dto.FornecedorDTO;
import br.com.jhonicosta.erp.dto.FornecedorNewDTO;
import br.com.jhonicosta.erp.repositories.EnderecoRepository;
import br.com.jhonicosta.erp.repositories.FornecedorRepository;
import br.com.jhonicosta.erp.services.exceptions.DataIntegrityException;
import br.com.jhonicosta.erp.services.exceptions.ObjectNotFoundException;

@Service
public class FornecedorService {

	@Autowired
	private FornecedorRepository repository;

	@Autowired
	private EnderecoRepository endRepository;

	public Fornecedor find(Integer id) {
		Optional<Fornecedor> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	public List<Fornecedor> findAll() {
		return repository.findAll();
	}

	@Transactional
	public Fornecedor insert(Fornecedor obj) {
		obj.setId(null);
		obj = repository.save(obj);
		endRepository.saveAll(obj.getEnderecos());
		return obj;
	}

	public Fornecedor update(Fornecedor obj) {
		Fornecedor fornecedor = find(obj.getId());
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

	public Page<Fornecedor> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}

	public Fornecedor fromDTO(FornecedorDTO objDto) {
		return new Fornecedor(objDto.getId(), objDto.getNome(), objDto.getEmail(), objDto.getCnpj(), objDto.getSite());
	}

	public Fornecedor fromDTO(FornecedorNewDTO objDto) {
		Fornecedor fornecedor = new Fornecedor(null, objDto.getNome(), objDto.getEmail(), objDto.getCnpj(),
				objDto.getSite());
		Cidade cidade = new Cidade(objDto.getCidadeId(), null, null);
		Endereco endereco = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(),
				cidade, objDto.getBairro(), objDto.getCep(), TipoEndereco.toEnum(objDto.getTipoEndereco()));
		fornecedor.getEnderecos().add(endereco);
		endereco.setFornecedor(fornecedor);
		fornecedor.getTelefones().add(objDto.getTelefone1());
		if (objDto.getTelefone2() != null) {
			fornecedor.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3() != null) {
			fornecedor.getTelefones().add(objDto.getTelefone3());
		}
		return fornecedor;
	}

	private void updateData(Fornecedor fornecedor, Fornecedor obj) {
		fornecedor.setNome(obj.getNome());
		fornecedor.setEmail(obj.getEmail());
		fornecedor.setCnpj(obj.getCnpj());
		fornecedor.setSite(obj.getSite());
	}
}
