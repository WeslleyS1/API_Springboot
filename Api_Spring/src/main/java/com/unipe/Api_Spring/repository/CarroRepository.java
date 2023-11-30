package com.unipe.Api_Spring.repository;

import java.util.List;
import java.util.Optional;
import com.unipe.Api_Spring.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Integer> {

    Optional<Carro> findCarroByModelo(String modelo);

    Optional<Carro> findCarroByMarca(String marca);

    Optional<Carro> findCarroByAno(String ano);

    void deleteByModelo(String modelo);

    @Query("SELECT car FROM Carro WHERE car.precoaluguel >= :precoaluguel")
    List<Carro> buscarMaiorOuIgualA50(@Param("precoaluguel") float precoAluguel);




}
