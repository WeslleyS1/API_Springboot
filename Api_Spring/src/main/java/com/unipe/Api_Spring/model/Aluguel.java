package com.unipe.Api_Spring.model;

import com.unipe.Api_Spring.utils.StringToDate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "alugueis")
public class Aluguel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto-incremento
    private int id;

    @OneToOne
    private Usuario locador;

    @Column(nullable = false, length = 50) //tamanho 50
    private float valorTotal;

    @OneToMany(mappedBy = "aluguel")
    private List<CarroAlugado> carrosAlugados;

    public Aluguel(Usuario locador, CarroAlugado carroAlugado ,String dataInicio, String dataFim){
        this.locador = locador;
        this.valorTotal = this.calculaValorTotal(carroAlugado.getCarro().getPrecoaluguel(), new StringToDate(dataInicio, dataFim));
    }

    public Aluguel(Usuario locador){
        this.locador = locador;
        carrosAlugados = new ArrayList<>();
    }

    public CarroAlugado encontraCarro(Carro carro){
        int idCarro = carro.getId();
        for (CarroAlugado carroAlugado : carrosAlugados){
            if (carroAlugado.getCarro().getId() == idCarro)
                return carroAlugado;
        }
        return null;
    }

    private float calculaValorTotal(float valordia, StringToDate stringToDate){
        float valorTotal = 0;
        double valorDiaria = valordia;
        int dias = stringToDate.getDataFimFormatada().getDayOfMonth() - stringToDate.getDataInicioFormatada().getDayOfMonth();
        valorTotal = (float) (valorDiaria * dias);
        return valorTotal;
    }


    public void addCarroAlugado(CarroAlugado carroAlugado) {
        carrosAlugados.add(carroAlugado);
    }

    public boolean removeCarroAlugado(CarroAlugado carroAlugado) {
        if (!carrosAlugados.contains(carroAlugado)) return false;
        carrosAlugados.remove(carroAlugado);
        return true;
    }
}
