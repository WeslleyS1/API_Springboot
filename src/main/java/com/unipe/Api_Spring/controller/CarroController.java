package com.unipe.Api_Spring.controller;

import com.unipe.Api_Spring.dto.form.CarroForm;
import com.unipe.Api_Spring.model.Carro;
import com.unipe.Api_Spring.service.CarroService;
import com.unipe.Api_Spring.utils.RequestResposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carro")
public class CarroController {

    @Autowired
    private CarroService carroService;


    @GetMapping("/listar")
    public ResponseEntity<List<Carro>> listar(){
        return new ResponseEntity<>(carroService.listar(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable int id){
        return RequestResposta.retornar(carroService.buscarPorId(id));
    }

    @GetMapping("/busca-por-modelo/{modelo}")
    public ResponseEntity<Object> buscarPorModelo(@PathVariable String modelo){
        return RequestResposta.retornar(carroService.buscarPorModelo(modelo));
    }

    @GetMapping("/busca-por-marca/{marca}")
    public ResponseEntity<Object> buscarPorMarca(@PathVariable String marca){
        return RequestResposta.retornar(carroService.buscarPorMarca(marca));
    }

    @GetMapping("/busca-por-ano/{ano}")
    public ResponseEntity<Object> buscarPorAno(@PathVariable String ano){
        return RequestResposta.retornar(carroService.buscarPorAno(ano));
    }

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody CarroForm carroForm){
        return RequestResposta.retornar(carroService.salvar(carroForm));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable int id){
        return RequestResposta.retornar(carroService.deletar(id));
    }
}
