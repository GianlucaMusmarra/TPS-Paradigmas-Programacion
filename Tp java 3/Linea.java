import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Linea {


    List<List<Character>> grilla = new ArrayList<>();
    private int altura;
    private int base;
    public String turno = "red";
    public char red = 'x';
    public char blue = 'o';
    public char modoDeJuego;
    public String ganador;


    public Linea(int base, int altura, char c) {

        if (base < 4 || altura < 4) {
            throw new RuntimeException("Invalid setup.");
        }
        if (c != 'A' && c != 'B' && c != 'C') {
            throw new RuntimeException("Invalid setup.");
        }

        IntStream.range(0, base).forEach(x -> grilla.add(new ArrayList()));

        this.altura = altura - 1; // esta bien restar?
        this.base = base - 1;
        modoDeJuego = c;
    }


    public boolean show() {
        return true;
    }

    public boolean finished() {
        return false;
    }

    public void playRedAt(int columna) {
        if (turno == "blue") {
            throw new RuntimeException("Wrong turn!");
        } else {
            turno = "blue";
            introduceMovement(columna, red);

        }
        ;

    }

    public void playBlueAt(int columna) {
        if (turno == "red") {
            throw new RuntimeException("Wrong turn!");
        } else {
            turno = "red";
            introduceMovement(columna, blue);

        }
        ;


    }

    public void introduceMovement(int columna, char ficha) {
        int realIndex = columna - 1;

        grilla.get(realIndex).add(ficha);

        verificarWin(realIndex); // le paso donde fue el ultimo movimiento


    }

    public void verificarWin(int indiceColumna) {
        // chequeo vertical


        if (modoDeJuego == 'A' || modoDeJuego == 'C') {
            int contadorAzulV = 0;
            int contadorRojoV = 0;
            int actualV;
            int fichaPreviaV = grilla.get(indiceColumna).get(0);

            for (int i = 0; i <= grilla.get(indiceColumna).size() - 1; i++) {
                actualV = grilla.get(indiceColumna).get(i);

                if (actualV == fichaPreviaV && fichaPreviaV == red) {
                    contadorRojoV += 1;
                    contadorAzulV = 0;

                }
                if (actualV == fichaPreviaV && fichaPreviaV == blue) {
                    contadorAzulV += 1;
                    contadorRojoV = 0;
                }

                if (contadorRojoV == 4) {
                    ganador = "red";
                }
                if (contadorAzulV == 4) {
                    ganador = "blue";
                }

                fichaPreviaV = actualV;

            }




            int contadorAzulH = 0;
            int contadorRojoH = 0;
            char actualH;
            int alturaHorizontal = grilla.get(indiceColumna).size() -1;
            List<Character> capaDeNivel = new ArrayList<>(); // Specify the type of elements in the list

            IntStream.range(0, base + 1).forEach(x -> capaDeNivel.add(verificarCasillas(x, alturaHorizontal)));

            char fichaPreviaH = capaDeNivel.get(0);

            for (int i = 1; i < base; i++) {
                actualH = capaDeNivel.get(i);

                if (actualH == fichaPreviaH && fichaPreviaH == red) {
                    contadorRojoH += 1;
                    contadorAzulH = 0;
                }
                if (actualH == fichaPreviaH && fichaPreviaH == blue) {
                    contadorAzulH += 1;
                    contadorRojoH = 0;
                }

                if (contadorRojoH == 4) {
                    ganador = "red";
                }
                if (contadorAzulH == 4) {
                    ganador = "blue";
                }

                fichaPreviaH = actualH;
            }



        }
        if (modoDeJuego == 'B' || modoDeJuego == 'C') {
            // chequeo diagonal
        }


    }

    public char verificarCasillas(int columna, int alturaReal) {

        if (alturaReal > grilla.get(columna).size() - 1) {
            return ' ';
        } else {
            return grilla.get(columna).get(alturaReal);
        }
    }
}

