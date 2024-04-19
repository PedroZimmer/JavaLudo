package org.example.Application;

import org.example.ConsoleUtil;
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


        Jogador jogador = new Jogador("A"); // Instancia um novo jogador

        System.out.println("\n");

        tabuleiro.adicionarPeca(1, jogador.getPecas().get(0), jogador); // Adiciona a peça do jogador ao tabuleiro
        tabuleiro.adicionarPeca(1, jogador.getPecas().get(0), jogador); // Adiciona a peça do jogador ao tabuleiro


        tabuleiro.printTabuleiroFormatado();

        int dadoRolado = tabuleiro.jogarDado();

        jogador.printPecasDispoviveis();
        jogador.printPecasTabuleiro();

        tabuleiro.removerPeca(1, jogador.getPecasTabuleiro().get(0), jogador);

        System.out.println("\n");

        jogador.printPecasDispoviveis();
        jogador.printPecasTabuleiro();

        tabuleiro.printTabuleiroFormatado();












    }

}
