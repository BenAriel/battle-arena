package Entity.Personagens;

import Entity.Personagem;
import Entity.Habilidade;
import Entity.Jogador;
import Resources.Aleatory;

public class Fade extends Personagem {
    public Fade() {
        super("Fade", "Caçadora de Recompensas", 100, geradorHabilidades());
    }

    private static Habilidade[] geradorHabilidades() {
        Habilidade[] habilidades = new Habilidade[4];
        String nome;
        String descricao;
        int energia;

        nome = "Claudura";
        descricao = "Com um nódulo de puro temor, Fade stunna um inimigo por 1 turno e diminui 15 pontos de sua vida.";
        energia = 1;
        habilidades[0] = new Habilidade(nome, descricao, energia, 2+energia);

        nome = "Assombrar";
        descricao = "Fade dispara uma assombração. Cada inimigo atingido sofre 30 pontos de dano.   (40% de chance de acerto)";
        energia = 3;
        habilidades[1] = new Habilidade(nome, descricao, energia, 2+energia);

        nome = "Espreitador";
        descricao = "Fade envia um Espreitador, atordoando um inimigo aleatório. O inimigo perderá 10 pontos de vida.";
        energia = 1;
        habilidades[2] = new Habilidade(nome, descricao, energia, 2+energia);

        nome = "Véu da Noite";
        descricao = "Fade dispara o poder dos pesadelo. Inimigos atingidos ficarão atordoados por 3 turnos e sofrerão 30 pontos de dano não-defensável. (40% de chance de acerto)";
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

        System.out.println("Fade ("+nomeJogador+") utilizou "+nomeHabilidade+".");

        if (idHabilidade == 0) {
            alvo.getPersonagens().get(idPersonagemAlvo).dano(15);
            alvo.getPersonagens().get(idPersonagemAlvo).stunnar(1);
            System.out.println("- "+alvo.getPersonagens().get(idPersonagemAlvo).getNome()+" tomou 15 pontos de dano e foi stunnado.");
        }

        else if (idHabilidade == 1) {
            for (int i = 0; i < 3; i++) {
                if (alvo.getPersonagens().get(i).getVida() > 0 && random.chance(80)) {
                    alvo.getPersonagens().get(i).dano(30);
                    System.out.println("- "+alvo.getPersonagens().get(i).getNome()+" tomou 30 pontos de dano.");
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

                        System.out.println("- "+alvo.getPersonagens().get(valor).getNome()+" tomou 10 pontos de dano e ficou atordoado.");
                        ativador = false;
                    }
                }

            }

        }

        else if (idHabilidade == 3) {
            for (int i = 0; i < 3; i++) {
                if (alvo.getPersonagens().get(i).getVida() > 0 && random.chance(40)) {
                    alvo.getPersonagens().get(i).dano(30);
                    System.out.println("- "+alvo.getPersonagens().get(i).getNome()+" tomou 30 pontos de dano.");
                }
            }
        }

        Jogador[] jogadores = {jogador, alvo};
        return jogadores;
    }
}
