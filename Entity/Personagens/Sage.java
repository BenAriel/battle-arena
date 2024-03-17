package Entity.Personagens;

import Entity.Personagem;
import Entity.Habilidade;
import Entity.Jogador;
import Resources.Aleatory;

public class Sage extends Personagem {
    public Sage() {
        super("Sage", "Fortaleza Chinesa", 100, geradorHabilidades());
    }

    private static Habilidade[] geradorHabilidades() {
        Habilidade[] habilidades = new Habilidade[4];
        String nome;
        String descricao;
        int energia;

        nome = "Orbe Curativo";
        descricao = "Sage usa um orbe curativo em um aliado.    \nCura(25).";
        energia = 1;
        habilidades[0] = new Habilidade(nome, descricao, energia, 2+energia);

        nome = "Orbe de Lentidão";
        descricao = "Sage joga um Orbe de Lentidão nos inimigos.    \nDano(15); Stun(1); Múltiplos Alvos; Chance(50%).";
        energia = 2;
        habilidades[1] = new Habilidade(nome, descricao, energia, 2+energia);

        nome = "Orbe de Barreira";
        descricao = "Sage usa uma Orbe de Barreira, protegendo a si e podendo proteger aliados. \nInvulnerável(1); Chance(20%).";
        energia = 2;
        habilidades[2] = new Habilidade(nome, descricao, energia, 2+energia);

        nome = "Ressurreição";
        descricao = "Sage usa uma habilidade de ressurreição em um aliado. \nVida(40).";
        energia = 5;
        habilidades[3] = new Habilidade(nome, descricao, energia, 2+energia);

        return habilidades;
    }

    public boolean[][] verificarHabilidade(boolean[][] vivos, boolean[] invulneraveis, int idPersonagem, int idHabilidade) {
        if (idHabilidade == 0) {
            vivos = bloquearInimigos(vivos);
        }
        else if (idHabilidade == 1) {
            vivos = bloquearAliados(vivos);
            vivos = bloquearInvulneraveis(vivos, invulneraveis);
        }
        else if (idHabilidade == 2) {
            vivos = bloquearInimigos(vivos);
            vivos = bloquearAliados(vivos);
            vivos = permitirUsuario(vivos, idPersonagem);
        }
        else if (idHabilidade == 3) {
            vivos = inverterAliados(vivos); // disponível apenas para mortos
            vivos = bloquearInimigos(vivos);
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
            if (idPersonagem == idPersonagemAlvo) {
                System.out.print("Sage se curou ("+jogador.getPersonagens().get(idPersonagemAlvo).getVida());
            }
            else {
                System.out.print("- "+jogador.getPersonagens().get(idPersonagemAlvo).getNome()+" recebeu 25 pontos de cura de Sage ("+jogador.getPersonagens().get(idPersonagemAlvo).getVida());
            }
            jogador.getPersonagens().get(idPersonagemAlvo).curar(25);
            System.out.println(" -> "+jogador.getPersonagens().get(idPersonagemAlvo).getVida()+").");
        }

        else if (idHabilidade == 1) {
            for (int i = 0; i < 3; i++) {
                if (alvo.getPersonagens().get(i).getVida() > 0 && alvo.getPersonagens().get(i).getInvulneravel() == 0 && random.chance(50)) { // se o personagem estiver vivo, executa em 50% dos casos
                    alvo.getPersonagens().get(i).dano(15);
                    alvo.getPersonagens().get(i).stunnar(1);
                    System.out.println("- "+alvo.getPersonagens().get(i).getNome()+" sofreu 15 pontos de dano e foi atordoado(a).");
                }
            }
        }

        else if (idHabilidade == 2) {
            jogador.getPersonagens().get(idPersonagemAlvo).ficarInvulneravel(1);
            System.out.println("- "+jogador.getPersonagens().get(idPersonagemAlvo).getNome()+" ficará invulnerável por 1 turno.");
            for (int i = 0; i < 3; i++) {
                if (jogador.getPersonagens().get(i).getVida() > 0 && i != idPersonagemAlvo && random.chance(10)) { // chance de 10$ em aliados
                    jogador.getPersonagens().get(i).ficarInvulneravel(1);
                    System.out.println("- "+jogador.getPersonagens().get(i).getNome()+" ficará invulnerável por 1 turno.");
                }
            }
        }

        else if (idHabilidade == 3) {
            jogador.getPersonagens().get(idPersonagemAlvo).ressuscitar(40);
            System.out.println("- "+jogador.getPersonagens().get(idPersonagemAlvo).getNome()+" foi ressucitado(a) por Sage.");
        }

        Jogador[] jogadores = {jogador, alvo};
        return jogadores;
    }
}
