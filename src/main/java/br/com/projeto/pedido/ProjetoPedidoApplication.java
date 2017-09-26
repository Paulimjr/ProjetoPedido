package br.com.projeto.pedido;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.projeto.pedido.entity.Categoria;
import br.com.projeto.pedido.repository.CategoriaRepository;

@SpringBootApplication
public class ProjetoPedidoApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ProjetoPedidoApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		Categoria c1 = new Categoria();
		c1.setId(null);
		c1.setNome("Informática");
		
		Categoria c2 = new Categoria();
		c2.setId(null);
		c2.setNome("Escritório");
		
		categoriaRepository.save(Arrays.asList(c1, c2));
	}
}
