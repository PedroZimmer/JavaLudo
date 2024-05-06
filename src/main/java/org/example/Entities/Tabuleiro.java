package org.example.Entities;

import org.example.ConsoleUtil;
import org.example.IA.Bot;

import javax.swing.*;
import java.util.*;


public class Tabuleiro {
    private Jogador jogador;
    private HashMap<Integer, Stack<Peca>> casas;
//    private HashMap<Integer, Stack<Peca>> caminhoFinal;

    private HashMap<Integer, Stack<Peca>> caminhoFinalA;
    private HashMap<Integer, Stack<Peca>> caminhoFinalB;
    private HashMap<Integer, Stack<Peca>> caminhoFinalC;
    private HashMap<Integer, Stack<Peca>> caminhoFinalD;

    private Peca peca;
    private Dado dado;

    public Tabuleiro() {
        casas = new HashMap<>();
        // Inicialize o tabuleiro com 52 casas vazias
        for (int i = 1; i <= 52; i++) {
            casas.put(i, new Stack<>());
        }
//        caminhoFinal = new HashMap<>();
//        // o hashmap do caminho final vai ter 20 casas
//        for (int i = 1; i <= 5; i++) {
//            caminhoFinal.put(i, new Stack<>());
//
        caminhoFinalA = new HashMap<>();
        caminhoFinalB = new HashMap<>();
        caminhoFinalC = new HashMap<>();
        caminhoFinalD = new HashMap<>();

        for (int i = 1; i <= 5; i++) {
            caminhoFinalA.put(i, new Stack<>());
        }
        for (int i = 1; i <= 5; i++) {
            caminhoFinalB.put(i, new Stack<>());
        }
        for (int i = 1; i <= 5; i++) {
            caminhoFinalC.put(i, new Stack<>());
        }
        for (int i = 1; i <= 5; i++) {
            caminhoFinalD.put(i, new Stack<>());
        }

    }

    public HashMap<Integer, Stack<Peca>> getCasas() {
        return casas;
    }


    public void jogar(Jogador jogador) {
        int valorDado = jogarDado();
        if (valorDado == 6) {
            System.out.println("VOCE TIROU 6!");
            if (jogador.temPecasTabuleiro() && !jogador.temPecasDisponiveis()) { // se o jogador tiver peças no tabuleiro e não tiver peças disponíveis
                chamarFuncMoverPeca(jogador, valorDado);
            } else if (jogador.temPecasDisponiveis() && !jogador.temPecasTabuleiro()) { // se o jogador tiver peças disponíveis e não tiver peças no tabuleiro
                Peca peca = tirarPecaDaCasinha(jogador);
                jogador.moverPecaParaListaTabuleiro(peca);
                adicionarNaCasaInicial(peca, jogador);
            }
            else { // se o jogador tiver peças disponíveis e peças no tabuleiro
                System.out.println("Quer mover ou tirar da casinha? (m/t)");
                String resposta = ConsoleUtil.getResposta();
                if (resposta.equals("m")) {
                    chamarFuncMoverPeca(jogador, valorDado);
                } else {
                    Peca peca = tirarPecaDaCasinha(jogador);
                    jogador.moverPecaParaListaTabuleiro(peca);
                    adicionarNaCasaInicial(peca, jogador);
                }
            }
        } else if (jogador.temPecasTabuleiro()) {
            chamarFuncMoverPeca(jogador, valorDado);
        } else {
            System.out.println("Nenhuma peça no tabuleiro para mover.");
        }
        System.out.println();
        printTabuleiroFormatado();
        System.out.print("");
        printCaminhoFinal();
        verificarSeGanhou(jogador);
    }

    public void verificarSePecaChegouNaUltimaCasaDoCaminhoFinal(Jogador jogador, Peca peca) {
        if (peca.getContador() > 56) {
            System.out.println("A peça " + peca.getIdPeca() + " do jogador " + jogador.getIdJogador() + " chegou na última casa do caminho final.");
            //adicionar a peca na lista de pecas ganhas
            jogador.moverPecaParaListaGanhas(peca);
            //remover a peca do tabuleiro
            removerPecaNoCaminhoFinal(jogador, peca);
        }
    }
    public final boolean verificarSeJaEstaNoCaminhoFinal(Jogador jogador, Peca peca) {
        if (peca.getContador() >= 52) {
            System.out.println("A peça " + peca.getIdPeca() + " do jogador " + jogador.getIdJogador() + " já está no caminho final.");
            return true;
        } else {
            return false;
        }
    }
    public boolean verificarSeVaiParaOCaminhaFinal(Jogador jogador, Peca peca, int valorDado) {
        if (jogador.temPecasTabuleiro()) {
            //cada Peca tem um ccontador que vai de 1 a 52
            //se o contador da peca mais o valor do dado der mais de 52 a peca vai para o caminho final
            if (peca.getContador() <= 51) {
                if (peca.getContador() + valorDado > 51) {
                    System.out.println("A peça " + peca.getIdPeca() + " do jogador " + jogador.getIdJogador() + " vai para o caminho final.");
                    return true;
                } else {
                    return false;
                }
            }
        } return false;
    }
    public void mandarParaOCaminhoFinal(Jogador jogador, Peca peca) {
        switch (jogador.getIdJogador()) {
            case "A" -> {
                caminhoFinalA.get(1).push(peca);
                //remover a peca do tabuleiro
                removerPeca(retornarPosicaoDaPeca(jogador, peca.getIdPeca()), peca, jogador);
            }
            case "B" -> {
                caminhoFinalB.get(1).push(peca);
                //remover a peca do tabuleiro
                removerPeca(retornarPosicaoDaPeca(jogador, peca.getIdPeca()), peca, jogador);

            }
            case "C" -> {
                caminhoFinalC.get(1).push(peca);
                //remover a peca do tabuleiro
                removerPeca(retornarPosicaoDaPeca(jogador, peca.getIdPeca()), peca, jogador);

            }
            case "D" -> {
                caminhoFinalD.get(1).push(peca);
                //remover a peca do tabuleiro
                removerPeca(retornarPosicaoDaPeca(jogador, peca.getIdPeca()), peca, jogador);
            }
        }
    }
    public int retornarPosicaoDaPecaNoCaminhoFinal(Jogador jogador, int idPeca) {
        switch (jogador.getIdJogador()) {
            case "A" -> {
                for (int i = 1; i <= 5; i++) {
                    Stack<Peca> casa = caminhoFinalA.get(i);
                    if (casa != null && !casa.isEmpty()) {
                        for (Peca p : casa) {
                            if (p.getJogador() == jogador && p.getIdPeca() == idPeca) {
                                return i;
                            }
                        }
                    }
                }
            }
            case "B" -> {
                for (int i = 1; i <= 5; i++) {
                    Stack<Peca> casa = caminhoFinalB.get(i);
                    if (casa != null && !casa.isEmpty()) {
                        for (Peca p : casa) {
                            if (p.getJogador() == jogador && p.getIdPeca() == idPeca) {
                                return i;
                            }
                        }
                    }
                }
            }
            case "C" -> {
                for (int i = 1; i <= 5; i++) {
                    Stack<Peca> casa = caminhoFinalC.get(i);
                    if (casa != null && !casa.isEmpty()) {
                        for (Peca p : casa) {
                            if (p.getJogador() == jogador && p.getIdPeca() == idPeca) {
                                return i;
                            }
                        }
                    }
                }
            }
            case "D" -> {
                for (int i = 1; i <= 5; i++) {
                    Stack<Peca> casa = caminhoFinalD.get(i);
                    if (casa != null && !casa.isEmpty()) {
                        for (Peca p : casa) {
                            if (p.getJogador() == jogador && p.getIdPeca() == idPeca) {
                                return i;
                            }
                        }
                    }
                }
            }
        }
        return -1;
    }


    public boolean verificarSeGanhou(Jogador jogador) {
        if (jogador.getPecasGanhas().size() == 4) {
            System.out.println("O jogador " + jogador.getIdJogador() + " ganhou o jogo!");
        } else {
            return false;
        }
        return true;
    }



    public int retornarPosicaoDaPeca(Jogador jogador, int idPeca) {
        for (int i = 1; i <= 52; i++) {
            Stack<Peca> casa = casas.get(i);
            if (casa != null && !casa.isEmpty()) {
                for (Peca p : casa) {
                    if (p.getJogador() == jogador && p.getIdPeca() == idPeca) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }


    public void voltarParaComecoDaLista(Jogador jogador, Peca peca, int comecoDaListaMaisValorRestante) {
        //adicionar a peca na casa 1
        adicionarPeca(comecoDaListaMaisValorRestante, peca, jogador);
        //remover a peca da casa 52
        removerPeca(52, peca, jogador);
    }


    public void chamarFuncMoverPeca(Jogador jogadorDaVez, int valorDado) {
        System.out.println("Escolha uma peça para mover: ");
        jogadorDaVez.printPecasTabuleiro();
        int idPeca = ConsoleUtil.getInt();
        Peca peca = jogadorDaVez.getPecasTabuleiro().get(idPeca);
        if (peca != null) { // Verifica se a peça foi encontrada
            int posicaoAtual = retornarPosicaoDaPeca(jogadorDaVez, idPeca);
            if (posicaoAtual != -1) { // Verifica se a peça está no tabuleiro
                if (verificarAdversarioCasaDestino(posicaoAtual + valorDado, jogadorDaVez)) {
                    Jogador jogadorQueVaiPerderUmaPeca = deQuemEhAPeca(posicaoAtual + valorDado);

                    if (jogadorQueVaiPerderUmaPeca != null) {
                        matarPeca(posicaoAtual + valorDado, jogadorQueVaiPerderUmaPeca);
                        moverPeca(posicaoAtual, posicaoAtual + valorDado,peca, jogadorDaVez, valorDado);

                    } else {
                        System.out.println("Jogador não encontrado.");
                    }
                } else {
                    moverPeca(posicaoAtual, posicaoAtual + valorDado, peca, jogadorDaVez, valorDado);
                }
            } else {
                int posicaoAtualNoCaminhoFinal = retornarPosicaoDaPecaNoCaminhoFinal(jogadorDaVez, idPeca); // Verifica se a peça está no caminho final
                // tem q verificar se a peca esta no caminho final caso esteja tem q andar com ela mesmo assim
                if (posicaoAtualNoCaminhoFinal != -1) { //se a peca estiver no caminho final
//                    if (posicaoAtualNoCaminhoFinal + valorDado > 5) { //se a peca chegar na ultima casa do caminho final
//                        System.out.println("A peça " + idPeca + " do jogador " + jogadorDaVez.getIdJogador() + " CHEGOU NO FINAL.");
//                        verificarSePecaChegouNaUltimaCasaDoCaminhoFinal(jogadorDaVez, peca);
//                    } else {
                        moverPecaNoCaminhoFinal(jogadorDaVez, peca, valorDado);
//                    }
                } else {
                    System.out.println("Peça não encontrada.");
                }

            }
        } else {
            System.out.println("Peça não encontrada.");
        }
    }

    public Peca tirarPecaDaCasinha(Jogador jogador) {
        System.out.println("Escolha uma peça para tirar da casa: ");
        jogador.printPecasDispoviveis();
        int idPeca = ConsoleUtil.getInt();
        Peca peca = jogador.getPecas().get(idPeca);
        jogador.moverPecaParaListaTabuleiro(peca);
        return peca;
    }

//    public void colocarPecaNaCasinha(Jogador jogador, Peca x) {
//        // ID do jogador que vai tirar a peca do tabuleiro // ID da peca que vai ser tirada do tabuleiro
//        Peca peca = jogador.getPecasTabuleiro().get(x.getIdPeca());
//        jogador.moverPecaParaLista(peca);
//    }

    public int jogarDado() {
        dado = new Dado();
        int valorDado = dado.rolarDado();
        System.out.println("Valor do dado: " + valorDado);
        return valorDado;
    }

    public void adicionarNaCasaInicial(Peca peca, Jogador jogador) {
        //casa 1 para o jogador a
        //casa 14 para o jogador b
        //casa 27 para o jogador c
        //casa 40 para o jogador d

        switch (jogador.getIdJogador()) {
            case "A" -> {
                adicionarPeca(1, peca, jogador);
                System.out.println("Peca adicionada na casa 1");
            }
            case "B" -> adicionarPeca(14, peca, jogador);
            case "C" -> adicionarPeca(27, peca, jogador);
            case "D" -> adicionarPeca(40, peca, jogador);
        }
    }

    public void adicionarPeca(int casaId, Peca peca, Jogador jogador) {
        Stack<Peca> casa = casas.get(casaId);
//        jogador.moverPecaParaListaTabuleiro(jogador.getPecas().get(0));
        if (casa != null) {
            casa.push(peca);
        } else {
            System.out.println("Casa inválida: " + casaId);
        }
    }

    public Peca removerPeca(int casaId, Peca peca, Jogador jogador) {
        List<Peca> casa = casas.get(casaId);
        if (casa != null && !casa.isEmpty()) {
            if (casa.remove(peca)) {
                return peca;
            } else {
                System.out.println("A peça especificada não foi encontrada na casa " + casaId);
                return null;
            }
        } else {
            System.out.println("Nenhuma peça encontrada na casa " + casaId);
            return null;
        }
    }

    public void moverPecaNoCaminhoFinal(Jogador jogador, Peca peca, int valorDado) {
        switch (jogador.getIdJogador()) {
            case "A" -> {
                moverPeca(retornarPosicaoDaPecaNoCaminhoFinal(jogador, peca.getIdPeca()), retornarPosicaoDaPecaNoCaminhoFinal(jogador, peca.getIdPeca()) + valorDado, peca, jogador, valorDado);
            }
            case "B" -> {
                moverPeca(retornarPosicaoDaPecaNoCaminhoFinal(jogador, peca.getIdPeca()), retornarPosicaoDaPecaNoCaminhoFinal(jogador, peca.getIdPeca()) + valorDado, peca, jogador, valorDado);
            }
            case "C" -> {
                moverPeca(retornarPosicaoDaPecaNoCaminhoFinal(jogador, peca.getIdPeca()), retornarPosicaoDaPecaNoCaminhoFinal(jogador, peca.getIdPeca()) + valorDado, peca, jogador, valorDado);
            }
            case "D" -> {
                moverPeca(retornarPosicaoDaPecaNoCaminhoFinal(jogador, peca.getIdPeca()), retornarPosicaoDaPecaNoCaminhoFinal(jogador, peca.getIdPeca()) + valorDado, peca, jogador, valorDado);
            }
        }
    }

    public void removerPecaNoCaminhoFinal(Jogador jogador, Peca peca) {
        switch (jogador.getIdJogador()) {
            case "A" -> {
                int posicao = retornarPosicaoDaPecaNoCaminhoFinal(jogador, peca.getIdPeca());
                Stack<Peca> casa = caminhoFinalA.get(posicao);
                if (casa != null && !casa.isEmpty()) {
                    casa.pop();
                }
            }
            case "B" -> {
                int posicao = retornarPosicaoDaPecaNoCaminhoFinal(jogador, peca.getIdPeca());
                Stack<Peca> casa = caminhoFinalB.get(posicao);
                if (casa != null && !casa.isEmpty()) {
                    casa.pop();
                }
            }
            case "C" -> {
                int posicao = retornarPosicaoDaPecaNoCaminhoFinal(jogador, peca.getIdPeca());
                Stack<Peca> casa = caminhoFinalC.get(posicao);
                if (casa != null && !casa.isEmpty()) {
                    casa.pop();
                }
            }
            case "D" -> {
                int posicao = retornarPosicaoDaPecaNoCaminhoFinal(jogador, peca.getIdPeca());
                Stack<Peca> casa = caminhoFinalD.get(posicao);
                if (casa != null && !casa.isEmpty()) {
                    casa.pop();
                }
            }
        }
    }

    public void adicionarPecaCaminhoFinal(int casaId, Jogador jogador, Peca peca) {
        switch (jogador.getIdJogador()) {
            case "A" -> {
                Stack<Peca> casa = caminhoFinalA.get(casaId);
                if (casa != null) {
                    casa.push(peca);
                } else {
                    System.out.println("Casa inválida: " + casaId);
                }
            }
            case "B" -> {
                Stack<Peca> casa = caminhoFinalB.get(casaId);
                if (casa != null) {
                    casa.push(peca);
                } else {
                    System.out.println("Casa inválida: " + casaId);
                }
            }
            case "C" -> {
                Stack<Peca> casa = caminhoFinalC.get(casaId);
                if (casa != null) {
                    casa.push(peca);
                } else {
                    System.out.println("Casa inválida: " + casaId);
                }
            }
            case "D" -> {
                Stack<Peca> casa = caminhoFinalD.get(casaId);
                if (casa != null) {
                    casa.push(peca);
                } else {
                    System.out.println("Casa inválida: " + casaId);
                }
            }
        }
    }

    public void moverPeca(int casaOrigem, int casaDestino, Peca peca, Jogador jogador, int valorDado) {

        if (verificarSeJaEstaNoCaminhoFinal(jogador, peca)) {
            removerPecaNoCaminhoFinal(jogador, peca);
            peca.contarCasas(valorDado);
            adicionarPecaCaminhoFinal(casaDestino,jogador, peca);
            verificarSePecaChegouNaUltimaCasaDoCaminhoFinal(jogador, peca);
        } else {
            if (verificarSeVaiParaOCaminhaFinal(jogador, peca, valorDado)) {
                mandarParaOCaminhoFinal(jogador, peca);//essa funcao verifica se a peca vai para o caminho final, mas nocaso se ela ja estiver la ela ainda esta cgamando a funcao
                peca.contarCasas(valorDado);
                printCaminhoFinal();
                verificarSePecaChegouNaUltimaCasaDoCaminhoFinal(jogador, peca);
            } else {
                removerPeca(casaOrigem, peca, jogador);
                peca.contarCasas(valorDado);
                if (casaDestino > 52) {
                    voltarParaComecoDaLista(jogador, peca, casaDestino - 52);
                } else {
                    adicionarPeca(casaDestino, peca, jogador);
                }
            }
        }



    }

    public void matarPeca (int casaDestino, Jogador jogador) {
        Stack<Peca> casa = casas.get(casaDestino);
        if (casa != null && !casa.isEmpty()) {
            Peca peca = casa.pop();
            jogador.moverPecaParaLista(peca);
        }
    }

    public Jogador deQuemEhAPeca(int casaDestino) {
        Stack<Peca> casa = casas.get(casaDestino);
        if (casa != null && !casa.isEmpty()) {
            Peca peca = casa.peek();
            return peca.getJogador();
        }
        return null;
    }

    public void jogadorDaVez(Jogador jogador) {
        this.jogador = jogador;
    }

    public boolean verificarAdversarioCasaDestino(int casaDestino, Jogador jogador) {
        // Verifica se a casa destino contém peças do adversário
        Stack<Peca> casa = casas.get(casaDestino);
        if (casa != null && !casa.isEmpty()) {
            for (Peca peca : casa) {
                if (!peca.getJogador().equals(jogador)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void printCaminhoFinal() {
        System.out.printf("Caminho final A: %s %s %s %s %s", caminhoFinalA.get(1), caminhoFinalA.get(2), caminhoFinalA.get(3), caminhoFinalA.get(4), caminhoFinalA.get(5));
        System.out.println();
        System.out.printf("Caminho final B: %s %s %s %s %s", caminhoFinalB.get(1), caminhoFinalB.get(2), caminhoFinalB.get(3), caminhoFinalB.get(4), caminhoFinalB.get(5));
        System.out.println();
        System.out.printf("Caminho final C: %s %s %s %s %s", caminhoFinalC.get(1), caminhoFinalC.get(2), caminhoFinalC.get(3), caminhoFinalC.get(4), caminhoFinalC.get(5));
        System.out.println();
        System.out.printf("Caminho final D: %s %s %s %s %s", caminhoFinalD.get(1), caminhoFinalD.get(2), caminhoFinalD.get(3), caminhoFinalD.get(4), caminhoFinalD.get(5));
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

    // Função para o bot escolher automaticamente qual peça tirar da casinha
    public Peca escolherPecaParaMoverBOT(Jogador bot, int valorDado) {
        // Verificar se há peças disponíveis no tabuleiro
        if (!bot.getPecasTabuleiro().isEmpty()) {
            // Se houver peças no tabuleiro, escolher uma para mover
            Peca pecaMover = null;
            int contadorMax = Integer.MIN_VALUE;
            for (Peca peca : bot.getPecasTabuleiro().values()) {
                if (peca.getContador() + valorDado <= 52 && peca.getContador() > contadorMax) {
                    pecaMover = peca;
                    contadorMax = peca.getContador();
                }
            }
            return pecaMover;
        } else {
            // Se não houver peças no tabuleiro, não é possível mover
            return null;
        }
    }


    public Peca tirarPecaDaCasinhaBOT(Jogador bot) {
        // Obter todas as peças disponíveis do Bot
        ArrayList<Peca> pecasDisponiveis = bot.getPecasDisponiveis();

        // Verificar se há peças disponíveis
        if (!pecasDisponiveis.isEmpty()) {
            // Gerar um índice aleatório dentro do intervalo das peças disponíveis
            Random random = new Random();
            int indice = random.nextInt(pecasDisponiveis.size());

            // Retornar a peça correspondente ao índice gerado
            Peca pecaEscolhida = pecasDisponiveis.get(indice);

            // Mover a peça escolhida para a lista de peças no tabuleiro
            bot.moverPecaParaListaTabuleiro(pecaEscolhida);

            // Retornar a peça escolhida
            return pecaEscolhida;
        } else {
            // Se não houver peças disponíveis, retornar null
            return null;
        }
    }



    public void jogarBOT(Jogador bot) {
        int valorDado = jogarDado();
        System.out.println("O BOT tirou " + valorDado + "!");

        // Verificar se o dado tirado é 6
        if (valorDado == 6) {
            System.out.println("O BOT tirou 6!");

            // Verificar se o bot tem peças no tabuleiro e não tem peças disponíveis
            if (bot.temPecasTabuleiro() && !bot.temPecasDisponiveis()) {
                // Realizar a jogada de acordo com o resultado da função matarOuAndar
                matarOuAndar(bot, valorDado);
            }
            // Verificar se o bot tem peças disponíveis e não tem peças no tabuleiro
            else if (bot.temPecasDisponiveis() && !bot.temPecasTabuleiro()) {
                // Retirar uma peça da casinha
                Peca peca = tirarPecaDaCasinhaBOT(bot);
                // Mover a peça para a lista de peças do tabuleiro
                bot.moverPecaParaListaTabuleiro(peca);
                // Adicionar a peça na casa inicial
                adicionarNaCasaInicial(peca, bot);
            } else {
                // Se o bot tiver peças disponíveis e peças no tabuleiro
                System.out.println("O BOT tem peças disponíveis e peças no tabuleiro.");
                // Verificar se o bot pode matar uma peça ou se deve mover uma peça
                matarOuAndar(bot, valorDado);
            }
        }
        // Se o dado tirado não for 6
        else {
            // Verificar se o bot tem peças no tabuleiro
            if (bot.temPecasTabuleiro()) {
                // Realizar a jogada de acordo com o resultado da função matarOuAndar
                matarOuAndar(bot, valorDado);
            } else {
                // Se o bot não tiver peças no tabuleiro, informar que não há peças para mover
                System.out.println("Nenhuma peça no tabuleiro para mover.");
            }
        }
        // Imprimir o tabuleiro formatado após a jogada do bot
        printTabuleiroFormatado();
        // Imprimir o caminho final após a jogada do bot
        printCaminhoFinal();
        // Verificar se o bot ganhou após sua jogada
        verificarSeGanhou(bot);
    }


//    public void matarOuAndar(Jogador bot, int valorDado) {
//        // Variável para armazenar a peça que será movida
//        Peca pecaMovida = null;
//
//        // Variável para armazenar a maior contagem de casas
//        int maiorContador = Integer.MIN_VALUE;
//
//        // Iterar sobre todas as peças do bot no tabuleiro
//        for (Peca peca : bot.getPecasTabuleiro().values()) {
//            // Verificar se a peça pode ser movida com o valor do dado
//            int posicaoAtual = retornarPosicaoDaPeca(bot, peca.getIdPeca());
//            int posicaoNova = posicaoAtual + valorDado;
//
//            // Verificar se a nova posição está dentro do tabuleiro
//            if (posicaoNova <= 52) {
//                // Verificar se há peças adversárias na casa de destino
//                if (verificarAdversarioCasaDestino(posicaoNova, bot)) {
//                    // Matar a peça adversária
//                    Jogador jogadorAdversario = deQuemEhAPeca(posicaoNova);
//                    matarPeca(posicaoNova, jogadorAdversario);
//                    // Mover a peça do bot para a posição nova
//                    moverPeca(posicaoAtual, posicaoNova, peca, bot, valorDado);
//                    // Atualizar a variável pecaMovida
//                    pecaMovida = peca;
//                    // Interromper o loop, pois uma peça foi movida
//                    break;
//                }
//            } else {
//                // Se a peça atual pode ser movida para fora do tabuleiro, movê-la
//                moverPeca(posicaoAtual, posicaoNova, peca, bot, valorDado);
//                // Atualizar a variável pecaMovida
//                pecaMovida = peca;
//                // Interromper o loop, pois uma peça foi movida
//                break;
//            }
//
//            // Verificar se a contagem da peça atual é a maior até agora
//            if (peca.getContador() > maiorContador) {
//                // Atualizar a maior contagem
//                maiorContador = peca.getContador();
//                // Atualizar a variável pecaMovida
//                pecaMovida = peca;
//            }
//        }
//
//        // Se nenhuma peça foi movida por causa de uma captura ou porque a maior contagem é maior que a quantidade de casas do tabuleiro, mover a peça com a maior contagem
//        if (pecaMovida != null) {
//            // Obter a posição atual da peça movida
//            int posicaoAtual = retornarPosicaoDaPeca(bot, pecaMovida.getIdPeca());
//            // Calcular a nova posição com base na contagem anterior e no valor do dado
//            int posicaoNova = posicaoAtual + valorDado;
//            // Mover a peça para a nova posição
//            moverPeca(posicaoAtual, posicaoNova, pecaMovida, bot, valorDado);
//        }
//    }

//    public void matarOuAndar(Jogador bot, int valorDado) {
//        // Variável para armazenar a peça que será movida
//        Peca pecaMovida = null;
//
//        // Variável para armazenar a maior contagem de casas
//        int maiorContador = Integer.MIN_VALUE;
//
//        // Iterar sobre todas as peças do bot no tabuleiro
//        for (Peca peca : bot.getPecasTabuleiro().values()) {
//            // Verificar se a peça pode ser movida com o valor do dado
//            int posicaoAtual = retornarPosicaoDaPeca(bot, peca.getIdPeca());
//            int posicaoNova = posicaoAtual + valorDado;
//
//            // Verificar se a nova posição está dentro do tabuleiro
//            if (posicaoNova <= 52) {
//                // Verificar se há peças adversárias na casa de destino
//                if (verificarAdversarioCasaDestino(posicaoNova, bot)) {
//                    // Matar a peça adversária
//                    Jogador jogadorAdversario = deQuemEhAPeca(posicaoNova);
//                    matarPeca(posicaoNova, jogadorAdversario);
//                    // Mover a peça do bot para a posição nova
//                    moverPeca(posicaoAtual, posicaoNova, peca, bot, valorDado);
//                    // Atualizar a variável pecaMovida
//                    pecaMovida = peca;
//                    // Interromper o loop, pois uma peça foi movida
//                    break;
//                }
//            } else {
//                // Se a peça atual pode ser movida para fora do tabuleiro, movê-la
//                moverPeca(posicaoAtual, posicaoNova, peca, bot, valorDado);
//                // Atualizar a variável pecaMovida
//                pecaMovida = peca;
//                // Interromper o loop, pois uma peça foi movida
//                break;
//            }
//
//            // Verificar se a contagem da peça atual é a maior até agora
//            if (peca.getContador() > maiorContador) {
//                // Atualizar a maior contagem
//                maiorContador = peca.getContador();
//                // Atualizar a variável pecaMovida
//                pecaMovida = peca;
//            }
//        }
//
//        // Se nenhuma peça foi movida por causa de uma captura ou porque a maior contagem é maior que a quantidade de casas do tabuleiro, mover a peça com a maior contagem
//        if (pecaMovida == null) {
//            // Escolher uma peça para mover utilizando a função escolherPecaParaMoverBOT
//            pecaMovida = escolherPecaParaMoverBOT(bot, valorDado);
//            if (pecaMovida != null) {
//                // Obter a posição atual da peça movida
//                int posicaoAtual = retornarPosicaoDaPeca(bot, pecaMovida.getIdPeca());
//                // Calcular a nova posição com base na contagem anterior e no valor do dado
//                int posicaoNova = posicaoAtual + valorDado;
//                // Mover a peça para a nova posição
//                moverPeca(posicaoAtual, posicaoNova, pecaMovida, bot, valorDado);
//            }
//        }
//    }

    public void matarOuAndar(Jogador bot, int valorDado) {
        // Variável para armazenar a peça que será movida
        Peca pecaMovida = null;

        // Variável para armazenar a maior contagem de casas
        int maiorContador = Integer.MIN_VALUE;
        boolean movimentoEfetuado = false;

        // Iterar sobre todas as peças do bot no tabuleiro
        for (Peca peca : bot.getPecasTabuleiro().values()) {
            // Verificar se a peça pode ser movida com o valor do dado
            int posicaoAtual = retornarPosicaoDaPeca(bot, peca.getIdPeca());
            int posicaoNova = posicaoAtual + valorDado;

            // Verificar se a nova posição está dentro do tabuleiro
            if (posicaoNova <= 52) {
                // Verificar se há peças adversárias na casa de destino
                if (verificarAdversarioCasaDestino(posicaoNova, bot)) {
                    // Matar a peça adversária
                    Jogador jogadorAdversario = deQuemEhAPeca(posicaoNova);
                    matarPeca(posicaoNova, jogadorAdversario);
                    // Mover a peça do bot para a posição nova
                    moverPeca(posicaoAtual, posicaoNova, peca, bot, valorDado);
                    // Atualizar a variável pecaMovida
                    pecaMovida = peca;
                    // Indicar que um movimento foi efetuado
                    movimentoEfetuado = true;
                    // Interromper o loop, pois uma peça foi movida
                    break;
                }
            } else {
                // Se a peça atual pode ser movida para fora do tabuleiro, movê-la
                moverPeca(posicaoAtual, posicaoNova, peca, bot, valorDado);
                // Atualizar a variável pecaMovida
                pecaMovida = peca;
                // Indicar que um movimento foi efetuado
                movimentoEfetuado = true;
                // Interromper o loop, pois uma peça foi movida
                break;
            }

            // Verificar se a contagem da peça atual é a maior até agora
            if (peca.getContador() > maiorContador) {
                // Atualizar a maior contagem
                maiorContador = peca.getContador();
                // Atualizar a variável pecaMovida
                pecaMovida = peca;
            }
        }

        // Se nenhum movimento foi efetuado, tentar mover uma peça com a maior contagem
        if (!movimentoEfetuado && pecaMovida != null) {
            // Obter a posição atual da peça movida
            int posicaoAtual = retornarPosicaoDaPeca(bot, pecaMovida.getIdPeca());
            // Calcular a nova posição com base na contagem anterior e no valor do dado
            int posicaoNova = posicaoAtual + valorDado;
            // Mover a peça para a nova posição
            moverPeca(posicaoAtual, posicaoNova, pecaMovida, bot, valorDado);
        }
    }





}
