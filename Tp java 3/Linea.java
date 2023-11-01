import java.util.ArrayList;
import java.util.List;

public class Linea {

    public List<List<Character>> grilla = new ArrayList<>();
    private int altura;
    public String turno = "Red";


    public Linea(int base, int altura, char c) {

        for (int i = 1; i <= base; i++) {
            List lista = new ArrayList();
            for (int x = 1; x <= altura; x++) {
                lista.add(' ');

            }
            grilla.add(lista);
        }
    }

    public boolean show() {
        return true;

    }

    public boolean finished() {
        return false;
    }

    public void playRedAt(int prompt) {

    }

    public void playBlueAt(int prompt) {
    }
}
