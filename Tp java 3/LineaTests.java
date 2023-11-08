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
    public void profundidadBaseCorrecta(){
        Linea linea = new Linea(4,4,'A'); // modificar dsp los argumentos. ni idea que poner

        assertEquals(4, linea.grilla.size());
    }

    @Test
    public void MovimientoAumentaAltura(){
        Linea linea = new Linea(4,4,'A'); // modificar dsp los argumentos. ni idea que poner

        linea.playRedAt(1);


        assertEquals(1 , linea.grilla.get(0).size());
        assertEquals(0 , linea.grilla.get(1).size());
        assertEquals(0 , linea.grilla.get(2).size());
        assertEquals(0 , linea.grilla.get(3).size());
    }
    @Test
    public void DosMovimientosAumentanDosAlturaa(){
        Linea linea = new Linea(4,4,'A'); // modificar dsp los argumentos. ni idea que poner

        linea.playRedAt(1);
        linea.playBlueAt(1);


        assertEquals(2 , linea.grilla.get(0).size());
        assertEquals(0 , linea.grilla.get(1).size());
        assertEquals(0 , linea.grilla.get(2).size());
        assertEquals(0 , linea.grilla.get(3).size());
    }

    @Test
    public void excedeLimiteVertical(){
        Linea linea = new Linea(4,4,'A'); // modificar dsp los argumentos. ni idea que poner

        linea.playRedAt(1);
        linea.playBlueAt(1);
        linea.playRedAt(1);
        linea.playBlueAt(1);

        assertThrowsLike(()->linea.playRedAt(1), "Out of bounds!");

    }
    @Test
    public void excedeLimiteHorizontal(){
        Linea linea = new Linea(4,4,'A'); // modificar dsp los argumentos. ni idea que poner

        assertThrowsLike(()->linea.playRedAt(5), "Out of bounds!");

    }

    @Test
    public void rojoGanaVerticalmente(){
        Linea linea = new Linea(4,4,'A'); // modificar dsp los argumentos. ni idea que poner
        linea.playRedAt(1);
        linea.playBlueAt(2);
        linea.playRedAt(1);
        linea.playBlueAt(2);
        linea.playRedAt(1);
        linea.playBlueAt(2);
        linea.playRedAt(1);

        assertEquals("red", linea.ganador);
        assertTrue(linea.finished());
    }
    @Test
    public void azulGanaVerticalmente(){
        Linea linea = new Linea(4,4,'A'); // modificar dsp los argumentos. ni idea que poner

        linea.playRedAt(1);
        linea.playBlueAt(2);
        linea.playRedAt(1);
        linea.playBlueAt(2);
        linea.playRedAt(1);
        linea.playBlueAt(2);
        linea.playRedAt(1);
        linea.playBlueAt(2);

        assertEquals("blue", linea.ganador);
        assertTrue(linea.finished());
    }

    @Test
    public void rojoGanaHorizontal(){
        Linea linea = new Linea(4,4,'A'); // modificar dsp los argumentos. ni idea que poner

        linea.playRedAt(1);
        linea.playBlueAt(1);
        linea.playRedAt(2);
        linea.playBlueAt(2);
        linea.playRedAt(3);
        linea.playBlueAt(3);
        linea.playRedAt(4);

        assertEquals("red", linea.ganador);
        assertTrue(linea.finished());
    }

    @Test
    public void azulGanaHorizontal(){
        Linea linea = new Linea(4,4,'A'); // modificar dsp los argumentos. ni idea que poner


        linea.playRedAt(1);
        linea.playBlueAt(1);
        linea.playRedAt(2);
        linea.playBlueAt(2);
        linea.playRedAt(3);
        linea.playBlueAt(3);
        linea.playRedAt(1);
        linea.playBlueAt(4);
        linea.playRedAt(1);
        linea.playBlueAt(4);

        assertEquals("blue", linea.ganador);
        assertTrue(linea.finished());
    }

    @Test
    public void rojoGanaDiagonalCreciente(){
        Linea linea = new Linea(4, 4, 'B');

        linea.playRedAt(1);
        linea.playBlueAt(2);
        linea.playRedAt(2);
        linea.playBlueAt(3);
        linea.playRedAt(3);
        linea.playBlueAt(1);
        linea.playRedAt(3);
        linea.playBlueAt(4);
        linea.playRedAt(4);
        linea.playBlueAt(4);
        linea.playRedAt(4);

        assertEquals("red", linea.ganador);
        assertTrue(linea.finished());
    }

   @Test
   public void rojoGanaDiagonalDecreciente(){
       Linea linea = new Linea(4, 4, 'B');

       linea.playRedAt(1);
       linea.playBlueAt(2);
       linea.playRedAt(1);
       linea.playBlueAt(1);
       linea.playRedAt(1);
       linea.playBlueAt(2);
       linea.playRedAt(2);
       linea.playBlueAt(3);
       linea.playRedAt(3);
       linea.playBlueAt(2);
       linea.playRedAt(4);

       assertEquals("red", linea.ganador);
       assertTrue(linea.finished());

   }

   @Test
   public void azulGanaDiagonalCreciente(){

       Linea linea = new Linea(5, 5, 'B');

       linea.playRedAt(1);
       linea.playBlueAt(2);
       linea.playRedAt(3);
       linea.playBlueAt(3);
       linea.playRedAt(4);
       linea.playBlueAt(4);
       linea.playRedAt(1);
       linea.playBlueAt(4);
       linea.playRedAt(5);
       linea.playBlueAt(5);
       linea.playRedAt(5);
       linea.playBlueAt(5);

       assertEquals("blue", linea.ganador);
       assertTrue(linea.finished());
   }

   @Test
   public void azulGanaDiagonalDecreciente(){
       Linea linea = new Linea(5, 5, 'B');
       linea.playRedAt(2);
       linea.playBlueAt(1);
       linea.playRedAt(4);
       linea.playBlueAt(5);
       linea.playRedAt(3);
       linea.playBlueAt(4);
       linea.playRedAt(1);
       linea.playBlueAt(3);
       linea.playRedAt(1);
       linea.playBlueAt(3);
       linea.playRedAt(1);
       linea.playBlueAt(2);
       linea.playRedAt(2);
       linea.playBlueAt(2);

       assertEquals("blue", linea.ganador);
       assertTrue(linea.finished());
   }




    public void assertThrowsLike (Executable executable, String message ) {
        assertEquals (message,
                assertThrows( Exception.class, executable).getMessage() ); }
    }


