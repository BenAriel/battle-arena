package Entity.Personagens;

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
        descricao = "Sage usa um orbe curativo em um aliado.    \nCura(35).";
        energia = 2;
        habilidades[1] = new Habilidade(nome, descricao, energia, 2+energia);

        nome = "Orbe de Lentidão";
        descricao = "Sage joga um Orbe de Lentidão nos inimigos.    \nStun(3); Múltiplos Alvos; Chance(66%).";
        energia = 3;
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
            vivos = bloquearAliados(vivos);
            vivos = permitirUsuario(vivos, idPersonagem);
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

    public Jogador[] utilizarHabilidade(Jogador jogador, Jogador alvo, int idHabilidade, int idPersonagem, int idPersonagemAlvo) {
        Aleatory<Integer> random = new Aleatory<>();
        String nomeJogador = jogador.getNick();
        String nomeHabilidade = getHabilidades()[idHabilidade].getNome();

        jogador.getPersonagens().get(idPersonagem).getHabilidades()[idHabilidade].setCountdownAtual();

        if (idHabilidade == 0) {
            for (int i = 0; i < 3; i++) {
                if (jogador.getPersonagens().get(i).getVida() > 0) {
                    jogador.getPersonagens().get(i).setDefesa(20);
                }
            }
        }

        else if (idHabilidade == 1) {
            jogador.getPersonagens().get(idPersonagemAlvo).curar(25);
        }

        else if (idHabilidade == 2) {
            for (int i = 0; i < 3; i++) {
                if (alvo.getPersonagens().get(i).getVida() > 0 && alvo.getPersonagens().get(i).getInvulneravel() == 0 && random.chance(66)) { // se o personagem estiver vivo, executa em 50% dos casos
                    alvo.getPersonagens().get(i).stunnar(3);
                }
            }
        }

        else if (idHabilidade == 3) {
            jogador.getPersonagens().get(idPersonagemAlvo).ressuscitar(40);
        }

        Jogador[] jogadores = {jogador, alvo};
        return jogadores;
    }
}
