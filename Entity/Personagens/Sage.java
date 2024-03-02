package Entity.Personagens;

import Entity.Personagem;
import Entity.Habilidade;
import Entity.Jogador;

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
        descricao = "Com um Orbe Curativo, Sage recupera 25 pontos de vida de um aliado.";
        energia = 1;
        habilidades[0] = new Habilidade(nome, descricao, energia, 2+energia);

        nome = "Orbe de Lentidão";
        descricao = "Sage joga um Orbe de Lentidão nos inimigos, ficando stunados por 1 turno e tomando 15 pontos de dano. Cada inimigo têm 50% de chance de ser atingido.";
        energia = 2;
        habilidades[1] = new Habilidade(nome, descricao, energia, 2+energia);

        nome = "Orbe de Barreira";
        descricao = "Com um Orbe de Barreira, Sage cria uma barreira, ficando invulnerável por 1 turno. Cada aliado tem 10% de chance de ser protegido pela barreira.";
        energia = 2;
        habilidades[2] = new Habilidade(nome, descricao, energia, 2+energia);

        nome = "Ressurreição";
        descricao = "Sage usa uma habilidade de ressurreição em um aliado, que retorna com 40 pontos de vida.";
        energia = 5;
        habilidades[3] = new Habilidade(nome, descricao, energia, 2+energia);

        return habilidades;
    }

    public boolean[][] verificarHabilidade(boolean[][] vivos, int idPersonagem, int idHabilidade) {
        if (idHabilidade == 0) {
            vivos = bloquearInimigos(vivos);
        }
        else if (idHabilidade == 1) {
            vivos = bloquearAliados(vivos);
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

    public Jogador[] utilizarHabilidade(Jogador jogador, Jogador alvo, int idHabilidade, int idPersonagemAlvo) {
        if (idHabilidade == 0) {
            jogador.getPersonagens().get(idPersonagemAlvo).curar(25);
        }
        else if (idHabilidade == 3) {
            jogador.getPersonagens().get(idPersonagemAlvo).ressuscitar(40);
        }

        Jogador[] jogadores = {jogador, alvo};
        return jogadores;
    }
}
