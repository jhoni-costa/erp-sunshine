package br.com.jhonicosta.erp.domain;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Entrada extends Movimentacao {
	private static final long serialVersionUID = 1L;

	private Fornecedor fornecedor;

	public Entrada() {
	}

	public Entrada(Integer id, Date data, Double total, Fornecedor fornecedor) {
		super(id, data, total);
		this.fornecedor = fornecedor;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

}
