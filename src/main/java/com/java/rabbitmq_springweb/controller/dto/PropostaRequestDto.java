package com.java.rabbitmq_springweb.controller.dto;

public class PropostaRequestDto {

    private String nome;

    private String sobrenome;

    private String telefone;

    private String cpf;

    private Double renda;

    private String valorSolicitado;

    private Integer prazoPagamento;

    public PropostaRequestDto() {

    }

    public PropostaRequestDto(String nome,
                              String sobrenome,
                              String telefone,
                              String cpf,
                              Double renda,
                              String valorSolicitado,
                              Integer prazoPagamento) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
        this.cpf = cpf;
        this.renda = renda;
        this.valorSolicitado = valorSolicitado;
        this.prazoPagamento = prazoPagamento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Double getRenda() {
        return renda;
    }

    public void setRenda(Double renda) {
        this.renda = renda;
    }

    public String getValorSolicitado() {
        return valorSolicitado;
    }

    public void setValorSolicitado(String valorSolicitado) {
        this.valorSolicitado = valorSolicitado;
    }

    public Integer getPrazoPagamento() {
        return prazoPagamento;
    }

    public void setPrazoPagamento(Integer prazoPagamento) {
        this.prazoPagamento = prazoPagamento;
    }
}
