package Controller;

import Entity.*;
import Entity.Personagens.*;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Dados {
    public static Jogador jogadorA;
    public static Jogador jogadorB;
    public static Partida partida;
    static List<Jogador> statsJogadores = new ArrayList<>();
    static int[] idJogadores;

    public static void gerarJogadores() {
        carregarDBJogadores();

        List<Personagem> timeA = new ArrayList<>();
        timeA.add(new Ivern());
        timeA.add(new Kennen());
        timeA.add(new Garen());
        jogadorA = new Jogador("Lucas", timeA);

        List<Personagem> timeB = new ArrayList<>();
        timeB.add(new Toph());
        timeB.add(new Phoenix());
        timeB.add(new Reyna());
        jogadorB = new Jogador("Ariel", timeB);

        boolean[] novoJogador = {true, true};
        idJogadores = new int[]{-1, -1};
        for (int i = 0; i < statsJogadores.size(); i++) {
            if (statsJogadores.get(i).getNick().equals(jogadorA.getNick())) {
                jogadorA.setWinDrawLoss(statsJogadores.get(i).getWinDrawLoss());
                novoJogador[0] = false;
                idJogadores[0] = i;
            }
            else if (statsJogadores.get(i).getNick().equals(jogadorB.getNick())) {
                jogadorB.setWinDrawLoss(statsJogadores.get(i).getWinDrawLoss());
                novoJogador[1] = false;
                idJogadores[1] = i;
            }
        }

        if (novoJogador[0]) {
            idJogadores[0] = statsJogadores.size();
            statsJogadores.add(new Jogador(jogadorA.getNick(), 0, 0, 0));
        }
        if (novoJogador[1]) {
            idJogadores[1] = statsJogadores.size();
            statsJogadores.add(new Jogador(jogadorB.getNick(), 0, 0, 0));
        }

        jogadorA.meuTurno();

        Jogador[] jogadores = {jogadorA, jogadorB};

        partida = new Partida(jogadores);
    }

    public static void carregarDBJogadores() {
        String nomeArquivo = "DB/Jogadores.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] split = linha.split(" ");

                String nick = split[0];
                int vitorias = Integer.parseInt(split[1]);
                int empates = Integer.parseInt(split[2]);
                int derrotas = Integer.parseInt(split[3]);
                statsJogadores.add(new Jogador(nick, vitorias, empates, derrotas));
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    public static void escreverDBJogadores() {
        String nomeArquivo = "DB/Jogadores.txt";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (int i = 0; i < statsJogadores.size(); i++) {
                int[] winDrawLoss = statsJogadores.get(i).getWinDrawLoss();

                bw.write(statsJogadores.get(i).getNick()+" ");
                bw.write(Integer.toString(winDrawLoss[0])+" ");
                bw.write(Integer.toString(winDrawLoss[1])+" ");
                bw.write(Integer.toString(winDrawLoss[2])+"\n");
            }
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }

    public static void fimPartida() {
        int vencedor = 0;
        int perdedor = 0;

        if (jogadorA.getNick().equals(partida.getJogadorAtacante().getNick())) {
            vencedor = idJogadores[0];
            perdedor = idJogadores[1];
        }
        else {
            vencedor = idJogadores[1];
            perdedor = idJogadores[0];
        }

        statsJogadores.get(vencedor).adicionarVitoria();
        statsJogadores.get(perdedor).adicionarDerrota();

        escreverDBJogadores();
    }
}
