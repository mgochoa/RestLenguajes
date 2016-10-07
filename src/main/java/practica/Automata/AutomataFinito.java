package practica.Automata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class AutomataFinito {

    //para iniciar:
    public String[][] automata;
    public String[][] modelo;
    public String[] aceptaciones;
    public String[] simbolos;
    //Para deterministico a no deterministico
    String[] row;
    ArrayList<String[]> rows;
    //Para simplificar:
    public String[] partRechaz;
    public String[] partAcepta;
    public List<String> partNuevas;
    public List<String> valoresPorParticion;
    String aux1, aux2;
    private Stack pila = new Stack();
    public Stack<String[]> estadosNuevos = new Stack<>();

    public AutomataFinito(String[][] automata) {
        this.automata = automata;

        getModelo();
    }

    public final void getModelo() {
        this.modelo = new String[automata.length - 1][automata[1].length - 1];
        this.simbolos = new String[automata[1].length - 2];
        this.aceptaciones = new String[automata.length - 1];
        for (int j = 2; j < automata[1].length; j++) {
            simbolos[j - 2] = automata[0][j];
        }
        for (int i = 1; i < automata.length; i++) {
            aceptaciones[i - 1] = automata[i][1];
        }

        for (int i = 1; i < automata.length; i++) {
            for (int j = 0; j < automata[1].length; j++) {

                if (j == 0) {
                    modelo[i - 1][j] = automata[i][j];
                } else if (j != 1) {
                    modelo[i - 1][j - 1] = automata[i][j];
                }

            }
        }
    }

    public String EncontrarTransicion(String estado, int simboloEntrada) {
        String e = "";
        for (int i = 1; i < automata.length; i++) {
            e = automata[i][0];
            if (e.equals(estado)) {
                return automata[i][simboloEntrada];
            }
        }
        return e;
    }

    public Boolean isAFND() {

        for (int i = 1; i < automata.length; i++) {
            for (int j = 2; j < automata[1].length; j++) {
                if (automata[i][j].contains(",")) {
                    return true;
                }
            }

        }
        return false;

    }

    public void AFNDtoAFD2() {
        //Metodo que quita las comas del automata y deja los valores en las celdas como una osla hilera
        String valor;
        String[] estadoSeparado;
        row = new String[modelo[1].length];
        rows = new ArrayList<>();

        //Apilo estados nuevos quitando extraños
        for (int j = 0; j < modelo[1].length; j++) {
            valor = modelo[0][j];
            if (j > 0 && modelo[0][j].contains(",")) {
                estadoSeparado = valor.split(",");
                if (esNuevo2(estadosNuevos, estadoSeparado)) {
                    estadosNuevos.add(estadoSeparado);
                    valor = valor.replace(",", "");
                }
                row[j] = valor;

            }

        }
        rows.add(row);
        row = new String[modelo[1].length];
        //Normalizo modelo.
        for (int i = 0; i < modelo.length; i++) {
            for (int j = 0; j < modelo[1].length; j++) {//for`s para recorrer matriz
                valor = modelo[i][j];
                if (modelo[i][j].contains(",")) {

                    valor = valor.replace(",", "");

                }
                modelo[i][j] = valor;

            }
        }
        if (!estadosNuevos.isEmpty()) {

        }

    }

    public boolean esNuevo2(Stack s, String[] o) {
        Stack<String[]> stack = s;

        Iterator<String[]> iter = stack.iterator();
        if (s.isEmpty()) {
            return true;
        }
        while (iter.hasNext()) {
            if (Arrays.hashCode(iter.next()) == Arrays.hashCode(o)) {
                return false;
            }
        }
        return true;
    }

}
