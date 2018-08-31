package br.com.jhonicosta.erp.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.jhonicosta.erp.domain.Produto;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

	@RequestMapping(method = RequestMethod.GET)
	public List<Produto> listar() {

		Produto prod1 = new Produto(1, "Coca-cola Zero", 
				"Refrigerante de cola, zero açucar, garrafa PET de 2L",
				"7257642965942",
				3.99);
		Produto prod2 = new Produto(2, "Antartica Sub-Zero", 
				"Cerveja Antartica Sub-Zero, pack com 12 latas de 350ml",
				"274857428",
				10.99);

		List<Produto> lista = new ArrayList<>();
		lista.add(prod1);
		lista.add(prod2);
		
		return lista;

	}
}
