package org.example.IA;

import org.example.Entities.Jogador;
import org.example.Entities.Peca;
import org.example.Entities.Tabuleiro;

import javax.swing.text.TabableView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Bot extends Jogador {

    private int qtdBot;
    private int idBot;
    private HashMap<Integer, Peca> pecas;
    private HashMap<Integer, Peca> pecasTabuleiro;
    private ArrayList<Peca> pecasGanhas;
    private Tabuleiro tabuleiro;

    public Bot(String idJogador) {
        super(idJogador);
    }

    public void setQtdBot(int qtdBot) {
        this.qtdBot = qtdBot;
    }

//    public void matarOuAndar(Tabuleiro tabuleiro, Bot bot, Peca peca, int valorDado) {
//        // o bot vai jogar o dado e conforme o número q cair vai verificar se:
//        // 1. ele pode matar uma peça de outro jogador
//        // se ele n conseguir matar ninguem ele anda com a peca com o contador mais alto
//        HashMap<Integer, Stack<Peca>> casas = tabuleiro.getCasas();
//        //vai ter q fazer um for pra cada peca q tem no tabuleiro
//        for (Peca pecaTabuleiro : pecasTabuleiro.values()) {
//            //verificar se a peca do bot esta na mesma casa q a peca do jogador
//            if (pecaTabuleiro.getContador() == peca.getContador()) {
//                int posicaoAtual = tabuleiro.retornarPosicaoDaPeca(bot, peca);
//            }
//                //matar a peca do jogador
//                //remover a peca do jogador da lista de pecas do jogador
//                //adicionar a peca do jogador na lista de pecas ganhas do bot
//                //mover a peca do jogador para a casa inicial
//                //mover a peca do jogador para a lista de pecas do bot
//                //mover a peca do jogador para a lista de pecas no tabuleiro do bot
//                //mover a peca do jogador para a casa inicial
//
//    }



    public void moverPecaParaListaTabuleiro(Peca peca) {
//        pecas.remove(peca); // Remove a peça da lista de peças do jogador
//        pecasTabuleiro.add(peca); // Adiciona a peça à lista de peças no tabuleiro
        pecas.remove(peca.getIdPeca());
        pecasTabuleiro.put(peca.getIdPeca(), peca);
    }





}
