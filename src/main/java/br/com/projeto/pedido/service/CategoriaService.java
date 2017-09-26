package br.com.projeto.pedido.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.pedido.entity.Categoria;
import br.com.projeto.pedido.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	/**
	 * Método para buscar uma categoria pelo ID
	 * 
	 * @author paulo
	 * @param id o identificador da categoria
	 * @return a categoria caso encontre.
	 */
	public Categoria buscar(final Integer id) {
		Categoria cat = categoriaRepository.findOne(id) ;		
		return cat;
	}
		
}
