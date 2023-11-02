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

        return verificarWin();
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

        verificarWin(); // ?? no se si hace falta
    }

    public boolean verificarWin() {
        int rachaRojo;
        int rachaAzul;

        char previo;
        char actual;


        // vertical

        for(int i = 0 ; i < base ; i ++) {
            rachaRojo = 0;
            rachaAzul = 0;
            previo = grilla.get(i).get(0);

            for (int x = 0; x < altura; x++) {

                actual = grilla.get(i).get(x);

                if ((previo == actual) && (previo == red)) {
                    rachaRojo += 1;
                    rachaAzul = 0;
                }
                else if ((previo == actual) && (previo == blue)) {
                    rachaAzul += 1;
                    rachaRojo = 0;
                }


                if(rachaAzul == 4){
                    return true;
                }
                if(rachaRojo == 4){
                    return true;
                }

                previo = grilla.get(i).get(x);

            }

        }
         // horizontal
        for(int i = 0 ; i < altura ; i ++) {
            rachaRojo = 0;
            rachaAzul = 0;
            previo = grilla.get(0).get(i);

            for (int x = 0; x < base; x++) {

                actual = grilla.get(x).get(i);

                if ((previo == actual) && (previo == red)) {
                    rachaRojo += 1;
                    rachaAzul = 0;
                }
                else if ((previo == actual) && (previo == blue)) {
                    rachaAzul += 1;
                    rachaRojo = 0;
                }


                if(rachaAzul == 4){
                    return true;
                }
                if(rachaRojo == 4){
                    return true;
                }

                previo = grilla.get(x).get(i);

            }

        }






           return false;
        }



    }

