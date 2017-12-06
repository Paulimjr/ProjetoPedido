package br.com.projeto.pedido.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.projeto.pedido.dto.ProdutoDTO;
import br.com.projeto.pedido.entity.Produto;
import br.com.projeto.pedido.service.ProdutoService;
import br.com.projeto.pedido.service.validation.utils.URL;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService produtoService; 
	
	/**
	 * API para encontrar um produto
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Produto> buscarProdutoPeloId(@PathVariable Integer id) {
		Produto obj = produtoService.buscarProdutoPeloId(id);
		return ResponseEntity.ok().body(obj);
	}
	
	/**
	 * API para buscar produto com paginação
	 * 
	 * @param nome
	 * @param categorias
	 * @param page
	 * @param linesPerPage
	 * @param orderBy
	 * @param direction
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value="nome", defaultValue="") String nome, 
			@RequestParam(value="categorias", defaultValue="") String categorias, 
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		String nomeDecoded = URL.decodeParam(nome);
		List<Integer> ids = URL.decodeIntList(categorias);
		Page<Produto> list = produtoService.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listDto = list.map(obj -> new ProdutoDTO(obj));  
		
		return ResponseEntity.ok().body(listDto);
	}
	
	/**
	 * API para alterar um produto especifico
	 * 
	 * @param objDto
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> alterarProduto(@Valid @RequestBody ProdutoDTO obj, @PathVariable Integer id) {
		
		Produto prd = produtoService.fromDTO(obj);	
		prd.setId(id);
		
		produtoService.update(prd);
		
		return ResponseEntity.noContent().build();
	}
	
	/**
	 * API para inserir um novo prodouto
	 * 
	 * @param prd produto para cadastrar
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ProdutoDTO prd) {
		
		Produto produto = produtoService.fromDTO(prd);
		produto = produtoService.criarProduto(produto);
		
 		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
 				.path("/{id}").buildAndExpand(prd.getId()).toUri();
	 	return ResponseEntity.created(uri).build();
	}
	
	/**
	 * API para remover um produto
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		produtoService.deletarProduto(id);
		return ResponseEntity.noContent().build();
	}

}