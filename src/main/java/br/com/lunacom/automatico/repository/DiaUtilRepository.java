package br.com.lunacom.automatico.repository;

import br.com.lunacom.automatico.domain.entity.DiasUteis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaUtilRepository extends JpaRepository<DiasUteis, Integer> { }
