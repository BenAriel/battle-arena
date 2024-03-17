package Entity.Personagens;

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
        descricao = "Garen corre até o inimigo e o acerta com a espada,ignorando qualquer proteção.    \n\t dano(20).";
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
        }
        else if (idHabilidade == 1) {
            vivos = bloquearAliados(vivos);
            vivos = bloquearInimigos(vivos);
            vivos = permitirUsuario(vivos, idPersonagem);
        }
        else if (idHabilidade == 2) {
            vivos = bloquearAliados(vivos);
        }
        else if (idHabilidade == 3) {
            vivos = bloquearAliados(vivos);
        }

        return vivos;
    }

    public Jogador[] utilizarHabilidade(Jogador jogador, Jogador alvo, int idHabilidade, int idPersonagem, int idPersonagemAlvo) {
        String nomeJogador = jogador.getNick();
        String nomeHabilidade = getHabilidades()[idHabilidade].getNome();

        jogador.getPersonagens().get(idPersonagem).getHabilidades()[idHabilidade].setCountdownAtual();

        System.out.println("Garen ("+nomeJogador+") utilizou "+nomeHabilidade+".");

        if (idHabilidade == 0) {
            
                System.out.print("- "+alvo.getPersonagens().get(idPersonagemAlvo).getNome()+" recebeu 20 pontos de dano de Garen ("+alvo.getPersonagens().get(idPersonagemAlvo).getVida());
            alvo.getPersonagens().get(idPersonagemAlvo).danoDireto(20);
            System.out.println(" -> "+alvo.getPersonagens().get(idPersonagemAlvo).getVida()+").");
        }

        else if (idHabilidade == 1) {

            jogador.getPersonagens().get(idPersonagem).defender(50);
                    System.out.println("Garen se defendeu.");
                }


        else if (idHabilidade == 2) {
            for (int i = 0; i < 3; i++) {
                if (alvo.getPersonagens().get(i).getVida() > 0 && alvo.getPersonagens().get(i).getInvulneravel() == 0) { 
                    alvo.getPersonagens().get(i).dano(25);
                    System.out.println("- "+alvo.getPersonagens().get(i).getNome()+" sofreu 25 de dano.");
                }
            }
        }

        else if (idHabilidade == 3) {
            if(alvo.getPersonagens().get(idPersonagemAlvo).getVida() <= 40){
                alvo.getPersonagens().get(idPersonagemAlvo).danoDireto(40);
                System.out.println("- "+alvo.getPersonagens().get(idPersonagemAlvo).getNome()+"   foi executado por Garen.");
            }
            else{
                alvo.getPersonagens().get(idPersonagemAlvo).dano(20);
                System.out.println("- "+alvo.getPersonagens().get(idPersonagemAlvo).getNome()+" sofreu 20 de dano.");
            }
        }

        Jogador[] jogadores = {jogador, alvo};
        return jogadores;
    }
}
