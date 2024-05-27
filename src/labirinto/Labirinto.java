package labirinto;

import javax.swing.*;
import java.awt.*;

public class Labirinto extends JPanel {

    private Cella[] celle;

    /**
     * Genera un labirinto random
     * @param larghezza numero di celle in una riga
     * @param lunghezza numero di righe
     */
    public Labirinto(int larghezza, int lunghezza) {
        setLayout(new GridLayout(larghezza, lunghezza));

        // inizializza componenti
        celle = new Cella[larghezza * lunghezza];
        for(int i = 0; i < celle.length; i++) {
            celle[i] = new Cella();
        }

        setVisible(true);
    }

}
