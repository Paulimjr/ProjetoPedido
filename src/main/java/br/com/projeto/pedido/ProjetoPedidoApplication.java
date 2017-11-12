package br.com.projeto.pedido;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.projeto.pedido.entity.Categoria;
import br.com.projeto.pedido.entity.Cidade;
import br.com.projeto.pedido.entity.Cliente;
import br.com.projeto.pedido.entity.Endereco;
import br.com.projeto.pedido.entity.Estado;
import br.com.projeto.pedido.entity.Produto;
import br.com.projeto.pedido.entity.enums.TipoCliente;
import br.com.projeto.pedido.repository.CategoriaRepository;
import br.com.projeto.pedido.repository.CidadeRepository;
import br.com.projeto.pedido.repository.ClienteRepository;
import br.com.projeto.pedido.repository.EnderecoRepository;
import br.com.projeto.pedido.repository.EstadoRepository;
import br.com.projeto.pedido.repository.ProdutoRepository;

@SpringBootApplication
public class ProjetoPedidoApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(ProjetoPedidoApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		Categoria cat1 = new Categoria(null, "Informátia");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
			
		cat1.getProdutos().addAll(Arrays.asList(p1, p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.save(Arrays.asList(cat1, cat2));
		produtoRepository.save(Arrays.asList(p1, p2, p3));
		

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.save(Arrays.asList(est1, est2));
		cidadeRepository.save(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "44444444444", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("34992209811", "3499110022"));
		
		Endereco e1 = new Endereco(null, "Rua flores", "300","Apt", "Jardim", "38055580", cli1, c1);
		Endereco e2 = new Endereco(null, "Rua Johen", "450","sem complemento", "Lidice", "38055580", cli1, c2);
		
		cli1.getEndercos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.save(Arrays.asList(cli1));
		enderecoRepository.save(Arrays.asList(e1, e2));
	}
}
