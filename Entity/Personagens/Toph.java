package Entity.Personagens;

import Controller.Dados;
import Entity.HabilidadePendente;
import Entity.Personagem;
import Entity.Habilidade;
import Entity.Jogador;
import Resources.Aleatory;

public class Toph extends Personagem {
    public Toph() {
        super("Toph", "A Bandida Cega", 100, geradorHabilidades());
    }

    private static Habilidade[] geradorHabilidades() {
        Habilidade[] habilidades = new Habilidade[4];
        String nome;
        String descricao;
        int energia;

        nome = "Arremesso de Pedra";
        descricao = "Toph arremessa uma pedra gigante em um inimigo. \n\tDano(30).";
        energia = 1;
        habilidades[0] = new Habilidade(nome, descricao, energia, 2+energia);

        nome = "Cabana de Terra";
        descricao = "Toph cria uma Cabana de Terra, protegendo-se ou protegendo um aliado.    \n\tDefesa(30).";
        energia = 2;
        habilidades[1] = new Habilidade(nome, descricao, energia, 2+energia);

        nome = "Armadura de Metal";
        descricao = "Toph cria uma armadura de metal em volta de seu corpo.    \n\tInvulnerável(3).";
        energia = 2;
        habilidades[2] = new Habilidade(nome, descricao, energia, 2+energia);

        nome = "Pilares de Terra";
        descricao = "Toph cria pilares de terra, que brotam do chão e arremessam inimigos. \n\tDano(35); Múltiplos Alvos.";
        energia = 4;
        habilidades[3] = new Habilidade(nome, descricao, energia, 2+energia);

        return habilidades;
    }

    public boolean[][] verificarHabilidade(boolean[][] vivos, boolean[] invulneraveis, int idPersonagem, int idHabilidade) {
        if (idHabilidade == 0) {
            vivos = bloquearAliados(vivos);
            vivos = bloquearInvulneraveis(vivos, invulneraveis);
        }
        else if (idHabilidade == 1) {
            vivos = bloquearInimigos(vivos);
        }
        else if (idHabilidade == 2) {
            vivos = bloquearAliados(vivos);
            vivos = bloquearInimigos(vivos);
            vivos = permitirUsuario(vivos, idPersonagem);
        }
        else if (idHabilidade == 3) {
            vivos = bloquearAliados(vivos);
            vivos = bloquearInvulneraveis(vivos, invulneraveis);
        }

        return vivos;
    }

    public void utilizarHabilidade(HabilidadePendente habilidade) {
        Aleatory<Integer> random = new Aleatory<>();

        int idPersonagem = habilidade.getIdPersonagem();
        int idHabilidade = habilidade.getIdHabilidade();
        int idJogadorAlvo = habilidade.getIdJogadorAlvo();
        int idPersonagemAlvo = habilidade.getIdPersonagemAlvo();

        Jogador[] jogadores = Dados.partida.getJogadores();

        jogadores[0].getPersonagens().get(idPersonagem).getHabilidades()[idHabilidade].setCountdownAtual();

        if (idHabilidade == 0) {
            jogadores[idJogadorAlvo].getPersonagens().get(idPersonagemAlvo).dano(30);
        }

        else if (idHabilidade == 1) {
            jogadores[0].getPersonagens().get(idPersonagemAlvo).defender(50);
        }

        else if (idHabilidade == 2) {
            jogadores[0].getPersonagens().get(idPersonagem).ficarInvulneravel(3);
        }

        else if (idHabilidade == 3) {
            for (int i = 0; i < 3; i++) {
                jogadores[idJogadorAlvo].getPersonagens().get(i).dano(35);
            }
        }

        Dados.partida.setJogadores(jogadores);
    }
}
