package com.java.rabbitmq_springweb.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String sobreNome;

    private String cpf;

    private String telefone;

    private Double renda;

    @OneToOne(mappedBy = "usuario")
    private Proposta proposta;

}
