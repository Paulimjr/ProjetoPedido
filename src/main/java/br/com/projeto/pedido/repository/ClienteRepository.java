package br.com.projeto.pedido.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.projeto.pedido.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	
	/**
	 * Buscar o cliente pelo email informado
	 * 
	 * @param email
	 * @return
	 */
	@Transactional(readOnly=true)
	Cliente findByEmail(String email);
	
}
