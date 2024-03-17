package Controller;

import Entity.*;
import Entity.Personagens.Fade;
import Entity.Personagens.Sage;
import Entity.Personagens.Viper;

import java.util.ArrayList;

public class Dados {
    public static Jogador jogadorA;
    public static Jogador jogadorB;
    public static Partida partida;

    public static void gerarJogadores() {
        ArrayList<Personagem> timeA = new ArrayList<>();
        timeA.add(new Sage());
        timeA.add(new Fade());
        timeA.add(new Viper());
        jogadorA = new Jogador("Lucas", timeA);

        ArrayList<Personagem> timeB = new ArrayList<>();
        timeB.add(new Sage());
        timeB.add(new Viper());
        timeB.add(new Fade());
        jogadorB = new Jogador("Ariel", timeB);

        jogadorA.meuTurno();

        Jogador[] jogadores = {jogadorA, jogadorB};

        partida = new Partida(jogadores);
    }
}
