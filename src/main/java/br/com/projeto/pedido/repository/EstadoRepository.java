package br.com.projeto.pedido.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.pedido.entity.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {

}
