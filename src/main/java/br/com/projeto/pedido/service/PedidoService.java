package br.com.projeto.pedido.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.pedido.entity.Pedido;
import br.com.projeto.pedido.repository.PedidoRepository;
import br.com.projeto.pedido.service.exception.ObjectNotFoundException;

/**
 * Controlar serviços relacionado a pedido
 * 
 * @author paulo
 *
 */
@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	/**
	 * Método para buscar uma pedido pelo ID
	 * 
	 * @author paulo
	 * @throws ObjectNotFoundException
	 * @param id o identificador da pedido
	 * @return a pedido caso encontre.
	 */
	public Pedido buscar(final Integer id) {
		Pedido ped = pedidoRepository.findOne(id);
		
		if (ped == null) {
			throw new ObjectNotFoundException(String.format("Pedido com o ID %s não foi encontrada.", id));
		}
		
		return ped;
	}
		
}
