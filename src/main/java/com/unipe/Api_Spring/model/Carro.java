package com.unipe.Api_Spring.model;

import com.unipe.Api_Spring.dto.form.CarroForm;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "carros")
public class Carro {
    // Model class para carro
    // Atributos: id, nome, marca, ano, cor, precoAluguel

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto-incremento
    private int id;

    @Column(nullable = false, length = 50) //tamanho 50
    private String marca;

    @Column(nullable = false, length = 50) //tamanho 50
    private String modelo;

    @Column(nullable = false, length = 50) //tamanho 50
    private String ano;

    @Column(nullable = false, length = 50) //tamanho 50
    private String cor;

    @Column(nullable = false, length = 50) //tamanho 50
    private float precoaluguel;

    public Carro(CarroForm carroForm){
        this.marca = carroForm.getMarca();
        this.modelo = carroForm.getModelo();
        this.ano = carroForm.getAno();
        this.cor = carroForm.getCor();
        this.precoaluguel = carroForm.getPrecoaluguel();
    }
}
