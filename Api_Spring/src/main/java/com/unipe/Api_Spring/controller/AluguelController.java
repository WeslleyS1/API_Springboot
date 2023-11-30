package com.unipe.Api_Spring.controller;

import com.unipe.Api_Spring.service.AluguelService;
import com.unipe.Api_Spring.utils.RequestResposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/aluguel")
public class AluguelController {

    @Autowired
    private AluguelService aluguelService;


    @PostMapping("/alugar/{idCarro}")
    public ResponseEntity<Object> alugarCarro(@PathVariable int idCarro, @RequestParam long idUsuario, @RequestParam String dataInicio, @RequestParam String dataFim) {
        return RequestResposta.retornar(aluguelService.alugarCarro(idCarro, idUsuario, dataInicio, dataFim));
    }

    @GetMapping("/ver/{idUsuario}")
    public ResponseEntity<Object> verAluguel(@PathVariable long idUsuario) {
        return RequestResposta.retornar(aluguelService.listarAlugueisPorUsuario(idUsuario));
    }

    @GetMapping("/ver-carro/{idCarro}")
    public ResponseEntity<Object> verAluguel(@PathVariable int idCarro) {
        return RequestResposta.retornar(aluguelService.listarAlugueisPorCarro(idCarro));
    }

    @DeleteMapping("/deletar/{idAluguel}")
    public ResponseEntity<Object> deletarAluguel(@PathVariable int idAluguel) {
        return RequestResposta.retornar(aluguelService.deletarAluguel(idAluguel));
    }

}
