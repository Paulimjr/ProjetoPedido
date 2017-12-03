package br.com.projeto.pedido.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.projeto.pedido.dto.ClienteDTO;
import br.com.projeto.pedido.entity.Categoria;
import br.com.projeto.pedido.entity.Cliente;
import br.com.projeto.pedido.repository.CategoriaRepository;
import br.com.projeto.pedido.repository.ClienteRepository;
import br.com.projeto.pedido.service.exception.DataIntegrityException;
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
	
	/**
	 * Atualizar informaçoes de um cliente
	 * 
	 * @param obj
	 * @return
	 */
	public Cliente update(Cliente obj) {
		Cliente newObj = this.buscar(obj.getId());
		updateData(newObj, obj);
		return clienteRepository.save(newObj);
	}
	
	/**
	 * Remover um cliente cadastrado
	 * 
	 * @param id
	 */
	public void delete(Integer id) {
		
		this.buscar(id);
		
		try {
			clienteRepository.delete(id);
		}
		catch (DataIntegrityException e) {
			throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas");
		}
	}
	
	/**
	 * Trazer todos os clientes cadastrados
	 * 
	 * @return
	 */
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}
	
	/**
	 * Consulta cliente com paginação
	 * 
	 * @param page
	 * @param linesPerPage
	 * @param orderBy
	 * @param direction
	 * @return
	 */
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageRequest);
	}
	
	/**
	 * Converter ClienteDTO para entidade Cliente
	 * 
	 * @param objDto
	 * @return
	 */
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}
	
	/**
	 * Atualizar os parametros
	 * 
	 * @param newObj
	 * @param obj
	 */
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
