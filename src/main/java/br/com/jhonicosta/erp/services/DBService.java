package br.com.jhonicosta.erp.services;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jhonicosta.erp.domain.Categoria;
import br.com.jhonicosta.erp.domain.Cidade;
import br.com.jhonicosta.erp.domain.Cliente;
import br.com.jhonicosta.erp.domain.Endereco;
import br.com.jhonicosta.erp.domain.Entrada;
import br.com.jhonicosta.erp.domain.Estado;
import br.com.jhonicosta.erp.domain.Estoque;
import br.com.jhonicosta.erp.domain.Fornecedor;
import br.com.jhonicosta.erp.domain.Produto;
import br.com.jhonicosta.erp.domain.Saida;
import br.com.jhonicosta.erp.domain.Usuario;
import br.com.jhonicosta.erp.domain.enuns.TipoCliente;
import br.com.jhonicosta.erp.domain.enuns.TipoEndereco;
import br.com.jhonicosta.erp.domain.enuns.TipoUsuario;
import br.com.jhonicosta.erp.repositories.CategoriaRepository;
import br.com.jhonicosta.erp.repositories.CidadeRepository;
import br.com.jhonicosta.erp.repositories.ClienteRepository;
import br.com.jhonicosta.erp.repositories.EnderecoRepository;
import br.com.jhonicosta.erp.repositories.EntradaRepository;
import br.com.jhonicosta.erp.repositories.EstadoRepository;
import br.com.jhonicosta.erp.repositories.EstoqueRepository;
import br.com.jhonicosta.erp.repositories.FornecedorRepository;
import br.com.jhonicosta.erp.repositories.ProdutoRepository;
import br.com.jhonicosta.erp.repositories.SaidaRepository;
import br.com.jhonicosta.erp.repositories.UsuarioRepository;
import br.com.jhonicosta.erp.services.validation.BR.BR;

@Service
public class DBService {

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

	@Autowired
	private EntradaRepository entradaRepository;

	@Autowired
	private SaidaRepository saidaRepository;

	@Autowired
	private EstoqueRepository estoqueRepository;

	public void instantiateTestDatabase() throws ParseException {

		instantiateProduto();

		instantiateEstadoECidade();

		instantiateCliente();

		instantiateFornecedor();

		instantiateUsuario();

		instantiateEndereco();

		instantiateEntrada();

		instantiateSaida();

	}

	private void instantiateProduto() {

		Categoria cat1 = new Categoria(null, "Bebidas");
		Categoria cat2 = new Categoria(null, "Bebidas Alcoolicas");
		Categoria cat3 = new Categoria(null, "Laticinios");
		Categoria cat4 = new Categoria(null, "Mercearia");
		Categoria cat5 = new Categoria(null, "Higiene Pessoal");
		Categoria cat6 = new Categoria(null, "Frios");
		Categoria cat7 = new Categoria(null, "Perfumaria");

		Produto prod1 = new Produto(null, "Coca-cola Zero", "Refrigerante de cola, zero açucar, garrafa PET de 2L",
				"7257642965942", 3.99);

		Produto prod2 = new Produto(null, "Antartica Sub-Zero",
				"Cerveja Antartica Sub-Zero, pack com 12 latas de 350ml", "274857428", 10.99);

		Produto prod3 = new Produto(null, "Leite Tirol", "Leite Integral Tirol, Caixa tetrapack 1L", "254256", 1.99);

		Produto prod4 = new Produto(null, "Leite Tirol Desnatado", "Leite Integral Tirol, Caixa tetrapack 1L", "254257",
				1.99);

		Produto prod5 = new Produto(null, "Creme de Leite Tirol", "Creme de Leite Tirol, Caixa tetrapack 200ml",
				"254257", 0.89);

		prod1.getCategorias().addAll(Arrays.asList(cat1));
		prod2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		prod3.getCategorias().addAll(Arrays.asList(cat1, cat3));
		prod4.setCategorias(Arrays.asList(cat1, cat3));
		prod5.setCategorias(Arrays.asList(cat3));

		cat1.setProdutos(Arrays.asList(prod1, prod2, prod3, prod4));
		cat2.setProdutos(Arrays.asList(prod2));
		cat3.setProdutos(Arrays.asList(prod3, prod4, prod5));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(prod1, prod2, prod3, prod4, prod5));

		Estoque estoque1 = new Estoque(prod1);
		Estoque estoque2 = new Estoque(prod2);
		Estoque estoque3 = new Estoque(prod3);
		Estoque estoque4 = new Estoque(prod4);
		Estoque estoque5 = new Estoque(prod5);

		prod1.setEstoque(estoque1);
		prod2.setEstoque(estoque2);
		prod3.setEstoque(estoque3);
		prod4.setEstoque(estoque4);
		prod5.setEstoque(estoque5);

		estoqueRepository.saveAll(Arrays.asList(estoque1, estoque2, estoque3, estoque4, estoque5));
		produtoRepository.saveAll(Arrays.asList(prod1, prod2, prod3, prod4, prod5));
	}

	@SuppressWarnings("unused")
	private void instantiateEstadoECidade() {

		Estado AC = new Estado(null, "Acre", "AC");
		Estado AL = new Estado(null, "Alagoas", "AL");
		Estado AP = new Estado(null, "Amapá", "AP");
		Estado AM = new Estado(null, "Amazonas", "AM");
		Estado BA = new Estado(null, "Bahia", "BA");
		Estado CE = new Estado(null, "Ceará", "CE");
		Estado DF = new Estado(null, "Distrito Federal", "DF");
		Estado ES = new Estado(null, "Espírito Santo", "ES");
		Estado GO = new Estado(null, "Goiás", "GO");
		Estado MA = new Estado(null, "Maranhão", "MA");
		Estado MT = new Estado(null, "Mato Grosso", "MT");
		Estado MS = new Estado(null, "Mato Grosso do Sul", "MS");
		Estado MG = new Estado(null, "Minas Gerais", "MG");
		Estado PA = new Estado(null, "Pará", "PA");
		Estado PB = new Estado(null, "Paraíba", "PB");
		Estado PR = new Estado(null, "Paraná", "PR");
		Estado PE = new Estado(null, "Pernambuco", "PE");
		Estado PI = new Estado(null, "Piauí", "PI");
		Estado RJ = new Estado(null, "Rio de Janeiro", "RJ");
		Estado RN = new Estado(null, "Rio Grande do Norte", "RN");
		Estado RS = new Estado(null, "Rio Grande do Sul", "RS");
		Estado RO = new Estado(null, "Rondônia", "RO");
		Estado RR = new Estado(null, "Roraima", "RR");
		Estado SC = new Estado(null, "Santa Catarina", "SC");
		Estado SP = new Estado(null, "São Paulo", "SP");
		Estado SE = new Estado(null, "Sergipe", "SE");
		Estado TO = new Estado(null, "Tocantins", "TO");

		Cidade cid1 = new Cidade(null, "Curitiba", PR);
		Cidade cid2 = new Cidade(null, "Joinville", SC);
		Cidade cid3 = new Cidade(null, "São Paulo", SP);
		Cidade cid4 = new Cidade(null, "Araucária", PR);

		PR.setCidades(Arrays.asList(cid1, cid4));
		SC.setCidades(Arrays.asList(cid2));
		SP.setCidades(Arrays.asList(cid3));

		estadoRepository.saveAll(Arrays.asList(PR, SC, SP));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3, cid4));
	}

	private void instantiateCliente() {

		Cliente c1 = new Cliente(null, "Gislane Martins", "gislane@gmail.com", BR.cpfGerenarion(),
				TipoCliente.PESSOA_FISICA);
		c1.getTelefones().addAll(Arrays.asList("99999999", "74857498542"));

		Cliente c2 = new Cliente(null, "Leticia da Silva", "leticia@gmail.com", BR.cnpjGerenarion(),
				TipoCliente.PESSOA_JURIDICA);
		c2.getTelefones().addAll(Arrays.asList("88888888", "54542542542"));

		Cliente c3 = new Cliente(null, "Maria Souza", "maria@gmail.com", BR.cpfGerenarion(), TipoCliente.PESSOA_FISICA);
		c3.getTelefones().addAll(Arrays.asList("77777777", "595782409"));

		clienteRepository.saveAll(Arrays.asList(c1, c2, c3));

	}

	private void instantiateFornecedor() {

		Produto prod1 = produtoRepository.findById(1).get();
		Produto prod3 = produtoRepository.findById(3).get();

		Fornecedor f1 = new Fornecedor(null, "Coca-Cola do Brasil", "coca@coca.com", BR.cnpjGerenarion(),
				"http://www.coca-cola.com.br");
		f1.getTelefones().addAll(Arrays.asList("21029999"));
		f1.setProdutos(Arrays.asList(prod1));

		Fornecedor f2 = new Fornecedor(null, "Tirol Laticineos", "tirol@gmail.com", BR.cnpjGerenarion(),
				"http://www.tirol.com.br");
		f2.getTelefones().addAll(Arrays.asList("88889999", "498475984"));
		f2.setProdutos(Arrays.asList(prod3));

		prod1.setFornecedores(Arrays.asList(f1));
		prod3.setFornecedores(Arrays.asList(f2));

		fornecedorRepository.saveAll(Arrays.asList(f1, f2));
		produtoRepository.saveAll(Arrays.asList(prod1, prod3));
	}

	private void instantiateUsuario() {

		Usuario user1 = new Usuario(null, "Jhoni Souza", "jhoni.test@gmail.com", BR.cpfGerenarion(),
				TipoUsuario.ADMINSTRATIVO, "123");
		user1.getTelefones().addAll(Arrays.asList("36243642"));

		Usuario user2 = new Usuario(null, "Afranio Costa", "afranio@gmail.com", BR.cpfGerenarion(),
				TipoUsuario.OPERACIONAL, "123");
		user2.getTelefones().addAll(Arrays.asList("9494949", "548574928"));

		usuarioRepository.saveAll(Arrays.asList(user1, user2));
	}

	private void instantiateEndereco() {

		Cidade cid1 = cidadeRepository.findById(1).get();

		Cidade cid2 = cidadeRepository.findById(2).get();

		Cidade cid3 = cidadeRepository.findById(3).get();

		Cidade cid4 = cidadeRepository.findById(4).get();

		Cliente c1 = clienteRepository.findById(1).get();
		Endereco endereco1 = new Endereco(null, "Avenida Visconde de Guarapuava", "2900", null, cid1, "Centro",
				"8894738", TipoEndereco.RESIDENCIAL);
		endereco1.setCliente(c1);

		Cliente c2 = clienteRepository.findById(2).get();
		Endereco endereco2 = new Endereco(null, "Avenida Paulista", "20200", null, cid3, "Centro", "42453",
				TipoEndereco.COMERCIAL);
		endereco2.setCliente(c2);
		c2.setEnderecos(Arrays.asList(endereco2));

		Cliente c3 = clienteRepository.findById(3).get();
		Endereco endereco3 = new Endereco(null, "Rua Rodolpho Hasselman", "200", "Fundos", cid4, "Fazenda Velha",
				"8370000", TipoEndereco.RESIDENCIAL);
		endereco3.setCliente(c3);
		c3.setEnderecos(Arrays.asList(endereco3));

		Endereco endereco4 = new Endereco(null, "Rua João Bettega", "3000", null, cid1, "CIC", "58947549",
				TipoEndereco.COMERCIAL);
		endereco4.setCliente(c1);
		c1.setEnderecos(Arrays.asList(endereco1, endereco4));

		Fornecedor f1 = fornecedorRepository.findById(1).get();
		Endereco endereco5 = new Endereco(null, "Rua Ananindeua", "4300", null, cid1, "Campo Comprido", "58947549",
				TipoEndereco.EMPRESARIAL);
		endereco5.setFornecedor(f1);
		f1.setEnderecos(Arrays.asList(endereco5));

		Fornecedor f2 = fornecedorRepository.findById(2).get();
		Endereco endereco6 = new Endereco(null, "Avenida General Gonçalves d'Avilla", "545", null, cid2,
				"Borda dos Açudes", "574958742", TipoEndereco.EMPRESARIAL);
		endereco6.setFornecedor(f2);
		f2.setEnderecos(Arrays.asList(endereco6));

		Usuario user1 = usuarioRepository.findById(1).get();
		Endereco endereco7 = new Endereco(null, "Maria Brunatto Cantador", "530", null, cid4, "Palamar", "83704383",
				TipoEndereco.RESIDENCIAL);
		endereco7.setUsuario(user1);
		user1.setEnderecos(Arrays.asList(endereco7));

		Usuario user2 = usuarioRepository.findById(2).get();
		Endereco endereco8 = new Endereco(null, "Maria Brunatto Cantador", "531", null, cid4, "Palamar", "83704383",
				TipoEndereco.RESIDENCIAL);
		endereco8.setUsuario(user2);
		user2.setEnderecos(Arrays.asList(endereco8));

		cid1.setEnderecos(Arrays.asList(endereco1, endereco4, endereco5));
		cid2.setEnderecos(Arrays.asList(endereco6));
		cid3.setEnderecos(Arrays.asList(endereco2));
		cid4.setEnderecos(Arrays.asList(endereco3, endereco7, endereco8));

		enderecoRepository.saveAll(
				Arrays.asList(endereco1, endereco2, endereco3, endereco4, endereco5, endereco6, endereco7, endereco8));
		clienteRepository.saveAll(Arrays.asList(c1, c2, c3));
		fornecedorRepository.saveAll(Arrays.asList(f1, f2));
		usuarioRepository.saveAll(Arrays.asList(user1, user2));

	}

	private void instantiateEntrada() {

		Fornecedor fornecedor = fornecedorRepository.findById(2).get();
		Produto p3 = produtoRepository.findById(3).get();
		p3.setQuantidade(20);

		Produto p4 = produtoRepository.findById(4).get();
		p4.setQuantidade(10);

		Produto p5 = produtoRepository.findById(5).get();
		p5.setQuantidade(10);

		Entrada entrada1 = new Entrada(null, Calendar.getInstance().getTime(), fornecedor, Arrays.asList(p3, p4, p5));
		entrada1.setTotal(entrada1.getTotal());

		p3.setEntrada(Arrays.asList(entrada1));
		p4.setEntrada(Arrays.asList(entrada1));
		p5.setEntrada(Arrays.asList(entrada1));

		Estoque e3 = estoqueRepository.findByProduto(p3);
		e3.entradaQuantidade(p3.getQuantidade());
		Estoque e4 = estoqueRepository.findByProduto(p4);
		e4.entradaQuantidade(p4.getQuantidade());
		Estoque e5 = estoqueRepository.findByProduto(p5);
		e5.entradaQuantidade(p5.getQuantidade());

		entradaRepository.save(entrada1);
		produtoRepository.saveAll(Arrays.asList(p3, p4, p5));
		estoqueRepository.saveAll(Arrays.asList(e3, e4, e5));

	}

	private void instantiateSaida() {

		Cliente cliente = clienteRepository.findById(1).get();
		Produto produto1 = produtoRepository.findById(1).get();
		produto1.setQuantidade(1);

		Produto produto2 = produtoRepository.findById(2).get();
		produto2.setQuantidade(12);

		Saida saida = new Saida(null, Calendar.getInstance().getTime(), cliente, Arrays.asList(produto1, produto2));
		saida.setTotal(saida.getTotal());

		produto1.setSaida(Arrays.asList(saida));
		produto2.setSaida(Arrays.asList(saida));

		Estoque e1 = estoqueRepository.findByProduto(produto1);
		e1.saidaQuantidade(produto1.getQuantidade());
		Estoque e2 = estoqueRepository.findByProduto(produto2);
		e2.saidaQuantidade(produto2.getQuantidade());

		saidaRepository.save(saida);
		produtoRepository.saveAll(Arrays.asList(produto1, produto2));
		estoqueRepository.saveAll(Arrays.asList(e1, e2));
	}
}
