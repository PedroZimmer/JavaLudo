package org.example.Entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;


public class Tabuleiro {

    private Jogador jogador;
    private HashMap<Integer, Stack<Peca>> casas;
    private Peca peca;

    public Tabuleiro() {
        casas = new HashMap<>();
        // Inicialize o tabuleiro com 52 casas vazias
        for (int i = 1; i <= 52; i++) {
            casas.put(i, new Stack<>());
        }
    }

    public void adicionarPeca(int casaId, Peca peca) {
        Stack<Peca> casa = casas.get(casaId);
        if (casa != null) {
            casa.push(peca);
        } else {
            System.out.println("Casa inválida: " + casaId);
        }
    }

    public Peca removerPeca(int casaId) {
        Stack<Peca> casa = casas.get(casaId);
        if (casa != null && !casa.isEmpty()) {
            return casa.pop();
        } else {
            System.out.println("Nenhuma peça encontrada na casa " + casaId);
            return null;
        }
    }

    public void printTabuleiro() {
        for (int i = 1; i <= 52; i++) {
            Stack<Peca> casa = casas.get(i);
            if (casa != null) {
                StringBuilder sb = new StringBuilder("[");
                for (Peca peca : casa) {
                    sb.append(peca.getIdPeca()).append(" ");
                }
                sb.append("]");
                System.out.print(sb);
            } else {
                System.out.print("[ ]");
            }
            System.out.print(" "); // Espaço entre as casas
        }
    }


}
