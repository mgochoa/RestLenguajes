package practica.Automata;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class AutomataFinito {

	// para iniciar:
	public String[][] automata;

	// Para simplificar:
	public String[] partRechaz;
	public String[] partAcepta;
	public List<String> partNuevas;
	public List<String> valoresPorParticion;
	String aux1, aux2;
	Stack estadosNuevos;
	Stack estadosViejos;
	boolean particionDividida = false;
	boolean primeraPasada = false;

	public AutomataFinito(String[][] automata) {
		this.automata = automata;
	}

	public AutomataFinito() {// constructor de ejemplo
		automata = new String[5][4];
		// Cabeceras
		automata[0][0] = "-";
		automata[0][1] = "-";

		// Simbolos entrada
		automata[0][2] = "0";
		automata[0][3] = "1";

		// Estados
		automata[1][0] = "A";
		automata[2][0] = "B";
		automata[3][0] = "C";
		automata[4][0] = "D";

		// Aceptacion v Rechazo
		automata[1][1] = "0";
		automata[2][1] = "0";
		automata[3][1] = "1";
		automata[4][1] = "1";
		// Transiciones
		automata[1][2] = "C";
		automata[2][2] = "D";
		automata[3][2] = "B";
		automata[4][2] = "A";

		automata[1][3] = "C";
		automata[2][3] = "D";
		automata[3][3] = "B";
		automata[4][3] = "A";

	}

	public void showAutomata() {
		aux2 = new String();
		for (int i = 0; i < automata.length; i++) {
			aux2 += i + "   ";
			for (int j = 0; j < automata[0].length; j++) {
				aux2 += "  " + automata[i][j];
			}
			System.out.println(aux2);
			aux2 = "";
		}
	}

	public boolean nuevoStack(Stack s, String o) {
		Stack stack = s;

		Iterator iter = stack.iterator();
		if (s.isEmpty()) {
			return true;
		}
		while (iter.hasNext()) {
			if (iter.next().hashCode() == o.hashCode()) {
				return false;
			}
		}
		return true;

	}

	public void EliminarExtranos() {
		estadosNuevos = new Stack();
		estadosViejos = new Stack();
		estadosViejos.add(automata[1][0]);

		int col = automata[1].length;
		String[] row = new String[automata[1].length];
		ArrayList<String[]> rows = new ArrayList<>();

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < col; j++) {

				row[j] = automata[i][j];
				if (i == 1 && j > 1) {
					if (nuevoStack(estadosNuevos, row[j])) {
						estadosNuevos.add(row[j]);
					}
				}

			}
			rows.add(row);
			row = new String[automata[1].length];
		}
		while (!estadosNuevos.empty()) {
			boolean aceptacion = false;

			String estadoNuevo = (String) estadosNuevos.peek();

			for (int j = 0; j < col; j++) {
				switch (j) {
				case 0:
					row[j] = estadoNuevo;
					break;
				case 1:
					row[j] = automata[EncontrarEstado(estadoNuevo, automata)][1];
					break;
				default:
					row[j] = EncontrarTransicion(estadoNuevo, j);
					boolean first = (nuevoStack(estadosViejos, row[j]));
					boolean scnd = (nuevoStack(estadosNuevos, row[j]));
					boolean thrd = (!row[j].equals(estadoNuevo));
					if (first & scnd & thrd) {
						estadosViejos.add(estadosNuevos.pop());
						estadosNuevos.add(row[j]);
						aceptacion = true;
					}
					break;
				}
			}
			if (!aceptacion) {
				estadosViejos.add(estadosNuevos.pop());
			}

			rows.add(row);
			row = new String[automata[1].length];
			aceptacion = false;
		}
		automata = rows.toArray(new String[rows.size()][automata[1].length]);
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

	public int EncontrarEstadoContenido(String estado, String[][] autom) {
		int cont = 0;
		for (int i = 1; i < automata.length; i++) {
			if (autom[i][0].contains((estado))) {
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
		// Primer paso: Cuento cuántos son de aceptación y cuántos de rechazo
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
		// Segundo paso: Lleno las particiones con los estados de aceptación o
		// rechazo
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

		// Segundo: miro cada una de las transiciones de las particiones con los
		// SE
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
		primeraPasada = true;
		aux1 = "";
		System.out.println("luego de una pasada, Particiones: " + partNuevas);
		System.out.println("luego de una pasada, Valores: " + valoresPorParticion);
		if (particionDividida) {
			for (int i = 0; i < partNuevas.size(); i++) {
				System.out.println("Voy en la particion: " + i);
				for (int j = 2; j < automata[0].length; j++) {
					aux1 = IterarEntreParticiones(j, i);
					aux1 = aux1.replaceAll(" ", "");
					VerificarTransiciones(aux1, i);
				}
			}
		}
		System.out.println("Particiones simplificadas: " + valoresPorParticion);
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
		// Empiezo con la particion de rechazo (P0) cuando llega un SE
		// específico
		int numSE = indiceSimboloEntrada;
		System.out.println("P" + particion + "/" + automata[0][numSE] + " : ");
		String iterador = "";
		aux1 = aux2 = "";
		if (!primeraPasada && particion == 0 && valoresPorParticion.get(particion).length() > 1) {
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
		} else if (!primeraPasada && particion == 1 && valoresPorParticion.get(particion).length() > 1) {
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
		} else if (primeraPasada && valoresPorParticion.get(particion).length() > 1) {
			for (int i = 0; i < valoresPorParticion.size(); i++) {
				aux1 = "" + valoresPorParticion.get(i);
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
			System.out.println("Voy a remover " + particion);
			valoresPorParticion.remove(particion);
			valoresPorParticion.add(particion, auxiliar2);
			System.out.println("Voy a añadir a " + particion + " esto : " + auxiliar2);
			auxiliar = auxiliar.replaceAll("" + auxiliar.charAt(0), "");
			if (!sobrante.equals("")) {
				particionDividida = true;
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
			} else {
				particionDividida = false;
			}
		}
	}

	public String BuscarEstadoEnParticiones(String estado) {// retorna la
															// particion en la
															// que está el
															// estado
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
			// System.out.println("Busco: "+ estado + "encuentro " + i);
			if (i.contains(estado)) {
				// System.out.println("Encontrado: " + i + ": "+ estado);
				return partNuevas.get(valoresPorParticion.indexOf(i));
			}
		}
		return "-- No encontrado --";
	}

	public String[][] CrearAutomataSimplificado() {
		String[][] nuevo = new String[partNuevas.size() + 1][automata[0].length];

		// posiciones inservibles, nunca se deben tener en cuenta
		nuevo[0][0] = "--";
		nuevo[0][1] = "--";

		// La primera fila son los símbolos de entrada
		for (int i = 2; i < automata[0].length; i++) {
			nuevo[0][i] = automata[0][i];
		}
		// La primera columna son los estados
		for (int i = 0; i < valoresPorParticion.size(); i++) {
			nuevo[i + 1][0] = valoresPorParticion.get(i);
			String[] estadoNuevo = valoresPorParticion.get(i).split("");
			for (int j = 2; j < automata[1].length; j++) {
				if (estadoNuevo.length > 1) {
					String append = "";
					for (int k = 0; k < estadoNuevo.length; k++) {
						if (!append.contains(EncontrarTransicion(estadoNuevo[k], j))) {
							append = append.concat(EncontrarTransicion(estadoNuevo[k], j));
						}

					}
					nuevo[i + 1][j] = append;
				} else {
					nuevo[i + 1][j] = EncontrarTransicion(estadoNuevo[0], j);
				}
			}
		}
		//Encontrar el estado de aceptacion o rechazo
		for	(int i = 1; i < nuevo.length; i++){			
			for(int j = 1; j< automata.length; j++){
				if	(nuevo[i][0].contains(automata[j][0])){
					nuevo[i][1]= automata[j][1];
				}
			}
		}
		//Encontrar las transiciones agrupadas
		for	(int i = 1; i < nuevo.length; i++){
			for(int j = 2; j < nuevo[0].length; j++){
				for(int k = 1; k< nuevo.length; k++){
					if(nuevo[k][0].contains(nuevo[i][j])){
						nuevo[i][j] = nuevo[k][0];
					}
				}
			}
		}
			
		return nuevo;
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

	public void AFNDTOAFD() {
		estadosNuevos = new Stack();
		estadosViejos = new Stack();

		int col = automata[1].length;
		String[] row = new String[automata[1].length];
		ArrayList<String[]> rows = new ArrayList<>();

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < col; j++) {
				if (i == 0) {
					row[j] = automata[i][j];
				} else if (i == 1 & j <= 1) {
					row[j] = automata[i][j];
				} else if (i == 1 & j > 1) {
					if (automata[i][j].contains(",") & nuevoStack(estadosNuevos, automata[i][j].replace(",", ""))) {
						row[j] = automata[i][j].replace(",", "");
						estadosNuevos.add(row[j]);

					} else if (nuevoStack(estadosNuevos, automata[i][j])) {
						row[j] = automata[i][j];
						estadosNuevos.add(row[j]);
					}

				}

			}
			rows.add(row);
			row = new String[automata[1].length];
		}
		while (!estadosNuevos.empty()) {
			boolean aceptacion = false;

			String estadoNuevo = (String) estadosNuevos.peek();

			for (int j = 0; j < col; j++) {
				switch (j) {
				case 0:

					row[j] = estadoNuevo;

					break;
				case 1:
					if (estadoNuevo.length() > 1) {
						boolean acept = false;
						for (String e : estadoNuevo.split("")) {
							if ((automata[EncontrarEstado(e, automata)][1].equals("1"))) {
								acept = true;
							}
						}
						if (acept) {
							row[j] = "1";
						} else {
							row[j] = "0";
						}

					} else {
						row[j] = automata[EncontrarEstado(estadoNuevo, automata)][1];
					}
					break;
				default:
					if (estadoNuevo.length() > 1) {
						String append = "";
						for (String e : estadoNuevo.split("")) {
							if (EncontrarTransicion(e, j).contains(",")) {
								for (String es : EncontrarTransicion(e, j).split(",")) {
									if (!append.contains(es)) {
										append = append.concat(es);
									}
								}

							} else {
								if (!append.contains(EncontrarTransicion(e, j))) {
									append = append.concat(EncontrarTransicion(e, j));
								}
							}
						}

						char[] ar = append.toCharArray();
						Arrays.sort(ar);
						String sorted = String.valueOf(ar);
						row[j] = sorted;
						boolean first = (nuevoStack(estadosViejos, row[j]));
						boolean scnd = (nuevoStack(estadosNuevos, row[j]));
						boolean thrd = (!row[j].equals(estadoNuevo));

						if (first & scnd & thrd) {
							if (estadosNuevos.peek().equals(estadoNuevo)) {
								estadosViejos.add(estadosNuevos.pop());
							}

							estadosNuevos.add(row[j]);
							aceptacion = true;
						}

					} else {
						if (EncontrarTransicion(estadoNuevo, j).contains(",")) {
							char[] ar = EncontrarTransicion(estadoNuevo, j).replace(",", "").toCharArray();
							Arrays.sort(ar);
							String sorted = String.valueOf(ar);
							row[j] = sorted;
						} else {
							row[j] = EncontrarTransicion(estadoNuevo, j);
						}
						boolean first = (nuevoStack(estadosViejos, row[j]));
						boolean scnd = (nuevoStack(estadosNuevos, row[j]));
						boolean thrd = (!row[j].equals(estadoNuevo));
						if (first & scnd & thrd) {
							estadosViejos.add(estadosNuevos.pop());
							estadosNuevos.add(row[j]);
							aceptacion = true;
						}
					}
					break;

				}
			}
			if (!aceptacion) {
				estadosViejos.add(estadosNuevos.pop());
			}

			rows.add(row);
			row = new String[automata[1].length];

		}

		automata = rows.toArray(new String[rows.size()][automata[1].length]);
	}

}