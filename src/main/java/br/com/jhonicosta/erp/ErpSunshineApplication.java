package br.com.jhonicosta.erp;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.jhonicosta.erp.domain.Produto;
import br.com.jhonicosta.erp.repositories.ProdutoRepository;

@SpringBootApplication
public class ErpSunshineApplication implements CommandLineRunner {

	@Autowired
	private ProdutoRepository produtoRepository;

	public static void main(String[] args) {
		SpringApplication.run(ErpSunshineApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Produto prod1 = new Produto(null, "Coca-cola Zero", "Refrigerante de cola, zero açucar, garrafa PET de 2L",
				"7257642965942", 3.99);
		Produto prod2 = new Produto(null, "Antartica Sub-Zero",
				"Cerveja Antartica Sub-Zero, pack com 12 latas de 350ml", "274857428", 10.99);

		produtoRepository.saveAll(Arrays.asList(prod1, prod2));

	}
}
