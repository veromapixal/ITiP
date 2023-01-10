package com.lab4;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class JImageDisplay extends JComponent{
    private BufferedImage image;
    //класс BufferedImage управляет изображением, содержимое которого можно записать

    public BufferedImage getImage(){
        return image;
    }

    //конструктор инициализирует объект BufferedImage новым изображением типа TYPE_INT_RGB
    //тип определяет как цвета каждого пикселя будут представлены в изображении
    //TYPE_INT_RGB значит, что красные, зеленые и синие компоненты имеют по 8 битов,
    //представленные в формате int в указанном порядке
    public JImageDisplay(int width, int height) {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Dimension dimension = new Dimension(width, height);
        super.setPreferredSize(dimension);//метод родительского класса
    }

    // переопределяем защищенный метод для собственного кода для отрисовки
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(),null);
    }
    //метод устанавливает все пиксели изображения в черный цвет
    public void clearImage(){
        for (int i = 0; i < image.getHeight(); i++){
            for (int j = 0; j < image.getWidth(); j++){
                 drawPixel(i, j, 0);
            }
        }
    }
    //метод устанавливает все пиксели изображения в определенный цвет
    public void drawPixel(int x, int y, int rgbColor){
            image.setRGB(x, y, rgbColor);
    }
}
