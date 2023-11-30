package com.unipe.Api_Spring.service;

import com.unipe.Api_Spring.dto.form.Login;
import com.unipe.Api_Spring.model.Usuario;
import com.unipe.Api_Spring.repository.UsuarioRepository;
import com.unipe.Api_Spring.utils.RequestResposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listar(){
        return usuarioRepository.findAll();
    }

    public RequestResposta buscarPorId(long id){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        return usuarioOptional.isEmpty()
                ? trataUsuarioInexistente()
                : new RequestResposta(usuarioOptional.get(), HttpStatus.OK);
    }

    public RequestResposta login(Login login){
        Optional<Usuario> usuarioOptional = usuarioRepository.findUsuarioByEmailAndSenha(login.getEmail(), login.getSenha());
        return usuarioOptional.isEmpty()
                ? trataUsuarioInexistente()
                : new RequestResposta("Login realizado com sucesso", HttpStatus.ACCEPTED);
    }

    public RequestResposta atualizaSenha(long id, String novaSenha) {
        Optional<Usuario> usuarioAtual = usuarioRepository.findById(id);
        if (usuarioAtual.isEmpty()) {
            return trataUsuarioInexistente();
        }
        Usuario usuario = usuarioAtual.get();
        usuario.setSenha(novaSenha);
        return new RequestResposta(usuarioRepository.save(usuario), HttpStatus.CREATED);
    }

    public RequestResposta atualizarEmail(long id, String novoEmail) {
        Optional<Usuario> usuarioAtual = usuarioRepository.findById(id);
        if (usuarioAtual.isEmpty()) {
            return trataUsuarioInexistente();
        }
        Usuario usuario = usuarioAtual.get();
        usuario.setEmail(novoEmail);
        return new RequestResposta(usuarioRepository.save(usuario), HttpStatus.CREATED);
    }

    public RequestResposta deletar(long id){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isEmpty()) {
            return trataUsuarioInexistente();
        }
        usuarioRepository.deleteById(id);
        return new RequestResposta("Usuário deletado com sucesso", HttpStatus.OK);
    }

    private RequestResposta trataUsuarioInexistente() {
        return new RequestResposta("Usuário não encontrado", HttpStatus.NOT_FOUND);
    }


}
