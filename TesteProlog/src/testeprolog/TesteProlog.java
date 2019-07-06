/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testeprolog;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import org.jpl7.Query;
import org.jpl7.Term;

/**
 *
 * @author Eduardo
 */
public class TesteProlog {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String c1="consult('sistemaEspecialista.pl')";
        Query q1 = new Query(c1);
        System.out.println("t1 "+ (q1.hasSolution()? "Conectado":"Sem conexão!"));
        
        String listaSintoma = "[febre(4)|[dorArticulacao(moderada)]]";
        String encontraSintoma = "retornaDoenca("+listaSintoma+",Lista)";
        Query q2 = new Query (encontraSintoma);
        Map<String, Term>[] doencas =  q2.allSolutions();     
        for(int i = 0; i<doencas.length; i++){
            System.out.println("Voce tem: "+ doencas[i].get("Lista"));
        }
        if(doencas.length==0){
            Query q3 = new Query("buscaNull("+listaSintoma+", Doenca)");
            System.out.println("Doenca 1 :"+q3.nextSolution().get("Doenca")); 
            for(int i = 0; i<2; i++){
                Query q4 = new Query("splitVetor(HeadListaSintomas,TailListaSintomas,ListaSintomas)");
                listaSintoma = q4.nextSolution().get("TailListaSintomas").toString();
                System.out.println(listaSintoma);
                Query q5 = new Query("buscaNull([dorArticulacao(moderada)], Doenca)");
                System.out.println("Doenca "+i+" :"+q5.nextSolution().get("Doenca"));   
            }
        }
    }
    
}
