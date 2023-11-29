package com.unipe.Api_Spring.controller;

import com.unipe.Api_Spring.dto.form.RegistroRequest;
import com.unipe.Api_Spring.dto.form.UsuarioForm;
import com.unipe.Api_Spring.service.auth.AuthService;
import com.unipe.Api_Spring.utils.RequestResposta;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/registro")
    public ResponseEntity<?> RegistroUser(@RequestBody RegistroRequest RegistroRequest) {
        if (authService.EmailExists(RegistroRequest.getEmail()))
            return RequestResposta.retornar(new RequestResposta("Email já cadastrado", HttpStatus.BAD_REQUEST));
        UsuarioForm usuarioFormCriado = authService.CreateUser(RegistroRequest);
        if (usuarioFormCriado == null)
            return RequestResposta.retornar(new RequestResposta("Usuário não criado", HttpStatus.BAD_REQUEST));
        return RequestResposta.retornar(new RequestResposta(usuarioFormCriado, HttpStatus.CREATED));
    }

    @PostMapping("/registro/admin")
    public ResponseEntity<?> RegistroAdmin(@RequestBody RegistroRequest RegistroRequest) {
        if (authService.EmailExists(RegistroRequest.getEmail()))
            return RequestResposta.retornar(new RequestResposta("Email já cadastrado", HttpStatus.BAD_REQUEST));
        UsuarioForm usuarioFormCriado = authService.CreateAdmin(RegistroRequest);
        if (usuarioFormCriado == null)
            return RequestResposta.retornar(new RequestResposta("Usuário não criado", HttpStatus.BAD_REQUEST));
        return RequestResposta.retornar(new RequestResposta(usuarioFormCriado, HttpStatus.CREATED));
    }

}
