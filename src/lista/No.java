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

    public void inserir_aresta(char vertice_adjacente) {
        arestas.add(vertice_adjacente);
    }

    public boolean remover_aresta(char vertice_adjacente) {
        for(int i = 0; i < arestas.size(); i++) {
            if(arestas.get(i) == vertice_adjacente) {
                arestas.remove(arestas.indexOf(vertice_adjacente));
                return true;
            }
        }
        return false;
    }

    public char getAresta (int id) {
        return arestas.get(id);
    }

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
