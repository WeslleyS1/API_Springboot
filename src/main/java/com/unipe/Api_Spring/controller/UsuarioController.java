package com.unipe.Api_Spring.controller;

import com.unipe.Api_Spring.dto.form.Login;
import com.unipe.Api_Spring.model.Usuario;
import com.unipe.Api_Spring.utils.RequestResposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.unipe.Api_Spring.service.UsuarioService;

import java.util.List;


@RestController
@RequestMapping("api/auth/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> listar(){
        return new ResponseEntity<>(usuarioService.listar(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable int id){
        return RequestResposta.retornar(usuarioService.buscarPorId(id));
    }

    @PutMapping("/{id}/nova-senha")
    public ResponseEntity<Object> atualizarSenha(@PathVariable int id, @RequestBody String novaSenha) {
        return RequestResposta.retornar(usuarioService.atualizaSenha(id, novaSenha));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable int id){
        return RequestResposta.retornar(usuarioService.deletar(id));
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody Login login ){
        return RequestResposta.retornar(usuarioService.login(login));
    }
}
