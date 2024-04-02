package Entity.Personagens;

import Controller.Dados;
import Entity.HabilidadePendente;
import Entity.Personagem;
import Entity.Habilidade;
import Entity.Jogador;
import Resources.Aleatory;

public class Reyna extends Personagem {
    private int ult = 0;
    public Reyna() {
        super("Reyna", "Caçadora de Recompensas", 100, geradorHabilidades());
    }

    private static Habilidade[] geradorHabilidades() {
        Habilidade[] habilidades = new Habilidade[4];
        String nome;
        String descricao;
        int energia;

        nome = "Dispensar";
        descricao = "";
        energia = 0;
        habilidades[0] = new Habilidade(nome, descricao, energia, 2+energia);

        nome = "Olhar Voraz";
        descricao = "";
        energia = 0;
        habilidades[1] = new Habilidade(nome, descricao, energia, 2+energia);

        nome = "Imperatriz";
        descricao = "Entra em estado de Frenesi, aumentando de forma drástica suas habilidades. \nTurnos (1).";
        energia = 1;
        habilidades[2] = new Habilidade(nome, descricao, energia, 3+energia);

        nome = "Devorar";
        descricao = "";
        energia = 0;
        habilidades[3] = new Habilidade(nome, descricao, energia, 2+energia);

        return habilidades;
    }

    public boolean[][] verificarHabilidade(boolean[][] vivos, boolean[] invulneraveis, int idPersonagem, int idHabilidade) {
        if (idHabilidade == 0) {
            vivos = bloquearAliados(vivos);
            vivos = bloquearInimigos(vivos);
            vivos = permitirUsuario(vivos, idPersonagem);
        }
        else if (idHabilidade == 1) {
            vivos = bloquearAliados(vivos);
            vivos = bloquearInvulneraveis(vivos, invulneraveis);
        }
        else if (idHabilidade == 2) {
            vivos = bloquearAliados(vivos);
            vivos = bloquearInimigos(vivos);
            vivos = permitirUsuario(vivos, idPersonagem);
        }
        else if (idHabilidade == 3) {
            vivos = bloquearAliados(vivos);
            vivos = bloquearInimigos(vivos);
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
            jogadores[0].getPersonagens().get(idPersonagemAlvo).ficarInvulneravel(1);
        }

        else if (idHabilidade == 1) {
            for (int i = 0; i < 3; i++) {
                if (jogadores[1].getPersonagens().get(i).getVida() > 0 && jogadores[1].getPersonagens().get(i).getInvulneravel() == 0 && (random.chance(85) || ult > 0)) {
                    if (ult == 0) {
                        jogadores[1].getPersonagens().get(i).dano(25);
                    }
                    else {
                        jogadores[1].getPersonagens().get(i).dano(35);
                    }
                    jogadores[1].getPersonagens().get(i).stunnar(1);
                }
            }
        }

        else if (idHabilidade == 2) {
            for (int i = 0; i < 3; i++) {
                ult = 2; // um extra pq sai depois que passa o turno
                mudarDescricao();
            }
        }

        if (idHabilidade == 3) {
            for (int i = 0; i < 3; i++) {
                if (jogadores[1].getPersonagens().get(i).getVida() == 0) {
                    if (ult == 0) {
                        jogadores[0].getPersonagens().get(idPersonagem).curar(15);
                    }
                    else {
                        jogadores[0].getPersonagens().get(idPersonagem).curar(25);
                    }
                }
            }
        }

        Dados.partida.setJogadores(jogadores);
    }

    public void mudarDescricao() {
        if (ult > 0) {
            getHabilidades()[0].setDescricao("Reyna consome um orbe de alma, ficando intangível. \nIntangível(1).");
            getHabilidades()[0].setEnergia(0);
            getHabilidades()[1].setDescricao("Reyna usa Um olhar etereo e destrutível. \nDano(35); Stun(1); Múltiplos Alvos; Chance(66%).");
            getHabilidades()[1].setEnergia(2);
            getHabilidades()[3].setDescricao("Reyna recupera pontos de vida para cada inimigo morto. \nCura (25 p/ morto).");
            getHabilidades()[3].setEnergia(2);


        }
        else if (ult == 0) {
            getHabilidades()[0].setDescricao("Reyna consome um orbe de alma, ficando intangível. \nIntangível(1).");
            getHabilidades()[0].setEnergia(1);
            getHabilidades()[1].setDescricao("Reyna utiliza um olhar etereo e destrutivo. \nDano(25); Stun(1); Múltiplos Alvos; Chance(66%).");
            getHabilidades()[1].setEnergia(2);
            getHabilidades()[3].setDescricao("Reyna recupera pontos de vida para cada inimigo morto. \nCura (15 p/ morto).");
            getHabilidades()[3].setEnergia(2);

            for (int i = 0; i < 4; i++) {
                getHabilidades()[i].setCountdown(getHabilidades()[i].getEnergia() + 3);
            }
        }
    }

    @Override
    public void meuTurno() {
        super.meuTurno();

        if (getTurno() == 1) {
            mudarDescricao();
        }
    }

    @Override
    public void passarTurno() {
        super.passarTurno();

        if (ult == 1) {
            ult--;
            mudarDescricao();
        }
        else if (ult > 0) {
            ult--;
        }
    }
}
