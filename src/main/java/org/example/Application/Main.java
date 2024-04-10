package org.example.Application;

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

        System.out.println("Bem vindo ao jogo de tabuleiro!");
        Tabuleiro tabuleiro = new Tabuleiro();

        System.out.println("Inserir quantidade de oponentes(IA): ");
        int qtdBots = scanner.nextInt();
















    }

}
