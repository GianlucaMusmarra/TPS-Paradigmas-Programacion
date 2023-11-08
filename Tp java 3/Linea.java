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
        return ganador != null;
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

        if(realIndex > base || grilla.get(realIndex).size() == altura + 1){ // medio hardcodeado ese +1... qcy
            throw new RuntimeException("Out of bounds!");
        }

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

                if (fichaPreviaV == red) {
                    contadorRojoV += 1;
                    contadorAzulV = 0;

                }
                else if (fichaPreviaV == blue) {
                    contadorAzulV += 1;
                    contadorRojoV = 0;
                }

                else{
                    contadorAzulV = 0;
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
            int alturaHorizontal = grilla.get(indiceColumna).size() - 1;
            List<Character> capaDeNivel = new ArrayList<>(); // Specify the type of elements in the list

            IntStream.range(0, base + 1).forEach(x -> capaDeNivel.add(fichaEnCasilla(x, alturaHorizontal)));

            for (int i = 0; i < capaDeNivel.size(); i++) {
                actualH = capaDeNivel.get(i);

                if (actualH == red) {
                    contadorRojoH += 1;
                    contadorAzulH = 0;
                }
                else if (actualH == blue) {
                    contadorAzulH += 1;
                    contadorRojoH = 0;
                }
                else{
                    contadorRojoH = 0;
                    contadorAzulH = 0;
                }

                if (contadorRojoH == 4) {
                    ganador = "red";
                }
                if (contadorAzulH == 4) {
                    ganador = "blue";
                }
            }


        }
        if (modoDeJuego == 'B' || modoDeJuego == 'C') {

            // diagonal creciente


            int alturaActual = grilla.get(indiceColumna).size() - 1;
            int baseActual = indiceColumna;


            // para descubrir donde empieza la diagonal , (x1, y1) = (x - min(x1,y1) , y - min(x,y) )
            // donde termina (x2 , y2) = ( x + (min(x,y) - max(x,y)) , y + (min(x,y) - max(x,y)) )

            int minimo = Math.min(baseActual, alturaActual);
            int maximo = Math.max(baseActual, alturaActual);

            int baseMinima = baseActual - minimo;
            int alturaMinima = alturaActual - minimo;

            baseActual = baseMinima;
            alturaActual = alturaMinima;


            List<Character> capaDeNivelC = new ArrayList<>();

            while (baseActual <= base && alturaActual <= altura) {
                capaDeNivelC.add(fichaEnCasilla(baseActual, alturaActual));

                baseActual++;
                alturaActual++;

            }

            char actualC;
            int contadorRojoC = 0; // Contador de fichas rojas en la diagonal creciente
            int contadorAzulC = 0; // Contador de fichas azules en la diagonal creciente


            for (int i = 0; i < capaDeNivelC.size(); i++) {
                actualC = capaDeNivelC.get(i);

                if (actualC == red) {
                    contadorRojoC += 1;
                    contadorAzulC = 0;
                }
                else if (actualC == blue) {
                    contadorAzulC += 1;
                    contadorRojoC = 0;
                }
                else{
                    contadorAzulC = 0;
                    contadorRojoC = 0;
                }

                if (contadorRojoC == 4) {
                    ganador = "red";
                }
                if (contadorAzulC == 4) {
                    ganador = "blue";
                }

            }

            // ahora la diagonal decreciente

            int alturaActualD = grilla.get(indiceColumna).size() - 1;
            int baseActualD = indiceColumna;

            int baseMinimaD = baseActualD;
            int alturaMinimaD = alturaActualD;

            while (alturaMinimaD > 0 && baseMinimaD < altura){ // crafteo el vertice... por asi decir
                baseMinimaD ++;
                alturaMinimaD--;
            }

            List<Character> capaDeNivelD = new ArrayList<>();

            while (baseMinimaD >= 0 && alturaMinimaD <= altura) {
                capaDeNivelD.add(fichaEnCasilla(baseMinimaD, alturaMinimaD));

                baseMinimaD--;
                alturaMinimaD++;

            }

            char actualD;
            int contadorRojoD = 0; // Contador de fichas rojas en la diagonal decreciente
            int contadorAzulD = 0; // Contador de fichas azules en la diagonal decreciente

            for (int i = 0; i < capaDeNivelD.size(); i++) {
                actualD = capaDeNivelD.get(i);

                if (actualD == red) {
                    contadorRojoD += 1;
                    contadorAzulD = 0;
                }
                if (actualD == blue) {
                    contadorAzulD += 1;
                    contadorRojoD = 0;
                }

                if (contadorRojoD == 4) {
                    ganador = "red";
                }
                if (contadorAzulD == 4) {
                    ganador = "blue";
                }

            }






        }
    }

        public char fichaEnCasilla(int columna, int alturaReal){ // aca no hace falta sacar los ifs
            if (alturaReal > grilla.get(columna).size() - 1) {
                return ' ';
            } else {
                return grilla.get(columna).get(alturaReal);
            }
        }
    }


