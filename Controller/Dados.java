package Controller;

import Entity.*;
import Entity.Personagens.*;

import java.util.ArrayList;

public class Dados {
    public static Jogador jogadorA;
    public static Jogador jogadorB;
    public static Partida partida;

    public static void gerarJogadores() {
        ArrayList<Personagem> timeA = new ArrayList<>();
        timeA.add(new Phoenix());
        timeA.add(new Reyna());
        timeA.add(new Sage());
        jogadorA = new Jogador("Jogador A", timeA);

        ArrayList<Personagem> timeB = new ArrayList<>();
        timeB.add(new Toph());
        timeB.add(new Phoenix());
        timeB.add(new Reyna());
        jogadorB = new Jogador("Jogador B", timeB);

        jogadorA.meuTurno();

        Jogador[] jogadores = {jogadorA, jogadorB};

        partida = new Partida(jogadores);
    }
}
