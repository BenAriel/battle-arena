package Controller;

import Entity.*;
import Entity.Personagens.*;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.MouseEvent;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.layout.Pane;

public class TelaPartidaController {
    @FXML
    private Text nickJog1, nickJog2,
            winRateJog1, winRateJog2;
    @FXML
    private Text turno;
    @FXML
    private ImageView Char1, Char2, Char3, Char4, Char5, Char6;
    @FXML
    private Rectangle Rect1, Rect2, Rect3, Rect4, Rect5, Rect6;
    @FXML
    private Text Vida1, Vida2, Vida3, Vida4, Vida5, Vida6;
    @FXML
    private Text EnergiaVermelha, EnergiaAmarela, EnergiaAzul, EnergiaPreta;
    @FXML
    private ImageView Habilidade0Pers1, Habilidade1Pers1, Habilidade2Pers1, Habilidade3Pers1,
            Habilidade0Pers2, Habilidade1Pers2, Habilidade2Pers2, Habilidade3Pers2,
            Habilidade0Pers3, Habilidade1Pers3, Habilidade2Pers3, Habilidade3Pers3;
    @FXML
    private ImageView HabilidadePendente1, HabilidadePendente2, HabilidadePendente3;
    @FXML
    private ImageView HabilidadeAtiva1Pers1Jog1, HabilidadeAtiva1Pers2Jog1, HabilidadeAtiva1Pers3Jog1,
            HabilidadeAtiva2Pers1Jog1, HabilidadeAtiva2Pers2Jog1, HabilidadeAtiva2Pers3Jog1,
            HabilidadeAtiva3Pers1Jog1, HabilidadeAtiva3Pers2Jog1, HabilidadeAtiva3Pers3Jog1,
            HabilidadeAtiva4Pers1Jog1, HabilidadeAtiva4Pers2Jog1, HabilidadeAtiva4Pers3Jog1;
    @FXML
    private ImageView HabilidadeAtiva1Pers1Jog2, HabilidadeAtiva1Pers2Jog2, HabilidadeAtiva1Pers3Jog2,
            HabilidadeAtiva2Pers1Jog2, HabilidadeAtiva2Pers2Jog2, HabilidadeAtiva2Pers3Jog2,
            HabilidadeAtiva3Pers1Jog2, HabilidadeAtiva3Pers2Jog2, HabilidadeAtiva3Pers3Jog2,
            HabilidadeAtiva4Pers1Jog2, HabilidadeAtiva4Pers2Jog2, HabilidadeAtiva4Pers3Jog2;
    @FXML
    private ImageView botaoPassarTurno;
    @FXML
    private Text Countdown0Pers1, Countdown1Pers1, Countdown2Pers1, Countdown3Pers1,
            Countdown0Pers2, Countdown1Pers2, Countdown2Pers2, Countdown3Pers2,
            Countdown0Pers3, Countdown1Pers3, Countdown2Pers3, Countdown3Pers3;

    @FXML
    private Pane BarraInferior, QuadradoEnergiaHabilidadeClicada;
    boolean controladorBarraInferior = false;
    boolean[][] alvosDisponiveis;
    int idPersonagemAtacante;
    int idHabilidade;

    @FXML
    private Text NomeHabilidadeClicada, EnergiaHabilidadeClicada;

    private HabilidadePendente[] habilidadesPendentes = new HabilidadePendente[3];

    public void initialize() {
        turno.setText("Turno: "+Dados.partida.getTurno()+"/30");

        Text[] nomesJogadores = {nickJog1, nickJog2};
        Text[] winRate = {winRateJog1, winRateJog2};

        Jogador jogA = Dados.partida.getJogadorAtacante();
        nomesJogadores[0].setText(jogA.getNick());
        winRate[0].setText(jogA.getWinRate());


        Jogador jogB = Dados.partida.getJogadorDefensor();
        nomesJogadores[1].setText(jogB.getNick());
        winRate[1].setText(jogB.getWinRate());


        Personagem[] timeA = {
                Dados.partida.getJogadores()[0].getPersonagens().get(0),
                Dados.partida.getJogadores()[0].getPersonagens().get(1),
                Dados.partida.getJogadores()[0].getPersonagens().get(2)
        };
        Personagem[] timeB = {
                Dados.partida.getJogadores()[1].getPersonagens().get(0),
                Dados.partida.getJogadores()[1].getPersonagens().get(1),
                Dados.partida.getJogadores()[1].getPersonagens().get(2)
        };

        botaoPassarTurno.setImage(new Image("view/Imagens/BotaoPassarTurno.png"));


        Image[] img = {
                new Image("view/Imagens/Personagens/"+timeA[0].getNome()+".png"),
                new Image("view/Imagens/Personagens/"+timeA[1].getNome()+".png"),
                new Image("view/Imagens/Personagens/"+timeA[2].getNome()+".png"),
                new Image("view/Imagens/Personagens/"+timeB[0].getNome()+".png"),
                new Image("view/Imagens/Personagens/"+timeB[1].getNome()+".png"),
                new Image("view/Imagens/Personagens/"+timeB[2].getNome()+".png")
        };


        desativarAtivados();

        ImageView[][] chars = {
            {Char1, Char2, Char3},
            {Char4, Char5, Char6}

        };
        Rectangle[][] rects = {
            {Rect1, Rect2, Rect3},
            {Rect4, Rect5, Rect6}
        };
        Text[][] vidas = {
            {Vida1, Vida2, Vida3},
            {Vida4, Vida5, Vida6}
        };

        // Define energias na tela
        Text[] energias = {EnergiaVermelha, EnergiaAmarela, EnergiaAzul, EnergiaPreta};
        int[] valorEnergias = Dados.partida.getJogadores()[0].getEnergia();
        for (int i = 0; i < energias.length; i++) {
            energias[i].setText(Integer.toString(valorEnergias[i]));
        }

        // Define Imagens de Habilidades
        ImageView[][] imgsHabilidades = {
                {Habilidade0Pers1, Habilidade1Pers1, Habilidade2Pers1, Habilidade3Pers1},
                {Habilidade0Pers2, Habilidade1Pers2, Habilidade2Pers2, Habilidade3Pers2},
                {Habilidade0Pers3, Habilidade1Pers3, Habilidade2Pers3, Habilidade3Pers3}
        };
        ImageView[] imgsHabilidadesPendentes = {
                HabilidadePendente1, HabilidadePendente2, HabilidadePendente3
        };

        Text[][] countdowns = {
                {Countdown0Pers1, Countdown1Pers1, Countdown2Pers1, Countdown3Pers1},
                {Countdown0Pers2, Countdown1Pers2, Countdown2Pers2, Countdown3Pers2},
                {Countdown0Pers3, Countdown1Pers3, Countdown2Pers3, Countdown3Pers3}
        };
        Image[][] imgHabilidades = new Image[3][4];
        Image[] imgHabilidadesPendentes = new Image[3];
        for (int i = 0; i < 3; i++) {
            boolean personagemMorto = Dados.partida.getJogadores()[0].getPersonagens().get(i).getVida() == 0; // verifica se o personagem está morto
            boolean stunned = Dados.partida.getJogadores()[0].getPersonagens().get(i).getStunned() > 0; // verifica se o personagem está stunnado
            for (int j = 0; j < 4; j++) {
                imgHabilidades[i][j] = new Image("view/Imagens/Personagens/"+timeA[i].getNome()+j+".png"); // obtem a imagem da skill

                if (habilidadesPendentes[i] != null && habilidadesPendentes[i].getIdHabilidade() == j) {
                    imgHabilidades[i][j] = new Image("view/Imagens/Personagens/White.png");
                    imgHabilidadesPendentes[i] = new Image("view/Imagens/Personagens/"+timeA[i].getNome()+j+".png");
                }

                imgsHabilidades[i][j].setImage(imgHabilidades[i][j]);
                imgsHabilidadesPendentes[i].setImage(imgHabilidadesPendentes[i]);

                boolean countdown = Dados.partida.getJogadores()[0].getPersonagens().get(i).getHabilidades()[j].getCountdownAtual() > 0;
                if (countdown && !personagemMorto) { // caso o personagem esteja em countdown, exibe
                    int numCountdown = Dados.partida.getJogadores()[0].getPersonagens().get(i).getHabilidades()[j].getCountdownAtual();

                    countdowns[i][j].setFill(Color.DARKGREY);
                    countdowns[i][j].setText(Integer.toString(numCountdown));
                    countdowns[i][j].setStroke(Color.WHITE);
                    countdowns[i][j].setStrokeWidth(1);
                }
                else { // caso o personagem não esteja em countdown, fica transparante
                    countdowns[i][j].setFill(Color.TRANSPARENT);
                    countdowns[i][j].setStroke(Color.TRANSPARENT);
                }

                imgsHabilidades[i][j].setEffect(null);
                if (personagemMorto || stunned || countdown) { // se o personagem está morto, stunnado ou em countdown, skills ficam cinza
                    ColorAdjust colorAdjust = new ColorAdjust();
                    colorAdjust.setSaturation(-1);
                    imgsHabilidades[i][j].setEffect(colorAdjust);
                }
                else {
                    int custoHabilidade = Dados.partida.getJogadores()[0].getPersonagens().get(i).getHabilidades()[j].getEnergia();
                    int energiaDisponivel = Dados.partida.getJogadores()[0].getEnergia()[3]+Dados.partida.getJogadores()[0].getEnergia()[i];

                    if (custoHabilidade > energiaDisponivel) {
                        ColorAdjust colorAdjust = new ColorAdjust();
                        colorAdjust.setSaturation(-1);
                        imgsHabilidades[i][j].setEffect(colorAdjust);
                    }
                }
            }
        }

        // Imagens e vida do Time A
        for (int i = 0; i < 3; i++) {
            chars[0][i].setImage(img[i]);

            rects[0][i].setWidth(Char1.getFitWidth()*timeA[i].getVida()/100);

            vidas[0][i].setFill(Color.BLACK);
            vidas[0][i].setText(timeA[i].getVida()+"/100");
            vidas[0][i].setTextAlignment(TextAlignment.CENTER);
            if (timeA[i].getVida() >= 70) {
                rects[0][i].setFill(Color.GREEN);
                vidas[0][i].setFill(Color.WHITE);
            }
            else if (timeA[i].getVida() >= 30) {
                rects[0][i].setFill(Color.YELLOW);
            }
            else {
                rects[0][i].setFill(Color.RED);
            }

            // Ajusta para Escalas de Cinza, caso esteja morto
            if (timeA[i].getVida() <= 0) {
                ColorAdjust colorAdjust = new ColorAdjust();
                colorAdjust.setSaturation(-1);
                chars[0][i].setEffect(colorAdjust);
            }
            else {
                chars[0][i].setEffect(null);
            }
        }

        // Imagens e Vida do Time B
        for (int i = 0; i < 3; i++) {
            chars[1][i].setImage(img[i+3]);
            chars[1][i].setScaleX(-1); // espelha imagem horizontalmente

            rects[1][i].setWidth(Char1.getFitWidth()*timeB[i].getVida()/100);

            vidas[1][i].setFill(Color.BLACK);
            vidas[1][i].setText(timeB[i].getVida()+"/100");
            vidas[1][i].setTextAlignment(TextAlignment.CENTER);
            if (timeB[i].getVida() >= 70) {
                rects[1][i].setFill(Color.GREEN);
                vidas[1][i].setFill(Color.WHITE);
            }
            else if (timeB[i].getVida() >= 30) {
                rects[1][i].setFill(Color.YELLOW);
            }
            else {
                rects[1][i].setFill(Color.RED);
            }

            // Ajusta para Escalas de Cinza, caso esteja morto
            if (timeB[i].getVida() <= 0) {
                ColorAdjust colorAdjust = new ColorAdjust();
                colorAdjust.setSaturation(-1);
                chars[1][i].setEffect(colorAdjust);
            }
            else {
                chars[1][i].setEffect(null);
            }
        }



        // imagens das habilidades ativas
        ImageView[][] habilidadesAtivasJogadorA = {
                {HabilidadeAtiva1Pers1Jog1, HabilidadeAtiva1Pers2Jog1, HabilidadeAtiva1Pers3Jog1},
                {HabilidadeAtiva2Pers1Jog1, HabilidadeAtiva2Pers2Jog1, HabilidadeAtiva2Pers3Jog1},
                {HabilidadeAtiva3Pers1Jog1, HabilidadeAtiva3Pers2Jog1, HabilidadeAtiva3Pers3Jog1},
                {HabilidadeAtiva4Pers1Jog1, HabilidadeAtiva4Pers2Jog1, HabilidadeAtiva4Pers3Jog1}
        };

        ImageView[][] habilidadesAtivasJogadorB = {
                {HabilidadeAtiva1Pers1Jog2, HabilidadeAtiva1Pers2Jog2, HabilidadeAtiva1Pers3Jog2},
                {HabilidadeAtiva2Pers1Jog2, HabilidadeAtiva2Pers2Jog2, HabilidadeAtiva2Pers3Jog2},
                {HabilidadeAtiva3Pers1Jog2, HabilidadeAtiva3Pers2Jog2, HabilidadeAtiva3Pers3Jog2},
                {HabilidadeAtiva4Pers1Jog2, HabilidadeAtiva4Pers2Jog2, HabilidadeAtiva4Pers3Jog2}
        };


        Jogador jogadorAtacante = Dados.partida.getJogadorAtacante();
        Jogador jogadorDefensor = Dados.partida.getJogadorDefensor();

        for (int i = 0; i < 3; i++) {
            String[] str = {"Escudo", "Stun", "Invulneravel"};

            Personagem personagemAtual = jogadorAtacante.getPersonagens().get(i);
            int controlador = 0;
            boolean[] ativadores = {
                    personagemAtual.getDefesa() > 0,
                    personagemAtual.getStunned() > 0,
                    personagemAtual.getInvulneravel() > 0
            };

            personagemAtual = jogadorDefensor.getPersonagens().get(i);
            int controladorJogB = 0;
            boolean[] ativadoresJogadorB = {
                personagemAtual.getDefesa() > 0,
                personagemAtual.getStunned() > 0,
                personagemAtual.getInvulneravel() > 0
            };

            for (int j = 0; j < str.length; j++) {
                habilidadesAtivasJogadorA[j][i].setImage(null);
                if (ativadores[j]) {
                    habilidadesAtivasJogadorA[controlador][i].setImage(new Image("view/Imagens/"+str[j]+".png"));
                    controlador++;
                }

                habilidadesAtivasJogadorB[j][i].setImage(null);
                if (ativadoresJogadorB[j]) {
                    habilidadesAtivasJogadorB[controladorJogB][i].setImage(new Image("view/Imagens/"+str[j]+".png"));
                    controladorJogB++;
                }

            }



        }

        // ajusta a tonalidade dos alvos disponíveis
        if (alvosDisponiveis != null) {
            for (int i = 0; i < alvosDisponiveis.length; i++) {
                for (int j = 0; j < alvosDisponiveis[i].length; j++) {
                    if (alvosDisponiveis[i][j]) {
                        ColorAdjust colorAdjust = new ColorAdjust();
                        colorAdjust.setHue(0.1); // Ajusta a tonalidade para uma cor amarelada
                        colorAdjust.setSaturation(0.5); // Ajusta a saturação para intensificar a cor
                        chars[i][j].setEffect(colorAdjust);
                    }
                }
            }
        }


    }

    private void desativarAtivados() {
        if (controladorBarraInferior) {
            controladorBarraInferior = false;
        }
        else {
            BarraInferior.setStyle("-fx-border-color: transparent;");
            this.alvosDisponiveis = null;
            NomeHabilidadeClicada.setFill(Color.TRANSPARENT);
            EnergiaHabilidadeClicada.setFill(Color.TRANSPARENT);
            QuadradoEnergiaHabilidadeClicada.setStyle("-fx-border-color: transparent;");
        }
    }

    public void cliqueHabilidade(MouseEvent event) {
        ImageView imagemClicada = (ImageView) event.getSource();
        String idImagem = imagemClicada.getId();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                String code = "Habilidade"+j+"Pers"+(i+1);

                if (idImagem.equals(code)) {
                    // exibe a barra inferior

                    BarraInferior.setStyle("-fx-border-color: black;");
                    controladorBarraInferior = true;

                    String nomeEDescricao = Dados.partida.getJogadores()[0].getPersonagens().get(i).getHabilidades()[j].getNome()+"\n";
                    nomeEDescricao += Dados.partida.getJogadores()[0].getPersonagens().get(i).getHabilidades()[j].getDescricao();
                    int custo = Dados.partida.getJogadores()[0].getPersonagens().get(i).getHabilidades()[j].getEnergia();

                    NomeHabilidadeClicada.setFill(Color.BLACK);
                    NomeHabilidadeClicada.setText(nomeEDescricao);
                    EnergiaHabilidadeClicada.setFill(Color.WHITE);
                    EnergiaHabilidadeClicada.setText(Integer.toString(custo));

                    if (i == 0) {
                        QuadradoEnergiaHabilidadeClicada.setStyle("-fx-background-color: red;");
                    }
                    else if (i == 1) {
                        QuadradoEnergiaHabilidadeClicada.setStyle("-fx-background-color: #DEAF0C;");
                    }
                    else {
                        QuadradoEnergiaHabilidadeClicada.setStyle("-fx-background-color: blue;");
                    }

                    int custoHabilidade = Dados.partida.getJogadores()[0].getPersonagens().get(i).getHabilidades()[j].getEnergia();
                    int energiaDisponivel = Dados.partida.getJogadores()[0].getEnergia()[3]+Dados.partida.getJogadores()[0].getEnergia()[i];

                    if (custoHabilidade <= energiaDisponivel) {
                        // obtem em quais personagens é possível usar a habildiade
                        this.alvosDisponiveis = Dados.partida.getJogadores()[0].verificarHabilidade(i, j, Dados.partida.getJogadores()[1].getPersonagens());
                        this.idPersonagemAtacante = i;
                        this.idHabilidade = j;
                    }
                    else {
                        alvosDisponiveis = null;
                        this.idPersonagemAtacante = -1;
                        this.idHabilidade = -1;
                    }
                }
            }
        }

        if (alvosDisponiveis != null) {
            for (int i = 0; i < 3; i++) {
                ColorAdjust colorAdjust = new ColorAdjust();
                colorAdjust.setSaturation(-1);
                Char1.setEffect(colorAdjust);
            }
        }


        initialize();
    }

    public void cliquePersonagem(MouseEvent event) {
        ImageView imagemClicada = (ImageView) event.getSource();
        String idImagem = imagemClicada.getId();

        int idJogadorAlvo = 0;
        int idPersonagemAlvo = 0;
        for (int i = 1; i <= 6; i++) {
            String code = "Char"+i;

            if (idImagem.equals("Char"+i)) {
                if (i <= 3) {
                    idJogadorAlvo = 0;
                    idPersonagemAlvo = i - 1;
                }
                else {
                    idJogadorAlvo = 1;
                    idPersonagemAlvo = i - 4;
                }
            }
        }
        if (alvosDisponiveis != null && alvosDisponiveis[idJogadorAlvo][idPersonagemAlvo]) {
            //Dados.partida.getJogadores()[0].utilizarHabilidade(atacante, alvo, this.indexPersonagem, this.indexHabilidade, indexPersonagemAlvo);


            if (habilidadesPendentes[this.idPersonagemAtacante] == null) {
                habilidadesPendentes[this.idPersonagemAtacante] = new HabilidadePendente(idPersonagemAtacante, idHabilidade, idJogadorAlvo, idPersonagemAlvo);
            }
        }

        initialize();
    }

    public void cliqueHabilidadePendente(MouseEvent event) {
        ImageView imagemClicada = (ImageView) event.getSource();
        String idImagem = imagemClicada.getId();

        for (int i = 0; i < 3; i++) {
            String nm = "HabilidadePendente"+(i+1);
            if (idImagem.equals(nm) && habilidadesPendentes[i] != null) {
                habilidadesPendentes[i].liberar();
                habilidadesPendentes[i] = null;
            }
        }
        initialize();
    }

    public void passarTurno(MouseEvent event) {
        for (int i = 0; i < 3; i++) {
            if (habilidadesPendentes[i] != null) {
                Dados.partida.utilizarHabilidade(habilidadesPendentes[i]);

                habilidadesPendentes[i] = null;
            }
        }

        boolean mortos = true;
        for (int i = 0; i < 3; i++) {
            if (Dados.partida.getJogadorDefensor().getPersonagens().get(i).getVida() > 0) {
                mortos = false;
            }
        }
        if (mortos) {
            Dados.fimPartidaVitoria();
        }

        Dados.partida.inverterJogadores();

        initialize();

    }

}
