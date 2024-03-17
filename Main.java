import java.util.ArrayList;
import Entity.Jogador;
import Entity.Partida;
import Entity.Personagem;
import Entity.Personagens.Fade;
import Entity.Personagens.Garen;
import Entity.Personagens.Sage;
import Entity.Personagens.Viper;

public class Main {
    public static void main(String[] args) {
        //launch(args);
       

        Jogador[] jogadores = new Jogador[2];

        ArrayList<Personagem> personagensA = new ArrayList<>();
        personagensA.add(new Fade());
        personagensA.add(new Sage());
        personagensA.add(new Garen());
        jogadores[0] = new Jogador ("Ariel", personagensA);

        ArrayList<Personagem> personagensB = new ArrayList<>();
        personagensB.add(new Viper());
        personagensB.add(new Sage());
        personagensB.add(new Fade());
        jogadores[1] = new Jogador ("Lucas", personagensB);

        Partida partidaA = new Partida(jogadores);
        partidaA.turno();
    }

}