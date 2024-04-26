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

        tabuleiro.jogar(jogadorA);


        tabuleiro.jogar(jogadorB);

        tabuleiro.jogar(jogadorA);

        tabuleiro.jogar(jogadorA);
        tabuleiro.jogar(jogadorA);


        tabuleiro.printTabuleiroFormatado();









    }

}
