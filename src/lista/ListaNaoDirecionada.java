package lista;

import java.util.ArrayList;
import java.util.List;

public class ListaNaoDirecionada {
    private List<No> grafo;

    ListaNaoDirecionada () {
        grafo = new ArrayList<No>();
    }

    public boolean isGrafosEmpty() {
        if(grafo.isEmpty()){
            return true;
        }
        return false;
    }

    public boolean verificar_vertice(char id) {
        // Encontra o vertice no grafo
        for(int i = 0; i < grafo.size(); i++) {
            if(grafo.get(i).getId() == id) {
                return true;
            }
        }
        
        return false;
    }

    /**
     * Adiciona um novo vertice ao grafo
     * @param id_vertice
     */
    public void inserir_vertice (char id_vertice) {
        No new_vertice = new No(id_vertice);

        grafo.add(new_vertice);
    }

    /**
     * Adiciona uma aresta do grafo
     * @param aresta a ser inserida (String), indicada com seus vertices adjacentes
     * @return true, caso encontrar os vertices, ou false, caso não encontrar algum dos vertices adjacentes
     */
    public boolean inserir_aresta(String aresta) {
        // Encontra o primeiro vertice no grafo
        for(int i = 0; i < grafo.size(); i++) {
            if (grafo.get(i).getId() == aresta.charAt(0)) {
                // Encontra o segundo vertice no grafo
                for (int j = 0; j < grafo.size(); j++) {
                    if (grafo.get(j).getId() == aresta.charAt(1)) {
                        // Insere as arestas
                        grafo.get(i).inserir_aresta(aresta.charAt(1));
                        grafo.get(j).inserir_aresta(aresta.charAt(0));
                        return true;
                    }
                }
                // Termina loop se não encontrar o segundo vertice
                break;
            }
        }

        return false;
    }
    
    /**
     * Remove uma aresta do grafo
     * @param aresta a ser retirada (String), indicada com seus vertices adjacentes
     * @return true, caso encontrar os vertices, ou false, caso não encontrar algum dos vertices adjacentes
     */
    public boolean remover_aresta(String aresta) {
        // Encontra o primeiro vertice no grafo
        for(int i = 0; i < grafo.size(); i++) {
            if (grafo.get(i).getId() == aresta.charAt(0)) {
                // Encontra o segundo vertice no grafo
                for (int j = 0; j < grafo.size(); j++) {
                    if (grafo.get(j).getId() == aresta.charAt(1)) {
                        // Remove a aresta
                        grafo.get(i).remover_aresta(aresta.charAt(1));
                        grafo.get(j).remover_aresta(aresta.charAt(0));
                        return true;
                    }
                }
            }
        }

        // Caso não encontrar algum dos vertices
        return false;
    }

    /**
     * Imprime a lista de adjacencia no console
     */
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
