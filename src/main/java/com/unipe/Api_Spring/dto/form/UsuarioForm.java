package com.unipe.Api_Spring.dto.form;

import com.unipe.Api_Spring.model.Enums.UserCargo;
import lombok.Data;

@Data
public class UsuarioForm {

    private int id;
    private String nome;
    private String email;
    private UserCargo cargo;

}
