package org.example.Entities;

import java.util.Random;

public class Dado {
    private int valor;

    public int rolarDado() {
        //aleatorio entre 1 e 6
        Random rand = new Random();
        valor = rand.nextInt(6) + 1; // gera um número aleatório entre 1 e 6
        return valor;
    }
    public int getValor() {
        return 0;
    }
    public void setValor(int valor) {

    }
}