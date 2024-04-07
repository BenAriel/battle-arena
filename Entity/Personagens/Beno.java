package Entity.Personagens;

import Controller.Dados;
import Entity.HabilidadePendente;
import Entity.Personagem;
import Entity.Habilidade;
import Entity.Jogador;

public class Beno extends Personagem {
    public Beno() {
        super("Beno", "La criatura", 150, geradorHabilidades());
    }

    private static Habilidade[] geradorHabilidades() {
        Habilidade[] habilidades = new Habilidade[4];
        String nome;
        String descricao;
        int energia;

        nome = "Olhar de la muerte";
        descricao = " Beno olha diretamente para os olhos de todos os inimigos, deixando todos eles com medo.  \n\t Stun(1).";
        energia = 1;
        habilidades[0] = new Habilidade(nome, descricao, energia, 2 + energia);

        nome = " Sorriso Contagiante";
        descricao = "Beno ri para seus aliados, tornando eles confiantes na vitória e recuperando eles mentalmente.  \n\t Cura para todos(50).";
        energia = 2;
        habilidades[1] = new Habilidade(nome, descricao, energia, 2 + energia);

        nome = "Maldição do Gato";
        descricao = "Beno amaldiçoa todos os oponentes em troca da vida de seu gato,causando dano a todos eles de forma equivalente à sua dor. \n\t Dano direto a todos(30).";
        energia = 3;
        habilidades[2] = new Habilidade(nome, descricao, energia, 2 + energia);

        nome = "Enfurecer";
        descricao = "Beno se enfurece e quer acabar com guerra de vez,fazendo com que um de seus adversários faça companhia com ele no inverno para o resto da vida . \n\t Dano direto(100).";
        energia = 5;
        habilidades[3] = new Habilidade(nome, descricao, energia, 2 + energia);

        return habilidades;
    }

    public boolean[][] verificarHabilidade(boolean[][] vivos, boolean[] invulneraveis, int idPersonagem,
            int idHabilidade) {
        if (idHabilidade == 0) {
            vivos = bloquearAliados(vivos);
        } else if (idHabilidade == 1) {
            vivos = bloquearAliados(vivos);
            vivos = permitirUsuario(vivos, idPersonagem);
        } else if (idHabilidade == 2) {
            vivos = bloquearAliados(vivos);
            vivos = bloquearInvulneraveis(vivos, invulneraveis);
        } else if (idHabilidade == 3) {
            vivos = bloquearAliados(vivos);
        }

        return vivos;
    }

    public void utilizarHabilidade(HabilidadePendente habilidade) {
        int idPersonagem = habilidade.getIdPersonagem();
        int idHabilidade = habilidade.getIdHabilidade();
        int idJogadorAlvo = habilidade.getIdJogadorAlvo();
        int idPersonagemAlvo = habilidade.getIdPersonagemAlvo();

        Jogador[] jogadores = Dados.partida.getJogadores();

        jogadores[0].getPersonagens().get(idPersonagem).getHabilidades()[idHabilidade].setCountdownAtual();

        if (idHabilidade == 0) {
            for (int i = 0; i < 3; i++) {
                if (jogadores[idJogadorAlvo].getPersonagens().get(i).getVida() > 0){
                    jogadores[idJogadorAlvo].getPersonagens().get(i).stunnar(1);
                }
            }
        }

        else if (idHabilidade == 1) {
            for (int i = 0; i < 3; i++) {
                if (jogadores[0].getPersonagens().get(i).getVida() > 0) {
                    jogadores[0].getPersonagens().get(i).curar(50);
                }
            }
        }

        else if (idHabilidade == 2) {
            for (int i = 0; i < 3; i++) {
                if (jogadores[idJogadorAlvo].getPersonagens().get(i).getVida() > 0 && jogadores[idJogadorAlvo].getPersonagens().get(i).getInvulneravel() == 0) {
                    jogadores[idJogadorAlvo].getPersonagens().get(i).danoDireto(30);
                }
            }
        }

        else if (idHabilidade == 3) {
            jogadores[idJogadorAlvo].getPersonagens().get(idPersonagemAlvo).danoDireto(100);
        }

        Dados.partida.setJogadores(jogadores);
    }
}
