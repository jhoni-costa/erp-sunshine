package br.com.jhonicosta.erp.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.jhonicosta.erp.domain.enuns.TipoEndereco;

@Entity
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String logradouro;
	private String numero;
	private String complemento;

	@ManyToOne
	@JoinColumn(name = "cidade_id")
	private Cidade cidade;
	private String bairro;
	private String cep;
	private Integer tipo;

	@JsonBackReference
	@ManyToOne
	@JoinTable(name = "CLIENTE_ENDERECOS", joinColumns = @JoinColumn(name = "endereco_id"), inverseJoinColumns = @JoinColumn(name = "cliente_id"))
	private Cliente cliente;

	@JsonBackReference
	@ManyToOne
	@JoinTable(name = "FORNECEDOR_ENDERECOS", joinColumns = @JoinColumn(name = "endereco_id"), inverseJoinColumns = @JoinColumn(name = "fornecedor_id"))
	private Fornecedor fornecedor;

	@JsonBackReference
	@ManyToOne
	@JoinTable(name = "USUARIO_ENDERECOS", joinColumns = @JoinColumn(name = "endereco_id"), inverseJoinColumns = @JoinColumn(name = "usuario_id"))
	private Usuario usuario;

	public Endereco() {
	}

	public Endereco(Integer id, String logradouro, String numero, String complemento, Cidade cidade, String bairro,
			String cep, TipoEndereco tipo) {
		this.id = id;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.cidade = cidade;
		this.bairro = bairro;
		this.cep = cep;
		this.tipo = (tipo == null) ? null : tipo.getCod();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public TipoEndereco getTipo() {
		return TipoEndereco.toEnum(tipo);
	}

	public void setTipo(TipoEndereco tipo) {
		this.tipo = tipo.getCod();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Endereco other = (Endereco) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
