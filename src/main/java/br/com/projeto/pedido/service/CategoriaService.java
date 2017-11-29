package br.com.projeto.pedido.service;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.projeto.pedido.entity.Categoria;
import br.com.projeto.pedido.repository.CategoriaRepository;
import br.com.projeto.pedido.service.exception.ObjectNotFoundException;

/**
 * Controlar serviços relacionado a categoria
 * 
 * @author paulo
 *
 */
@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	/**
	 * Método para buscar uma categoria pelo ID
	 * 
	 * @author paulo
	 * @throws ObjectNotFoundException
	 * @param id o identificador da categoria
	 * @return a categoria caso encontre.
	 */
	public Categoria buscar(final Integer id) {
		Categoria cat = categoriaRepository.findOne(id);
		
		if (cat == null) {
			throw new ObjectNotFoundException(String.format("Categoria com o ID %s não foi encontrada.", id));
		}
		
		return cat;
	}
	
	/**
	 * Método para inserir uma nova categoria
	 * 
	 * @param obj
	 * @return
	 */
	public Categoria insert(final Categoria cat) {
		 cat.setId(null);
		 return categoriaRepository.save(cat);
	}		
}
