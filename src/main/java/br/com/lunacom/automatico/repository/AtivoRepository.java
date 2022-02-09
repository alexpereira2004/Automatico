package br.com.lunacom.automatico.repository;

import br.com.lunacom.automatico.domain.entity.Ativo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AtivoRepository extends JpaRepository<Ativo, Integer> {

    List<Ativo> findAllBySeguindo(String seguindo);
}
