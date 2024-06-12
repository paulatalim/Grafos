package graph.representacao.lista;

import java.util.List;
import java.util.ArrayList;

public class No {
    private String id;
    private List<String> arestas;
    private List<Integer> pesos;

    public No(String id) {
        this.id = id;
        arestas = new ArrayList<String>();
        pesos = new ArrayList<Integer>();
    }

    /**
     * Adiciona nova aresta ao vertice
     * 
     * @param vertice_adjacente que deseja criar uma aresta
     */
    public void inserir_aresta(String vertice_adjacente) {
        arestas.add(vertice_adjacente);
        pesos.add(1);
    }

    /**
     * Adiciona nova aresta ao vertice
     * 
     * @param vertice_adjacente que deseja criar uma aresta
     * @param peso da aresta
     */
    public boolean inserir_aresta(String vertice_adjacente, int peso) {
        if(!arestas.contains(vertice_adjacente)) {
            arestas.add(vertice_adjacente);
            pesos.add(peso);
            return true;
        }
        return false;
    }

    /**
     * Retira uma aresta
     * 
     * @param vertice_adjacente a ser retirado a aresta
     * @return true, se a aresta foi retirada, false, se o vertice adjacente nao for encontrado
     */
    public boolean remover_aresta(String vertice_adjacente) {
        for(int i = 0; i < arestas.size(); i++) {
            if(arestas.get(i).equals(vertice_adjacente)) {
                arestas.remove(i);
                pesos.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean isArestaeExist(String id) {
        return arestas.contains(id);
    }

    /**
     * Encontra o id do vertice adjacente com o id dele na lista de arestas do vertice
     * 
     * @param id do vertice adjacente na lista
     * @return id do vertice adjacente
     */
    public String getAresta(int id) {
        return arestas.get(id);
    }

    /**
     * Encontra o peso da aresta do vertice adjacente
     * 
     * @param id do vertice adjacente na lista
     * @return peso da aresta
     */
    public int getPeso(int id) {
        return pesos.get(id);
    }

    /**
     * Atualiza o peso de uma aresta
     * 
     * @param id do vertice adjacente
     * @param newPeso novo peso da aresta
     */
    public void updatePeso(String id, int newPeso) {
        pesos.set(arestas.indexOf(id), newPeso);
    }

    /**
     * Calcula a quantidade de arestas o vertice possui
     * 
     * @return quantidade de arestas do vertice
     */
    public int qnt_aresta() {
        if (arestas ==  null) {
            return 0;
        }
        return arestas.size();
    }

    /**
     * @return char return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
}
