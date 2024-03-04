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
        descricao = "Viper dispara um emissor de gás nos inimigos, dando 20 pontos de dano.  (75% de chance de acerto)";
        energia = 1;
        habilidades[0] = new Habilidade(nome, descricao, energia, 2+energia);

        nome = "Cortina Tóxica";
        descricao = "Viper dispara uma longa linha de gás, dando 30 pontos de dano.  (90% de chance de acerto)";
        energia = 2;
        habilidades[1] = new Habilidade(nome, descricao, energia, 2+energia);

        nome = "Veneno de Cobra";
        descricao = "Viper lança um cilindro, que se rompe e de gera uma zona química, deixando inimigos stunnados e dando 30 pontos de dano.    (75% de chance de acerto)";
        energia = 3;
        habilidades[2] = new Habilidade(nome, descricao, energia, 2+energia);

        nome = "Poço Peçonhento";
        descricao = "Viper cria uma grande nuvem que reduz, ficando invulnerável por 2 turnos e aplicando 40 pontos de dano em um inimigo aleatório, ignorando invulnerabilidade";
        energia = 5;
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

        System.out.println("Fade ("+nomeJogador+") utilizou "+nomeHabilidade+".");

        if (idHabilidade == 0) {
            for (int i = 0; i < 3; i++) {
                if (alvo.getPersonagens().get(i).getVida() > 0 && alvo.getPersonagens().get(i).getInvulneravel() == 0 && random.chance(75)) {
                    alvo.getPersonagens().get(i).dano(20);
                    System.out.println("- "+alvo.getPersonagens().get(i).getNome()+" sofreu 20 pontos de dano.");
                }
            }
        }

        else if (idHabilidade == 1) {
            for (int i = 0; i < 3; i++) {
                if (alvo.getPersonagens().get(i).getVida() > 0 && alvo.getPersonagens().get(i).getInvulneravel() == 0 && random.chance(75)) {
                    alvo.getPersonagens().get(i).dano(20);
                    System.out.println("- "+alvo.getPersonagens().get(i).getNome()+" sofreu 30 pontos de dano.");
                }
            }
        }

        else if (idHabilidade == 2) {
            boolean ativador = true;

            boolean[] opcoes = {
                alvo.getPersonagens().get(0).getVida() > 0 && alvo.getPersonagens().get(0).getInvulneravel() == 0,
                alvo.getPersonagens().get(1).getVida() > 0 && alvo.getPersonagens().get(1).getInvulneravel() == 0,
                alvo.getPersonagens().get(2).getVida() > 0 && alvo.getPersonagens().get(2).getInvulneravel() == 0
            };

            if (opcoes[0] || opcoes[1] || opcoes[2]) {
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

        }

        else if (idHabilidade == 3) {
            boolean ativador = true;

            Integer[] lista = {0, 1, 2};
            while (ativador) {
                int valor = random.choice(lista);

                if (alvo.getPersonagens().get(valor).getVida() > 0) {
                    alvo.getPersonagens().get(valor).dano(40);
                    jogador.getPersonagens().get(idPersonagem).ficarInvulneravel(2);

                    System.out.println("- "+alvo.getPersonagens().get(valor).getNome()+" tomou 40 pontos de dano.");
                    System.out.println("- "+jogador.getPersonagens().get(idPersonagem).getNome()+" ficou invulnerável por 2 turnos.");
                    ativador = false;
                }
            }

        }

        Jogador[] jogadores = {jogador, alvo};
        return jogadores;
    }
}
