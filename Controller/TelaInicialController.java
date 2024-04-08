package Controller;

import Entity.Jogador;
import javafx.fxml.FXML;
import javafx.scene.image.*;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import view.Telas;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.scene.text.Text;


public class TelaInicialController {
    @FXML
    ImageView imagem, ordenar1, ordenar2, ordenar3;

    @FXML
    Text posJogador1, posJogador2, posJogador3;
    @FXML
    Text nomeJogador1, nomeJogador2, nomeJogador3;
    @FXML
    Text statsJogador1, statsJogador2, statsJogador3;
    String ordem = "Vitória";
    String[][] rank = new String[3][3];

    public void initialize() {
        Dados.gerarJogadores("a", "a");

        Text[][] textos = {
                {posJogador1, posJogador2, posJogador3},
                {nomeJogador1, nomeJogador2, nomeJogador3},
                {statsJogador1, statsJogador2, statsJogador3}
        };

        if (Dados.statsJogadores.size() >= 3) {
            ordenar(ordem);

            for (int i = 0; i < textos.length; i++) {
                for (int j = 0; j < textos[i].length; j++) {
                    textos[i][j].setText(rank[j][i]);
                }
            }

        }
        else {
            for (int i = 0; i < textos.length; i++) {
                for (int j = 0; j < textos[i].length; j++) {
                    textos[i][j].setText("");
                }
            }
        }

        imagem.setImage(new Image("view/Imagens/BotaoNovaPartida.png"));
        ordenar1.setImage(new Image("view/Imagens/BotaoOrdenar1.png"));
        ordenar2.setImage(new Image("view/Imagens/BotaoOrdenar2.png"));
        ordenar3.setImage(new Image("view/Imagens/BotaoOrdenar3.png"));
    }

    public void ordenar(String ordem) {

        List<Jogador> statsJogadores = Dados.statsJogadores;
        if (ordem.equals("Vitória")) {
            // SELECT SHORT

            for (int i = 0; i < statsJogadores.size(); i++) {
                int index = i;
                int maiorValor = statsJogadores.get(index).getWinDrawLoss()[0];

                for (int j = index + 1; j < statsJogadores.size(); j++) {
                    if (maiorValor < statsJogadores.get(j).getWinDrawLoss()[0]) {
                        maiorValor = statsJogadores.get(j).getWinDrawLoss()[0];
                        index = j;
                    }
                }

                Jogador jogAux = statsJogadores.get(i);
                statsJogadores.set(i, statsJogadores.get(index));
                statsJogadores.set(index, jogAux);
            }

        }

        else if (ordem.equals("Empate")) {
            // SELECT SHORT
            for (int i = 0; i < statsJogadores.size(); i++) {
                int index = i;
                int maiorValor = statsJogadores.get(index).getWinDrawLoss()[1];

                for (int j = index + 1; j < statsJogadores.size(); j++) {
                    if (maiorValor < statsJogadores.get(j).getWinDrawLoss()[1]) {
                        maiorValor = statsJogadores.get(j).getWinDrawLoss()[1];
                        index = j;
                    }
                }

                Jogador jogAux = statsJogadores.get(i);
                statsJogadores.set(i, statsJogadores.get(index));
                statsJogadores.set(index, jogAux);
            }
        }

        else if (ordem.equals("Derrota")) {
            // SELECT SHORT
            for (int i = 0; i < statsJogadores.size(); i++) {
                int index = i;
                int maiorValor = statsJogadores.get(index).getWinDrawLoss()[2];

                for (int j = index + 1; j < statsJogadores.size(); j++) {
                    if (maiorValor < statsJogadores.get(j).getWinDrawLoss()[2]) {
                        maiorValor = statsJogadores.get(j).getWinDrawLoss()[2];
                        index = j;
                    }
                }

                Jogador jogAux = statsJogadores.get(i);
                statsJogadores.set(i, statsJogadores.get(index));
                statsJogadores.set(index, jogAux);
            }
        }

        for (int i = 0; i < 3; i++) {
            int[] winDrawRate = statsJogadores.get(i).getWinDrawLoss();

            rank[i][0] = (i+1)+"º";
            rank[i][1] = statsJogadores.get(i).getNick();
            rank[i][2] = winDrawRate[0]+"V/"+winDrawRate[1]+"E/"+winDrawRate[2]+"D";
        }
    }

    public void iniciarPartida() {
        String nomeJogador1 = "";
        String nomeJogador2 = "";

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Jogador 1");
        dialog.setHeaderText(null);
        dialog.setContentText("Digite seu nome:");

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
            nomeJogador1 = result.get();
        }

        if (!nomeJogador1.isEmpty()) {
            dialog = new TextInputDialog();
            dialog.setTitle("Jogador 2");
            dialog.setHeaderText(null);
            dialog.setContentText("Digite seu nome:");

            result = dialog.showAndWait();

            if (result.isPresent()) {
                nomeJogador2 = result.get();
            }
        }

        if (!nomeJogador1.isEmpty() && !nomeJogador2.isEmpty() && !nomeJogador1.equals(nomeJogador2)) {
            Dados.gerarJogadores(nomeJogador1, nomeJogador2);

            Telas.switchScene("/view/ve/TelaSelecionarPersonagens.fxml/");
        }

    }

    public void ordenar(MouseEvent event) {
        ImageView imagemClicada = (ImageView) event.getSource();
        String idImagem = imagemClicada.getId();

        if (idImagem.equals("ordenar1")) {
            ordem = "Vitória";
        }
        else if (idImagem.equals("ordenar2")) {
            ordem = "Empate";
        }
        else if (idImagem.equals("ordenar3")) {
            ordem = "Derrota";
        }

        initialize();
    }
}
