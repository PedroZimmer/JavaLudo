package org.example.Entities;

import java.util.ArrayList;
import java.util.List;

public class Jogador {
    private String idJogador = "A";
    private List<Peca> pecas;
    private List<Peca> pecasTabuleiro;

    public Jogador(String idJogador) {
        this.idJogador = idJogador;
        this.pecas = new ArrayList<>();
        this.pecasTabuleiro = new ArrayList<>();
        // Criar e adicionar quatro peças ao jogador
        for (int i = 1; i <= 4; i++) {
            pecas.add(new Peca(idJogador, i));
        }
    }

    public List<Peca> getPecas() {
        return pecas;
    }

    public List<Peca> getPecasTabuleiro() {
        return pecasTabuleiro;
    }

    public void printPecas() {
        for (Peca peca : pecas) {
            System.out.println(peca.getIdJogador() + " - " + peca.getIdPeca());
        }
    }

    public void printPecasDispoviveis() {
        System.out.println("Peças disponíveis: ");
        for (Peca peca : pecas) {
            System.out.println(peca.getIdJogador() + " - " + peca.getIdPeca());
        }
    }

    public void printPecasTabuleiro() {
        System.out.println("Peças no tabuleiro: ");
        for (Peca peca : pecasTabuleiro) {
            System.out.println(peca.getIdJogador() + " - " + peca.getIdPeca());
        }
    }

    public void moverPecaParaTabuleiro(Peca peca) {
        pecas.remove(peca); // Remove a peça da lista de peças do jogador
        pecasTabuleiro.add(peca); // Adiciona a peça à lista de peças no tabuleiro
    }

    public void moverPecaParaLista(Peca peca) {
        pecasTabuleiro.remove(peca); // Remove a peça da lista de peças no tabuleiro
        pecas.add(peca); // Adiciona a peça à lista de peças do jogador
    }


}
