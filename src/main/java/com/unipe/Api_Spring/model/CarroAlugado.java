package com.unipe.Api_Spring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity @Table
public class CarroAlugado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "carro_id", referencedColumnName = "id")
    private Carro carro;

    @ManyToOne
    @JoinColumn(name = "aluguel_id", referencedColumnName = "id")
    private Aluguel aluguel;

    @Column(nullable = false, length = 50) //tamanho 50
    private String dataInicio;

    @Column(nullable = false, length = 50) //tamanho 50
    private String dataFim;


    public CarroAlugado(Carro carro, Aluguel aluguel) {
        this.carro = carro;
        this.aluguel = aluguel;
    }

    public CarroAlugado(Carro carro, Aluguel aluguel, String dataInicio, String dataFim) {
        this.carro = carro;
        this.aluguel = aluguel;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }
}
