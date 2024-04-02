package Entity.Personagens;

import Controller.Dados;
import Entity.HabilidadePendente;
import Entity.Personagem;
import Entity.Habilidade;
import Entity.Jogador;

public class Garen extends Personagem {
    public Garen() {
        super("Garen", "O poder de demacia", 100, geradorHabilidades());
    }

    private static Habilidade[] geradorHabilidades() {
        Habilidade[] habilidades = new Habilidade[4];
        String nome;
        String descricao;
        int energia;

        nome = "Acerto Decisivo";
        descricao = "Garen corre até o inimigo e o acerta com a espada.    \n\t dano(20).";
        energia = 1;
        habilidades[0] = new Habilidade(nome, descricao, energia, 2+energia);

        nome = "Coragem";
        descricao = "Garen se inspira, ganhando motivação para continuar na batalha.  \n\t Defesa(50).";
        energia = 2;
        habilidades[1] = new Habilidade(nome, descricao, energia, 2+energia);

        nome = "Julgamento";
        descricao = "Garen gira rapidamente a espada ao redor do corpo dele, causando Dano a todos inimigos. \n\t Dano(25).";
        energia = 3;
        habilidades[2] = new Habilidade(nome, descricao, energia, 2+energia);

        nome = "Justiça Demaciana";
        descricao = "Garen evoca o Poder de Demacia para tentar executar um Campeão inimigo com vida baixa. \n\t Dano(20);Porcentagem de vida para executar(40%).";
        energia = 5;
        habilidades[3] = new Habilidade(nome, descricao, energia, 2+energia);

        return habilidades;
    }

    public boolean[][] verificarHabilidade(boolean[][] vivos, boolean[] invulneraveis, int idPersonagem, int idHabilidade) {
        if (idHabilidade == 0) {
            vivos = bloquearAliados(vivos);
            vivos = bloquearInvulneraveis(vivos, invulneraveis);
        }
        else if (idHabilidade == 1) {
            vivos = bloquearAliados(vivos);
            vivos = bloquearInimigos(vivos);
            vivos = permitirUsuario(vivos, idPersonagem);
        }
        else if (idHabilidade == 2) {
            vivos = bloquearAliados(vivos);
            vivos = bloquearInvulneraveis(vivos, invulneraveis);
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
            jogadores[idJogadorAlvo].getPersonagens().get(idPersonagemAlvo).dano(20);
        }

        else if (idHabilidade == 1) {
            jogadores[0].getPersonagens().get(idPersonagem).defender(50);
        }


        else if (idHabilidade == 2) {
            for (int i = 0; i < 3; i++) {
                if (jogadores[idJogadorAlvo].getPersonagens().get(i).getVida() > 0 && jogadores[idJogadorAlvo].getPersonagens().get(i).getInvulneravel() == 0) {
                    jogadores[idJogadorAlvo].getPersonagens().get(i).dano(25);
                }
            }
        }

        else if (idHabilidade == 3) {
            if(jogadores[idJogadorAlvo].getPersonagens().get(idPersonagemAlvo).getVida() <= 40){
                jogadores[idJogadorAlvo].getPersonagens().get(idPersonagemAlvo).danoDireto(40);
            }
            else{
                jogadores[idJogadorAlvo].getPersonagens().get(idPersonagemAlvo).dano(20);
            }
        }

        Dados.partida.setJogadores(jogadores);
    }
}
