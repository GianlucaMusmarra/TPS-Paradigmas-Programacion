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


    public Linea(int base, int altura, char c) {

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
            introduceMovement(columna, red);
            turno = "blue";
        };

    }

    public void playBlueAt(int columna) {
        if (turno == "red") {
            throw new RuntimeException("Wrong turn!");
        }
        else {
            introduceMovement(columna, blue);
            turno = "red";
        };


    }

    public void introduceMovement(int columna, char ficha ) {
        int realIndex = columna - 1;
        int valueContador = contador.get(realIndex);

        List<Character> columnaToUpdate = grilla.get(realIndex);
        columnaToUpdate.set(valueContador, ficha);

        grilla.set(realIndex, columnaToUpdate);
        contador.set(realIndex, valueContador + 1);

        verificarWin(realIndex); // ?? no se si hace falta
    }

    public boolean verificarWin(int index) {


        char actual;


        // vertical


           int rachaRojoV = 0;
           int rachaAzulV = 0;
            char previoVert = grilla.get(index).get(0);

            for (int x = 0; x < altura; x++) {

                actual = grilla.get(index).get(x);

                if ((previoVert == actual) && (previoVert == red)) {
                    rachaRojoV += 1;
                    rachaAzulV = 0;
                }
                else if ((previoVert == actual) && (previoVert == blue)) {
                    rachaAzulV += 1;
                    rachaRojoV = 0;
                }


                if(rachaAzulV == 4){
                    return true;
                }
                if(rachaRojoV == 4){
                    return true;
                }

                previoVert = grilla.get(index).get(x);

            }


         // horizontal

            int rachaRojoH = 0;
            int rachaAzulH = 0;
            char previoH = grilla.get(0).get(index);

            for (int x = 0; x < base; x++) {

                actual = grilla.get(x).get(index);

                if ((previoH == actual) && (previoH == red)) {
                    rachaRojoH += 1;
                    rachaAzulH = 0;
                }
                else if ((previoH == actual) && (previoH == blue)) {
                    rachaAzulH += 1;
                    rachaRojoH = 0;
                }

                if(rachaAzulH == 4){
                    return true;
                }
                if(rachaRojoH == 4){
                    return true;
                }

                previoH = grilla.get(x).get(index);

            }






           return false;
        }



    }

