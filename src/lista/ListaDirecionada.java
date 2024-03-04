package lista;

import java.util.List;
import java.util.ArrayList;

public class ListaDirecionada {
    private List<No> grafo;

    ListaDirecionada () {
        grafo = new ArrayList<No>();
    }

    public void inserir_vertice (char id_vertice) {
        No new_vertice = new No(id_vertice);

        grafo.add(new_vertice);
    }
    
    public boolean inserir_aresta(String aresta) {
        char id = aresta.charAt(0);

        // Encontra o vertice no grafo
        for(int i = 0; i < grafo.size(); i++) {
            if(grafo.get(i).getId() == id) {
                // Adiciona uma nova aresta
                grafo.get(i).inserir_aresta(aresta.charAt(1));
                return true;
            }
        }

        return false;
    }

    public void exibir_lista () {
        for (int i = 0; i < grafo.size(); i++) {
            No no = grafo.get(i);
            System.out.print(no.getId() + ": [ ");

            if (no.qnt_aresta() > 0) {
                System.out.print(no.getAresta(0));
            }

            for (int j = 1; j < no.qnt_aresta(); j++) {
                System.out.print("," + no.getAresta(j));
            }

            System.out.println(" ]");
        }
    }

    /**
     * @return List<No> return the grafo
     */
    public List<No> getGrafo() {
        return grafo;
    }
}
