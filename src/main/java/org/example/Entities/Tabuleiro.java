package org.example.Entities;

public class Tabuleiro {

    private final int qtdCasas = 52;
    private int qtdPecas;
    private Jogador jogador;

    public Tabuleiro(int qtdJogadores) {
        this.jogador = new Jogador();
    }

    public Tabuleiro(){

    }

    public int getQtdPecas() {
        return qtdPecas;
    }

    public void setQtdPecas(int qtdPecas) {
        this.qtdPecas = qtdPecas;
    }

    public int getQtdcasas (){
        return qtdCasas;
    }
}
