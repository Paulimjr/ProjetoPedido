package br.com.projeto.pedido.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.projeto.pedido.dto.CategoriaDTO;
import br.com.projeto.pedido.entity.Categoria;
import br.com.projeto.pedido.service.CategoriaService;

/**
 * Controlar as requisições de cateogorias
 * 
 * @author paulo
 *
 */
@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService categoriaService;
	
	/**
	 * API para consultar uma categoria pelo identificador
	 * 
	 * @author paulo
	 * @param id o identificador da categoria
	 * @return a categoria
	 */
	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> buscarCategoriaPeloId(@PathVariable final Integer id) {
		Categoria cat = this.categoriaService.buscar(id);
		return  ResponseEntity.ok().body(cat);
	}
	
	/**
	 * API para inserir uma nova categoria
	 * 
	 * @param cat a categoria para inserir
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Categoria cat) {
 		cat = categoriaService.insert(cat);
 		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
 				.path("/{id}").buildAndExpand(cat.getId()).toUri();
	 	return ResponseEntity.created(uri).build();
	}
	
	/**
	 * API para alterar uma categoria
	 * 
	 * @param obj a categoria para alterar
	 * @param id o identificador da categoria
	 * @return
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	 public ResponseEntity<Void> update(@RequestBody Categoria obj, @PathVariable Integer id) {
	 	obj.setId(id);
	 	obj = categoriaService.update(obj);
	 	return ResponseEntity.noContent().build();
	 }
	
	/**
	 * API para deletar uma categoria
	 * 
	 * @param obj a categoria para alterar
	 * @param id o identificador da categoria
	 * @return
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	 public ResponseEntity<Void> delete(@PathVariable Integer id) {	 	
	 	categoriaService.delete(id);
	 	return ResponseEntity.noContent().build();
	 }
	
	/**
	 * API para listar todas as categorias cadastradas
	 * 
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		List<Categoria> categorias = categoriaService.findAll();
		List<CategoriaDTO> listCat = categorias.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());  
		return ResponseEntity.ok().body(listCat);
	}
	
	
}
