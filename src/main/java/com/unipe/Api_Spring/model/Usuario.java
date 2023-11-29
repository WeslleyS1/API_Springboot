package com.unipe.Api_Spring.model;

import com.unipe.Api_Spring.model.Enums.UserCargo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Table(name = "usuarios")
public class Usuario {
    // Model class para usuario
    // Atributos: id, nome, email, senha

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String senha;
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String token;
    private UserCargo cargo;
}
