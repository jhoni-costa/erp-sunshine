package br.com.jhonicosta.erp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.jhonicosta.erp.domain.Estoque;
import br.com.jhonicosta.erp.domain.Produto;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Integer> {
	
	@Transactional(readOnly=true)
	Estoque findByProduto(Produto produto);
}