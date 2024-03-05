package Entity.Personagens;

import Entity.Personagem;
import Entity.Habilidade;
import Entity.Jogador;
import Resources.Aleatory;

public class Viper extends Personagem {
    public Viper() {
        super("Viper", "Caçadora de Recompensas", 100, geradorHabilidades());
    }

    private static Habilidade[] geradorHabilidades() {
        Habilidade[] habilidades = new Habilidade[4];
        String nome;
        String descricao;
        int energia;

        nome = "Nuvem Venenosa";
        descricao = "Viper dispara um emissor de gás nos inimigos. \n\t Dano(20); Múltiplos Alvos; Chance(50%).";
        energia = 1;
        habilidades[0] = new Habilidade(nome, descricao, energia, 2+energia);

        nome = "Cortina Tóxica";
        descricao = "Viper dispara uma longa linha de gás. \n\t Dano(30); Múltiplos Alvos; Chance(50%)";
        energia = 2;
        habilidades[1] = new Habilidade(nome, descricao, energia, 2+energia);

        nome = "Veneno de Cobra";
        descricao = "Viper lança um cilindro, que se rompe e de gera uma zona química. \n\t Dano(40); Stun(1); Dano Aleatório.";
        energia = 3;
        habilidades[2] = new Habilidade(nome, descricao, energia, 2+energia);

        nome = "Poço Peçonhento";
        descricao = "Viper cria uma grande nuvem que reduz a vida dos inimigos. \n\t Dano; (40); Invulnerável(2); Ignora Defesa; Dano Aleatório.";
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
        }

        return vivos;
    }

    public Jogador[] utilizarHabilidade(Jogador jogador, Jogador alvo, int idHabilidade, int idPersonagem, int idPersonagemAlvo) {
        Aleatory<Integer> random = new Aleatory<>();
        String nomeJogador = jogador.getNick();
        String nomeHabilidade = getHabilidades()[idHabilidade].getNome();

        jogador.getPersonagens().get(idPersonagem).getHabilidades()[idHabilidade].setCountdownAtual();

        System.out.println("Fade ("+nomeJogador+") utilizou "+nomeHabilidade+".");

        if (idHabilidade == 0) {
            for (int i = 0; i < 3; i++) {
                if (alvo.getPersonagens().get(i).getVida() > 0 && alvo.getPersonagens().get(i).getInvulneravel() == 0 && random.chance(50)) {
                    alvo.getPersonagens().get(i).dano(20);
                    System.out.println("- "+alvo.getPersonagens().get(i).getNome()+" sofreu 20 pontos de dano.");
                }
            }
        }

        else if (idHabilidade == 1) {
            for (int i = 0; i < 3; i++) {
                if (alvo.getPersonagens().get(i).getVida() > 0 && alvo.getPersonagens().get(i).getInvulneravel() == 0 && random.chance(50)) {
                    alvo.getPersonagens().get(i).dano(30);
                    System.out.println("- "+alvo.getPersonagens().get(i).getNome()+" sofreu 30 pontos de dano.");
                }
            }
        }

        else if (idHabilidade == 2) {
            boolean ativador = true;
            
            Integer[] lista = {0, 1, 2};
            while (ativador) {
                int valor = random.choice(lista);

                if (alvo.getPersonagens().get(valor).getVida() > 0 && alvo.getPersonagens().get(valor).getInvulneravel() == 0) {
                    alvo.getPersonagens().get(valor).dano(10);
                    alvo.getPersonagens().get(valor).stunnar(1);

                    System.out.println("- "+alvo.getPersonagens().get(valor).getNome()+" tomou 30 pontos de dano e ficou atordoado.");
                    ativador = false;
                }
            }

        }

        else if (idHabilidade == 3) {
            boolean ativador = true;

            Integer[] lista = {0, 1, 2};
            while (ativador) {
                int valor = random.choice(lista);

                if (alvo.getPersonagens().get(valor).getVida() > 0) {
                    alvo.getPersonagens().get(valor).danoDireto(40);
                    jogador.getPersonagens().get(idPersonagem).ficarInvulneravel(2);

                    System.out.println("- "+alvo.getPersonagens().get(valor).getNome()+" tomou 40 pontos de dano.");
                    System.out.println("- Viper ficou invulnerável por 2 turnos.");
                    ativador = false;
                }
            }

        }

        Jogador[] jogadores = {jogador, alvo};
        return jogadores;
    }
}
