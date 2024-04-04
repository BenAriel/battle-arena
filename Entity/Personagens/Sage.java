package Entity.Personagens;

import Controller.Dados;
import Entity.HabilidadePendente;
import Entity.Personagem;
import Entity.Habilidade;
import Entity.Jogador;
import Resources.Aleatory;

public class Sage extends Personagem {
    public Sage() {
        super("Sage", "Fortaleza Chinesa", 100, geradorHabilidades());
    }

    private static Habilidade[] geradorHabilidades() {
        Habilidade[] habilidades = new Habilidade[4];
        String nome;
        String descricao;
        int energia;

        nome = "Orbe de Barreira";
        descricao = "Sage usa uma Orbe de Barreira, protegendo a si e seus aliados. \nDefesa(20); Múltiplos Alvos.";
        energia = 1;
        habilidades[0] = new Habilidade(nome, descricao, energia, 2+energia);

        nome = "Orbe Curativo";
        descricao = "Sage usa um orbe curativo em um aliado.    \nCura(30).";
        energia = 2;
        habilidades[1] = new Habilidade(nome, descricao, energia, 2+energia);

        nome = "Orbe de Lentidão";
        descricao = "Sage joga um Orbe de Lentidão nos inimigos.    \nStun(2); Múltiplos Alvos; Chance(66%).";
        energia = 2;
        habilidades[2] = new Habilidade(nome, descricao, energia, 2+energia);

        nome = "Ressurreição";
        descricao = "Sage usa uma habilidade de ressurreição em um aliado. \nVida(40).";
        energia = 5;
        habilidades[3] = new Habilidade(nome, descricao, energia, 2+energia);

        return habilidades;
    }

    public boolean[][] verificarHabilidade(boolean[][] vivos, boolean[] invulneraveis, int idPersonagem, int idHabilidade) {
        if (idHabilidade == 0) {
            vivos = bloquearInimigos(vivos);
        }
        else if (idHabilidade == 1) {
            vivos = bloquearInimigos(vivos);
        }
        else if (idHabilidade == 2) {
            vivos = bloquearAliados(vivos);
            vivos = bloquearInvulneraveis(vivos, invulneraveis);
        }
        else if (idHabilidade == 3) {
            vivos = inverterAliados(vivos); // disponível apenas para mortos
            vivos = bloquearInimigos(vivos);
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
            for (int i = 0; i < 3; i++) {
                if (jogadores[0].getPersonagens().get(i).getVida() > 0) {
                    jogadores[0].getPersonagens().get(i).setDefesa(20);
                }
            }
        }

        else if (idHabilidade == 1) {
            jogadores[0].getPersonagens().get(idPersonagemAlvo).curar(25);
        }

        else if (idHabilidade == 2) {
            for (int i = 0; i < 3; i++) {
                if (jogadores[idJogadorAlvo].getPersonagens().get(i).getVida() > 0 && jogadores[idJogadorAlvo].getPersonagens().get(i).getInvulneravel() == 0 && random.chance(66)) { // se o personagem estiver vivo, executa em 50% dos casos
                    jogadores[idJogadorAlvo].getPersonagens().get(i).stunnar(2);
                }
            }
        }

        else if (idHabilidade == 3) {
            jogadores[0].getPersonagens().get(idPersonagemAlvo).ressuscitar(40);
        }

        Dados.partida.setJogadores(jogadores);
    }
}
