package lista;

import java.util.List;
import java.util.ArrayList;

public class No {
    private char id;
    private List<Character> arestas;

    public No (char id) {
        this.id = id;
        arestas = new ArrayList<Character>();;
    }

    /**
     * Adiciona nova aresta ao vertice
     * 
     * @param vertice_adjacente que deseja criar uma aresta
     */
    public void inserir_aresta(char vertice_adjacente) {
        arestas.add(vertice_adjacente);
    }

    /**
     * Retira uma aresta
     * 
     * @param vertice_adjacente a ser retirado a aresta
     * @return true, se a aresta foi retirada, false, se o vertice adjacente nao for encontrado
     */
    public boolean remover_aresta(char vertice_adjacente) {
        for(int i = 0; i < arestas.size(); i++) {
            if(arestas.get(i) == vertice_adjacente) {
                arestas.remove(arestas.indexOf(vertice_adjacente));
                return true;
            }
        }
        return false;
    }

    /**
     * Encontra o id do vertice adjacente com o id dele na lista de arestas do vertice
     * 
     * @param id do vertice adjacente na lista
     * @return id do vertice adjacente
     */
    public char getAresta (int id) {
        return arestas.get(id);
    }

    /**
     * Calcula a quantidade de arestas o vertice possui
     * 
     * @return quantidade de arestas do vertice
     */
    public int qnt_aresta () {
        if (arestas ==  null) {
            return 0;
        }
        return arestas.size();
    }

    /**
     * @return char return the id
     */
    public char getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(char id) {
        this.id = id;
    }
}
