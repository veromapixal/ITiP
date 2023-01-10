package com.lab4;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.SwingWorker;

//данный класс позволяет исследовать различные области фрактала, путем его создания,
//отображения через графический интерфейс Swing и обработки событий, вызванных
//взаимодействием приложения с пользователем.
public class FractalExplorer {

    //размер экрана, которое является шириной и высотой отображения в пикселях
    private int size;

    //данная ссылка нужна для обновления отображения в разных методах в процессе вычисления фрактала
    private JImageDisplay jImageDisplay;

    //данный объект будет использоваться ссылкой на базовый класс для отображения других видов фракталов в будущем
    private FractalGenerator fractalGenerator;

    //данный объект указывает диапазон комплексной плоскости, которая выводится на экран
    private Rectangle2D.Double range;

    //поле для отслеживания количество оставшихся строк
    private int rowsRemain;

    JComboBox jComboBox;
    JButton saveButton;
    JButton resetButton;

    //конструктор, принимает значение размера отображения
    public FractalExplorer(int displaySize){
        size = displaySize;
        range = new Rectangle2D.Double();
        jImageDisplay = new JImageDisplay(displaySize, displaySize);
        fractalGenerator = new Mandelbrot();
        fractalGenerator.getInitialRange(range);
    }


    //данный метод инициализирует графический интерфейс Swing
    public void createAndShowGUI(){
        jImageDisplay.setLayout(new BorderLayout());
        JFrame frame = new JFrame("Fractal Explorer");

        frame.add(jImageDisplay, BorderLayout.CENTER);

        //reset
        JButton resetButton = new JButton("RESET");
        ResetButton  reset = new ResetButton();
        resetButton.addActionListener(reset);

        //save
        JButton saveButton = new JButton("SAVE");
        SaveButton save = new SaveButton();
        saveButton.addActionListener(save);

        JPanel jPanelSouth = new JPanel();
        jPanelSouth.add(resetButton);
        jPanelSouth.add(saveButton);
        frame.add(jPanelSouth, BorderLayout.SOUTH);

        //close
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

        String[] items = {"Mandelbrot", "Tricorn", "BurningShip"};
        JComboBox jComboBox = new JComboBox(items);

        //объект-пояснение к выпадающему списку
        JLabel jLabel = new JLabel("Type of fractal: ");
        JPanel jPanel = new JPanel();
        jPanel.add(jLabel);
        jPanel.add(jComboBox);
        frame.add(jPanel, BorderLayout.NORTH);

        ChooseButton chooseType = new ChooseButton();
        jComboBox.addActionListener(chooseType);

        //щелчок мыши
        MouseListener mouseClick = new MyMouseListener();
        jImageDisplay.addMouseListener(mouseClick);

        //Данные операции правильно разметят содержимое окна, сделают его
        //видимым (окна первоначально не отображаются при их создании для того,
        //чтобы можно было сконфигурировать их прежде, чем выводить на экран), и
        //затем запретят изменение размеров окна.
        frame.pack ();
        frame.setVisible (true);
        frame.setResizable (false);

    }

    //вкл/выкл кнопки
    private void enableUI(boolean val){
        resetButton.setEnabled(val);
        saveButton.setEnabled(val);
        jComboBox.setEnabled(val);
    }

    //вспомогательный метод для вывода на экран фрактала
    //lab6 upd: создаем для каждой строки отдельный рабочий объект
    //с помощью запускаем фоновый поток(т.е. запускаем задачу в фоновом режиме)
    private void drawFractal(){
        //выключаем кнопки
        //enableUI(false);
        //rowsRemain = общему кол-ву строк, которые надо нарисовать
        rowsRemain = size;
        for (int y = 0; y < size; y++){
            FractalWorker fractalWorker = new FractalWorker(y);
            fractalWorker.execute();
        }
    }



    //внутренний класс для обработки событий от кнопки сброса.
    //Обработчик должен сбросить диапазон к начальному, определенному генератором,
    //а затем перерисовать фрактал
    public class ResetButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent event){
            fractalGenerator.getInitialRange(range);
            drawFractal();
        }
    }
    //класс для сохранения изображения
    public class SaveButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent event) {
            JFileChooser chooser = new JFileChooser();
            //настройка средства выбора файлов, чтобы сохранять только в PNG
            FileFilter filter = new FileNameExtensionFilter("PNG Images", "png");
            chooser.setFileFilter(filter);
            chooser.setAcceptAllFileFilterUsed(false);

            int showResult = chooser.showSaveDialog(jImageDisplay);
            //если результат операции выбора файла == JFileChooser.APPROVE_OPTION
            //родолжить сохранение, иначе - пользователь отменил сохранение
            if (showResult == JFileChooser.APPROVE_OPTION){
                //узнаем директорию, выбранную пользователем
                File directory = chooser.getSelectedFile();
                String directoryToString = directory.toString();
                //обрабатываем исключения метода write()
                try{
                    BufferedImage image = jImageDisplay.getImage();
                    ImageIO.write(image, "png", directory);
                }
                catch (Exception exception){
                    JOptionPane.showMessageDialog(chooser, exception.getMessage(), "Cannot Save Image", JOptionPane.ERROR_MESSAGE);

                }
            }
        }
    }
    //данный класс при получении события о щелчке мышью, должен
    //отобразить пиксельные кооринаты щелчка в область фрактала
    public class MyMouseListener extends MouseAdapter {


        @Override
        public void mouseClicked(MouseEvent click){

            //провреяем, все ли строки перерисованы
            if (rowsRemain != 0){
                return;
            }

            int x = click.getX();
            double xCoord = fractalGenerator.getCoord(range.x, range.x + range.width, size,x);

            int y = click.getY();
            double yCoord = fractalGenerator.getCoord(range.y, range.y + range.height, size, y);

            fractalGenerator.recenterAndZoomRange(range, xCoord, yCoord, 0.5);

            drawFractal();

        }
    }
    //добавление выпадающего списка в реализацию ActionListener
    public class ChooseButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent event){
            JComboBox jComboBox = (JComboBox) event.getSource();
            //извлекаем выбранный элемент из виджета
            String type = (String) jComboBox.getSelectedItem();
            if (type == "Mandelbrot"){
                fractalGenerator = new Mandelbrot();
            }
            if (type == "Tricorn"){
                fractalGenerator = new Tricorn();
            }
            if (type == "BurningShip"){
                fractalGenerator = new BurningShip();
            }
            fractalGenerator.getInitialRange(range);
            drawFractal();
        }
    }
    //создаем подкласс SwingWorker для вычисления значений цвета для одной строки
    //фрактала
    private class FractalWorker extends SwingWorker<Object, Object>{

        //целочисленная y - координата вычисляемой строки
        private int y;
        //массив для хранения вычисленных значений RGB для каждого пикселя в этой строке
        private int[] rgbValues;

        //конструктор сохраняет координату у
        public FractalWorker(int y){
            this.y = y;
        }

        @Override
        protected Object doInBackground() throws Exception {
            rgbValues = new int[size];
            for (int x = 0; x < rgbValues.length; x++) {
                    double xCoord = fractalGenerator.getCoord(range.x, range.x + range.width, size, x);
                    double yCoord = fractalGenerator.getCoord(range.y, range.y + range.height, size, y);
                    int iterations = fractalGenerator.numIterations(xCoord, yCoord);
                    int rgbColor;
                    if (iterations == -1) {
                        rgbColor = 0;
                    }
                    else {
                        float hue = 0.7f + (float) iterations / 200f;
                        rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    }
                    rgbValues[x] = rgbColor;
            }
            return null;
        }


        @Override
        protected void done(){
            for (int i = 0; i < rgbValues.length; i++){
                jImageDisplay.drawPixel(i, y, rgbValues[i]);
            }
            //перерисовываем строку
            jImageDisplay.repaint(0, 0, y, size, 1);

            //последний шаг данной операции
            rowsRemain --;

            if (rowsRemain == 0){
                enableUI(true);
            }
        }

    }

    public static void main(String[] args) {
        FractalExplorer display = new FractalExplorer(800);
        display.createAndShowGUI();
        display.drawFractal();

    }
}
