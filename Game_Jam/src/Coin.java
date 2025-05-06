import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Coin {

    private Picture coinImage;

    public Coin (int fieldPadding, int fieldWidth, int fieldHeight) {
        int imageWidth = 200;
        int imageHeight = 200;

        // calculo para o meio campo
        int x = fieldPadding + (fieldWidth - imageWidth) / 2;
        int y = fieldPadding + (fieldHeight + 100- imageHeight) / 2;

        // Cria a imagem no centro
        coinImage = new Picture(x, y, "resources/lamacoin.png");
    }

    public void show() {
        coinImage.draw();
    }
}
