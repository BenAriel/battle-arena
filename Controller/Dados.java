package Controller;

import Entity.*;
import Entity.Personagens.*;
import view.Telas;

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

    public static void gerarJogadores(String nomeJogador1, String nomeJogador2) {
        statsJogadores = new ArrayList<>();
        carregarDBJogadores();

        List<Personagem> timeA = new ArrayList<>();
        timeA.add(new Ivern());
        timeA.add(new Kennen());
        timeA.add(new Garen());
        jogadorA = new Jogador(nomeJogador1, timeA);

        List<Personagem> timeB = new ArrayList<>();
        timeB.add(new Toph());
        timeB.add(new Phoenix());
        timeB.add(new Reyna());
        jogadorB = new Jogador(nomeJogador2, timeB);

        boolean[] novoJogador = {true, true};
        idJogadores = new int[]{-1, -1};
        // Busca
        for (int i = 0; i < statsJogadores.size(); i++) {
            if (statsJogadores.get(i).getNick().equals(jogadorA.getNick())) {
                jogadorA.setPersonagensLiberados(statsJogadores.get(i).getPersonagensLiberados());
                jogadorA.setWinDrawLoss(statsJogadores.get(i).getWinDrawLoss());
                novoJogador[0] = false;
                idJogadores[0] = i;
            }
            else if (statsJogadores.get(i).getNick().equals(jogadorB.getNick())) {
                jogadorB.setPersonagensLiberados(statsJogadores.get(i).getPersonagensLiberados());
                jogadorB.setWinDrawLoss(statsJogadores.get(i).getWinDrawLoss());
                novoJogador[1] = false;
                idJogadores[1] = i;
            }
        }

        if (novoJogador[0]) {
            idJogadores[0] = statsJogadores.size();

            String[] str = {"Sage", "Reyna", "Phoenix", "Viper"};
            jogadorA.setPersonagensLiberados(str);

            if (!nomeJogador1.equals("a")) {
                statsJogadores.add(jogadorA);
            }
        }
        if (novoJogador[1]) {
            idJogadores[1] = statsJogadores.size();

            String[] str = {"Sage", "Reyna", "Phoenix", "Viper"};
            jogadorB.setPersonagensLiberados(str);

            if (!nomeJogador2.equals("a")) {
                statsJogadores.add(jogadorB);
            }
        }

        jogadorA.meuTurno();

        Jogador[] jogadores = {jogadorA, jogadorB};

        partida = new Partida(jogadores);

        escreverDBJogadores();
    }

    public static void novosPersonagens(String[][] personagens) {
        ArrayList<Personagem>[] times = new ArrayList[2];
        times[0] = new ArrayList<>();
        times[1] = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                switch (personagens[i][j]) {
                    case "Aang":
                        times[i].add(new Aang());
                        break;
                    case "Beno":
                        times[i].add(new Beno());
                        break;
                    case "Garen":
                        times[i].add(new Garen());
                        break;
                    case "Ivern":
                        times[i].add(new Ivern());
                        break;
                    case "Kennen":
                        times[i].add(new Kennen());
                        break;
                    case "Phoenix":
                        times[i].add(new Phoenix());
                        break;
                    case "Reyna":
                        times[i].add(new Reyna());
                        break;
                    case "Sage":
                        times[i].add(new Sage());
                        break;
                    case "Toph":
                        times[i].add(new Toph());
                        break;
                    case "Viper":
                        times[i].add(new Viper());
                        break;
                }
            }
        }

        jogadorA.setPersonagens(times[0]);
        jogadorB.setPersonagens(times[1]);
        System.out.println(statsJogadores.size());
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
                Jogador novoJogador = new Jogador(nick, vitorias, empates, derrotas);

                // obtem personagens liberados
                linha = br.readLine();
                novoJogador.setPersonagensLiberados(linha.split(" "));

                statsJogadores.add(novoJogador);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    public static void escreverDBJogadores() {
        String nomeArquivo = "DB/Jogadores.txt";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (int i = 0; i < statsJogadores.size(); i++) {
                if (!statsJogadores.get(i).getNick().equals("a")) {
                    int[] winDrawLoss = statsJogadores.get(i).getWinDrawLoss();

                    bw.write(statsJogadores.get(i).getNick()+" ");
                    bw.write(Integer.toString(winDrawLoss[0])+" ");
                    bw.write(Integer.toString(winDrawLoss[1])+" ");
                    bw.write(Integer.toString(winDrawLoss[2])+"\n");

                    String[] personagensLiberados = statsJogadores.get(i).getPersonagensLiberados();
                    bw.write(personagensLiberados[0]);
                    for (int j = 1; j < personagensLiberados.length; j++) {
                        bw.write(" "+personagensLiberados[j]);
                    }

                    if (i < statsJogadores.size() - 1)
                        bw.write("\n");
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }

    public static void fimPartidaVitoria() {
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

        Telas.switchScene("/view/ve/TelaInicial.fxml/");
    }

    public static void fimPartidaEmpate() {
        statsJogadores.get(idJogadores[0]).adicionarEmpate();
        statsJogadores.get(idJogadores[1]).adicionarEmpate();

        escreverDBJogadores();

        Telas.switchScene("/view/ve/TelaInicial.fxml/");
    }
}
