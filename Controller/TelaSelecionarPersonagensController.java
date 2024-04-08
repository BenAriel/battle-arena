package Controller;

import javafx.fxml.FXML;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import Implementacoes.Classes.*;
import view.Telas;


public class TelaSelecionarPersonagensController {
    @FXML
    private ImageView personagem1jog1, personagem2jog1, personagem3jog1, personagem4jog1, personagem5jog1,
            personagem6jog1, personagem7jog1, personagem8jog1, personagem9jog1, personagem10jog1;
    @FXML
    private ImageView personagem1jog2, personagem2jog2, personagem3jog2, personagem4jog2, personagem5jog2,
            personagem6jog2, personagem7jog2, personagem8jog2, personagem9jog2, personagem10jog2;
    @FXML
    private Text nick1, nick2;

    String[] todosPersonagens = {
            "Sage", "Phoenix", "Viper", "Reyna", "Kennen", "Ivern", "Garen", "Toph", "Aang", "Beno"
    };
    // obter nome personagens e personagens bloqueados
    String[] personagensJog1 = {
            "Sage", "Phoenix", "Viper", "Reyna", "Kennen", "Ivern", "Garen", "Toph", "Aang"
    };
    //String[] personagensJog1 = Dados.jogadorA.getPersonagensLiberados();

    String[] personagensBloqueadosJog1;

    boolean[] selecionadoJog1 = new boolean[personagensJog1.length];
    Pilha<Integer> personagensSelecionadosJog1 = new Pilha<>();

    String[] personagensJog2 = {
            "Sage", "Phoenix", "Viper", "Reyna", "Kennen", "Ivern", "Garen", "Toph", "Aang"
    };
    //String[] personagensJog2 = Dados.jogadorA.getPersonagensLiberados();
    String[] personagensBloqueadosJog2;

    boolean[] selecionadoJog2 = new boolean[personagensJog2.length];
    Pilha<Integer> personagensSelecionadosJog2 = new Pilha<>();

    public void initialize() {
        gerarPersonagensBloqueados();

        if (personagensSelecionadosJog1.getSize() == 3 && personagensSelecionadosJog2.getSize() == 3) {
            iniciarPartida();
        }

        // exibir dados jogador 1
        String str = "Jogador 1 ("+Dados.jogadorA.getNick()+")";
        str += " | Personagens Adicionados ("+personagensSelecionadosJog1.getSize()+"/3)";

        if (personagensSelecionadosJog1.getSize() > 0) {
            str += " | Último Adicionado: ";

            try {
                str += personagensJog1[personagensSelecionadosJog1.peek()];
            }
            catch (Implementacoes.Excecao.Excecao e) {
                e.printStackTrace();
            }
        }

        nick1.setText(str);


        // exibir dados jogador 2
        str = "Jogador 2 ("+Dados.jogadorB.getNick()+")";
        str += " | Personagens Adicionados ("+personagensSelecionadosJog2.getSize()+"/3)";

        if (personagensSelecionadosJog2.getSize() > 0) {
            str += " | Último Adicionado: ";

            try {
                str += personagensJog2[personagensSelecionadosJog2.peek()];
            }
            catch (Implementacoes.Excecao.Excecao e) {
                e.printStackTrace();
            }
        }
        nick2.setText(str);

        // exibir personagens jogador 1
        ImageView[] imagensJog1 = {personagem1jog1, personagem2jog1, personagem3jog1, personagem4jog1, personagem5jog1,
                personagem6jog1, personagem7jog1, personagem8jog1, personagem9jog1, personagem10jog1};

        for (int i = 0; i < personagensJog1.length; i++) {
            if (!selecionadoJog1[i]) { // exibe se não foi selecionado
                imagensJog1[i].setImage(new Image("view/Imagens/Personagens/"+personagensJog1[i]+".png"));
            }
            else {
                imagensJog1[i].setImage(null);
            }
        }

        for (int i = 0; i < personagensBloqueadosJog1.length; i++) {
            imagensJog1[i+personagensJog1.length].setImage(new Image("view/Imagens/Personagens/"+personagensBloqueadosJog1[i]+".png"));

            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setSaturation(-1);
            imagensJog1[i+personagensJog1.length].setEffect(colorAdjust);
        }

        // exibir personagens jogador 2
        ImageView[] imagensJog2 = {personagem1jog2, personagem2jog2, personagem3jog2, personagem4jog2, personagem5jog2,
                personagem6jog2, personagem7jog2, personagem8jog2, personagem9jog2, personagem10jog2};

        for (int i = 0; i < personagensJog2.length; i++) {
            if (!selecionadoJog2[i]) { // exibe se não foi selecionado
                imagensJog2[i].setImage(new Image("view/Imagens/Personagens/"+personagensJog2[i]+".png"));
            }
            else {
                imagensJog2[i].setImage(null);
            }
        }

        for (int i = 0; i < personagensBloqueadosJog2.length; i++) {
            imagensJog2[i+personagensJog2.length].setImage(new Image("view/Imagens/Personagens/"+personagensBloqueadosJog2[i]+".png"));

            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setSaturation(-1);
            imagensJog2[i+personagensJog2.length].setEffect(colorAdjust);
        }
    }

    public void selecionarPersonagem(MouseEvent event) {
        ImageView imagemClicada = (ImageView) event.getSource();
        String idImagem = imagemClicada.getId();

        int idPersonagem = -1;

        for (int i = 1; i <= personagensJog1.length; i++) {
            if (idImagem.equals("personagem"+i+"jog1")) {
                idPersonagem = i - 1;
            }
        }
        try {
            if (idPersonagem != -1 && !selecionadoJog1[idPersonagem] && personagensSelecionadosJog1.getSize() < 3) {
                personagensSelecionadosJog1.push(idPersonagem);
                selecionadoJog1[idPersonagem] = true;
            }
        } catch (Implementacoes.Excecao.Excecao e) {
            e.printStackTrace();
        }

        idPersonagem = -1;
        for (int i = 1; i <= personagensJog2.length; i++) {
            if (idImagem.equals("personagem"+i+"jog2")) {
                idPersonagem = i - 1;
            }
        }
        try {
            if (idPersonagem != -1 && !selecionadoJog2[idPersonagem] && personagensSelecionadosJog2.getSize() < 3) {
                personagensSelecionadosJog2.push(idPersonagem);
                selecionadoJog2[idPersonagem] = true;
            }
        } catch (Implementacoes.Excecao.Excecao e) {
            e.printStackTrace();
        }

        initialize();
    }

    public void iniciarPartida() {
        try {
            String[] personagensAtacantes = {
                    personagensJog1[personagensSelecionadosJog1.pop()],
                    personagensJog1[personagensSelecionadosJog1.pop()],
                    personagensJog1[personagensSelecionadosJog1.pop()]
            };

            String[] personagensDefensores = {
                    personagensJog2[personagensSelecionadosJog2.pop()],
                    personagensJog2[personagensSelecionadosJog2.pop()],
                    personagensJog2[personagensSelecionadosJog2.pop()]
            };

            String[][] str = {personagensAtacantes, personagensDefensores};
            Dados.novosPersonagens(str);
        }
        catch (Implementacoes.Excecao.Excecao e) {
            e.printStackTrace();
        }

        Telas.switchScene("/view/ve/TelaPartida.fxml/");
    }

    public void gerarPersonagensBloqueados() {
        personagensBloqueadosJog1 = new String[todosPersonagens.length - personagensJog1.length];

        int k = 0;

        for (int i = 0; i < todosPersonagens.length; i++) {
            boolean personagemNaoExiste = true;
            for (int j = 0; j < personagensJog1.length; j++) {
                if (todosPersonagens[i].equals(personagensJog1[j])) {
                    personagemNaoExiste = false;
                }
            }

            if (personagemNaoExiste) {
                personagensBloqueadosJog1[k] = todosPersonagens[i];
                k++;
            }
        }


        personagensBloqueadosJog2 = new String[todosPersonagens.length - personagensJog2.length];

        k = 0;
        for (int i = 0; i < todosPersonagens.length; i++) {
            boolean personagemNaoExiste = true;
            for (int j = 0; j < personagensJog2.length; j++) {
                if (todosPersonagens[i].equals(personagensJog2[j])) {
                    personagemNaoExiste = false;
                }
            }

            if (personagemNaoExiste) {
                personagensBloqueadosJog2[k] = todosPersonagens[i];
                k++;
            }
        }
    }
}

