package br.com.lunacom.automatico.repository;

import br.com.lunacom.automatico.domain.entity.Ativo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AtivoRepository extends JpaRepository<Ativo, Integer> {

    List<Ativo> findAllBySeguindo(String seguindo);

    @Modifying
    @Query("UPDATE Ativo a set a.ultimaAtualizacao = :dia where a.codigo = :codigo")
    void updateUltimaAtualizacao(
            @Param(value = "codigo") String codigo,
            @Param(value = "dia") LocalDateTime dia
    );
}
