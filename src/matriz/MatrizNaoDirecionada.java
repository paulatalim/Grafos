package matriz;

import java.util.ArrayList;
import java.util.List;

public class MatrizNaoDirecionada {
    private int[][] grafo;
    private ArrayList<Character> vertices = new ArrayList<Character>();

    /**
     * Verifica se um vertice existe
     * 
     * @param id do vertice a ser verificado
     * @return true, se o vertice existir, false, caso contrario
     */
    public boolean isNoExist(char id) {
        // Encontra o vertice no grafo
        if(vertices.contains(id)) {
            return true;
        }
        
        return false;
    }

    /**
     * Encontra um vertice no grafo
     * @param id do vertice a ser procurado
     * @return indice na lista no grafo
     */
    private int buscar_vertice(char id) {
        for(int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i) == id) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Adiciona um novo vertice ao grafo
     * @param id_vertice
     */
    public void inserir_vertice (char id_vertice) {
        // Verifica se o vertice ja existe
        if(!isNoExist(id_vertice)) {
           // Adiciona o vertice a lista
           if(id_vertice != '0') vertices.add(id_vertice);
           else criar_matriz();
        }
    }

    /**
     * Recebe o ID de um vértice e calcula o seu grau
     * @param id_vertice
     * @return vetor de inteiro representando o grau do vértice
     */
    public int[] grau_vertice (char id_vertice) {
        int[] grau = new int[1];
        grau[0] = 0;
        int indexNo = buscar_vertice(id_vertice);

        for(int i = 0; i < vertices.size(); i++) {
            if(grafo[indexNo][i] != 0) {
                if(indexNo == i) {
                    grau[0] += 2 * grafo[indexNo][i];
                } else {
                    grau[0] += grafo[indexNo][i];
                }
            }
        }

        return grau;
    }

    /**
     * Adiciona uma aresta do grafo
     * @param aresta a ser inserida (String), indicada com seus vertices adjacentes
     * @return true, caso encontrar os vertices, ou false, caso não encontrar algum dos vertices adjacentes
     */
    public boolean inserir_aresta(String aresta) {
        int i = buscar_vertice(aresta.charAt(0));
        int j = buscar_vertice(aresta.charAt(1));

        if(i >=0 && j >= 0) {
            // Adiciona uma nova aresta
            grafo[i][j] += 1;
            grafo[j][i] += 1;
            return true;
        }

        return false;
    }

    /**
     * Verifica a vizinhaca do vertice
     * @param id_vertice a ser analisado
     * @return vetor de char (vertices adjacentes ao analisado)
     */
    public char[] verifica_vizinhanca(char id_vertice){

        List<Character> listaVizinhos = new ArrayList<>();

        int index_vertice = buscar_vertice(id_vertice);

        for(int i = 0; i < vertices.size(); i ++){
            if(grafo[index_vertice][i] != 0){
                listaVizinhos.add(vertices.get(i));
            }
        }
        
        char[] vetorVizinhos = new char[listaVizinhos.size()];
        for(int i = 0; i < listaVizinhos.size(); i++) {
            vetorVizinhos[i] = Character.valueOf(listaVizinhos.get(i));
        }
        return vetorVizinhos;
    }

    /**
     * Remove uma aresta do grafo
     * @param aresta a ser retirada (String), indicada com seus vertices adjacentes
     * @return true, caso encontrar os vertices, ou false, caso não encontrar algum dos vertices adjacentes
     */
    public boolean remover_aresta(String aresta) {
        int i = buscar_vertice(aresta.charAt(0));
        int j = buscar_vertice(aresta.charAt(1));

        if(i >=0 && j >= 0 && grafo[i][j] != 0 && grafo[i][j] != 0) {
            // Adiciona uma nova aresta
            grafo[i][j] -= 1;
            grafo[j][i] -= 1;
            return true;
        }

        return false;
    }

    /**
     * Cria a matriz a partir da lista de vértices.
     */
    private void criar_matriz () {
        int tamanho = vertices.size();
        this.grafo = new int[tamanho][tamanho];
        for(int i = 0; i < tamanho; i++) {
            for(int j = 0; j < tamanho; j++) {
                this.grafo[i][j] = 0;
            }
        }
    }

    /**
     * Verifica se o grafo está vazio
     * @return true, se estiver vazio, false, caso contrario
     */
    public boolean isGrafosEmpty() {
        if(vertices.isEmpty()){
            return true;
        }
        return false;
    }

    /**
     * Confere se o grafo é simples ou não
     * @return true se for simples, caso contrário, false
     */
    public boolean isGrafosSimples() {
        for(int i = 0; i < vertices.size(); i++) {
            for(int j = 0; j < vertices.size(); j++) {
                if(grafo[i][j] != 0 && i == j || grafo[i][j] > 1) return false;
            }
        }
        return true;
    }

    /**
     * Confere se o grafo é regular ou não
     * @return true se for, caso contrário, false
     */
    public boolean isGrafosRegular() {
        int grau = grau_vertice(vertices.get(0))[0];
        int aux = 0;

        for (int i = 1; i < vertices.size(); i++) { 
            aux = grau_vertice(vertices.get(i))[0];
            if (aux != grau) {
                return false;
            }
        }

        return true;
    }

    /**
     * Confere se o grafo é completo ou não
     * @return true se for, caso contrário, false
     */
    public boolean isGrafosCompleto() {
        if(isGrafosSimples()) {
            for(int i = 0; i < vertices.size(); i++) {
                for(int j = 0; j < vertices.size(); j++) {
                    if(grafo[i][j] == 0 && i != j) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Verifica se o grafo possui aresta
     * @return true, se tiver aresta, false caso contrario
     */
    public boolean temAresta() {
        for(int i = 0; i < vertices.size(); i++) {
            for(int j = 0; j < vertices.size(); j++) {
                if(grafo[i][j] != 0) return true;
            }
        }
        return false;
    }

    /**
     * Imprime a matriz de adjacencia no console
     */
    public void exibir_matriz () {
        System.out.print("\t   ");
        for (int i = 0; i < vertices.size(); i++) {
            System.out.print(vertices.get(i) + " ");
        }
        System.out.print("\n");
        for (int i = 0; i < vertices.size(); i++) {
            System.out.print("\t" + vertices.get(i) + "| ");
            for (int j = 0; j < vertices.size(); j++) {
                System.out.print(grafo[i][j] + " ");
            }
            System.out.println("|");
        }
    }

    /**
     * @return int[][] return do grafo
     */
    public int[][] getGrafo() {
        return grafo;
    }
}
