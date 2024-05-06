package org.example.Application;

import org.example.Entities.Jogador;
import org.example.Entities.Tabuleiro;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println(
                "██╗      ██╗   ██╗██████╗  ██████╗ \n" +
                        "██║      ██║   ██║██╔══██╗██╔═══██╗\n" +
                        "██║      ██║   ██║██║  ██║██║   ██║\n" +
                        "██║      ██║   ██║██║  ██║██║   ██║\n" +
                        "███████╗ ╚██████╔╝██████╔╝╚██████╔╝\n" +
                        "╚══════╝  ╚═════╝ ╚═════╝  ╚═════╝ \n"
        );

        Scanner scanner = new Scanner(System.in);
        Tabuleiro tabuleiro = new Tabuleiro(); // Instancia um novo tabuleiro

        System.out.println("Bem vindo ao jogo de tabuleiro!");

        Jogador jogadorA = new Jogador("A"); // Instancia um novo jogador
        Jogador jogadorB = new Jogador("B"); // Instancia um novo jogador

        // Loop principal do jogo
        while (true) {
            // Vez do jogador humano
            System.out.println(" ");
            System.out.println("Vez do jogador " + jogadorA.getIdJogador());
            System.out.println("Pressione ENTER para jogar o dado");
            scanner.nextLine();
            tabuleiro.jogar(jogadorA); // Jogador humano move suas peças
            if (tabuleiro.verificarSeGanhou(jogadorA)) {
                System.out.println("O jogador " + jogadorA.getIdJogador() + " venceu!");
                break;
            }

            // Vez do bot
            System.out.println(" ");
            System.out.println("Vez do jogador " + jogadorB.getIdJogador());
//            System.out.println("Pressione ENTER para a jogada do bot");
//            scanner.nextLine();
            tabuleiro.jogarBOT(jogadorB); // Bot decide a jogada e move suas peças
            if (tabuleiro.verificarSeGanhou(jogadorB)) {
                System.out.println("O jogador " + jogadorB.getIdJogador() + " venceu!");
                break;
            }
        }
    }
}
