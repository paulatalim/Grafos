package graph.representacao.matriz;

import java.util.ArrayList;
import java.util.List;

import graph.busca.BreadthFirstSearch;

public class MatrizNaoDirecionada {
    private int[][] grafo;
    private boolean isPonderado;
    private ArrayList<Character> vertices = new ArrayList<Character>();

    /**
     * Cria a matriz a partir da lista de vértices.
     */
    private void initMatriz() {
        int tamanho = vertices.size();
        this.grafo = new int[tamanho][tamanho];
        for(int i = 0; i < tamanho; i++) {
            for(int j = 0; j < tamanho; j++) {
                this.grafo[i][j] = 0;
            }
        }
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
     * Converte uma List<Character> para um vertor de char
     * @param list - lista a ser convertida
     * @return Array de caracteres
     */
    private char[] toArrayChar(List<Character> list) {
        char[] vetor = new char[list.size()];
        
        for(int i = 0; i < list.size(); i++) {
            vetor[i] = Character.valueOf(list.get(i));
        }

        return vetor;
    }

    /**
     * Adiciona um novo vertice ao grafo
     * @param id_vertice
     */
    public void inserir_vertice(char id_vertice) {
        // Verifica se o vertice ja existe
        if(!isNoExist(id_vertice)) {
           // Adiciona o vertice a lista
           if(id_vertice != '0') vertices.add(id_vertice);
           else initMatriz();
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
            if(i != j) {
                // Adiciona uma nova aresta
                grafo[i][j] += 1;
                grafo[j][i] += 1;
            } else {
                // Adiciona um laco
                grafo[i][j] ++;
            }
            return true;
        }

        return false;
    }

    /**
     * Adiciona uma aresta do grafo com peso
     * @param aresta a ser inserida (String), indicada com seus vertices adjacentes
     * @param peso da aresta
     * @return true, caso encontrar os vertices, haver aresta eo peso ser valido, ou false, caso contrario
     */
    public boolean inserir_aresta(String aresta, int peso) {
        if(isPonderado && peso > 0) {
            int i = buscar_vertice(aresta.charAt(0));
            int j = buscar_vertice(aresta.charAt(1));

            if(i >= 0 && j >= 0) {
                if(i != j) {
                    // Adiciona uma nova aresta
                    grafo[i][j] = peso;
                    grafo[j][i] = peso;
                } else {
                    // Adiciona um laco
                    grafo[i][j] = peso;
                }
                return true;
            }

            return false;
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

        if(i >=0 && j >= 0 && grafo[i][j] != 0 && grafo[i][j] != 0) {
            if(i != j) {
                // Remove uma nova aresta
                grafo[i][j] = 0;
                grafo[j][i] = 0;
            } else {
                // Remove um laco
                grafo[i][j] = 0;
            }

            return true;
        }

        return false;
    }

    /**
     * Atualiza o peso de uma aresta
     * @param aresta que o peso será atualizado, indicada com seus vertices adjacentes
     * @param newPeso novo peso da aresta
     * @return true, caso encontrar os vertices e haver aresta entre eles, ou false, caso contrario
     */
    public boolean atualizarPeso(String aresta, int newPeso) {
        if(isPonderado && newPeso > 0) {
            int i = buscar_vertice(aresta.charAt(0));
            int j = buscar_vertice(aresta.charAt(1));
            
            if(i > 0 && j > 0) {
                if(i != j) {
                    // Adiciona uma nova aresta
                    grafo[i][j] = newPeso;
                    grafo[j][i] = newPeso;
                } else {
                    // Adiciona um laco
                    grafo[i][j] = newPeso;
                }
                return true;
            }
            
            return false;
        }

        return false;
    }

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
     * Recebe o ID de um vértice e calcula o seu grau
     * @param id_vertice
     * @return vetor de inteiro representando o grau do vértice
     */
    public int[] grau_vertice(char id_vertice) {
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
        
        return toArrayChar(listaVizinhos);
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
     * Confere se o grafo é bipartido ou não
     * @return true se for, caso contrário, false
     */
    public boolean isGrafosBipartido() {
        if(isGrafosEmpty()) {
            return false;
        }

        // inicia um vetor do tamanho dos vértices existentes
        int[] cores = new int[vertices.size()];
        // associa "nenhuma cor" para cada vértice no vetor
        for (int i = 1; i < cores.length; i++) {
            cores[i] = -1;
        }

        cores[0] = 1; // inicia o primeiro vértice com uma das duas cores
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = 0; j < vertices.size(); j++) {
                // Verifica se eh um laco
                if(i == j && grafo[i][j] != 0) {
                    return false;
                }
                // confere se há aresta e se não é um laço
                else if(grafo[i][j] != 0 && i != j) {
                    // tenta colorir o vértice vizinho com uma cor oposta a sua
                    if(cores[i] == 0 && (cores[j] == -1 || cores[j] == 1)) {
                        cores[j] = 1;
                    }
                    else if(cores[i] == 1 && (cores[j] == -1 || cores[j] == 0)) {
                        cores[j] = 0;
                    }
                    // caso não consiga, já determina que o grafo não é bipartido
                    else {
                        return false;
                    }
                }
            }
        }

        if(!isGrafosConexo()) return false;

        return true;
    }

    /**
     * Confere se o grafo é conexo ou não
     * @return true, se for conexo, false, caso contrário
     */
    public boolean isGrafosConexo() {    
        for (int i = 0; i < vertices.size(); i++) {
            if (grau_vertice(vertices.get(i))[0] == 0) {
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
                if(grafo[i][j] != 0) return true;
            }
        }
        return false;
    }

    public char[] realizarBuscaLargura() {
        BreadthFirstSearch BFS = new BreadthFirstSearch(grafo, vertices);
        return toArrayChar(BFS.bfs(vertices.get(0)));
    }


    /**
     * Imprime a matriz de adjacencia no console
     */
    public void exibir_matriz() {
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
     * Se o Grafo eh poderado
     * @param isPonderado
     */
    public void setIsPonderado(boolean isPonderado) {
        this.isPonderado = isPonderado;
    }

    /**
     * Se o grafo eh poderado
     * 
     * @return true ou false
     */
    public boolean isPonderado() {
        return isPonderado;
    }
    
    /**
     * @return int[][] return do grafo
     */
    public int[][] getGrafo() {
        return grafo;
    }
}
