import java.util.ArrayList;
import java.util.List;

public class Linea {


    public List<List<Character>> grilla = new ArrayList<>();
    public List<Integer> contador = new ArrayList<>();
    private int altura;
    private int base;
    public String turno = "red";
    public char red = 'x';
    public char blue = 'o';
    public char modoDeJuego;
    public String ganador;


    public Linea(int base, int altura, char c) {

        if (base < 4 || altura < 4){
            throw new RuntimeException("Invalid setup.");
        }
        if (c != 'A' && c!= 'B' && c!= 'C' ){
            throw new RuntimeException("Invalid setup.");
        }


        for (int i = 1; i <= base; i++) {
            List lista = new ArrayList();
            for (int x = 1; x <= altura; x++) {
                lista.add(' ');

            }
            grilla.add(lista);
        }

        for(int x = 0; x <= base; x++){
            contador.add(0);
        }

        this.altura = altura;
        this.base = base;
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
        }
        else {
            turno = "blue";
            introduceMovement(columna, red);

        };

    }

    public void playBlueAt(int columna) {
        if (turno == "red") {
            throw new RuntimeException("Wrong turn!");
        }
        else {
            turno = "red";
            introduceMovement(columna, blue);

        };


    }

    public void introduceMovement(int columna, char ficha ) {
        int realIndex = columna - 1;
        int valueContador = contador.get(realIndex);

        List<Character> columnaToUpdate = grilla.get(realIndex);
        columnaToUpdate.set(valueContador, ficha);

        grilla.set(realIndex, columnaToUpdate);
        contador.set(realIndex, valueContador + 1);

    }

    public boolean verificarWin(int index) {

        char actual;

        if(modoDeJuego == 'A' || modoDeJuego == 'C') {
            // vertical
            int realIndex = index - 1;

            int rachaRojoV = 0;
            int rachaAzulV = 0;
            char previoVert = grilla.get(realIndex).get(0);

            for (int x = 0; x < altura; x++) {

                actual = grilla.get(realIndex).get(x);

                if ((previoVert == actual) && (previoVert == red)) {
                    rachaRojoV += 1;
                    rachaAzulV = 0;
                } else if ((previoVert == actual) && (previoVert == blue)) {
                    rachaAzulV += 1;
                    rachaRojoV = 0;
                }

                if (rachaAzulV == 4) {
                    ganador = "blue";
                    return true;
                }
                if (rachaRojoV == 4) {
                    ganador = "red";
                    return true;
                }

                previoVert = grilla.get(realIndex).get(x);

            }


            // horizontal

            int rachaRojoH = 0;
            int rachaAzulH = 0;
            char previoH = grilla.get(0).get(contador.get(realIndex) - 1);

            for (int x = 0; x < base; x++) {

                actual = grilla.get(x).get(contador.get(realIndex) - 1);

                if ((previoH == actual) && (previoH == red)) {
                    rachaRojoH += 1;
                    rachaAzulH = 0;
                } else if ((previoH == actual) && (previoH == blue)) {
                    rachaAzulH += 1;
                    rachaRojoH = 0;
                }

                if (rachaAzulH == 4) {
                    ganador = "blue";
                    return true;
                }
                if (rachaRojoH == 4) {
                    ganador = "red";
                    return true;
                }

                previoH = grilla.get(x).get(contador.get(realIndex) - 1);

            }

        }
        if(modoDeJuego == 'B' || modoDeJuego == 'C'){
            // diagonal.
            //return false;
        }

            return false;
        }



    }

