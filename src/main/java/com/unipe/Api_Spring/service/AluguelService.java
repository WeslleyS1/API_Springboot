package com.unipe.Api_Spring.service;

import com.unipe.Api_Spring.model.Aluguel;
import com.unipe.Api_Spring.model.Carro;
import com.unipe.Api_Spring.model.Usuario;
import com.unipe.Api_Spring.repository.AluguelRepository;
import com.unipe.Api_Spring.repository.CarroAlugadoRepository;
import com.unipe.Api_Spring.repository.CarroRepository;
import com.unipe.Api_Spring.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AluguelService {

    @Autowired
    private AluguelRepository aluguelRepository;

    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CarroAlugadoRepository carroAlugadoRepository;

    @Transactional
    public void alugarCarro(int idCarro, long idUsuario, String dataInicio, String dataFim) {
        Optional<Carro> carroOptional = carroRepository.findById(idCarro);
        if (carroOptional.isEmpty()) {
            System.out.println("Carro não encontrado");
        }
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(idUsuario);
        if (usuarioOptional.isEmpty()) {
            System.out.println("Usuário não encontrado");
        }

        Carro carro = carroOptional.get();
        Usuario usuario = usuarioOptional.get();

        Optional<Aluguel> aluguelOptional = aluguelRepository.findAluguelByLocador(usuario.getId());

    }

}
