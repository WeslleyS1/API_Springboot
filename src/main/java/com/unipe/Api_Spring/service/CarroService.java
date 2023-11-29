package com.unipe.Api_Spring.service;

import com.unipe.Api_Spring.dto.form.CarroForm;
import com.unipe.Api_Spring.model.Carro;
import com.unipe.Api_Spring.repository.CarroRepository;
import com.unipe.Api_Spring.utils.RequestResposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    public List<Carro> listar(){
        return carroRepository.findAll();
    }

    public RequestResposta buscarPorId(int id){
        Optional<Carro> carroOptional = carroRepository.findById(id);
        return carroOptional.isEmpty()
                ? trataCarroInexistente()
                : new RequestResposta(carroOptional.get(), HttpStatus.OK);
    }

    public RequestResposta buscarPorModelo(String modelo){
        Optional<Carro> carroOptional = carroRepository.findCarroByModelo(modelo);
        return carroOptional.isEmpty()
                ? trataCarroInexistente()
                : new RequestResposta(carroOptional.get(), HttpStatus.OK);
    }

    public RequestResposta buscarPorMarca(String marca){
        Optional<Carro> carroOptional = carroRepository.findCarroByMarca(marca);
        return carroOptional.isEmpty()
                ? trataCarroInexistente()
                : new RequestResposta(carroOptional.get(), HttpStatus.OK);
    }

    public RequestResposta buscarPorAno(String ano){
        Optional<Carro> carroOptional = carroRepository.findCarroByAno(ano);
        return carroOptional.isEmpty()
                ? trataCarroInexistente()
                : new RequestResposta(carroOptional.get(), HttpStatus.OK);
    }

    public RequestResposta salvar(CarroForm carroForm){
        Optional<Carro> carroOptional = carroRepository.findCarroByModelo(carroForm.getModelo());
        return carroOptional.isEmpty()
                ? new RequestResposta(carroRepository.save(new Carro(carroForm)), HttpStatus.CREATED)
                : new RequestResposta("Carro já cadastrado", HttpStatus.CONFLICT);
    }

    public RequestResposta deletar(int id){
        Optional<Carro> carroOptional = carroRepository.findById(id);
        if (carroOptional.isEmpty()) {
            return trataCarroInexistente();
        }
        carroRepository.deleteById(id);
        return new RequestResposta("Carro deletado com sucesso", HttpStatus.OK);
    }

    private RequestResposta trataCarroInexistente() {
        return new RequestResposta("Carro não encontrado", HttpStatus.NOT_FOUND);
    }
}
