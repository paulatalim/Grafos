package lista;

import java.util.List;
import java.util.ArrayList;

public class ListaDirecionada {
    private List<No> grafo;

    ListaDirecionada () {
        grafo = new ArrayList<No>();
    }

    /**
     * Verifica se o grafo está vazio
     * @return true, se estiver vazio, false, caso contrario
     */
    public boolean isGrafosEmpty() {
        if(grafo.isEmpty()){
            return true;
        }
        return false;
    }

    /**
     * Verifica se o grafo possui aresta
     * @return true, se tiver aresta, false caso contrario
     */
    public boolean temAresta() {
        for(int i = 0; i < grafo.size(); i++) {
            if(grafo.get(i).qnt_aresta() != 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica se um vertice existe
     * 
     * @param id do vertice a ser verificado
     * @return true, se o vertice existir, false, caso contrario
     */
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

    /**
     * Remove uma aresta do grafo
     * @param aresta a ser retirada (String), indicada com seus vertices adjacentes
     * @return true, caso encontrar os vertices, ou false, caso não encontrar algum dos vertices adjacentes
     */
    public boolean remover_aresta(String aresta) {
        // encontra a aresta
        for(int i = 0; i < grafo.size(); i++) {
            if(grafo.get(i).getId() == aresta.charAt(0)) {
                // Remove a aresta
                grafo.get(i).remover_aresta(aresta.charAt(1));
                return true;
            }
        }

        // Caso nao encontrar a aresta
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
