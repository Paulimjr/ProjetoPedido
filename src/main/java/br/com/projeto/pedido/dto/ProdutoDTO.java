package br.com.projeto.pedido.dto;

import java.io.Serializable;

import javax.validation.constraints.DecimalMin;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.projeto.pedido.entity.Categoria;
import br.com.projeto.pedido.entity.Produto;

public class ProdutoDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String nome;
	
	@DecimalMin("1.0")
	private Double preco;
	
	private Categoria categoria;
	
	public ProdutoDTO() {
	}

	public ProdutoDTO(Produto obj) {
		id = obj.getId();
		nome = obj.getNome();
		preco = obj.getPreco();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	/**
	 * @return the categoria
	 */
	public Categoria getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProdutoDTO [id=" + id + ", nome=" + nome + ", preco=" + preco + ", categoria=" + categoria + "]";
	}

}
