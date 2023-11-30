package com.unipe.Api_Spring.service;

import com.unipe.Api_Spring.model.Aluguel;
import com.unipe.Api_Spring.model.Carro;
import com.unipe.Api_Spring.model.CarroAlugado;
import com.unipe.Api_Spring.model.Usuario;
import com.unipe.Api_Spring.repository.AluguelRepository;
import com.unipe.Api_Spring.repository.CarroAlugadoRepository;
import com.unipe.Api_Spring.repository.CarroRepository;
import com.unipe.Api_Spring.repository.UsuarioRepository;
import com.unipe.Api_Spring.utils.RequestResposta;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public RequestResposta alugarCarro(int idCarro, long idUsuario, String dataInicio, String dataFim) {
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
        if (aluguelOptional.isEmpty()) {
            Aluguel aluguel = new Aluguel(usuario);
            aluguelRepository.save(aluguel);
            CarroAlugado carroAlugado = new CarroAlugado(carro, aluguel, dataInicio, dataFim);
            carroAlugadoRepository.save(carroAlugado);
            aluguel.addCarroAlugado(carroAlugado);
            return new RequestResposta(aluguelRepository.save(aluguel), HttpStatus.CREATED);
        } else {
            Aluguel aluguelEncontrado = aluguelOptional.get();
            CarroAlugado carroAlugado = new CarroAlugado(carro, aluguelEncontrado, dataInicio, dataFim);
            carroAlugadoRepository.save(carroAlugado);
            aluguelEncontrado.addCarroAlugado(carroAlugado);
            aluguelRepository.save(aluguelEncontrado);

            String mensagem = "Adicionado o carro " + carro.getMarca() + carro.getModelo() + " ao aluguel do " + usuario.getNome() + "\n" + aluguelEncontrado;

            return new RequestResposta(mensagem, HttpStatus.CREATED);
        }
    }

    public RequestResposta listarAlugueis(){
        return new RequestResposta(aluguelRepository.findAll(), HttpStatus.OK);
    }

public RequestResposta listarAlugueisPorUsuario(long idUsuario){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(idUsuario);
        if (usuarioOptional.isEmpty()) {
            return new RequestResposta("Usuário não encontrado", HttpStatus.NOT_FOUND);
        }
        Usuario usuario = usuarioOptional.get();
        return new RequestResposta(aluguelRepository.findAluguelByLocador(usuario.getId()), HttpStatus.OK);
    }

    public RequestResposta listarAlugueisPorCarro(int idCarro){
        Optional<Carro> carroOptional = carroRepository.findById(idCarro);
        if (carroOptional.isEmpty()) {
            return new RequestResposta("Carro não encontrado", HttpStatus.NOT_FOUND);
        }
        Carro carro = carroOptional.get();
        return new RequestResposta(aluguelRepository.findAluguelByCarro(carro.getId()), HttpStatus.OK);
    }

    public RequestResposta deletarAluguel(int idAluguel) {
        Optional<Aluguel> aluguelOptional = aluguelRepository.findById(idAluguel);
        if (aluguelOptional.isEmpty())
            return new RequestResposta("Aluguel não encontrado", HttpStatus.NOT_FOUND);
        aluguelRepository.delete(aluguelOptional.get());
        return new RequestResposta("Aluguel deletado com sucesso", HttpStatus.OK);
    }


}
