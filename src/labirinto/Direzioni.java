package labirinto;

import java.util.Random;

public enum Direzioni {
    SU, SINISTRA, GIU, DESTRA;

    public static Direzioni generaDirezioneRandom() {
        Random r = new Random();
        return values()[r.nextInt(values().length)];
    }

    /**
     * Trasforma l'enum in un paio di incrementi: ad esempio, se chiamato su SU, restituisce [1,0],
     * ovvero una riga su, zero colonne dx/sx
     * @return l'incremento da applicare ad un elemento di una matrice per spostarsi secondo la direzione
     */
    public int[] getIncrementoRigaColonna() {
        switch (this) {
            case SU -> {
                return new int[] {1,0};
            }
            case GIU -> {
                return new int[] {-1,0};
            }
            case DESTRA -> {
                return new int[] {0,1};
            }
            case SINISTRA -> {
                return new int[] {0,-1};
            }
        }
        return null;
    }

}
