package br.com.projeto.pedido.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.pedido.entity.Cliente;
import br.com.projeto.pedido.service.ClienteService;
/**
 * Controlar as requisções de clientes
 * 
 * @author paulo
 *
 */
@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService clienteService;
	
	/**
	 * API para consultar um cliente pelo identificador
	 * 
	 * @author paulo
	 * @param id o identificador do cliente
	 * @return o cliente
	 */
	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> buscarClientePeloId(@PathVariable final Integer id) {
		Cliente cli = this.clienteService.buscar(id);
		return  ResponseEntity.ok().body(cli);
	}
}
