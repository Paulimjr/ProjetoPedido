package br.com.projeto.pedido.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.projeto.pedido.entity.enums.TipoCliente;

@Entity
public class Cliente implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String email;
	private String cpfOuCnjpj;
	private Integer tipo;
	
	@JsonManagedReference
	@OneToMany(mappedBy="cliente")
	private List<Endereco> endercos = new ArrayList<>();
	
	@ElementCollection
	@CollectionTable(name = "telefone")
	private Set<String> telefones = new HashSet<>();

	public Cliente(Integer id, String nome, String email, String cpfOuCnjpj, TipoCliente tipo) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpfOuCnjpj = cpfOuCnjpj;
		this.tipo = tipo.getCod();
	}

	public Cliente() {
		super();
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the cpfOuCnjpj
	 */
	public String getCpfOuCnjpj() {
		return cpfOuCnjpj;
	}

	/**
	 * @param cpfOuCnjpj the cpfOuCnjpj to set
	 */
	public void setCpfOuCnjpj(String cpfOuCnjpj) {
		this.cpfOuCnjpj = cpfOuCnjpj;
	}

	/**
	 * @return the TipoCliente
	 */
	public TipoCliente getTipo() {
		return TipoCliente.toEnum(tipo);
	}

	/**
	 * @param tipo the TipoCliente to set
	 */
	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo.getCod();
	}

	/**
	 * @return the endercos
	 */
	public List<Endereco> getEndercos() {
		return endercos;
	}

	/**
	 * @param endercos the endercos to set
	 */
	public void setEndercos(List<Endereco> endercos) {
		this.endercos = endercos;
	}

	/**
	 * @return the telefones
	 */
	public Set<String> getTelefones() {
		return telefones;
	}

	/**
	 * @param telefones the telefones to set
	 */
	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
