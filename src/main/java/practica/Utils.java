/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica;

import java.util.ArrayList;
import java.util.List;
import practica.pojo.Dato;
import practica.pojo.Datum;
import practica.pojo.Pojo;
import practica.pojo.Simbolo;

/**
 *
 * @author mguillermo.ochoa
 */
public class Utils {

    public static String[][] PojoToAutomata(Pojo p) {
        String automata[][] = new String[p.getDatos().size() + 1][p.getSimbolos().size() + 2];
        for (int i = 2; i < automata[1].length; i++) {
            automata[0][i] = p.getSimbolos().get(i - 2).getSim();
        }
        for (int j = 1; j < automata.length; j++) {
            for (int k = 0; k < automata[1].length; k++) {
                if (k == 0) {
                    automata[j][k] = p.getDatos().get(j - 1).getData().get(k).getEs();
                } else if (k == 1) {

                    automata[j][k] = p.getDatos().get(j - 1).getAcept() ? "1" : "0";
                } else {
                    automata[j][k] = p.getDatos().get(j - 1).getData().get(k - 1).getEs();

                }
            }

        }
        return automata;
    }

    public static Pojo AutomataToPojo(String[][] automata) {
        Pojo p = new Pojo();

        List<Dato> datos = new ArrayList<>();

        List<Simbolo> simbolos = new ArrayList<>();
        List<Datum> data= new ArrayList<>();
        Boolean acept = null;
        //Agregar simbolos al array
        for (int i = 2; i < automata[1].length; i++) {
            simbolos.add(new Simbolo().withSim(automata[0][i]));
        }
        p.setSimbolos(simbolos);
        //Agregar las columnas con la aceptacion al array
        for (int k = 1; k < automata.length; k++) {
            for (int j = 0; j < automata[1].length; j++) {
            if(j==0){
                data.add(new Datum().withEs(automata[k][j]));
            }else if(j==1){
                if(automata[k][j].equals("0")){
                acept=false;
                }else{
                    acept=true;
                }
            }else{
                data.add(new Datum().withEs(automata[k][j]));
            }
            
            }
            datos.add(new Dato().withData(data).withAcept(acept));
            data=new ArrayList<>();
            acept=null;
            
        }
        p.setDatos(datos);
        

        return p;
    }

}
