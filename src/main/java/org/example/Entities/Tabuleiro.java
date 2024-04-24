package org.example.Entities;

import org.example.ConsoleUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;


public class Tabuleiro {
    private Jogador jogador;
    private HashMap<Integer, Stack<Peca>> casas;
    private Peca peca;
    private Dado dado;

    public Tabuleiro() {
        casas = new HashMap<>();
        // Inicialize o tabuleiro com 52 casas vazias
        for (int i = 1; i <= 52; i++) {
            casas.put(i, new Stack<>());
        }
    }

    public void jogar(Jogador jogador) {
        int valorDado = jogarDado();
        if (valorDado == 6) {
            if (jogador.temPecasDisponiveis()) {
                System.out.println("VOCE TIROU 6!");
                System.out.println("Deseja tirar uma peca da casinha? (s/n)");
                String resposta = ConsoleUtil.getResposta();
                if (resposta.equals("s")) { // se o jogador quiser mover uma peça para o tabuleiro
                    Peca peca = tirarPecaDaCasinha(jogador);
                    jogador.moverPecaParaListaTabuleiro(peca);
                } else {
                    System.out.println("Escolha uma peça para mover: ");
                    jogador.printPecasTabuleiro();
                    int idPeca = ConsoleUtil.getInt();
                    Peca peca = jogador.getPecasTabuleiro().get(idPeca-1);
                    moverPeca( retornarPosicaoDaPeca(peca), retornarPosicaoDaPeca(peca) + valorDado, jogador, valorDado);

                }
            }
            System.out.println("Escolha uma peça para mover: ");
            jogador.printPecasTabuleiro();
            int idPeca = ConsoleUtil.getInt();
            Peca peca = jogador.getPecasTabuleiro().get(idPeca-1);
            moverPeca( retornarPosicaoDaPeca(peca), retornarPosicaoDaPeca(peca) + valorDado, jogador, valorDado);
        } else {
            System.out.println("Escolha uma peça para mover: ");
            jogador.printPecasTabuleiro();
            int idPeca = ConsoleUtil.getInt();
            Peca peca = jogador.getPecasTabuleiro().get(idPeca-1);
            moverPeca( retornarPosicaoDaPeca(peca), retornarPosicaoDaPeca(peca) + valorDado, jogador, valorDado);
        }
        printTabuleiroFormatado();
    }


    public int retornarPosicaoDaPeca(Peca peca) {
        for (int i = 1; i <= 52; i++) {
            Stack<Peca> casa = casas.get(i);
            if (casa != null && !casa.isEmpty()) {
                Peca p = casa.peek();
                if (p.getIdJogador().equals(peca.getIdJogador()) && p.getIdPeca() == peca.getIdPeca()) {
                    return i;
                }
            }
        }
        return -1;
    }


    public Peca tirarPecaDaCasinha(Jogador jogador) {
        System.out.println("Escolha uma peça para tirar da casa: ");
        jogador.printPecasDispoviveis();
        int idPeca = ConsoleUtil.getInt();
        Peca peca = jogador.getPecas().get(idPeca);
        jogador.moverPecaParaListaTabuleiro(peca);
        return peca;
    }

    public int jogarDado() {
        dado = new Dado();
        int valorDado = dado.rolarDado();
        System.out.println("Valor do dado: " + valorDado);
        return valorDado;
    }

    public void adicionarPeca(int casaId, Peca peca, Jogador jogador) {
        Stack<Peca> casa = casas.get(casaId);
        jogador.moverPecaParaListaTabuleiro(jogador.getPecas().get(0));
        if (casa != null) {
            casa.push(peca);
        } else {
            System.out.println("Casa inválida: " + casaId);
        }
    }

    public Peca removerPeca(int casaId, Peca peca, Jogador jogador) {
        Stack<Peca> casa = casas.get(casaId);
//        jogador.moverPecaParaLista(jogador.getPecasTabuleiro().get(0)); // aqui ele ta pegando a 1a que esta na casa
        if (casa != null && !casa.isEmpty()) {
            return casa.pop(); // aqui esta pegando a ultima que esta na casa
        } else {
            System.out.println("Nenhuma peça encontrada na casa " + casaId);
            return null;
        }
    }

    public void moverPeca(int casaOrigem, int casaDestino, Jogador jogador, int valorDado) {
        Peca peca = removerPeca(casaOrigem, jogador.getPecasTabuleiro().get(0), jogador);
        if (peca != null) {
            peca.contarCasas(valorDado);
            adicionarPeca(casaDestino, peca, jogador);
        }
    }

    public void matarPeca (int casaDestino, Jogador jogador) {
        Stack<Peca> casa = casas.get(casaDestino);
        if (casa != null && !casa.isEmpty()) {
            Peca peca = casa.pop();
            jogador.moverPecaParaLista(peca);
        }
    }

    public void jogadorDaVez(Jogador jogador) {
        this.jogador = jogador;
    }

    public Boolean verificarAdversarioCasaDestino(int casaDestino, Jogador jogador) {
        // Verifica se a casa destino contém peças do adversário
        Stack<Peca> casa = casas.get(casaDestino);
        if (casa != null && !casa.isEmpty()) {
            Peca peca = casa.peek();
            if (!peca.getIdJogador().equals(jogador.getIdJogador())) {
//                System.out.println("Adversário encontrado na casa " + casaDestino);
                return true;
            }
        } else {
//            System.out.println("Nenhuma peça encontrada na casa " + casaDestino);
        }
        return false;

    }

    public void printTabuleiro() {
        for (int i = 1; i <= 52; i++) {
            Stack<Peca> casa = casas.get(i); // Pega a pilha de peças da casa i
            if (casa != null) {
                StringBuilder sb = new StringBuilder("[");
                for (Peca peca : casa) {
                    sb.append(peca.getIdJogador());
                }
                sb.append("]");
                System.out.print(sb);
            } else {
                System.out.print("[ ]");
            }
            System.out.print(" "); // Espaço entre as casas
        }
    }

    public void printTabuleiroFormatado() {
        System.out.printf("                    %s %s %s", casas.get(11), casas.get(12), casas.get(13));
        System.out.println("                   ");
        System.out.printf("                    %s    %s", casas.get(10), casas.get(14), "");
        System.out.println("                   ");
        System.out.printf("                    %s    %s", casas.get(9), casas.get(15), "");
        System.out.println("                   ");
        System.out.printf("                    %s    %s", casas.get(8), casas.get(16), "");
        System.out.println("                   ");
        System.out.printf("                    %s    %s", casas.get(7), casas.get(17), "");
        System.out.println("                   ");
        System.out.printf("                    %s    %s", casas.get(6), casas.get(18), "");
        System.out.println("                   ");
        System.out.printf("   %s %s %s %s %s %s         %s %s %s %s %s %s", casas.get(52), casas.get(1), casas.get(2), casas.get(3), casas.get(4), casas.get(5), casas.get(19), casas.get(20), casas.get(21), casas.get(22), casas.get(23), casas.get(24));
        System.out.println("                   ");
        System.out.printf("   %s                                       %s", casas.get(51), casas.get(25));
        System.out.println("                   ");
        System.out.printf("   %s %s %s %s %s %s         %s %s %s %s %s %s", casas.get(50), casas.get(49), casas.get(48), casas.get(47), casas.get(46), casas.get(45), casas.get(31), casas.get(30), casas.get(29), casas.get(28), casas.get(27), casas.get(26));
        System.out.println("                   ");
        System.out.printf("                    %s    %s", casas.get(44), casas.get(32), "");
        System.out.println("                   ");
        System.out.printf("                    %s    %s", casas.get(43), casas.get(33), "");
        System.out.println("                   ");
        System.out.printf("                    %s    %s", casas.get(42), casas.get(34), "");
        System.out.println("                   ");
        System.out.printf("                    %s    %s", casas.get(41), casas.get(35), "");
        System.out.println("                   ");
        System.out.printf("                    %s    %s", casas.get(40), casas.get(36), "");
        System.out.println("                   ");
        System.out.printf("                    %s %s %s", casas.get(39), casas.get(38), casas.get(37));
        System.out.println("                   ");


//        System.out.println("                       11  12  13                   ");
//        System.out.println("                       10      14                   ");
//        System.out.println("                       09      15                   ");
//        System.out.println("                       08      16                   ");
//        System.out.println("                       07      17                   ");
//        System.out.println("                       06      18                   ");
//        System.out.println("      52 01 02 03 04 05           19 20 21 22 23 24 ");
//        System.out.println("      51                                         25 ");
//        System.out.println("      50 49 48 47 46 45           31 30 29 28 27 26 ");
//        System.out.printf("                        44      32                   ");
//        System.out.printf("                        43      33                   ");
//        System.out.printf("                        42      34                   ");
//        System.out.printf("                        41      35                   ");
//        System.out.printf("                        40      36                   ");
//        System.out.printf("                        39  38  37                   ");


    }


}
