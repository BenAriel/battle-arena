package Telas;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

public class TelaPartida extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Carrega a imagem
        Image[] imagem = {
                new Image("C:\\Users\\aypen\\OneDrive\\Ambiente de Trabalho\\Algoritmos\\Java\\LoL Arena\\Telas\\Imagens\\Personagens\\Fade.png"),
                new Image("C:\\Users\\aypen\\OneDrive\\Ambiente de Trabalho\\Algoritmos\\Java\\LoL Arena\\Telas\\Imagens\\Personagens\\Phoenix.png"),
                new Image("C:\\Users\\aypen\\OneDrive\\Ambiente de Trabalho\\Algoritmos\\Java\\LoL Arena\\Telas\\Imagens\\Personagens\\Viper.png"),
                new Image("C:\\Users\\aypen\\OneDrive\\Ambiente de Trabalho\\Algoritmos\\Java\\LoL Arena\\Telas\\Imagens\\Personagens\\Sage.png"),
                new Image("C:\\Users\\aypen\\OneDrive\\Ambiente de Trabalho\\Algoritmos\\Java\\LoL Arena\\Telas\\Imagens\\Personagens\\Astra.png"),
                new Image("C:\\Users\\aypen\\OneDrive\\Ambiente de Trabalho\\Algoritmos\\Java\\LoL Arena\\Telas\\Imagens\\Personagens\\Reyna.png")
        };

        // Cria ImageView para exibir as imagens
        ImageView[] imageView = {
                new ImageView(imagem[0]),
                new ImageView(imagem[1]),
                new ImageView(imagem[2]),
                new ImageView(imagem[3]),
                new ImageView(imagem[4]),
                new ImageView(imagem[5])
        };

        // Define a posição das imagens
        imageView[0].setX(100);
        imageView[0].setY(100);
        imageView[1].setX(100);
        imageView[1].setY(300);
        imageView[2].setX(100);
        imageView[2].setY(500);

        imageView[3].setScaleX(-1);
        imageView[3].setX(1600-100-160);
        imageView[3].setY(100);
        imageView[4].setScaleX(-1);
        imageView[4].setX(1600-100-160);
        imageView[4].setY(300);
        imageView[5].setScaleX(-1);
        imageView[5].setX(1600-100-160);
        imageView[5].setY(500);

        // Cria um layout Pane para posicionar a imagem em coordenadas específicas
        Pane root = new Pane();
        root.getChildren().add(imageView[0]);
        root.getChildren().add(imageView[1]);
        root.getChildren().add(imageView[2]);
        root.getChildren().add(imageView[3]);
        root.getChildren().add(imageView[4]);
        root.getChildren().add(imageView[5]);

        // Cria a cena e define o layout StackPane como o root da cena
        Scene scene = new Scene(root, 1600, 900);

        // Define a cena no palco (Stage) e exibe o palco
        primaryStage.setScene(scene);
        primaryStage.setTitle("Arena");
        primaryStage.show();

    }
}
