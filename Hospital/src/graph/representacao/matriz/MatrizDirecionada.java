package graph.representacao.matriz;

import java.util.ArrayList;
import java.util.List;

import graph.busca.BreadthFirstSearch;
// import graph.busca.DepthFirstSearch;
import graph.caminho.Dijkstra;
// import graph.ordenacao.OrdenacaoTopologica;

class MatrizDirecionada {
    private Integer[][] grafo;
    private boolean isPonderado;
    private ArrayList<String> vertices = new ArrayList<String>();

    /**
     * Cria a matriz a partir da lista de vértices.
     */
    private void initMatriz() {
        int tamanho = vertices.size();

        if(this.grafo == null) {
            this.grafo = new Integer[tamanho][tamanho];
            
            if(!isPonderado) {
                for(int i = 0; i < tamanho; i++) {
                    for(int j = 0; j < tamanho; j++) {
                        this.grafo[i][j] = 0;
                    }
                }
            } else {
                for(int i = 0; i < tamanho; i++) {
                    for(int j = 0; j < tamanho; j++) {
                        this.grafo[i][j] = null;
                    }
                }
            }
        } else {
            Integer[][] grafo_aux = this.grafo.clone();
            this.grafo = new Integer[tamanho][tamanho];
            
            if(!isPonderado) {
                for(int i = 0; i < tamanho; i++) {
                    for(int j = 0; j < tamanho; j++) {
                        if(i < grafo_aux.length && j < grafo_aux.length) {
                            this.grafo[i][j] = grafo_aux[i][j];
                        } else {
                            this.grafo[i][j] = 0;
                        }
                    }
                }
            } else {
                for(int i = 0; i < tamanho; i++) {
                    for(int j = 0; j < tamanho; j++) {
                        if(i < grafo_aux.length && j < grafo_aux.length) {
                            this.grafo[i][j] = grafo_aux[i][j];
                        } else {
                            this.grafo[i][j] = null;
                        }
                    }
                }
            }
        }
    }

    /**
     * Encontra um vertice no grafo
     * @param id do vertice a ser procurado
     * @return indice na lista no grafo
     */
    private int buscar_vertice(String id) {
        for(int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).equals(id)) {
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
    // private char[] toArrayChar(List<Character> list) {
    //     // Verifica se a lista existe
    //     if(list == null) {
    //         return null;
    //     }

    //     // Cria um vetor auxiliar para armazenar os valores convertidos
    //     char[] vetor = new char[list.size()];
        
    //     // Conversao dos valores da lista para vetor
    //     for(int i = 0; i < list.size(); i++) {
    //         vetor[i] = Character.valueOf(list.get(i));
    //     }

    //     // Retorno da lista convertida para vetor
    //     return vetor;
    // }

    /**
     * Adiciona um novo vertice ao grafo
     * @param id_vertice
     */
    public void inserir_vertice(String id_vertice) {
        // Verifica se o vertice ja existe
        if(!isNoExist(id_vertice)) {
           // Adiciona o vertice a lista
           vertices.add(id_vertice);
           initMatriz();
        }
    }

    /**
     * Adiciona uma aresta do grafo
     * @param aresta a ser inserida (String), indicada com seus vertices adjacentes
     * @return true, caso encontrar os vertices, ou false, caso não encontrar algum dos vertices adjacentes
     */
    public boolean inserir_aresta(String vertice1, String vertice2) {
        int i = buscar_vertice(vertice1);
        int j = buscar_vertice(vertice2);

        if(i >=0 && j >= 0) {
            // Adiciona uma nova aresta
            grafo[i][j] += 1;
            return true;
        }

        return false;
    }

    /**
     * Adiciona uma aresta do grafo com peso
     * @param aresta a ser inserida (String), indicada com seus vertices adjacentes
     * @param peso da aresta
     * @return true, caso encontrar os vertices, ou false, caso não encontrar algum dos vertices adjacentes
     */
    public boolean inserir_aresta(String vertice1, String vertice2, int peso) {
        if(isPonderado) {
            int i = buscar_vertice(vertice1);
            int j = buscar_vertice(vertice2);

            if(i >= 0 && j >= 0) {
                // Adiciona uma nova aresta
                grafo[i][j] = peso;
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
    public boolean remover_aresta(String vertice1, String vertice2) {
        int i = buscar_vertice(vertice1);
        int j = buscar_vertice(vertice2);

        if(i >= 0 && j >= 0) {
            // Remove uma nova aresta
            if(!isPonderado && grafo[i][j] != 0) {
                grafo[i][j] -= 1;
                return true;
            
            } else if (isPonderado && grafo[i][j] != null) {
                grafo[i][j] = null;
                return true;
            }
        }

        return false;
    }

    /**
     * Atualiza o peso de uma aresta
     * @param aresta que o peso será atualizado, indicada com seus vertices adjacentes
     * @param newPeso novo peso da aresta
     * @return true, caso encontrar os vertices e haver aresta entre eles, ou false, caso contrario
     */
    public boolean atualizarPeso(String vertice1, String vertice2, int newPeso) {
        if(isPonderado) {
            int i = buscar_vertice(vertice1);
            int j = buscar_vertice(vertice2);
            
            // Caso haver aresta
            if(i >= 0 && j >= 0) {
                // Adiciona uma nova aresta
                grafo[i][j] = newPeso;
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
    public boolean isNoExist(String id) {
        // Encontra o vertice no grafo
        if(vertices.contains(id)) {
            return true;
        }
        
        return false;
    }

    /**
     * Recebe o ID de um vértice e calcula os seus graus
     * @param id_vertice
     * @return vetor de inteiros representando os graus de saída e de entrada do vértice
     */
    public int[] grau_vertice (String id_vertice) {
        int[] graus = new int[2];
        int indexNo = buscar_vertice(id_vertice);
        
        // Inicializa o vetor
        graus[0] = 0;
        graus[1] = 0;

        for(int i = 0; i < vertices.size(); i++) {
            if((!isPonderado && grafo[indexNo][i] != 0) 
                || (isPonderado && grafo[indexNo][i] != null)) {
                graus[0] += grafo[indexNo][i];
            }
            if((!isPonderado && grafo[i][indexNo] != 0)
                || (isPonderado && grafo[i][indexNo] != null)) {
                graus[1] += grafo[i][indexNo];
            }
        }

        return graus;
    }

    /**
     * Verifica os sucessores de um vertice
     * @param id_vertice a ser analisado
     * @return vetor de char (vertices sucessores)
     */
    public List<String> verifica_sucessores(String id_vertice){
        List<String> listaSucessores = new ArrayList<>();

        int index_vertice = buscar_vertice(id_vertice);

        for(int i = 0; i < vertices.size(); i++){
            if((!isPonderado && grafo[index_vertice][i] != 0) ||
                (isPonderado && grafo[index_vertice][i] != null)) {
                listaSucessores.add(vertices.get(i));
            }
        }

        return listaSucessores;
    }

    /**
     * Verifica os predecessores de um vertice
     * @param id_vertice a ser analisado
     * @return vetor de char (vertices predecessores)
     */
    public List<String> verifica_predecessores(String id_vertice){
        List<String> listaPredecessores = new ArrayList<>();

        int index_vertice = buscar_vertice(id_vertice);

        for(int i = 0; i < vertices.size(); i++){
            if((!isPonderado && grafo[i][index_vertice] != 0) ||
                (isPonderado && grafo[i][index_vertice] != null)) {
                listaPredecessores.add(vertices.get(i));
            }
        }

        return listaPredecessores;
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
        if(!isPonderado) {
            for(int i = 0; i < vertices.size(); i++) {
                for(int j = 0; j < vertices.size(); j++) {
                    if(grafo[i][j] != 0 && i == j || grafo[i][j] > 1) return false;
                }
            }
        } else {
            // Verifica se possui laco quando o grafo eh ponderado
            for(int i = 0; i < vertices.size(); i++) {
                if(grafo[i][i] != null) return false;
            }
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
            if (auxEntrada != grauEntrada || auxSaida != grauSaida) {
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
                    if(((!isPonderado && grafo[i][j] == 0) ||
                        (isPonderado && grafo[i][j] == null)) && 
                        i != j ) {
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

        // Inicia um vetor do tamanho dos vértices existentes
        int[] cores = new int[vertices.size()];
        boolean allIsVerficado = false;

        // Associa "nenhuma cor" para cada vértice no vetor
        for (int i = 1; i < cores.length; i++) {
            cores[i] = -1;
        }
        
        // Inicia o primeiro vértice com uma das duas cores
        cores[0] = 1; 

        while(!allIsVerficado) {

            for (int i = 0; i < vertices.size(); i++) {
                for (int j = 0; j < vertices.size(); j++) {
                    // Verifica se eh um laco
                    if(i == j && (isPonderado && grafo[i][j] != null) || (!isPonderado && grafo[i][j] != 0)) {
                        return false;
                    }
                    // confere se há vinhanca
                    else if(((isPonderado && (grafo[i][j] != null || grafo[j][i] != null))
                            || (!isPonderado && (grafo[i][j] != 0 || grafo[j][i] != 0)))
                            && i != j) {
                        // tenta colorir o vértice vizinho com uma cor oposta a sua
                        if(cores[i] == 0 && (cores[j] == -1 || cores[j] == 1)) {
                            cores[j] = 1;
                        }
                        else if(cores[i] == 1 && (cores[j] == -1 || cores[j] == 0)) {
                            cores[j] = 0;
                        }
                        // caso não consiga, já determina que o grafo não é bipartido
                        else if(cores[i] != -1) {
                            return false;
                        }
                    }
                }
            }
            
            allIsVerficado = true;

            // Verifica se algum vertice nao foi verificado; colorido
            for(int i = 0; i < vertices.size(); i++) {
                if(cores[i] == -1) {
                    cores[i] = 0;
                    allIsVerficado = false;
                    break;
                }
            }
        }

        return true;
    }

    /**
     * Confere se o grafo é conexo ou não
     * @return true, se for conexo, false, caso contrário
     */
    public boolean isGrafosConexo() {    
        BreadthFirstSearch bfs = new BreadthFirstSearch(grafo, vertices, isPonderado);
        return bfs.getIsConexo();
    } 

    /**
     * Verifica se o grafo possui aresta
     * @return true, se tiver aresta, false caso contrario
     */
    public boolean temAresta() {
        for(int i = 0; i < vertices.size(); i++) {
            for(int j = 0; j < vertices.size(); j++) {
                if((!isPonderado && grafo[i][j] != 0) ||
                    (isPonderado && grafo[i][j] != null))
                    return true;
            }
        }
        return false;
    }

    /**
     * Calcula o caminho minimo entre a os vertices
     * @param a char (id do vertice a ser analisado)
     * @param b char (id do vertice a ser analisado)
     * @return Integer (tamanho do caminho minimo da raiz ate a saida), ou null (caso ocorra um erro) ou Integer.MAX_VALUE (caso nao exista caminho entre os vertices)
     */
    public Integer calcularCaminhoMinimo(String a, String b) {
        Dijkstra dijkstra = new Dijkstra(grafo, vertices, isPonderado);
        return dijkstra.calcularCaminhoMinimo(a, b);
    }

    /**
     * Realiza uma busca em largura no grafo
     * @param verticeInicial char (vertice que a busca em largura ira iniciar)
     * @return vetor de char (ordem de visitacao dos vertices na busca em largura)
     */
    // public char[] realizarBuscaLargura(char verticeInicial) {
    //     BreadthFirstSearch BFS = new BreadthFirstSearch(grafo, vertices, isPonderado);
    //     return toArrayChar(BFS.bfs(verticeInicial));
    // }

    /**
     * Realiza uma busca em profundidade no grafo
     * @param raiz char (vertice que a busca em profundidade irá iniciar)
     * @return objeto da classe DepthFirstSearch caso o vértice raiz exista no grafo, senão retorna null.
     */
    // public DepthFirstSearch realizarBuscaProfundidade(char raiz) {
    //     if(isNoExist(raiz)) {
    //         DepthFirstSearch DFS = new DepthFirstSearch(grafo, vertices, isPonderado);
    //         DFS.dfs(raiz);
    //         return DFS;
    //     }
    //     return null; 
    // }

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
                if(grafo[i][j] != null) {
                    System.out.print(grafo[i][j] + " ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println("|");
        }
    }

    /**
     * Faz uma ordenacao topologica com os vertices do grafo
     * @return objeto da ordenacao topologica
     */
    // public OrdenacaoTopologica ordenacaoTopologica() {
    //     OrdenacaoTopologica ordenacaoTopologica = new OrdenacaoTopologica(grafo, vertices, isPonderado);
    //     ordenacaoTopologica.imprimirOrdenacao();
    //     return ordenacaoTopologica;
    // }

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
    public Integer[][] getGrafo() {
        return grafo;
    }

    /**
     * @return ArrayList<String> return dos vertices
     */
    public ArrayList<String> getVertices() {
        return vertices;
    }

    /**
     * Busca o peso de uma aresta em um grafo ponderado
     * @param aresta é do tipo String e consiste do par de vértices que forma a aresta.
     * @return do tipo Integer e informa o peso da aresta requisitada.
     */
    public Integer getPeso(String aresta) {
        if (!isPonderado) return null;
        String[] arestaDividida = aresta.split(",");
        int index1 = buscar_vertice(arestaDividida[0]);
        int index2 = buscar_vertice(arestaDividida[1]);
        return grafo[index1][index2];
    }
}
