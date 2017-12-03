package br.com.projeto.pedido.service;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.projeto.pedido.entity.Categoria;
import br.com.projeto.pedido.repository.CategoriaRepository;
import br.com.projeto.pedido.service.exception.DataIntegrityException;
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

	/**
	 * Método para alterar uma categoria
	 * 
	 * @param obj
	 * @return
	 */
	public Categoria update(Categoria obj) {
		Categoria cat = this.buscar(obj.getId());
		cat.setNome(obj.getNome());
		
		return this.categoriaRepository.save(cat);
	}
	
	/**
	 * Método para deletar uma categoria
	 * 
	 * @param id
	 * @return
	 */
	public void delete(final Integer id) {
		this.buscar(id);
		try {
			categoriaRepository.delete(id);
		} catch (DataIntegrityException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
	}
	
	/**
	 * Método para retornar todas as categorias cadastradas
	 * 
	 * @return
	 */
	public List<Categoria> findAll() {
		return this.categoriaRepository.findAll();
	}		
}
