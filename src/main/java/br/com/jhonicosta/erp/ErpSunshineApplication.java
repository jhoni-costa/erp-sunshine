package br.com.jhonicosta.erp;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.jhonicosta.erp.domain.Categoria;
import br.com.jhonicosta.erp.domain.Cidade;
import br.com.jhonicosta.erp.domain.Endereco;
import br.com.jhonicosta.erp.domain.Estado;
import br.com.jhonicosta.erp.domain.Produto;
import br.com.jhonicosta.erp.domain.TipoEndereco;
import br.com.jhonicosta.erp.repositories.CategoriaRepository;
import br.com.jhonicosta.erp.repositories.CidadeRepository;
import br.com.jhonicosta.erp.repositories.EnderecoRepository;
import br.com.jhonicosta.erp.repositories.EstadoRepository;
import br.com.jhonicosta.erp.repositories.ProdutoRepository;

@SpringBootApplication
public class ErpSunshineApplication implements CommandLineRunner {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

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
		
		instanciaEstadoECidade();
		
				
	}
	
	private void instanciaEstadoECidade() {
//		
//		Estado AC = new Estado(null, "Acre", "AC");
//		Estado AL = new Estado(null, "Alagoas", "AL");
//		Estado AP = new Estado(null, "Amapá", "AP");
//		Estado AM = new Estado(null, "Amazonas", "AM");
//		Estado BA = new Estado(null, "Bahia", "BA");
//		Estado CE = new Estado(null, "Ceará", "CE");
//		Estado DF = new Estado(null, "Distrito Federal", "DF");
//		Estado ES = new Estado(null, "Espírito Santo", "ES");
//		Estado GO = new Estado(null, "Goiás", "GO");
//		Estado MA = new Estado(null, "Maranhão", "MA");
//		Estado MT = new Estado(null, "Mato Grosso", "MT");
//		Estado MS = new Estado(null, "Mato Grosso do Sul", "MS");
//		Estado MG = new Estado(null, "Minas Gerais", "MG");
//		Estado PA = new Estado(null, "Pará", "PA");
//		Estado PB = new Estado(null, "Paraíba", "PB");
		Estado PR = new Estado(null, "Paraná", "PR");
//		Estado PE = new Estado(null, "Pernambuco", "PE");
//		Estado PI = new Estado(null, "Piauí", "PI");
//		Estado RJ = new Estado(null, "Rio de Janeiro", "RJ");
//		Estado RN = new Estado(null, "Rio Grande do Norte", "RN");
//		Estado RS = new Estado(null, "Rio Grande do Sul", "RS");
//		Estado RO = new Estado(null, "Rondônia", "RO");
//		Estado RR = new Estado(null, "Roraima", "RR");
		Estado SC = new Estado(null, "Santa Catarina", "SC");
		Estado SP = new Estado(null, "São Paulo", "SP");
//		Estado SE = new Estado(null, "Sergipe", "SE");
//		Estado TO = new Estado(null, "Tocantins", "TO");
		
		
		Cidade curitiba = new Cidade(null, "Curitiba", PR);
		Cidade joinville = new Cidade(null, "Joinville", SC);
		Cidade saoPaulo = new Cidade(null, "São Paulo", SP);
		Cidade araucaria = new Cidade(null, "Araucária", PR);
		
		PR.setCidades(Arrays.asList(curitiba, araucaria));
		SC.setCidades(Arrays.asList(joinville));
		SP.setCidades(Arrays.asList(saoPaulo));
		
		Endereco endereco1 = new Endereco(null, "Avenida Visconde de Guarapuava", "2900", null, curitiba, "Centro", "8894738", TipoEndereco.RESIDENCIAL);
		Endereco endereco2 = new Endereco(null, "Avenida Paulista", "20200", null, saoPaulo, "Centro", "42453", TipoEndereco.COMERCIAL);
		Endereco endereco3 = new Endereco(null, "Rua Rodolpho Hasselman", "200", null, araucaria, "Fazenda Velha", "8370000", TipoEndereco.RESIDENCIAL);
		Endereco endereco4 = new Endereco(null, "Rua João Bettega", "3000", null, curitiba, "CIC", "58947549", TipoEndereco.COMERCIAL);
		
		curitiba.setEnderecos(Arrays.asList(endereco1, endereco4));
		saoPaulo.setEnderecos(Arrays.asList(endereco2));
		araucaria.setEnderecos(Arrays.asList(endereco3));
		
		estadoRepository.saveAll(Arrays.asList(PR,SC,SP));
		cidadeRepository.saveAll(Arrays.asList(curitiba, joinville, saoPaulo, araucaria));
		enderecoRepository.saveAll(Arrays.asList(endereco1, endereco2, endereco3, endereco4));
		
	}
	
}
