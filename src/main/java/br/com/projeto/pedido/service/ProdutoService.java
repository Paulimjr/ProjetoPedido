package br.com.projeto.pedido.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.projeto.pedido.dto.ProdutoDTO;
import br.com.projeto.pedido.entity.Categoria;
import br.com.projeto.pedido.entity.Produto;
import br.com.projeto.pedido.repository.CategoriaRepository;
import br.com.projeto.pedido.repository.ProdutoRepository;
import br.com.projeto.pedido.service.exception.DataIntegrityException;
import br.com.projeto.pedido.service.exception.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	
	/**
	 * Método para buscar produto pelo ID
	 * 
	 * @param id o identificador do produto
	 * @return o produto
	 */
	public Produto buscarProdutoPeloId(final Integer id) {
		Produto prd = produtoRepository.findOne(id);
		
		if (prd == null) {
			throw new ObjectNotFoundException("Produto com identificador: " +id+" não foi encontrado! ");
		}
		
		return prd;
	}
	
	/**
	 * Buscar produto com paginação
	 * 
	 * @param nomeDecoded
	 * @param ids os identificadores dos produtos
	 * @param page
	 * @param linesPerPage
	 * @param orderBy
	 * @param direction
	 * @return lista de produtos páginada
	 */
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage,
			String orderBy, String direction) {
		
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAll(ids);
		
		return produtoRepository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);	
	}
	

	/**
	 * Método para alterar um produto cadastrado
	 * 
	 * @param obj
	 * @param id
	 * @return
	 */
	public Produto update(final Produto obj) {
		Produto prd = produtoRepository.findOne(obj.getId());
		
		if (prd == null) {
			throw new ObjectNotFoundException("Produto não foi encontrado para alterar.");
		}
		
		prd.setNome(obj.getNome());
		prd.setPreco(obj.getPreco());
		
		produtoRepository.save(prd);
		
		return prd;
	}
	
	/**
	 * Converte ProdutoDTO para Produto entity
	 * 
	 * @param obj
	 * @return
	 */
	public Produto fromDTO(ProdutoDTO obj) {
		Produto prd = new Produto(null, obj.getNome(), obj.getPreco());
		if (obj.getCategoria() != null && obj.getCategoria().getId() != null) {
			Categoria cat = this.categoriaRepository.findOne(obj.getCategoria().getId());
			
			if (cat == null) {
				throw new ObjectNotFoundException(String.format("Categoria com o ID %s não foi encontrada.", obj.getCategoria().getId()));
			}
			
			prd.setCategorias(Arrays.asList(cat));
		}
		
		return prd;
	}
	
	/**
	 * Método para inserir um novo produto
	 * 
	 * @param obj
	 * @return
	 */
	public Produto criarProduto(final Produto prd) {
		prd.setId(null);
		return produtoRepository.save(prd);
	}
	
	/**
	 * Passando o produto já com a categoria
	 * 
	 * @param prd
	 * @return
	 */
	public Produto insereCategoriaNoProduto(final Produto prd) {
		return produtoRepository.save(prd);
	}

	/**
	 * Deletar um produto especifico
	 * 
	 * @param id
	 */
	public void deletarProduto(Integer id) {
		this.buscarProdutoPeloId(id);
		
		try {
			produtoRepository.delete(id);
		}
		catch (DataIntegrityException e) {
			throw new DataIntegrityException("Não é possível excluir porque há pedidos ou categorias relacionados");
		}
	}
	
}
