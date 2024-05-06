package org.example.Entities;

public class Peca {

    private int idPeca;

    private Jogador jogador; // Referência ao objeto Jogador ao qual esta peça pertence
    private String idJogador;
    private int contador = 1;

    public Peca(Jogador jogador, int idPeca) {
        this.jogador = jogador;
        this.idPeca = idPeca;
    }


    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public int getIdPeca() {
        return idPeca;
    }

    public void setIdPeca(int idPeca) {
        this.idPeca = idPeca;
    }

//    public String getIdJogador() {
//        return idJogador;
//    }

    public void contarCasas(int valorDado) {
        contador += valorDado;
    }

    @Override
    public String toString() {
        return jogador.getIdJogador() + idPeca;
    }

    public int getContador() {
        return contador;
    }


}
