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

import br.com.jhonicosta.erp.domain.Cidade;
import br.com.jhonicosta.erp.domain.Endereco;
import br.com.jhonicosta.erp.domain.Usuario;
import br.com.jhonicosta.erp.domain.enuns.TipoEndereco;
import br.com.jhonicosta.erp.domain.enuns.TipoUsuario;
import br.com.jhonicosta.erp.dto.UsuarioDTO;
import br.com.jhonicosta.erp.dto.UsuarioNewDTO;
import br.com.jhonicosta.erp.repositories.EnderecoRepository;
import br.com.jhonicosta.erp.repositories.UsuarioRepository;
import br.com.jhonicosta.erp.services.exceptions.DataIntegrityException;
import br.com.jhonicosta.erp.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private EnderecoRepository endRepository;

	public Usuario find(Integer id) {
		Optional<Usuario> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));
	}

	public List<Usuario> findAll() {
		return repository.findAll();
	}

	@Transactional
	public Usuario insert(Usuario obj) {
		obj.setId(null);
		obj = repository.save(obj);
		endRepository.saveAll(obj.getEnderecos());
		return obj;
	}

	public Usuario update(Usuario obj) {
		Usuario usuario = find(obj.getId());
		updateData(usuario, obj);
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

	public Page<Usuario> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}

	public Usuario fromDTO(UsuarioDTO objDto) {
		return new Usuario(objDto.getId(), objDto.getNome(), objDto.getEmail(), objDto.getCpf(),
				TipoUsuario.toEnum(objDto.getTipo()), objDto.getSenha());
	}

	public Usuario fromDTO(UsuarioNewDTO objDto) {
		Usuario usuario = new Usuario(null, objDto.getNome(), objDto.getEmail(), objDto.getCpf(),
				TipoUsuario.toEnum(objDto.getTipo()), objDto.getSenha());
		Cidade cidade = new Cidade(objDto.getCidadeId(), null, null);
		Endereco endereco = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(),
				cidade, objDto.getBairro(), objDto.getCep(), TipoEndereco.toEnum(objDto.getTipoEndereco()));
		usuario.getEnderecos().add(endereco);
		endereco.setUsuario(usuario);
		usuario.getTelefones().add(objDto.getTelefone1());
		if (objDto.getTelefone2() != null) {
			usuario.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3() != null) {
			usuario.getTelefones().add(objDto.getTelefone3());
		}

		return usuario;
	}

	private void updateData(Usuario usuario, Usuario obj) {
		usuario.setNome(obj.getNome());
		usuario.setEmail(obj.getEmail());
		usuario.setCpf(obj.getCpf());
		usuario.setTipo(obj.getTipo());
		usuario.setSenha(obj.getSenha());
	}
}
