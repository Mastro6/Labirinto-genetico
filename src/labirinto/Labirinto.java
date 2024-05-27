package labirinto;

import javax.swing.*;
import java.awt.*;

public class Labirinto extends JPanel {

    private static final int GAP_FROM_FRAME_EDGES = 100;
    private static final Dimension preferredDimension = new Dimension(Finestra.WIDTH - GAP_FROM_FRAME_EDGES, Finestra.HEIGHT - GAP_FROM_FRAME_EDGES);
    private Cella[] celle;

    /**
     * Genera un labirinto random
     * @param larghezza numero di celle in una riga
     * @param lunghezza numero di righe
     */
    public Labirinto(int larghezza, int lunghezza) {
        setPreferredSize(preferredDimension);
        setLayout(new GridLayout(larghezza, lunghezza));

        // inizializza componenti
        celle = new Cella[larghezza * lunghezza];
        for(int i = 0; i < celle.length; i++) {
            celle[i] = new Cella();
            add(celle[i]);
        }

        setVisible(true);
    }

}
