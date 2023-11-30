package com.unipe.Api_Spring.repository;

import com.unipe.Api_Spring.model.Aluguel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AluguelRepository extends JpaRepository<Aluguel, Integer> {

    @Query("SELECT a FROM Aluguel a WHERE a.locador.id = :idUsuario")
    Optional<Aluguel> findAluguelByLocador(@Param("idUsuario")int idUsuario);

    Optional<Aluguel> findAluguelByCarro(int id);
}
