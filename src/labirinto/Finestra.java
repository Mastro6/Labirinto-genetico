package labirinto;

import javax.swing.*;
import java.awt.*;

public class Finestra extends JFrame {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;

    private JPanel mainPanel = new JPanel(new GridBagLayout());

    public Finestra() {
        setTitle("Labirinto Genetico");
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mainPanel.setPreferredSize(getSize());
        mainPanel.add(new Labirinto(10, 10));
        add(mainPanel);

//        setVisible(true);
    }

}
