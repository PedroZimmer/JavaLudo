package org.example.Entities;

public class Peca {

    private int idPeca;
    private String idJogador;
    private int contador = 1;

    public Peca(String idJogador, int idPeca) {
        this.idJogador = idJogador;
        this.idPeca = idPeca;
    }


    public Peca() {
    }

    public int getIdPeca() {
        return idPeca;
    }

    public void setIdPeca(int idPeca) {
        this.idPeca = idPeca;
    }

    public String getIdJogador() {
        return idJogador;
    }

    public void contarCasas(int valorDado) {
        contador += valorDado;
    }

    @Override
    public String toString() {
        return idJogador + idPeca;
    }


}
