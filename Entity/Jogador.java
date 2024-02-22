package Entity;

import java.util.ArrayList;
import java.util.List;

public class Jogador {
    private String nick;
    private int vida;
    private List<Personagem> personagens;
    private Personagem[] personagemUsado = new Personagem[3];
    public Jogador(String nick, List<Personagem> personagens, Personagem[] personagemUsado) {
        setNick(nick);
        setVida(vida);
        setPersonagens(personagens);
        setPersonagemUsado(personagemUsado);
    }
    public int getVida() {
        return vida;
    }
    public String getNick() {
        return nick;
    }
    public Personagem[] getPersonagemUsado() {
        return personagemUsado;
    }
    public List<Personagem> getPersonagens() {
        return personagens;
    }
    public void setNick(String nick) {
        if(!nick.isEmpty() && nick.length() > 3) {
            this.nick = nick;
        }
    }
    public void setVida(int vida) {
        this.vida = 100;
    }
    public void setPersonagemUsado(Personagem[] personagemUsado) {
        if(personagemUsado.length == 3) {
            if(personagemUsado[0] != null && personagemUsado[1] != null && personagemUsado[2] != null)
            {

            this.personagemUsado = personagemUsado;
            }
        }
    }
    public void setPersonagens(List<Personagem> personagens) {
        this.personagens= new ArrayList<Personagem>();
        this.personagens = personagens;
    }
    public void mudarPersonagensLuta(Personagem[] novosPersonagens) {
        if (novosPersonagens.length == 3) {
            for(int i = 0; i < 3; i++) {
                personagemUsado[i] = novosPersonagens[i];
            }
        }
    }
}
