package br.com.projeto.pedido.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.pedido.entity.Categoria;
import br.com.projeto.pedido.entity.Cliente;
import br.com.projeto.pedido.repository.CategoriaRepository;
import br.com.projeto.pedido.repository.ClienteRepository;
import br.com.projeto.pedido.service.exception.ObjectNotFoundException;

/**
 * Controla serviços relaciona ao cliente
 * 
 * @author paulo
 *
 */
@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	/**
	 * Método para buscar um cliente pelo ID
	 * 
	 * @author paulo
	 * @throws ObjectNotFoundException
	 * @param id o identificador do cliente
	 * @return o cliente caso encontre.
	 */
	public Cliente buscar(final Integer id) {
		Cliente cli = clienteRepository.findOne(id);
		
		if (cli == null) {
			throw new ObjectNotFoundException(String.format("Cliente com o ID %s não foi encontrado.", id));
		}
		
		return cli;
	}
}
