package Entity.Personagens;

import Controller.Dados;
import Entity.HabilidadePendente;
import Entity.Personagem;
import Entity.Habilidade;
import Entity.Jogador;
import Resources.Aleatory;

public class Phoenix extends Personagem {
    private int ult = 0;
    public Phoenix() {
        super("Phoenix", "Poder Estelar", 100, geradorHabilidades());
    }

    private static Habilidade[] geradorHabilidades() {
        Habilidade[] habilidades = new Habilidade[4];
        String nome;
        String descricao;
        int energia;

        nome = "Bola Curva";
        descricao = "Phoenix arremessa uma esféra de luz, que afeta a visão dos inimigos.    \n\t Stun(1); Chance de Fogo Amigo(20%); Múltiplos Alvos.";
        energia = 1;
        habilidades[0] = new Habilidade(nome, descricao, energia, 2+energia);

        nome = "Mãos Quentes";
        descricao = "Phoenix arremessa uma bola de fogo no chão, acertando inimigos e se curando.    \n\t Cura(15); Dano(15); Chance de Fogo Amigo(20%); Múltiplos Alvos.";
        energia = 2;
        habilidades[1] = new Habilidade(nome, descricao, energia, 2+energia);

        nome = "Labareda";
        descricao = "Phoenix cria uma barreira de fogo, stunnado a si e aos inimigos e se curando. \n\tCura(30); Auto-stun(1); Dano(25); Chance de Fogo Amigo(20%); Stun(1); Múltiplos Alvos.";
        energia = 3;
        habilidades[2] = new Habilidade(nome, descricao, energia, 2+energia);

        nome = "Renascimento";
        descricao = "Morrer no próximo turno trará Phoenix de volta em seguida. \n\tVida(100).";
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
            vivos = permitirUsuario(vivos, idPersonagem);
        }
        else if (idHabilidade == 2) {
            vivos = bloquearAliados(vivos);
            vivos = permitirUsuario(vivos, idPersonagem);
        }
        else if (idHabilidade == 3) {
            vivos = bloquearInimigos(vivos);
            vivos = bloquearAliados(vivos);
            vivos = permitirUsuario(vivos, idPersonagem);
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
                jogadores[idJogadorAlvo].getPersonagens().get(i).stunnar(1);

                if (i != idPersonagem && random.chance(20)) {
                    jogadores[0].getPersonagens().get(i).stunnar(1);
                }
            }
        }

        else if (idHabilidade == 1) {
            jogadores[0].getPersonagens().get(idPersonagem).curar(15);

            for (int i = 0; i < 3; i++) {
                jogadores[idJogadorAlvo].getPersonagens().get(i).dano(15);

                if (i != idPersonagem && random.chance(20)) {
                    jogadores[0].getPersonagens().get(i).dano(15);
                }
            }
        }

        else if (idHabilidade == 2) {
            jogadores[0].getPersonagens().get(idPersonagem).curar(30);
            jogadores[0].getPersonagens().get(idPersonagem).stunnar(1);

            for (int i = 0; i < 3; i++) {
                jogadores[idJogadorAlvo].getPersonagens().get(i).dano(25);
                jogadores[idJogadorAlvo].getPersonagens().get(i).stunnar(1);

                if (i != idPersonagem && random.chance(20)) {
                    jogadores[0].getPersonagens().get(i).dano(25);
                }
            }
        }

        else if (idHabilidade == 3) {
            ult = 2; // extra por enquanto
        }

        Dados.partida.setJogadores(jogadores);
    }

    public void dano(int dano) {
        super.dano(dano);

        if (ult > 0 && getVida() == 0) {
            setVida(100);
        }
    }

    public void meuTurno() {
        super.meuTurno();
    }

    public void passarTurno() {
        if (ult > 0) {
            ult--;
        }

        super.passarTurno();
    }


}
