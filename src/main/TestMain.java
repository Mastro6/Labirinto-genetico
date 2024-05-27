package main;

import labirinto.Cella;
import labirinto.Direzioni;

import javax.swing.*;
import java.awt.*;

public class TestMain {
    public static void main(String[] args) {
        System.out.println("Hello World3");

        JFrame testFrame = new JFrame("Test");
        testFrame.setSize(800,800);
        testFrame.setResizable(false);
        testFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        testFrame.setVisible(true);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        Dimension testDimension = new Dimension(100, 100);

        //region quadrato

        /*
            prima idea:
        il labirinto è una matrice di JPanel che, in base a una sua lista di bordi, mostra o meno le pareti

        da WikiPedia:
        Consider the space for a maze being a large grid of cells (like a large chess board),
        each cell starting with four walls. Starting from a random cell,
        the computer then selects a random neighbouring cell that has not yet been visited.
        The computer removes the wall between the two cells and marks the new cell as visited

         */


//        JPanel squarePanel = new JPanel(new GridLayout(2, 2));
//        squarePanel.setPreferredSize(testDimension);
//
//        JPanel[] angoli = new JPanel[4];
//        angoli[0] = new JPanel();
//        // come mostrare le pareti
//        angoli[0].setBorder(BorderFactory.createMatteBorder(5, 5, 0, 0, Color.green));
//        angoli[0].setPreferredSize(testDimension);
//        squarePanel.add(angoli[0]);
//
//        angoli[1] = new JPanel();
//        angoli[1].setBorder(BorderFactory.createMatteBorder(5, 0, 0, 5, Color.red));
//        angoli[1].setPreferredSize(testDimension);
//        squarePanel.add(angoli[1]);
//
//        angoli[2] = new JPanel();
//        angoli[2].setBorder(BorderFactory.createMatteBorder(0, 5, 5, 0, Color.yellow));
//        angoli[2].setPreferredSize(testDimension);
//        squarePanel.add(angoli[2]);
//
//        angoli[3] = new JPanel();
//        angoli[3].setBorder(BorderFactory.createMatteBorder(0, 0, 5, 5, Color.blue));
//        angoli[3].setPreferredSize(testDimension);
//        squarePanel.add(angoli[3]);
//
//        mainPanel.add(squarePanel);
//        testFrame.add(mainPanel);
//        testFrame.revalidate();
        //endregion

        Cella cellaTest = new Cella();
        cellaTest.setPreferredSize(testDimension);
        mainPanel.add(cellaTest);
        testFrame.add(mainPanel);
        testFrame.revalidate();

        int i = 0;
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (i >= 1)
                cellaTest.addBorder(Direzioni.values()[(i - 1) % 4]);

            cellaTest.removeBorder(Direzioni.values()[i++ % 4]);

            mainPanel.revalidate();
            testFrame.revalidate();
        }

    }
}
