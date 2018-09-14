package br.com.jhonicosta.erp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jhonicosta.erp.domain.Saida;

@Repository
public interface SaidaRepository extends JpaRepository<Saida, Integer>{

}
