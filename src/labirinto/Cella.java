package labirinto;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Cella extends JPanel {

    private static final int BORDER_THICKNESS = 1;

    ArrayList<Direzioni> lati = new ArrayList<>();

    public Cella() {
        // Di default, una cella ha tutti e quattro i "muri"
        lati.addAll(Arrays.asList(Direzioni.values()));
        updateBorders();

        setVisible(true);
    }

    public void updateBorders() {
        setBorder(BorderFactory.createMatteBorder(
                lati.contains(Direzioni.SU) ? BORDER_THICKNESS : 0,
                lati.contains(Direzioni.SINISTRA) ? BORDER_THICKNESS : 0,
                lati.contains(Direzioni.GIU) ? BORDER_THICKNESS : 0,
                lati.contains(Direzioni.DESTRA) ? BORDER_THICKNESS : 0,
                Color.black
        ));
        revalidate();
    }

    public boolean aggiungiMuro(Direzioni lato) {
        if (lati.contains(lato))
            return false;
        lati.add(lato);
        updateBorders();
        return true;
    }

    public boolean rimuoviMuro(Direzioni lato) {
        if (!lati.contains(lato))
            return false;
        lati.remove(lato);
        updateBorders();
        return true;
    }

}
