package br.com.projeto.pedido.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	@RequestMapping("/listar")
	public String listar() {
		return "Está funcionando";
	}
	
}
