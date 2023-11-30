package com.unipe.Api_Spring.repository;

import com.unipe.Api_Spring.model.CarroAlugado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarroAlugadoRepository extends JpaRepository<CarroAlugado, Integer> {




}
