package Entity.Personagens;

import Controller.Dados;
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
        descricao = "Reyna consome um orbe de alma, ficando intangível. \nIntangível(1).";
        energia = 1;
        habilidades[0] = new Habilidade(nome, descricao, energia, 2+energia);

        nome = "Devorar";
        descricao = "Reyna recupera pontos de vida para cada inimigo morto. \nCura (30 p/ morto).";
        energia = 2;
        habilidades[1] = new Habilidade(nome, descricao, energia, 2+energia);

        nome = "Olha Voraz";
        descricao = "Um olhar etereo e destrutível. \nDano(25); Stun(1); Múltiplos Alvos; Chance(85%).";
        energia = 3;
        habilidades[2] = new Habilidade(nome, descricao, energia, 2+energia);

        nome = "Imperatriz";
        descricao = "Entra em estado de frenesi, aumentando de forma drástica suas habilidades. \nTurnos(2).";
        energia = 3;
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
            vivos = bloquearInimigos(vivos);
            vivos = permitirUsuario(vivos, idPersonagem);
        }
        else if (idHabilidade == 2) {
            vivos = bloquearAliados(vivos);
            vivos = bloquearInvulneraveis(vivos, invulneraveis);
        }
        else if (idHabilidade == 3) {
            vivos = bloquearAliados(vivos);
            vivos = bloquearInimigos(vivos);
            vivos = permitirUsuario(vivos, idPersonagem);
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
                if (ult == 0) {
                    alvo.getPersonagens().get(i).ficarInvulneravel(1);
                }
                else {
                    alvo.getPersonagens().get(i).ficarInvulneravel(2);
                }
            }
        }

        if (idHabilidade == 1) {
            if (nomeJogador.equals(Dados.jogadorA.getNick())) {
                alvo = Dados.jogadorB;
            }
            else {
                alvo = Dados.jogadorA;
            }

            for (int i = 0; i < 3; i++) {
                if (alvo.getPersonagens().get(i).getVida() == 0) {
                    if (ult == 0) {
                        jogador.getPersonagens().get(idPersonagem).curar(30);
                    }
                    else {
                        jogador.getPersonagens().get(idPersonagem).curar(40);
                    }
                }
            }
        }

        else if (idHabilidade == 2) {
            for (int i = 0; i < 3; i++) {
                if (alvo.getPersonagens().get(i).getVida() > 0 && alvo.getPersonagens().get(i).getInvulneravel() == 0 && (random.chance(85) || ult > 0)) {
                    if (ult == 0) {
                        alvo.getPersonagens().get(i).dano(25);
                        alvo.getPersonagens().get(i).stunnar(1);
                    }
                    else {
                        alvo.getPersonagens().get(i).dano(40);
                        alvo.getPersonagens().get(i).stunnar(2);
                    }
                }
            }
        }

        else if (idHabilidade == 3) {
            for (int i = 0; i < 3; i++) {
                ult = 3; // um extra pq sai depois que passa o turno
                mudarDescricao();
            }
        }

        Jogador[] jogadores = {jogador, alvo};
        return jogadores;
    }

    public void mudarDescricao() {
        if (ult > 0) {
            getHabilidades()[0].setDescricao("Reyna consome um orbe de alma, ficando intangível. \nIntangível(2).");
            getHabilidades()[1].setDescricao("Reyna recupera pontos de vida para cada inimigo morto. \nCura (40 p/ morto).");
            getHabilidades()[2].setDescricao("Um olhar etereo e destrutível. \nDano(40); Stun(2); Múltiplos Alvos; Chance(100%).");

        }
        else {
            getHabilidades()[0].setDescricao("Reyna consome um orbe de alma, ficando intangível. \nIntangível(1).");
            getHabilidades()[1].setDescricao("Reyna recupera pontos de vida para cada inimigo morto. \nCura (30 p/ morto).");
            getHabilidades()[2].setDescricao("Um olhar etereo e destrutível. \nDano(25); Stun(1); Múltiplos Alvos; Chance(85%).");
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
