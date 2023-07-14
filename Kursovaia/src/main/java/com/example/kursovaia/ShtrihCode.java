package com.example.kursovaia;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;

public class ShtrihCode {

    @FXML
    public Canvas canvas;


    public void initialize() {



        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
        double mm = (double) Toolkit.getDefaultToolkit().getScreenResolution() / 25.4;

        int[] palks = new int[]{
                5, 1, 4, 0, 9, 2, 0, 2, 0, 1, 2, 3, 4, 5, 6,
        };


        gc.setFill(Color.BLACK);

        int x0 = 20, y0 = 10;
        double heightPalks = 22.85 * mm;
        double weightOgrPalks = 0.5 * mm;
        double rasstoyanieMegdyPalk = 0.5 * mm;



        gc.fillRect((double)x0,(double) y0, weightOgrPalks, heightPalks + 1.65 * mm);
        int otcydaPalks =(int)((double)x0 + weightOgrPalks +rasstoyanieMegdyPalk);
        int otcydaZifra = otcydaPalks;
        gc.fillRect((double) otcydaPalks,(double)y0,weightOgrPalks, heightPalks + 1.65 * mm);
        otcydaPalks =(int)((double)otcydaPalks + weightOgrPalks +rasstoyanieMegdyPalk);

        boolean printSrednyaPalks = false;
        for (int numberPalks = 0; numberPalks < palks.length; numberPalks++) {
            double shirinaPalks = (double) palks[numberPalks] * 0.15 * mm;
            if (numberPalks == palks.length / 2 && !printSrednyaPalks) {
                gc.setFill(Color.BLACK);
                gc.fillRect((double) otcydaPalks,(double) y0, weightOgrPalks, heightPalks + 1.65 * mm);
                otcydaPalks =(int)((double)otcydaPalks + weightOgrPalks + rasstoyanieMegdyPalk);
                gc.fillRect((double) otcydaPalks,(double) y0, weightOgrPalks, heightPalks + 1.65 * mm);
                otcydaPalks =(int)((double)otcydaPalks + weightOgrPalks + rasstoyanieMegdyPalk);
                --numberPalks;
                printSrednyaPalks = true;
            }
            else {
                if(shirinaPalks == 0.00){
                    shirinaPalks = 1.35 * mm;
                    gc.setFill(Color.WHITE);
                }else{
                    gc.setFill(Color.BLACK);
                }
                gc.fillRect((double) otcydaPalks,(double) y0, shirinaPalks, heightPalks);
                otcydaPalks =(int)((double)otcydaPalks + shirinaPalks + rasstoyanieMegdyPalk);

            }


        }

        gc.fillRect((double)otcydaPalks,(double) y0, weightOgrPalks, heightPalks + 1.65 * mm);
        otcydaPalks =(int)((double)otcydaPalks + weightOgrPalks + rasstoyanieMegdyPalk);

        gc.fillRect((double) otcydaPalks,(double) y0, weightOgrPalks, heightPalks + 1.65 * mm);
    }
}