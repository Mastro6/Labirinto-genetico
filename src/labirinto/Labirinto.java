package labirinto;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Labirinto extends JPanel {

    private static final int GAP_FROM_FRAME_EDGES = 100;
    private static final Dimension preferredDimension = new Dimension(Finestra.WIDTH - GAP_FROM_FRAME_EDGES, Finestra.HEIGHT - GAP_FROM_FRAME_EDGES);
    private Cella[][] celle;
    private HashMap<Cella, Integer> mappaVicini = new HashMap<>();

    /**
     * Genera un labirinto random
     * @param larghezza numero di celle in una riga
     * @param lunghezza numero di righe
     */
    public Labirinto(int larghezza, int lunghezza) {
        setPreferredSize(preferredDimension);
        setLayout(new GridLayout(larghezza, lunghezza));

        // inizializza componenti
        celle = new Cella[lunghezza][larghezza];
        for(int i = 0; i < lunghezza; i++) {
            for(int j = 0; j < larghezza; j++) {
                Cella cellaCorrente = new Cella();
                int numeroVicini = 4;
                if((i == 0 && j == 0)   // angolo in alto a sx
                    || (i == 0 && j == larghezza - 1)   // angolo in alto a destra
                    || (i == lunghezza - 1 && j == larghezza - 1) // angolo in basso a dx
                    || (i == lunghezza - 1 && j == 0))  // angolo in basso a sx
                    numeroVicini -= 2;
                else if(i == 0 || j == 0 || i == lunghezza - 1 || j == larghezza - 1)
                    numeroVicini -= 1;

                mappaVicini.put(cellaCorrente, numeroVicini);

                celle[i][j] = cellaCorrente;
                add(cellaCorrente);
            }
        }

        // TEST
        // visita la cella 1,1 per verificare l'aggiornamento effettivo
        System.out.println(mappaVicini());
        System.out.println("-------------------");
        int riga = 1;
        int colonna = 1;
        celle[riga][colonna].setVisitata(true);
        aggiornaVicini(riga, colonna);
        System.out.println(mappaVicini());

        setVisible(true);
    }

    public void generaLabirintoRandom() {

    }

    /**
     * Genera un percorso casuale che ricopra interamente il labirinto a partire dalla cella in input
     * In pratica, genera un albero (grafo) ricoprente tutti i nodi (celle) del labirinto
     * @param riga elemento corrente
     * @param colonna elemento corrente
     */
    private void backtracking(int riga, int colonna) {
        if(riga < 0 || riga >= celle.length || colonna < 0 || colonna >= celle[0].length)
            return;

       Cella cellaCorrente = celle[riga][colonna];
       cellaCorrente.setVisitata(true);

        if(mappaVicini.get(cellaCorrente) == 0)
            return;

        // Visita una casella vicina a caso
//        backtracking(...);
//        aggiornaVicini(cellaCorrente);
        // Richiama metodo su se stessa. Se ci fossero altri vicini, visita anche loro

    }

    /**
     * Aggiorna i valori della mappa in modo che le celle adiacenti alla cella in input abbiano un vicino in meno
     * Nessun controllo su parametri. Il metodo va chiamato unicamente quando si è modificata una cella del labirinto
     * @param riga della cella appena visitata
     * @param colonna della cella appena visitata
     */
    private void aggiornaVicini(int riga, int colonna) {
        // vicino superiore
        if (riga + 1 < celle.length)
            mappaVicini.put(celle[riga + 1][colonna], mappaVicini.get(celle[riga + 1][colonna]) - 1);
        // vicino inferiore
        if (riga - 1 >= 0)
            mappaVicini.put(celle[riga - 1][colonna], mappaVicini.get(celle[riga - 1][colonna]) - 1);
        // vicino di destra
        if (colonna + 1 < celle[0].length)
            mappaVicini.put(celle[riga][colonna + 1], mappaVicini.get(celle[riga][colonna + 1]) - 1);
        // vicino di sinistra
        if (colonna - 1 >= 0)
            mappaVicini.put(celle[riga][colonna - 1], mappaVicini.get(celle[riga][colonna - 1]) - 1);
    }

    /**
     * Restituisce una visualizzazione testuale della griglia di celle, indicando per ognuna il numero di vicini non ancora visitati
     * @return griglia contenente numero di vicini per ogni cella
     */
    public String mappaVicini() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < celle.length; i++) {
            sb.append('[');
            for (int j = 0; j < celle[0].length; j++) {
                sb.append(mappaVicini.get(celle[i][j]));
                if (j < celle[0].length - 1)
                    sb.append(',')
                            .append(' ');
            }
            sb.append(']')
                    .append('\n');
        }
        return sb.toString();
    }

}
