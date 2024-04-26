package org.example.Entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Jogador {
    private String idJogador = "A";
    private HashMap<Integer, Peca> pecas;
    private HashMap<Integer, Peca> pecasTabuleiro;

    public Jogador(String idJogador) {
        this.idJogador = idJogador;
        this.pecas = new HashMap<>();
        this.pecasTabuleiro = new HashMap<>();
        // Criar e adicionar quatro peças ao jogador
        for (int i = 1; i <= 4; i++) {
            pecas.put(i, new Peca(this, i));
        }
    }

    public HashMap<Integer, Peca> getPecas() {
        return pecas;
    }

    public HashMap<Integer, Peca> getPecasTabuleiro() {
        return pecasTabuleiro;
    }


    public void printPecasDispoviveis() {
        System.out.println("Peças disponíveis: ");
        for (Peca peca : pecas.values()) {
            System.out.println(getIdJogador() + " - " + peca.getIdPeca());
        }
    }

    public void printPecasTabuleiro() {
        System.out.println("Peças no tabuleiro: ");

        for (Peca peca : pecasTabuleiro.values()) {
            System.out.println(getIdJogador() + " - " + peca.getIdPeca());
        }
    }

    public void moverPecaParaListaTabuleiro(Peca peca) {
//        pecas.remove(peca); // Remove a peça da lista de peças do jogador
//        pecasTabuleiro.add(peca); // Adiciona a peça à lista de peças no tabuleiro
        pecas.remove(peca.getIdPeca());
        pecasTabuleiro.put(peca.getIdPeca(), peca);
    }

    public void moverPecaParaLista(Peca peca) {
//        pecasTabuleiro.remove(peca); // Remove a peça da lista de peças no tabuleiro
//        pecas.add(peca); // Adiciona a peça à lista de peças do jogador
        pecasTabuleiro.remove(peca.getIdPeca());
        pecas.put(peca.getIdPeca(), peca);
    }

    public String getIdJogador() {
        return idJogador;
    }

    public boolean temPecasDisponiveis() {
        return pecas.size() > 0;
    }

    public boolean temPecasTabuleiro() {
        return pecasTabuleiro.size() > 0;
    }

}
