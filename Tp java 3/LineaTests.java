import org.junit.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.junit.Assert.*;

public class LineaTests {

    @Test
    public void gameNotFinish(){
        Linea linea = new Linea(5,5,'A');

        assertFalse(linea.finished());
    }
    @Test
    public void invalidArguments(){

        assertThrowsLike(() ->new Linea(3,3,'A'), "Invalid setup." );
    }
    @Test
    public void invalidGameMode(){

        assertThrowsLike(() ->new Linea(4,4,'X'), "Invalid setup." );
    }

    @Test
    public void redAlwaysStart(){
        Linea linea = new Linea(4,4,'A'); // modificar dsp los argumentos. ni idea que poner

        assertEquals("red", linea.turno);
    }

    @Test
    public void laBaseDeLaGrillaFunca(){
        Linea linea = new Linea(4,4,'A'); // modificar dsp los argumentos. ni idea que poner

        assertEquals(4, linea.grilla.size());

    }

    @Test
    public void laAlturaDeLaGrillaFunca(){
        Linea linea = new Linea(4,4,'A'); // modificar dsp los argumentos. ni idea que poner

        assertEquals(4, linea.grilla.get(0).size());
        assertEquals(4, linea.grilla.get(1).size());
        assertEquals(4, linea.grilla.get(2).size());

    }

    @Test
    public void laGrillaTieneTodasLasCoordenadasVacias(){
        Linea linea = new Linea(4,4,'A'); // modificar dsp los argumentos. ni idea que poner

        assertTrue(linea.grilla.get(0).stream().allMatch(Character -> Character.equals(' ')));
    }

    @Test
    public void moverCambiaDeTurno(){
        Linea linea = new Linea(4,4,'A'); // modificar dsp los argumentos. ni idea que poner
        linea.playRedAt(1);
        assertEquals("blue", linea.turno);

        linea.playBlueAt(1);
        assertEquals("red", linea.turno);


    }

    @Test
    public void noEsTurnoDeAzul(){
        Linea linea = new Linea(4,4,'A'); // modificar dsp los argumentos. ni idea que poner

        assertThrowsLike( () ->linea.playBlueAt(1), "Wrong turn!");
    }


    @Test
    public void noEsTurnoDeRojo(){
        Linea linea = new Linea(4,4,'A'); // modificar dsp los argumentos. ni idea que poner
        linea.playRedAt(1);

        assertThrowsLike( () ->linea.playRedAt(1), "Wrong turn!");
    }



    @Test
    public void turnoMueveBienRojo(){
        Linea linea = new Linea(4,4,'A');
        linea.playRedAt(1);
        char character = linea.grilla.get(0).get(0);

        assertEquals('x', character );

    }

    @Test public void turnoMueveBienAzul(){
        Linea linea = new Linea(4,4,'A');
        linea.playRedAt(1);
        linea.playBlueAt( 1);
        char firstCharacter = linea.grilla.get(0).get(0);
        char secondCharacter = linea.grilla.get(0).get(1);

        assertEquals('x', firstCharacter );
        assertEquals('o', secondCharacter);

    }

    @Test public void rojoGanaVerticalmente(){
        Linea linea = new Linea(4,4,'A');

        linea.playRedAt(1);
        linea.playBlueAt( 2);
        linea.playRedAt(1);
        linea.playBlueAt( 2);
        linea.playRedAt(1);
        linea.playBlueAt( 2);
        linea.playRedAt(1);

        assertTrue(linea.verificarWin(1));
        assertEquals("red", linea.ganador);

    }

    @Test public void AzulGanaVerticalmente(){
        Linea linea = new Linea(4,4,'A');

        linea.playRedAt(1);
        linea.playBlueAt( 2);
        linea.playRedAt(1);
        linea.playBlueAt( 2);
        linea.playRedAt(1);
        linea.playBlueAt( 2);
        linea.playRedAt(3);
        linea.playBlueAt(2);

        assertTrue(linea.verificarWin(2));
        assertEquals("blue", linea.ganador);


    }

    @Test public void rojoGanaHorizontal(){
        Linea linea = new Linea(4,4,'A');

        linea.playRedAt(1);
        linea.playBlueAt( 1);
        linea.playRedAt(2);
        linea.playBlueAt( 2);
        linea.playRedAt(3);
        linea.playBlueAt( 3);
        linea.playRedAt(4);

        assertTrue(linea.verificarWin(4));
        assertEquals("red", linea.ganador);


    }

    @Test public void azulGanaHorizontal(){
        Linea linea = new Linea(4,4,'A');

        linea.playRedAt(1);
        linea.playBlueAt( 1);
        linea.playRedAt(2);
        linea.playBlueAt( 2);
        linea.playRedAt(3);
        linea.playBlueAt( 3);
        linea.playRedAt(1);
        linea.playBlueAt( 4);
        linea.playRedAt(1);
        linea.playBlueAt( 4);

        assertTrue(linea.verificarWin(4));
        assertEquals("blue", linea.ganador);

    }
    @Test public void azulGanaHorizontalPeroEnModoDIagonal(){
        Linea linea = new Linea(4,4,'B');

        linea.playRedAt(1);
        linea.playBlueAt( 1);
        linea.playRedAt(2);
        linea.playBlueAt( 2);
        linea.playRedAt(3);
        linea.playBlueAt( 3);
        linea.playRedAt(1);
        linea.playBlueAt( 4);
        linea.playRedAt(1);
        linea.playBlueAt( 4);

        assertFalse(linea.verificarWin(4));

    }




    public void assertThrowsLike (Executable executable, String message ) {
        assertEquals (message,
                assertThrows( Exception.class, executable).getMessage() ); }
    }


