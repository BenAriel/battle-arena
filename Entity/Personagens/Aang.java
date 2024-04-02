package Entity.Personagens;

import Controller.Dados;
import Entity.HabilidadePendente;
import Entity.Personagem;
import Entity.Habilidade;
import Entity.Jogador;

public class Aang extends Personagem {
    public Aang() {
        super("Aang", "Último Mestre do Ar", 100, geradorHabilidades());
    }

    public Aang(int vida) {
        super("Aang", "Último Mestre do Ar", vida, geradorHabilidades());
        getHabilidades()[3].setCountdownAtual();
    }

    private static Habilidade[] geradorHabilidades() {
        Habilidade[] habilidades = new Habilidade[4];
        String nome;
        String descricao;
        int energia;

        nome = "Ciclone";
        descricao = "Aang dispara um ciclone de ar em um inimigo..    \n\t Dano(25).";
        energia = 1;
        habilidades[0] = new Habilidade(nome, descricao, energia, 2+energia);

        nome = "Coragem";
        descricao = "Garen se inspira, ganhando motivação para continuar na batalha.  \n\t Defesa(50).";
        energia = 2;
        habilidades[1] = new Habilidade(nome, descricao, energia, 2+energia);

        nome = "Modo Avatar";
        descricao = "Aang utiliza os poderes de todos os elementos em um ataque. \n\t Dano(35); Dano Múltiplo.";
        energia = 4;
        habilidades[2] = new Habilidade(nome, descricao, energia, 2+energia);

        nome = "Dobra de Energia";
        descricao = "Aang remove completamente os poderes do alvo, o impossibilitando de lutar. \n\t Dano Coletaral(25); Stun(4)";
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
            vivos = bloquearAliados(vivos);
            vivos = bloquearInvulneraveis(vivos, invulneraveis);
        }
        else if (idHabilidade == 2) {
            vivos = bloquearAliados(vivos);
            vivos = bloquearInvulneraveis(vivos, invulneraveis);
        }
        else if (idHabilidade == 3) {
            vivos = bloquearAliados(vivos);
            vivos = bloquearInvulneraveis(vivos, invulneraveis);
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
            jogadores[idJogadorAlvo].getPersonagens().get(idPersonagemAlvo).danoDireto(20);
        }

        else if (idHabilidade == 1) {
            jogadores[0].getPersonagens().get(idPersonagem).defender(50);
        }


        else if (idHabilidade == 2) {
            for (int i = 0; i < 3; i++) {
                if (jogadores[idJogadorAlvo].getPersonagens().get(i).getVida() > 0 && jogadores[idJogadorAlvo].getPersonagens().get(i).getInvulneravel() == 0) {
                    jogadores[idJogadorAlvo].getPersonagens().get(i).dano(35);
                }
            }
        }

        else if (idHabilidade == 3) {
            jogadores[idJogadorAlvo].getPersonagens().get(idPersonagemAlvo).stunnar(5);

            jogadores[0].getPersonagens().get(idPersonagem).danoDireto(25);
        }

        Dados.partida.setJogadores(jogadores);
    }
}
