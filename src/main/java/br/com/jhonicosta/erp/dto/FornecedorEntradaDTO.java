package br.com.jhonicosta.erp.dto;

import java.io.Serializable;

import br.com.jhonicosta.erp.domain.Fornecedor;

public class FornecedorEntradaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nome;
	private String cnpj;

	public FornecedorEntradaDTO() {
	}

	public FornecedorEntradaDTO(Fornecedor fornecedor) {
		this.nome = fornecedor.getNome();
		this.cnpj = fornecedor.getCnpj();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

}
