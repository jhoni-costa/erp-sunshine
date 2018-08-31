package br.com.jhonicosta.erp;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.jhonicosta.erp.domain.Categoria;
import br.com.jhonicosta.erp.domain.Produto;
import br.com.jhonicosta.erp.repositories.CategoriaRepository;
import br.com.jhonicosta.erp.repositories.ProdutoRepository;

@SpringBootApplication
public class ErpSunshineApplication implements CommandLineRunner {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	public static void main(String[] args) {
		SpringApplication.run(ErpSunshineApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria categoria1 = new Categoria(null, "Bebidas");
		Categoria categoria2 = new Categoria(null, "Bebidas Alcoolicas");
		Categoria categoria3 = new Categoria(null, "Laticinios");

		Produto prod1 = new Produto(null, "Coca-cola Zero", "Refrigerante de cola, zero açucar, garrafa PET de 2L",
				"7257642965942", 3.99);
		Produto prod2 = new Produto(null, "Antartica Sub-Zero",
				"Cerveja Antartica Sub-Zero, pack com 12 latas de 350ml", "274857428", 10.99);
		Produto prod3 = new Produto(null, "Leite Tirol", "Leite Integral Tirol, Caixa tetrapack 1L", "254256", 1.99);

		categoria1.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3));
		categoria2.getProdutos().addAll(Arrays.asList(prod2));
		categoria3.getProdutos().addAll(Arrays.asList(prod3));

		prod1.getCategorias().addAll(Arrays.asList(categoria1));
		prod2.getCategorias().addAll(Arrays.asList(categoria1, categoria2));
		prod3.getCategorias().addAll(Arrays.asList(categoria1, categoria3));

		categoriaRepository.saveAll(Arrays.asList(categoria1, categoria2, categoria3));
		produtoRepository.saveAll(Arrays.asList(prod1, prod2, prod3));
		
	}
}
