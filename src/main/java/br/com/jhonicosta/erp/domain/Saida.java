package br.com.jhonicosta.erp.domain;

import java.util.Date;

//import javax.persistence.Entity;

//@Entity
public class Saida extends Movimentacao {
	private static final long serialVersionUID = 1L;

	private Cliente cliente;

	public Saida() {
	}

	public Saida(Integer id, Date data, Double total, Cliente cliente) {
		super(id, data, total);
		this.cliente = cliente;
	}


	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
