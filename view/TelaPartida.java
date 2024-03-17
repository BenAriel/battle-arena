package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TelaPartida extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Carrega a imagem
        Image[] imagem = {
                new Image("\\Telas\\Imagens\\Personagens\\Fade.png"),
                new Image("\\Telas\\Imagens\\Personagens\\Phoenix.png"),
                new Image("\\Telas\\Imagens\\Personagens\\Viper.png"),
                new Image("\\Telas\\Imagens\\Personagens\\Sage.png"),
                new Image("\\Telas\\Imagens\\Personagens\\Fade.png"),
                new Image("\\Telas\\Imagens\\Personagens\\Reyna.png")
        };

        // Cria ImageView para exibir as imagens
        ImageView[] imagensPersonagens = {
                new ImageView(imagem[0]),
                new ImageView(imagem[1]),
                new ImageView(imagem[2]),
                new ImageView(imagem[3]),
                new ImageView(imagem[4]),
                new ImageView(imagem[5])
        };

        // Cria um layout Pane para posicionar as imagens em coordenadas específicas
        Pane root = new Pane();

        int posXA = 100;
        int posXB = 1600-100-160;
        int posY = 100;
        int tamImgPersonagens = 160;

        int[] vidasProv = new int[]{39, 4, 40, 100, 42, 90};

        // Adiciona imagens de personagens a tela
        for (int i = 0; i < 3; i++) {
            imagensPersonagens[i].setX(posXA);
            imagensPersonagens[i].setY(posY + 250*i);
            imagensPersonagens[i].setFitWidth(tamImgPersonagens);
            imagensPersonagens[i].setFitHeight(tamImgPersonagens);
            root.getChildren().add(imagensPersonagens[i]);

            Rectangle borda = gerarBordaImagem(tamImgPersonagens, tamImgPersonagens, posXA, posY + 250*i);
            root.getChildren().add(borda);

            Rectangle bordaBarraVida = gerarBordaBarraVida(posXA, posY + 250*i + tamImgPersonagens, tamImgPersonagens);
            root.getChildren().add(bordaBarraVida);

            Rectangle barraVida = gerarBarraVida(posXA, posY + 250*i + tamImgPersonagens, tamImgPersonagens, vidasProv[i]);
            root.getChildren().add(barraVida);
        }
        for (int i = 3; i < 6; i++) {
            imagensPersonagens[i].setX(posXB);
            imagensPersonagens[i].setY(posY + (i-3)*250);
            imagensPersonagens[i].setFitWidth(tamImgPersonagens);
            imagensPersonagens[i].setFitHeight(tamImgPersonagens);
            imagensPersonagens[i].setScaleX(-1);
            root.getChildren().add(imagensPersonagens[i]);

            Rectangle borda = gerarBordaImagem(tamImgPersonagens, tamImgPersonagens, posXB, posY + 250*(i-3));
            root.getChildren().add(borda);

            Rectangle bordaBarraVida = gerarBordaBarraVida(posXB, posY + 250*(i-3) + tamImgPersonagens, tamImgPersonagens);
            root.getChildren().add(bordaBarraVida);

            Rectangle barraVida = gerarBarraVida(posXB, posY + 250*(i-3) + tamImgPersonagens, tamImgPersonagens, vidasProv[i]);
            root.getChildren().add(barraVida);
        }


        // Cria a cena e define o layout StackPane como o root da cena
        Scene scene = new Scene(root, 1600, 900);

        // Define a cena no palco (Stage) e exibe o palco
        primaryStage.setScene(scene);
        primaryStage.setTitle("Arena");
        primaryStage.show();

    }

    public Rectangle gerarBordaImagem(double width, double height, int posicaoX, int posicaoY) {
        Rectangle borda = new Rectangle(width, height);
        borda.setStroke(Color.BLACK);
        borda.setFill(Color.TRANSPARENT);
        borda.setStrokeWidth(1);
        borda.setX(posicaoX);
        borda.setY(posicaoY);

        return borda;
    }

    public Rectangle gerarBordaBarraVida(int posX, int posY, int tamImagem) {
        Rectangle retangulo = new Rectangle(tamImagem, 35, Color.TRANSPARENT);
        retangulo.setStroke(Color.BLACK);
        retangulo.setStrokeWidth(1);
        retangulo.setX(posX);
        retangulo.setY(posY+5);
        return retangulo;

    }

    public Rectangle gerarBarraVida(int posX, int posY, int tamImagem, int vida) {
        double tamBarraVida = (vida/100.0)*tamImagem;

        Color cor = Color.RED;
        if (vida >= 70) {
            cor = Color.GREEN;
        }
        else if (vida >= 30) {
            cor = Color.YELLOW;
        }

        Rectangle retangulo = new Rectangle(tamBarraVida, 35, cor);
        retangulo.setStrokeWidth(1);
        retangulo.setX(posX);
        retangulo.setY(posY+5);
        return retangulo;

    }
}
