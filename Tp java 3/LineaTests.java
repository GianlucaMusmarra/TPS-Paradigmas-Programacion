import org.junit.Test;

import static org.junit.Assert.*;

public class LineaTests {

    @Test
    public void gameNotFinish(){
        Linea linea = new Linea(2,2,'g');

        assertFalse(linea.finished());
    }

    @Test
    public void redAlwaysStart(){
        Linea linea = new Linea(2,2,'g'); // modificar dsp los argumentos. ni idea que poner

        assertEquals("Red", linea.turno);
    }

    @Test
    public void laBaseDeLaGrillaFunca(){
        Linea linea = new Linea(3,2,'g'); // modificar dsp los argumentos. ni idea que poner

        assertEquals(3, linea.grilla.size());

    }

    @Test
    public void laAlturaDeLaGrillaFunca(){
        Linea linea = new Linea(3,4,'g'); // modificar dsp los argumentos. ni idea que poner

        assertEquals(4, linea.grilla.get(0).size());
        assertEquals(4, linea.grilla.get(1).size());
        assertEquals(4, linea.grilla.get(2).size());

    }

    @Test
    public void laGrillaTieneTodasLasCoordenadasVacias(){
        Linea linea = new Linea(3,4,'g'); // modificar dsp los argumentos. ni idea que poner

        assertTrue(linea.grilla.get(0).stream().allMatch(Character -> Character.equals(' ')));

    }

}
