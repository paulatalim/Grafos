package matriz;

import java.util.ArrayList;

public class MatrizDirecionada {
    private boolean[][] grafo;
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
     * Recebe o ID de um vértice e calcula os seus graus
     * @param id_vertice
     * @return vetor de inteiros representando os graus de saída e de entrada do vértice
     */
    public int[] grau_vertice (char id_vertice) {
        int[] graus = new int[2];
        int indexNo = buscar_vertice(id_vertice);

        for(int i = 0; i < vertices.size(); i++) {
            if(grafo[indexNo][i]) {
                graus[0] += 1;
            }
            if(grafo[i][indexNo]) {
                graus[1] += 1;
            }
        }

        return graus;
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
     * Adiciona uma aresta do grafo
     * @param aresta a ser inserida (String), indicada com seus vertices adjacentes
     * @return true, caso encontrar os vertices, ou false, caso não encontrar algum dos vertices adjacentes
     */
    public boolean inserir_aresta(String aresta) {
        int i = buscar_vertice(aresta.charAt(0));
        int j = buscar_vertice(aresta.charAt(1));

        if(i >=0 && j >= 0) {
            // Adiciona uma nova aresta
            grafo[i][j] = true;
            return true;
        }

        return false;
    }

    /**
     * Remove uma aresta do grafo
     * @param aresta a ser retirada (String), indicada com seus vertices adjacentes
     * @return true, caso encontrar os vertices, ou false, caso não encontrar algum dos vertices adjacentes
     */
    public boolean remover_aresta(String aresta) {
        int i = buscar_vertice(aresta.charAt(0));
        int j = buscar_vertice(aresta.charAt(1));

        if(i >=0 && j >= 0) {
            // Adiciona uma nova aresta
            grafo[i][j] = false;
            return true;
        }

        return false;
    }

    /**
     * Cria a matriz a partir da lista de vértices.
     */
    private void criar_matriz () {
        int tamanho = vertices.size();
        this.grafo = new boolean[tamanho][tamanho];
        for(int i = 0; i < tamanho; i++) {
            for(int j = 0; j < tamanho; j++) {
                this.grafo[i][j] = false;
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
            if(grafo[i][i]) return false;
        }
        return true;
    }

   /**
     * Confere se o grafo é regular ou não
     * @return true se for, caso contrário, false
     */
    public boolean isGrafosRegular() {
        int graus[] = new int[2];
        graus = grau_vertice(vertices.get(0));
        int grauSaida = graus[0];
        int grauEntrada = graus[1];

        int[] grausAuxiliares;
        int auxSaida = 0;
        int auxEntrada = 0;

        for (int i = 1; i < vertices.size(); i++) { 
            grausAuxiliares = new int[2];
            grausAuxiliares = grau_vertice(vertices.get(i));
            auxSaida = grausAuxiliares[0];
            auxEntrada = grausAuxiliares[1];
            if (auxSaida != grauSaida || auxEntrada != grauEntrada) {
                return false;
            }
        }

        return true;
    }

    /**
     * Verifica se o grafo possui aresta
     * @return true, se tiver aresta, false caso contrario
     */
    public boolean temAresta() {
        for(int i = 0; i < vertices.size(); i++) {
            for(int j = 0; j < vertices.size(); j++) {
                if(grafo[i][j]) return true;
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
                if(grafo[i][j]) {
                    System.out.print("1 ");
                }
                else {
                    System.out.print("0 ");
                }
            }
            System.out.println("|");
        }
    }
    
    /**
     * @return boolean[][] return the grafo
     */
    public boolean[][] getGrafo() {
        return grafo;
    }
}   
