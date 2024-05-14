package lista;

import java.util.List;
import java.util.ArrayList;

public class No {
    private char id;
    private List<Character> arestas;
    private List<Integer> pesos;

    public No(char id) {
        this.id = id;
        arestas = new ArrayList<Character>();
        pesos = new ArrayList<Integer>();
    }

    /**
     * Adiciona nova aresta ao vertice
     * 
     * @param vertice_adjacente que deseja criar uma aresta
     */
    public void inserir_aresta(char vertice_adjacente) {
        arestas.add(vertice_adjacente);
        pesos.add(1);
    }

    /**
     * Adiciona nova aresta ao vertice
     * 
     * @param vertice_adjacente que deseja criar uma aresta
     * @param peso da aresta
     */
    public boolean inserir_aresta(char vertice_adjacente, int peso) {
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
    public boolean remover_aresta(char vertice_adjacente) {
        for(int i = 0; i < arestas.size(); i++) {
            if(arestas.get(i) == vertice_adjacente) {
                arestas.remove(i);
                pesos.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean isArestaeExist(char id) {
        return arestas.contains(id);
    }

    /**
     * Encontra o id do vertice adjacente com o id dele na lista de arestas do vertice
     * 
     * @param id do vertice adjacente na lista
     * @return id do vertice adjacente
     */
    public char getAresta(int id) {
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
     * @return true (se foi atualizado com sucesso) ou false (ocorreu algum erro)
     */
    public boolean updatePeso(char id, int newPeso) {
        if(newPeso > 0) {
            pesos.set(arestas.indexOf(id), newPeso);
            return true;
        }

        return false;
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
