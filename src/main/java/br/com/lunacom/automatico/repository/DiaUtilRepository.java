package br.com.lunacom.automatico.repository;

import br.com.lunacom.automatico.domain.entity.DiaUtil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface DiaUtilRepository extends JpaRepository<DiaUtil, Integer> {

    Optional<DiaUtil> findByDia(LocalDate dia);
}
