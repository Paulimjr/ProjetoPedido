package br.com.projeto.pedido.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.pedido.entity.Categoria;
import br.com.projeto.pedido.service.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService categoriaService;
	
	/**
	 * API para consultar uma categoria pelo identificador
	 * 
	 * @author paulo
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public ResponseEntity<Categoria> buscarCategoriaPeloId(@PathVariable final Integer id) {
		Categoria cat = this.categoriaService.buscar(id);
		return  ResponseEntity.ok().body(cat);
	}
}
