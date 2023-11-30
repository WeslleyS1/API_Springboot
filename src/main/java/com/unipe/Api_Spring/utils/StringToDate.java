package com.unipe.Api_Spring.utils;

import com.unipe.Api_Spring.model.Aluguel;
import com.unipe.Api_Spring.model.CarroAlugado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Data @AllArgsConstructor
@NoArgsConstructor
public class StringToDate {

    // transformando o datainicio e datafim que está em String do Aluguel em Data
    @Autowired
    private CarroAlugado carroAlugado;

    String dataInicio = carroAlugado.getDataInicio();
    String dataFim = carroAlugado.getDataFim();

    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy"); //

    LocalDate dataInicioFormatada = LocalDate.parse(dataInicio, formato);
    LocalDate dataFimFormatada = LocalDate.parse(dataFim, formato);


    public StringToDate(String dataInicio, String dataFim) {
    }
}
