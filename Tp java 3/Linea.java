import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class Linea {


    List<List<Character>> grilla = new ArrayList<>();
    private final int indiceAlturaMax;
    private final int indiceBaseMax;

    public LinkedList<Turno> turno = new LinkedList<>();
    public char red = 'x';
    public char blue = 'o';

    public char gameMode;
    public LinkedList<GameMode> allGameModes = new LinkedList<>();

    public LinkedList<FinalResult> resultadoFinal = new LinkedList<>();
    public String getFinalResult(){return resultadoFinal.get(0).toString();}

    public Linea(int base, int altura, char c) {
        turno.add(new TurnoRed());
        turno.add(new TurnoBlue());

        if (base < 1 || altura < 1) {
            throw new RuntimeException("Invalid setup.");
        }

        allGameModes.add(new GameModeC());
        allGameModes.add(new GameModeB());
        allGameModes.add(new GameModeA());
        if (allGameModes.stream().noneMatch(gameMode->gameMode.isGameMode(c))) {
            throw new RuntimeException("Invalid setup.");
        }
        gameMode = c;

        resultadoFinal.add(new FinalResultNone());

        IntStream.range(0, base).forEach(x -> grilla.add(new ArrayList<>()));

        this.indiceAlturaMax = altura - 1;
        this.indiceBaseMax = base - 1;
    }


    public String show() {

        StringBuilder impression =new StringBuilder();
        impression.append("\n" + "Turno: ").append(turno.get(0).toString()).append("\n");

        IntStream.range(0,indiceAlturaMax+1)
                .mapToObj(i -> indiceAlturaMax - i)
                .forEach(i-> {
            IntStream.range(0,indiceAlturaMax+1).forEach(x ->{
                impression.append("|").append(fichaEnCasilla(x, i)).append("| ");
            });

            impression.append("\n");
        });


        IntStream.range(0,indiceAlturaMax+1).forEach(e ->
                impression.append("|^| ")
        );
        impression.append("\n");

        impression.append(resultadoFinal.get(0).printFinalResult());

        return impression.toString();
    }

    public boolean finished() {
        return !(resultadoFinal.get(0) instanceof FinalResultNone);
    }

    public void playRedAt(int columna) {
        turno.get(0).PlayRed();
        introduceMovement(columna, red);
        turno.addFirst(turno.removeLast());
    }

    public void playBlueAt(int columna) {
        turno.get(0).PlayBlue();
        introduceMovement(columna, blue);
        turno.addFirst(turno.removeLast());
    }

    public String getCurrentTurn(){
        return turno.get(0).toString();
    }

    public void introduceMovement(int columna, char ficha) {


        int realIndex = columna - 1;

        if(realIndex > indiceBaseMax || grilla.get(realIndex).size() == indiceAlturaMax + 1){ // medio hardcodeado ese +1... qcy
            throw new RuntimeException("Out of bounds!");
        }

        grilla.get(realIndex).add(ficha);

        verificarWin(realIndex); // le paso donde fue el ultimo movimiento
    }

    public void verificarWin(int indiceColumna) {

        resultadoFinal.addFirst(new FinalResultTie());
        IntStream.range(0, indiceBaseMax + 1).filter(i -> grilla.get(i).size() != indiceAlturaMax + 1)
                .forEach(e -> resultadoFinal.addFirst(new FinalResultNone()));

        if (gameMode == 'A' || gameMode == 'C') {
            int[] contadorAzulV = {0};
            int[] contadorRojoV = {0};

            grilla.get(indiceColumna).forEach(actualV -> {
                if (actualV == red) {
                    contadorRojoV[0]++;
                    contadorAzulV[0] = 0;
                } else if (actualV == blue) {
                    contadorAzulV[0]++;
                    contadorRojoV[0] = 0;
                } else {
                    contadorAzulV[0] = 0;
                    contadorRojoV[0] = 0;
                }
            });

            if (contadorRojoV[0] == 4) {
                resultadoFinal.addFirst(new FinalResultRed());
            }
            if (contadorAzulV[0] == 4) {
                resultadoFinal.addFirst(new FinalResultBlue());
            }

            int alturaHorizontal = grilla.get(indiceColumna).size() - 1;
            List<Character> capaDeNivel = new ArrayList<>(); // Specify the type of elements in the list

            IntStream.range(0, indiceBaseMax + 1).forEach(x -> capaDeNivel.add(fichaEnCasilla(x, alturaHorizontal)));

            checkWin(capaDeNivel);


        }
        if (gameMode == 'B' || gameMode == 'C') {

            // diagonal creciente


            int alturaActual = grilla.get(indiceColumna).size() - 1;
            int baseActual = indiceColumna;


            // para descubrir donde empieza la diagonal , (x1, y1) = (x - min(x1,y1) , y - min(x,y) )
            // donde termina (x2 , y2) = ( x + (min(x,y) - max(x,y)) , y + (min(x,y) - max(x,y)) )

            int minimo = Math.min(baseActual, alturaActual);

            int baseMinima = baseActual - minimo;
            int alturaMinima = alturaActual - minimo;

            baseActual = baseMinima;
            alturaActual = alturaMinima;


            List<Character> capaDeNivelC = new ArrayList<>();

            while (baseActual <= indiceBaseMax && alturaActual <= indiceAlturaMax) {
                capaDeNivelC.add(fichaEnCasilla(baseActual, alturaActual));

                baseActual++;
                alturaActual++;

            }

            checkWin(capaDeNivelC);

            // ahora la diagonal decreciente

            int alturaActualD = grilla.get(indiceColumna).size() - 1;

            int baseMinimaD = indiceColumna;
            int alturaMinimaD = alturaActualD;

            while (alturaMinimaD > 0 && baseMinimaD < indiceAlturaMax){ // crafteo el vertice... por asi decir
                baseMinimaD ++;
                alturaMinimaD--;
            }

            List<Character> capaDeNivelD = new ArrayList<>();

            while (baseMinimaD >= 0 && alturaMinimaD <= indiceAlturaMax) {
                capaDeNivelD.add(fichaEnCasilla(baseMinimaD, alturaMinimaD));

                baseMinimaD--;
                alturaMinimaD++;

            }

            checkWin(capaDeNivelD);
        }
    }

        public char fichaEnCasilla(int columna, int alturaReal){ // aca no hace falta sacar los ifs
            if (alturaReal > grilla.get(columna).size() - 1) {
                return ' ';
            } else {
                return grilla.get(columna).get(alturaReal);
            }
        }

        public void checkWin(List<Character> capaDeNivel){
            char actualC;
            int contadorRojoC = 0; // Contador de fichas rojas en la diagonal creciente
            int contadorAzulC = 0; // Contador de fichas azules en la diagonal creciente


            for (Character character : capaDeNivel) {
                actualC = character;

                if (actualC == red) {
                    contadorRojoC += 1;
                    contadorAzulC = 0;
                } else if (actualC == blue) {
                    contadorAzulC += 1;
                    contadorRojoC = 0;
                } else {
                    contadorAzulC = 0;
                    contadorRojoC = 0;
                }

                if (contadorRojoC == 4) {
                    resultadoFinal.addFirst(new FinalResultRed());
                }
                if (contadorAzulC == 4) {
                    resultadoFinal.addFirst(new FinalResultBlue());
                }

            }
        }
    }


