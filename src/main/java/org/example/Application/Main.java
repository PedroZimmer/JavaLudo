package org.example.Application;

import org.example.ConsoleUtil;
import org.example.Entities.Jogador;
import org.example.Entities.Peca;
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

        //func para jogar, alternar jogadores, verificar se o jogador ganhou
        //tem q ser um loop ate alguem ganhar
        int ganhou = 0;
        while (ganhou == 0) {
            System.out.println(" ");
            System.out.println("Vez do jogador " + jogadorA.getIdJogador());
            System.out.println("Pressione ENTER para jogar o dado");
            scanner.nextLine();
            tabuleiro.jogar(jogadorA);
            if (tabuleiro.verificarSeGanhou(jogadorA)) {
                System.out.println("O jogador " + jogadorA.getIdJogador() + " venceu!");
                break;
            }
//            System.out.println(" ");
//            System.out.println("Vez do jogador " + jogadorB.getIdJogador());
//            System.out.println("Pressione ENTER para jogar o dado");
//            scanner.nextLine();
//            tabuleiro.jogar(jogadorB);
//            if (tabuleiro.verificarSeGanhou(jogadorB)) {
//                System.out.println("O jogador " + jogadorB.getIdJogador() + " venceu!");
//                break;
//            }
        }







    }

}
