package br.com.projeto.pedido.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.pedido.entity.Pedido;
import br.com.projeto.pedido.service.PedidoService;

/**
 * Controlar as requisições de pedidos
 * 
 * @author paulo
 *
 */
@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoService pedidoService;
	
	/**
	 * API para consultar uma pedido pelo identificador
	 * 
	 * @author paulo
	 * @param id o identificador da pedido
	 * @return o pedido
	 */
	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> buscarPedidoPeloId(@PathVariable final Integer id) {
		Pedido ped = this.pedidoService.buscar(id);
		return  ResponseEntity.ok().body(ped);
	}
}
