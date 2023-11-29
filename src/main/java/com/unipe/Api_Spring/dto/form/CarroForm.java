package com.unipe.Api_Spring.dto.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class CarroForm {
    private String nome;
    private String marca;
    private String modelo;
    private String cor;
    private String ano;
    private float precoaluguel;
}
