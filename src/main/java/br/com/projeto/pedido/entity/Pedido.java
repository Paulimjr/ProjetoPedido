package br.com.projeto.pedido.entity;

import java.util.Date;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

public class Pedido {
	
	private Integer id;
	private Date instante;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "pagamento_id")
	private Pagamento pagamento;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "endereco_entrega_id")
	private Endereco enderecoDeEntrega;

}
