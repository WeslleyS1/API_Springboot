package com.unipe.Api_Spring.service.auth;

import com.unipe.Api_Spring.dto.form.RegistroRequest;
import com.unipe.Api_Spring.dto.form.UsuarioForm;

public interface AuthService {

    UsuarioForm CreateUser(RegistroRequest RegistroRequest);

    UsuarioForm CreateAdmin(RegistroRequest RegistroRequest);

    boolean EmailExists(String email);

}
