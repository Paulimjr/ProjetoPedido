package br.com.projeto.pedido.entity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.projeto.pedido.entity.enums.EstadoPagamento;

public class Pagamento {

	private Integer id;
	private EstadoPagamento estado;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "pedido_id")
	private Pedido pedido;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;
}

