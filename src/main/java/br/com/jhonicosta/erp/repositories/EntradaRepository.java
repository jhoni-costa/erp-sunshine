package br.com.jhonicosta.erp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jhonicosta.erp.domain.Entrada;

@Repository
public interface EntradaRepository extends JpaRepository<Entrada, Integer>{

}
