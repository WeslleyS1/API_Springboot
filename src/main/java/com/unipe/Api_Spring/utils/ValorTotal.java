package com.unipe.Api_Spring.utils;

import com.unipe.Api_Spring.model.Aluguel;
import com.unipe.Api_Spring.model.Carro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data @NoArgsConstructor @AllArgsConstructor
public class ValorTotal {

    @Autowired
    private Aluguel aluguel;
    @Autowired
    private StringToDate stringToDate;
    @Autowired
    private Carro carro;

    private double valorTotal;

    public double calcularValorTotal() {
        double valorTotal = 0;
        double valorDiaria = carro.getPrecoaluguel();
        int dias = stringToDate.getDataFimFormatada().getDayOfMonth() - stringToDate.getDataInicioFormatada().getDayOfMonth();
        valorTotal = valorDiaria * dias;
        return valorTotal;
    }
}
