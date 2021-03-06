package br.com.jhonicosta.erp.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.jhonicosta.erp.domain.Cliente;
import br.com.jhonicosta.erp.services.validation.ClienteUpdate;

@ClienteUpdate
public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres")
	private String nome;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Email(message = "Email invalido")
	private String email;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String cpfOuCnpf;
	
	private Integer tipo;

	public ClienteDTO() {
	}

	public ClienteDTO(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
		this.cpfOuCnpf = cliente.getCpfOuCnpf();
		this.tipo = (cliente.getTipo() == null) ? null : cliente.getTipo().getCod();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfOuCnpf() {
		return cpfOuCnpf;
	}

	public void setCpfOuCnpf(String cpfOuCnpf) {
		this.cpfOuCnpf = cpfOuCnpf;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

}
