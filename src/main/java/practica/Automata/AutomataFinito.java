package practica.Automata;

import java.util.ArrayList;
import java.util.List;

public class AutomataFinito {

    //para iniciar:
    public String[][] automata;

    //Para simplificar:
    public String[] partRechaz;
    public String[] partAcepta;
    public List<String> partNuevas;
    public List<String> valoresPorParticion;
    String aux1, aux2;

    public AutomataFinito() {//constructor de ejemplo 
        automata = new String[6][4];
        //Cabeceras
        automata[0][0] = "-";
        automata[0][1] = "-";

        //Simbolos entrada
        automata[0][2] = "0";
        automata[0][3] = "1";

        //Estados
        automata[1][0] = "A";
        automata[2][0] = "B";
        automata[3][0] = "C";
        automata[4][0] = "D";
        automata[5][0] = "E";

        //Aceptacion v Rechazo
        automata[1][1] = "0";
        automata[2][1] = "0";
        automata[3][1] = "1";
        automata[4][1] = "1";
        automata[5][1] = "0";

        //Transiciones
        automata[1][2] = "B";
        automata[2][2] = "B";
        automata[3][2] = "C";
        automata[4][2] = "B";
        automata[5][2] = "E";

        automata[1][3] = "D";
        automata[2][3] = "C";
        automata[3][3] = "B";
        automata[4][3] = "D";
        automata[5][3] = "E";
    }

    public String[][] EliminarExtranos() {
        aux1 = automata[1][0];
        List<String> aux = new ArrayList<>();
        String[][] nuevo = new String[automata.length][automata[0].length];
        int cantidad = 2;
        for (int i = 0; i < nuevo.length; i++) {
            for (int j = 0; j < nuevo[0].length; j++) {
                nuevo[i][j] = "-";
            }
        }
        for (int j = 2; j < automata[0].length; j++) {
            aux2 = automata[1][j];
            aux.add(aux2);
            cantidad++;
        }
        nuevo[1][0] = automata[1][0];
        for (int i = 1; i < nuevo.length; i++) {
            nuevo[i][1] = automata[i][1];
        }
        for (int i = 1; i <= aux.size(); i++) {
            nuevo[i + 1][1] = aux.get(i - 1);
            nuevo[i + 1][0] = aux.get(i - 1);
        }

        for (int i = 2; i < cantidad; i++) {
            aux1 = nuevo[i][0];
            for (int j = 2; j < automata[0].length; j++) {
                aux2 = EncontrarTransicion(aux1, j);
                if (!ExisteEstado(aux2, nuevo)) {
                    nuevo[EncontrarEstado("-", nuevo)][0] = aux2;
                    cantidad++;
                }
                nuevo[i][j] = aux2;
            }
        }
        System.out.println("");
        for (int i = 0; i < nuevo.length; i++) {
            System.out.println("fila: " + i);
            for (int j = 0; j < nuevo[0].length; j++) {
                System.out.println("    Columna: " + j);
                System.out.println("    valor: " + nuevo[i][j]);
            }
        }
        cantidad = automata.length - (automata.length - cantidad);
        System.out.println(cantidad);
        String[][] autoF = new String[cantidad][automata[0].length];
        for (int i = 0; i < autoF.length; i++) {
            for (int j = 0; j < autoF[0].length; j++) {
                autoF[i][j] = nuevo[i][j];
            }
        }
        
        for (int i = 2; i < autoF[0].length; i++) {
            autoF[0][i] = automata[0][i];
        }
        
        for (int i = 1; i < autoF.length; i++) {
            autoF[i][0] = nuevo[i][0];
        }
        
        int contador = 1;
        for (int i = 1; i < nuevo.length; i++) {
            if (!nuevo[i][0].equals("-")) {
                for (int j = 2; j < nuevo[0].length; j++) {
                    autoF[contador][j] = nuevo[i][j];
                    System.out.println("autoF["+contador+"]["+j+"] = "+autoF[contador][j]);
                }                
                contador++;
            }
        }
        automata = autoF;

        for (int i = 0; i < autoF.length; i++) {
            System.out.println("fila: " + i);
            for (int j = 0; j < autoF[0].length; j++) {
                System.out.println("    Columna: " + j);
                System.out.println("    valor: " + autoF[i][j]);
            }
        }
        return autoF;
    }

    public int EncontrarEstado(String estado, String[][] autom) {
        int cont = 0;
        for (int i = 1; i < automata.length; i++) {
            if (autom[i][0].equals(estado)) {
                return i;
            }
        }
        return -1;
    }

    public boolean ExisteEstado(String estado, String[][] autom) {
        for (int i = 1; i < automata.length; i++) {
            if (autom[i][0].equals(estado)) {
                return true;
            }
        }
        return false;
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

    public void AgruparEquivalentes() {
        //Primer paso: Cuento cuántos son de aceptación y cuántos de rechazo
        int contRech = 0;
        int contAcep = 0;
        aux1 = "";
        aux2 = "";
        for (int i = 1; i < automata.length; i++) {
            if (automata[i][1].equals("0")) {
                contRech++;
                aux1 += automata[i][0] + " ";
            } else {
                contAcep++;
                aux2 += automata[i][0] + " ";
            }
        }
        //Segundo paso: Lleno las particiones con los estados de aceptación o rechazo
        partAcepta = aux2.split(" ");
        partRechaz = aux1.split(" ");
        aux2 = "";
        aux1 = "";
        System.out.println("Los que aceptan:");
        for (int i = 0; i < partAcepta.length; i++) {
            aux2 += partAcepta[i];
        }
        System.out.println(aux2);
        System.out.println("Los que rechazan:");
        for (int i = 0; i < partRechaz.length; i++) {
            aux1 += partRechaz[i];
        }
        System.out.println(aux1);
        partNuevas = new ArrayList<>();
        partNuevas.add(0, "0");
        partNuevas.add(1, "1");
        valoresPorParticion = new ArrayList<>();
        valoresPorParticion.add(0, aux1);
        valoresPorParticion.add(1, aux2);

        //Segundo: miro cada una de las transiciones de las particiones con los SE
        aux1 = "";

        for (int i = 0; i < partNuevas.size(); i++) {
            System.out.println("Voy en la particion: " + i);
            for (int j = 2; j < automata[0].length; j++) {
                System.out.println("particiones: " + partNuevas);
                System.out.println("Valores: " + valoresPorParticion);
                aux1 = IterarEntreParticiones(j, i);
                aux1 = aux1.replaceAll(" ", "");
                VerificarTransiciones(aux1, i);
            }
        }
        System.out.println("Particiones simplificadas: "+ valoresPorParticion);
        automata = CrearAutomataSimplificado();
        aux2 = "";
        System.out.println(aux2);
        for (int i = 0; i < automata.length; i++) {
            aux2 += i + "   ";
            for (int j = 0; j < automata[0].length; j++) {
                aux2 += "  " + automata[i][j];
            }
            System.out.println(aux2);
            aux2 = "";
        }
    }

    public String IterarEntreParticiones(int indiceSimboloEntrada, int particion) {
        //Empiezo con la particion de rechazo (P0) cuando llega un SE específico
        int numSE = indiceSimboloEntrada;
        System.out.println("P" + particion + "/" + automata[0][numSE] + " : ");
        String iterador = "";
        aux1 = aux2 = "";
        if (particion == 0 && valoresPorParticion.get(particion).length() > 1) {
            for (int i = 0; i < partRechaz.length; i++) {
                aux1 = partRechaz[i];
                for (int j = 1; j < automata.length; j++) {
                    if (automata[j][0].equals(aux1)) {
                        iterador = automata[j][numSE] + " ";
                        System.out.println("    " + aux1 + " -> " + iterador);
                        aux2 += iterador;
                    }
                }
            }
        } else if (particion == 1 && valoresPorParticion.get(particion).length() > 1) {
            for (int i = 0; i < partAcepta.length; i++) {
                aux1 = partAcepta[i];
                for (int j = 1; j < automata.length; j++) {
                    if (automata[j][0].equals(aux1)) {
                        iterador = automata[j][numSE] + " ";
                        System.out.println("    " + aux1 + " -> " + iterador);
                        aux2 += iterador;
                    }
                }
            }
        } else if (particion > 1 && valoresPorParticion.get(particion).length() > 1) {
            for (int i = 0; i < valoresPorParticion.size(); i++) {
                aux1 = "" + valoresPorParticion.get(i).charAt(i);
                for (int j = 1; j < automata.length; j++) {
                    if (automata[j][0].equals(aux1)) {
                        iterador = automata[j][numSE] + " ";
                        System.out.println("    " + aux1 + " -> " + iterador);
                        aux2 += iterador;
                    }
                }
            }
        }
        return aux2;
    }

    public void VerificarTransiciones(String estados, int particion) {
        if (estados.length() > 1) {
            String iterador = "";
            String auxiliar = "";
            String auxiliar2 = "";
            String sobrante = "";
            int cantidad = partNuevas.size() - 1;
            for (int i = 0; i < estados.length(); i++) {
                iterador = "" + estados.charAt(i);
                auxiliar += BuscarEstadoEnParticiones(iterador);
            }
            iterador = estados.charAt(0) + ",";
            auxiliar2 = valoresPorParticion.get(particion).charAt(0) + ",";
            for (int i = 1; i < auxiliar.length(); i++) {
                if (Character.toString(auxiliar.charAt(i)).equals("" + auxiliar.charAt(i - 1))) {
                    iterador += estados.charAt(i) + ",";
                    auxiliar2 += valoresPorParticion.get(particion).charAt(i);
                } else {
                    sobrante += "" + valoresPorParticion.get(particion).charAt(i);
                }
            }
            partRechaz = auxiliar2.split(",");
            auxiliar2 = auxiliar2.replaceAll(",", "");
            valoresPorParticion.remove(particion);
            valoresPorParticion.add(particion, auxiliar2);
            auxiliar = auxiliar.replaceAll("" + auxiliar.charAt(0), "");
            if (!sobrante.equals("")) {
                iterador = "" + sobrante.charAt(0);
                while (auxiliar.length() > 0) {
                    for (int i = 1; i < auxiliar.length(); i++) {
                        if (Character.toString(auxiliar.charAt(i)).equals("" + auxiliar.charAt(0))) {
                            iterador += sobrante.charAt(i);
                        }
                    }
                    cantidad++;
                    partNuevas.add(cantidad, "" + cantidad);
                    valoresPorParticion.add(cantidad, iterador);
                    auxiliar = auxiliar.replaceAll("" + auxiliar.charAt(0), "");
                }
            }
        }
    }

    public String BuscarEstadoEnParticiones(String estado) {//retorna la particion en la que está el estado
        for (int i = 0; i < partRechaz.length; i++) {
            if (estado.equals(partRechaz[i])) {
                return "0";
            }
        }
        for (int i = 0; i < partAcepta.length; i++) {
            if (estado.equals(partAcepta[i])) {
                return "1";
            }
        }
        for (String i : valoresPorParticion) {
            //System.out.println("Busco: "+ estado + "encuentro " + i);
            if (i.contains(estado)) {
                // System.out.println("Encontrado: " + i + ": "+ estado);
                return partNuevas.get(valoresPorParticion.indexOf(i));
            }
        }
        return "-- No encontrado --";
    }

    public String[][] CrearAutomataSimplificado() {
        String[][] nuevo = new String[partNuevas.size() + 1][automata[0].length];

        //posiciones inservibles, nunca se deben tener en cuenta        
        nuevo[0][0] = "--";
        nuevo[0][1] = "--";

        //La primera fila son los símbolos de entrada
        for (int i = 2; i < automata[0].length; i++) {
            nuevo[0][i] = automata[0][i];
        }
        //La primera columna son los estados
        for (int i = 0; i < valoresPorParticion.size(); i++) {
            nuevo[i + 1][0] = valoresPorParticion.get(i);
        }

        for (int i = 0; i < valoresPorParticion.size(); i++) {

        }

        return nuevo;
    }
}