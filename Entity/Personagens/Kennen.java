package Entity.Personagens;

import Controller.Dados;
import Entity.HabilidadePendente;
import Entity.Personagem;
import Entity.Habilidade;
import Entity.Jogador;

public class Kennen extends Personagem {
    public Kennen() {
        super("Kennen", "O coração da Tempestade", 100, geradorHabilidades());
    }

    private static Habilidade[] geradorHabilidades() {
        Habilidade[] habilidades = new Habilidade[4];
        String nome;
        String descricao;
        int energia;

        nome = "Shuriken Trovejante";
        descricao = "Kennen arremessa uma shuriken embuida em raio.    \n\t dano(25).";
        energia = 1;
        habilidades[0] = new Habilidade(nome, descricao, energia, 2+energia);

        nome = "Surto eletrico";
        descricao = "Kennen solta raios no chao,acertando todos os alvos.    \n\t Dano(20).";
        energia = 2;
        habilidades[1] = new Habilidade(nome, descricao, energia, 2+energia);

        nome = "Investida relampago";
        descricao = "Kennen fica super-rápido,fazendo com que ninguém consiga pegar ele. ivulneravel(2).";
        energia = 3;
        habilidades[2] = new Habilidade(nome, descricao, energia, 2+energia);

        nome = "Turbilhão Cortante";
        descricao = "Kennen faz chover raios,causando grande dano a todos os inimigos e stunando eles. \n\t dano(30);Stun(1).";
        energia = 5;
        habilidades[3] = new Habilidade(nome, descricao, energia, 2+energia);

        return habilidades;
    }

    public boolean[][] verificarHabilidade(boolean[][] vivos, boolean[] invulneraveis, int idPersonagem, int idHabilidade) {
        if (idHabilidade == 0) {
            vivos = bloquearAliados(vivos);
        }
        else if (idHabilidade == 1) {
            vivos = bloquearAliados(vivos);
        }
        else if (idHabilidade == 2) {
            vivos = bloquearInimigos(vivos);
            vivos = bloquearAliados(vivos);
            vivos = permitirUsuario(vivos, idPersonagem);
        }
        else if (idHabilidade == 3) {
            vivos = bloquearAliados(vivos);
        }

        return vivos;
    }

    public void utilizarHabilidade(HabilidadePendente habilidade) {
        Resources.Aleatory<Integer> random = new Resources.Aleatory<>();

        int idPersonagem = habilidade.getIdPersonagem();
        int idHabilidade = habilidade.getIdHabilidade();
        int idJogadorAlvo = habilidade.getIdJogadorAlvo();
        int idPersonagemAlvo = habilidade.getIdPersonagemAlvo();

        Jogador[] jogadores = Dados.partida.getJogadores();

        jogadores[0].getPersonagens().get(idPersonagem).getHabilidades()[idHabilidade].setCountdownAtual();

        if (idHabilidade == 0) {
            if (jogadores[idJogadorAlvo].getPersonagens().get(idPersonagemAlvo).getInvulneravel() == 0 && jogadores[idJogadorAlvo].getPersonagens().get(idPersonagemAlvo).getVida() > 0){
                jogadores[idJogadorAlvo].getPersonagens().get(idPersonagemAlvo).dano(25);
            }
        }

        else if (idHabilidade == 1) {
            for (int i = 0; i < 3; i++) {
                if (jogadores[idJogadorAlvo].getPersonagens().get(i).getVida() > 0 && jogadores[idJogadorAlvo].getPersonagens().get(i).getInvulneravel() == 0){
                    jogadores[idJogadorAlvo].getPersonagens().get(i).dano(20);

                }
                }
            }

        else if (idHabilidade == 2) {
            jogadores[0].getPersonagens().get(idPersonagem).ficarInvulneravel(2);
        }

        else if (idHabilidade == 3) {
            for (int i = 0; i < 3; i++) {
                if (jogadores[idJogadorAlvo].getPersonagens().get(i).getVida() > 0 && jogadores[idJogadorAlvo].getPersonagens().get(i).getInvulneravel() == 0){
                    jogadores[idJogadorAlvo].getPersonagens().get(i).dano(30);
                    jogadores[idJogadorAlvo].getPersonagens().get(i).stunnar(1);
                }
            }
        }

        Dados.partida.setJogadores(jogadores);
    }
}
