package Entity.Personagens;

import Entity.Personagem;
import Entity.Habilidade;
import Entity.Jogador;
import Resources.Aleatory;

public class Ivern extends Personagem {
    public Ivern() {
        super("Ivern", "O pai do verde", 100, geradorHabilidades());
    }

    private static Habilidade[] geradorHabilidades() {
        Habilidade[] habilidades = new Habilidade[4];
        String nome;
        String descricao;
        int energia;

        nome = "Encantador de Raízes";
        descricao = "Ivern conjura uma vinha, causando dano e enraizando o alvos inimigo.    \n\t dano(15); stun(1).";
        energia = 1;
        habilidades[0] = new Habilidade(nome, descricao, energia, 2+energia);

        nome = "Formação de Arbustos";
        descricao = "Ivern cria arbustos,e ele e os aliados ficam ivulneráveis dentro. chance de alidados ficarem(50%)";
        energia = 2;
        habilidades[1] = new Habilidade(nome, descricao, energia, 2+energia);

        nome = "Semente Engatilhada";
        descricao = " ivern entrelaça um aliado com plantas e o cura. cura(30)";
        energia = 2;
        habilidades[2] = new Habilidade(nome, descricao, energia, 2+energia);

        nome = "MARGARIDA!";
        descricao = "Ivern invoca a margarida, que bate no chão e causa dano a todos os inimigos. Além disso, ela o defende pelos próximos ataques . \n\t Dano(20); Defesa(40).";
        energia = 5;
        habilidades[3] = new Habilidade(nome, descricao, energia, 2+energia);

        return habilidades;
    }

    public boolean[][] verificarHabilidade(boolean[][] vivos, boolean[] invulneraveis, int idPersonagem, int idHabilidade) {
        if (idHabilidade == 0) {
            vivos = bloquearAliados(vivos);
        }
        else if (idHabilidade == 1) {
            vivos = bloquearInimigos(vivos);
            vivos = bloquearAliados(vivos);
            vivos = permitirUsuario(vivos, idPersonagem);
        }
        else if (idHabilidade == 2) {
            vivos = bloquearInimigos(vivos);
        }
        else if (idHabilidade == 3) {
            vivos = bloquearAliados(vivos);
        }

        return vivos;
    }

    public Jogador[] utilizarHabilidade(Jogador jogador, Jogador alvo, int idHabilidade, int idPersonagem, int idPersonagemAlvo) {
        Aleatory<Integer> random = new Aleatory<>();
        String nomeJogador = jogador.getNick();
        String nomeHabilidade = getHabilidades()[idHabilidade].getNome();

        jogador.getPersonagens().get(idPersonagem).getHabilidades()[idHabilidade].setCountdownAtual();

        System.out.println("Sage ("+nomeJogador+") utilizou "+nomeHabilidade+".");

        if (idHabilidade == 0) {
            alvo.getPersonagens().get(idPersonagemAlvo).dano(15);
            alvo.getPersonagens().get(idPersonagemAlvo).stunnar(1);
                System.out.print( alvo.getPersonagens().get(idPersonagemAlvo).getNome() + "Sofreu 15 de dano de ivern e foi stunado por 1 turno ");
        }

        else if (idHabilidade == 1) {
            jogador.getPersonagens().get(idPersonagemAlvo).ficarInvulneravel(1);
            System.out.println("- "+jogador.getPersonagens().get(idPersonagemAlvo).getNome()+" ficará invulnerável por 1 turno.");
            for (int i = 0; i < 3; i++) {
                if (jogador.getPersonagens().get(i).getVida() > 0 && i != idPersonagemAlvo && random.chance(50)) { 
                    jogador.getPersonagens().get(i).ficarInvulneravel(1);
                    System.out.println("- "+jogador.getPersonagens().get(i).getNome()+" ficará invulnerável por 1 turno.");
                }
            }
        }

        else if (idHabilidade == 2) {
            jogador.getPersonagens().get(idPersonagemAlvo).curar(30);
            System.out.println("- "+jogador.getPersonagens().get(idPersonagemAlvo).getNome()+" foi curado(a) em 30 por Ivern.");
            
        }

        else if (idHabilidade == 3) {
            for (int i = 0; i < 3; i++) {
                if (alvo.getPersonagens().get(i).getVida() > 0 && alvo.getPersonagens().get(i).getInvulneravel() == 0){
                    alvo.getPersonagens().get(i).dano(20);
                    System.out.println("- "+alvo.getPersonagens().get(i).getNome()+" sofreu 20 de dano de Ivern.");
                }

                jogador.getPersonagens().get(idPersonagem).defender(40);
                System.out.println("- Ivern está defendendo 40 de dano.");
            }
        }

        Jogador[] jogadores = {jogador, alvo};
        return jogadores;
    }
}

