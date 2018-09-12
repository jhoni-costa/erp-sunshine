package br.com.jhonicosta.erp;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.jhonicosta.erp.domain.Categoria;
import br.com.jhonicosta.erp.domain.Cidade;
import br.com.jhonicosta.erp.domain.Cliente;
import br.com.jhonicosta.erp.domain.Endereco;
import br.com.jhonicosta.erp.domain.Estado;
import br.com.jhonicosta.erp.domain.Fornecedor;
import br.com.jhonicosta.erp.domain.Produto;
import br.com.jhonicosta.erp.domain.Usuario;
import br.com.jhonicosta.erp.domain.enuns.TipoCliente;
import br.com.jhonicosta.erp.domain.enuns.TipoEndereco;
import br.com.jhonicosta.erp.domain.enuns.TipoUsuario;
import br.com.jhonicosta.erp.repositories.CategoriaRepository;
import br.com.jhonicosta.erp.repositories.CidadeRepository;
import br.com.jhonicosta.erp.repositories.ClienteRepository;
import br.com.jhonicosta.erp.repositories.EnderecoRepository;
import br.com.jhonicosta.erp.repositories.EstadoRepository;
import br.com.jhonicosta.erp.repositories.FornecedorRepository;
import br.com.jhonicosta.erp.repositories.ProdutoRepository;
import br.com.jhonicosta.erp.repositories.UsuarioRepository;
import br.com.jhonicosta.erp.services.validation.BR.BR;

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

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private FornecedorRepository fornecedorRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ErpSunshineApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria categoria1 = new Categoria(null, "Bebidas");
		Categoria categoria2 = new Categoria(null, "Bebidas Alcoolicas");
		Categoria categoria3 = new Categoria(null, "Laticinios");
		Categoria categoria4 = new Categoria(null, "Mercearia");
		Categoria categoria5 = new Categoria(null, "Higiene Pessoal");
		Categoria categoria6 = new Categoria(null, "Frios");
		Categoria categoria7 = new Categoria(null, "Perfumaria");

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

		categoriaRepository.saveAll(Arrays.asList(categoria1, categoria2, categoria3, categoria4, categoria5, categoria6, categoria7));
		produtoRepository.saveAll(Arrays.asList(prod1, prod2, prod3));

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

		Cliente c1 = new Cliente(null, "Gislane Martins", "gislane@gmail.com", BR.cpfGerenarion(), TipoCliente.PESSOA_FISICA);
		c1.getTelefones().addAll(Arrays.asList("99999999", "74857498542"));

		Cliente c2 = new Cliente(null, "Leticia da Silva", "leticia@gmail.com", BR.cnpjGerenarion(), TipoCliente.PESSOA_JURIDICA);
		c2.getTelefones().addAll(Arrays.asList("88888888", "54542542542"));

		Cliente c3 = new Cliente(null, "Maria Souza", "maria@gmail.com", BR.cpfGerenarion(), TipoCliente.PESSOA_FISICA);
		c3.getTelefones().addAll(Arrays.asList("77777777", "595782409"));

		Fornecedor f1 = new Fornecedor(null, "Coca-Cola do Brasil", "coca@coca.com", BR.cnpjGerenarion(),
				"http://www.coca-cola.com.br");
		f1.getTelefones().addAll(Arrays.asList("21029999"));
		f1.getProdutos().addAll(Arrays.asList(prod1));
		prod1.getFornecedores().addAll(Arrays.asList(f1));
		
		Fornecedor f2 = new Fornecedor(null, "Tirol Laticineos", "tirol@gmail.com", BR.cnpjGerenarion(),
				"http://www.tirol.com.br");
		f2.getTelefones().addAll(Arrays.asList("88889999", "498475984"));
		f2.getProdutos().addAll(Arrays.asList(prod3));
		prod3.getFornecedores().addAll(Arrays.asList(f2));
		
		Usuario user1 = new Usuario(null, "Jhoni Souza", "jhoni.test@gmail.com", BR.cpfGerenarion(), TipoUsuario.ADMINSTRATIVO, "123");
		user1.getTelefones().addAll(Arrays.asList("36243642"));
		
		Usuario user2 = new Usuario(null, "Afranio Costa", "afranio@gmail.com", BR.cpfGerenarion(), TipoUsuario.OPERACIONAL, "123");
		user2.getTelefones().addAll(Arrays.asList("9494949", "548574928"));

		Endereco endereco1 = new Endereco(null, "Avenida Visconde de Guarapuava", "2900", null, curitiba, "Centro",
				"8894738", TipoEndereco.RESIDENCIAL);
		endereco1.setCliente(c1);
		
		Endereco endereco2 = new Endereco(null, "Avenida Paulista", "20200", null, saoPaulo, "Centro", "42453",
				TipoEndereco.COMERCIAL);
		endereco2.setCliente(c2);
		c2.setEnderecos(Arrays.asList(endereco2));
		
		Endereco endereco3 = new Endereco(null, "Rua Rodolpho Hasselman", "200", "Fundos", araucaria, "Fazenda Velha",
				"8370000", TipoEndereco.RESIDENCIAL);
		endereco3.setCliente(c3);
		c3.setEnderecos(Arrays.asList(endereco3));
		
		Endereco endereco4 = new Endereco(null, "Rua João Bettega", "3000", null, curitiba, "CIC", "58947549",
				TipoEndereco.COMERCIAL);
		endereco4.setCliente(c1);
		c1.setEnderecos(Arrays.asList(endereco1, endereco4));

		Endereco endereco5 = new Endereco(null, "Rua Ananindeua", "4300", null, curitiba, "Campo Comprido", "58947549",
				TipoEndereco.EMPRESARIAL);
		endereco5.setFornecedor(f1);
		f1.setEnderecos(Arrays.asList(endereco5));
		
		Endereco endereco6 = new Endereco(null, "Avenida General Gonçalves d'Avilla", "545", null, joinville,
				"Borda dos Açudes", "574958742", TipoEndereco.EMPRESARIAL);
		endereco6.setFornecedor(f2);
		f2.setEnderecos(Arrays.asList(endereco6));
		
		Endereco endereco7 = new Endereco(null, "Maria Brunatto Cantador", "530", null, araucaria,
				"Palamar", "83704383", TipoEndereco.RESIDENCIAL);
		endereco7.setUsuario(user1);
		user1.setEnderecos(Arrays.asList(endereco7));
		
		Endereco endereco8 = new Endereco(null, "Maria Brunatto Cantador", "531", null, araucaria,
				"Palamar", "83704383", TipoEndereco.RESIDENCIAL);
		endereco8.setUsuario(user2);
		user2.setEnderecos(Arrays.asList(endereco8));

		curitiba.setEnderecos(Arrays.asList(endereco1, endereco4, endereco5));
		saoPaulo.setEnderecos(Arrays.asList(endereco2));
		joinville.setEnderecos(Arrays.asList(endereco6));
		araucaria.setEnderecos(Arrays.asList(endereco3, endereco7, endereco8));

		estadoRepository.saveAll(Arrays.asList(PR, SC, SP));
		cidadeRepository.saveAll(Arrays.asList(curitiba, joinville, saoPaulo, araucaria));
		clienteRepository.saveAll(Arrays.asList(c1, c2, c3));
		fornecedorRepository.saveAll(Arrays.asList(f1, f2));
		usuarioRepository.saveAll(Arrays.asList(user1, user2));
		enderecoRepository.saveAll(Arrays.asList(endereco1, endereco2, endereco3, endereco4, endereco5, endereco6, endereco7, endereco8));

	}

}
