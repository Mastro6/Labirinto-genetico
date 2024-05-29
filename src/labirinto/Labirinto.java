package labirinto;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Labirinto extends JPanel {

    private static final int GAP_FROM_FRAME_EDGES = 100;
    private static final Dimension preferredDimension = new Dimension(Finestra.WIDTH - GAP_FROM_FRAME_EDGES, Finestra.HEIGHT - GAP_FROM_FRAME_EDGES);
    private Cella[][] celle;
    private HashMap<Cella, Integer> mappaVicini = new HashMap<>();
    private HashMap<Cella, Boolean> mappaCelleVisitate = new HashMap<>();

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
                mappaCelleVisitate.put(cellaCorrente, false);

                celle[i][j] = cellaCorrente;
                add(cellaCorrente);
            }
        }

        // TEST generazione backtracking
        backtracking(3,3, null);
        System.out.println(mappaCelleVisitate());
        System.out.println(mappaVicini());

        setVisible(true);
    }

    /**
     * Genera un percorso casuale che ricopra interamente il labirinto a partire dalla cella in input
     * In pratica, genera un albero (grafo) ricoprente tutti i nodi (celle) del labirinto
     * @param riga elemento corrente
     * @param colonna elemento corrente
     */
    private void backtracking(int riga, int colonna, Direzioni ultimaDirezione) {
        System.out.println(mappaCelleVisitate());
        System.out.println(mappaVicini());

        if(riga < 0 || riga >= celle.length || colonna < 0 || colonna >= celle[0].length)
            return;

        Cella cellaCorrente = celle[riga][colonna];
        if (mappaCelleVisitate.get(cellaCorrente))   // Cella già visitata
            return;

        // Imposta come visitata la cella corrente e aggiorna vicini
        mappaCelleVisitate.put(cellaCorrente, true);
        aggiornaVicini(riga, colonna);

        // Rimuovi muro fra cella corrente e cella da cui è stata mandata la chiamata
        if (ultimaDirezione != null) {
            Direzioni inversoUltimaDirezione = ultimaDirezione.inverso();
            int[] incremento = inversoUltimaDirezione.getIncrementoRigaColonna();
            // Muro casella precedente
            celle[riga + incremento[0]][colonna + incremento[1]].rimuoviMuro(ultimaDirezione);
            // Muro casella corrente
            celle[riga][colonna].rimuoviMuro(inversoUltimaDirezione);
        }

        Direzioni direzioneRandom = null;
        while(mappaVicini.get(cellaCorrente) != 0) {
            direzioneRandom = Direzioni.generaDirezioneRandom();
           // Visita una cella adiacente random
           int[] incremento = direzioneRandom.getIncrementoRigaColonna();
           backtracking(riga + incremento[0], colonna + incremento[1], direzioneRandom);
        }
    }

    /**
     * Aggiorna i valori della mappa in modo che le celle adiacenti alla cella visitata abbiano un vicino in meno
     * Nessun controllo su parametri: il metodo va chiamato unicamente quando si è modificata una cella del labirinto
     * @param riga della cella visitata
     * @param colonna della cella visitata
     */
    private void aggiornaVicini(int riga, int colonna) {
        // vicino superiore
        if (riga + 1 < celle.length)
            mappaVicini.put(celle[riga + 1][colonna], Math.max(mappaVicini.get(celle[riga + 1][colonna]) - 1, 0));
        // vicino inferiore
        if (riga - 1 >= 0)
            mappaVicini.put(celle[riga - 1][colonna], Math.max(mappaVicini.get(celle[riga - 1][colonna]) - 1, 0));
        // vicino di destra
        if (colonna + 1 < celle[0].length)
            mappaVicini.put(celle[riga][colonna + 1], Math.max(mappaVicini.get(celle[riga][colonna + 1]) - 1, 0));
        // vicino di sinistra
        if (colonna - 1 >= 0)
            mappaVicini.put(celle[riga][colonna - 1], Math.max(mappaVicini.get(celle[riga][colonna - 1]) - 1, 0));
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

    /**
     * Fornisce versione String della HashMap mappaCelleVisitate
     * @return visualizzazione a matrice di 'x' per celle visitate, ' ' per celle non visitate
     */
    public String mappaCelleVisitate() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < celle.length; i++) {
            sb.append('[');
            for (int j = 0; j < celle[0].length; j++) {
                sb.append(mappaCelleVisitate.get(celle[i][j]) ? 'x' : ' ');

                if (j < celle[0].length - 1)
                    sb.append(' ');
            }
            sb.append(']')
                    .append('\n');
        }
        return sb.toString();
    }

}
