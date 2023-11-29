package com.unipe.Api_Spring.dto.form;

import lombok.Data;

@Data
public class RegistroRequest {

    private String email;
    private String nome;
    private String senha;
}
