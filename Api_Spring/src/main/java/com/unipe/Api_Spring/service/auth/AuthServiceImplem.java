package com.unipe.Api_Spring.service.auth;

import com.unipe.Api_Spring.dto.form.RegistroRequest;
import com.unipe.Api_Spring.model.Enums.UserCargo;
import com.unipe.Api_Spring.dto.form.UsuarioForm;
import com.unipe.Api_Spring.model.Usuario;
import com.unipe.Api_Spring.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImplem implements AuthService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UsuarioForm CreateUser(RegistroRequest RegistroRequest) {
        Usuario user = new Usuario();
        user.setNome(RegistroRequest.getNome());
        user.setEmail(RegistroRequest.getEmail());
        user.setSenha(RegistroRequest.getSenha());
        user.setCargo(UserCargo.USER);
        Usuario usuarioCriado = usuarioRepository.save(user);
        UsuarioForm usuarioForm = new UsuarioForm();
        usuarioForm.setId(usuarioCriado.getId());
        return usuarioForm;
    }

    @Override
    public UsuarioForm CreateAdmin(RegistroRequest RegistroRequest) {
        Usuario user = new Usuario();
        user.setNome(RegistroRequest.getNome());
        user.setEmail(RegistroRequest.getEmail());
        user.setSenha(RegistroRequest.getSenha());
        user.setCargo(UserCargo.ADMIN);
        Usuario usuarioCriado = usuarioRepository.save(user);
        UsuarioForm usuarioForm = new UsuarioForm();
        usuarioForm.setId(usuarioCriado.getId());
        return usuarioForm;
    }


    @Override
    public boolean EmailExists(String email) {
        return usuarioRepository.findByEmail(email).isPresent();
    }


}
