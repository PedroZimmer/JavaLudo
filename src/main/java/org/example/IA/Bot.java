package org.example.IA;

import org.example.Entities.Peca;

import java.util.ArrayList;

public class Bot {

    private int qtdBot;
    private int idBot;
    private ArrayList<Peca> listaPecas = new ArrayList<Peca>();

    public Bot(int qtdBot) {
        this.qtdBot = qtdBot;
    }

    public Bot() {

    }

    public int getQtdBot() {
        return qtdBot;
    }

    public void setQtdBot(int qtdBot) {
        this.qtdBot = qtdBot;
    }

    public int getIdBot() {
        return idBot;
    }

    public void setIdBot(int idBot) {
        this.idBot = idBot;
    }



}
