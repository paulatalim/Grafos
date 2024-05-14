package graph.representacao.lista;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import graph.caminho.Dijkstra;
import graph.ordenacao.OrdenacaoTopologica;
import graph.busca.BreadthFirstSearch;
import graph.busca.DepthFirstSearch;

class ListaDirecionada {
    private List<No> grafo;
    private boolean ponderado;

    ListaDirecionada() {
        grafo = new ArrayList<No>();
    }

    /**
     * Encontra um vertice no grafo
     * @param id do vertice a ser procurado
     * @return indice na lista no grafo ou -1, se o vertice não existir
     */
    private int buscar_vertice(char id) {
        for(int i = 0; i < grafo.size(); i++) {
            if (grafo.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
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
    public boolean isNoExist(char id) {
        // Encontra o vertice no grafo
        if(buscar_vertice(id) >= 0) {
            return true;
        }
        
        return false;
    }

    /**
     * Adiciona um novo vertice ao grafo
     * @param id_vertice
     */
    public void inserir_vertice (char id_vertice) {
        // Verifica se o vertice ja existe
        if(!isNoExist(id_vertice)) {
            // Cria o novo vertice
            No new_vertice = new No(id_vertice);

            // Adiciona o vertice a lista
            grafo.add(new_vertice);
        }
    }
    
    /**
     * Adiciona uma aresta do grafo
     * @param aresta a ser inserida (String), indicada com seus vertices adjacentes
     * @return true, caso encontrar os vertices, ou false, caso não encontrar algum dos vertices adjacentes
     */
    public boolean inserir_aresta(String aresta) {
        int aux = buscar_vertice(aresta.charAt(0));

        if(aux >= 0 && buscar_vertice(aresta.charAt(1)) >= 0) {
            // Adiciona uma nova aresta
            grafo.get(aux).inserir_aresta(aresta.charAt(1));
            return true;
        }

        return false;
    }

    /**
     * Adiciona uma aresta do grafo
     * @param aresta a ser inserida (String), indicada com seus vertices adjacentes
     * @param peso da aresta
     * @return true, caso encontrar os vertices, ou false, caso não encontrar algum dos vertices adjacentes
     */
    public boolean inserir_aresta(String aresta, int peso) {
        int aux1 = buscar_vertice(aresta.charAt(0));
        int aux2 = buscar_vertice(aresta.charAt(1));

        if(aux1 >= 0 && aux2 >= 0 && ponderado) {
            // Adiciona uma nova aresta
            return grafo.get(aux1).inserir_aresta(aresta.charAt(1), peso);
        }

        return false;
    }

    /**
     * Remove uma aresta do grafo
     * @param aresta a ser retirada (String), indicada com seus vertices adjacentes
     * @return true, caso encontrar os vertices, ou false, caso não encontrar algum dos vertices adjacentes
     */
    public boolean remover_aresta(String aresta) {
        int aux1 = buscar_vertice(aresta.charAt(0));

        if (aux1 >= 0) {
            return grafo.get(aux1).remover_aresta(aresta.charAt(1));
        }

        // Caso nao encontrar a aresta
        return false;
    }

    /**
     * Verifica se uma aresta existe no grafo
     * @param aresta id da aresta ser avaliada (String)
     * @return true (se a aresta ser valida) ou false (se a aresta nao existir nao grafo)
     */
    public boolean isArestaeExist(String aresta) {
        int aux1 = buscar_vertice(aresta.charAt(0));

        if(aux1 >= 0) {
            return grafo.get(aux1).isArestaeExist(aresta.charAt(1));
        }

        return false;
    }

    /**
     * Atualiza o Peso de uma aresta
     * 
     * @param aresta que o peso vai ser atualizado
     * @param newPeso o novo peso da aresta
     * @return true (caso a atualizacao ocorrer com sucesso) ou false (caso ocorrer algum erro)
     */
    public boolean atualizarPeso(String aresta, int newPeso) {
        if(ponderado) {
            grafo.get(buscar_vertice(aresta.charAt(0))).updatePeso(aresta.charAt(1), newPeso);
            return true;
        }
        return false;
    }

    /**
     * Converte uma List<Character> para um vertor de char
     * @param list - lista a ser convertida
     * @return Array de caracteres
     */
    private char[] toArrayChar(List<Character> list) {
        // Verifica se a lista existe
        if(list == null) {
            return null;
        }

        // Cria um vetor auxiliar para armazenar os valores convertidos
        char[] vetor = new char[list.size()];
        
        // Conversao dos valores da lista para vetor
        for(int i = 0; i < list.size(); i++) {
            vetor[i] = Character.valueOf(list.get(i));
        }

        // Retorno da lista convertida para vetor
        return vetor;
    }

    /**
     * Verifica os predecessores de um vertice
     * @param vertice a ser analisado
     * @return vetor de char (vertices predecessores)
     */
    public char[] encontrarPredecessores(char vertice) {
        List<Character> predecessor = new ArrayList<Character>();

        for (int i = 0; i < grafo.size(); i++) {
            for(int j = 0; j < grafo.get(i).qnt_aresta(); j++) {
                if(grafo.get(i).getAresta(j) == vertice && !predecessor.contains(grafo.get(i).getId())) {
                    predecessor.add(Character.valueOf(grafo.get(i).getId()));
                }
            }
        }

        return toArrayChar(predecessor);
    }

    /**
     * Verifica os sucessores de um vertice
     * @param vertice a ser analisado
     * @return vetor de char (vertices sucessores)
     */
    public char[] encontrarSucessores(char vertice) {
        List<Character> sucessores = new ArrayList<Character>();

        for (int i = 0; i < grafo.size(); i++) {
            if(grafo.get(i).getId() == vertice) {
                for(int j = 0; j < grafo.get(i).qnt_aresta(); j++) {
                    if(!sucessores.contains(grafo.get(i).getAresta(j))) {
                        sucessores.add(grafo.get(i).getAresta(j));
                    }
                }
            }
        }

        return toArrayChar(sucessores);
    }

    /**
     * Calcula o grau de entrada de um vertice
     * @param vertice a ser analisado
     * @return int (grau de entrada do vertice)
     */
    public int calcularGrauEntrada(char vertice) {
        int grau = 0;

        for (int i = 0; i < grafo.size(); i++) {
            for(int j = 0; j < grafo.get(i).qnt_aresta(); j++) {
                if(grafo.get(i).getAresta(j) == vertice) {
                    grau += grafo.get(i).getPeso(j);
                }
            }
        }

        return grau;
    }

     /**
     * Calcula o grau de saida de um vertice
     * @param vertice a ser analisado
     * @return int (grau de saida do vertice)
     */
    public int calcularGrauSaida(char vertice) {
        for (int i = 0; i < grafo.size(); i++) {
            if(grafo.get(i).getId() == vertice) {
                int grau = 0;

                for(int j = 0; j < grafo.get(i).qnt_aresta(); j++) {
                    grau += grafo.get(i).getPeso(j);
                }

                return grau;
            }
        }

        return -1;
    }

    /**
     * Verifica se o grafo eh simples
     * @return true, se for simples, false, caso contrario
     */
    public boolean isSimples() {
        for(int i = 0; i < grafo.size(); i++) {
            for(int j = 0; j < grafo.get(i).qnt_aresta(); j++) {
                // Verifica se ha laco
                if(grafo.get(i).getId() == grafo.get(i).getAresta(j)) {
                    return false;
                }

                // Verifica se há aresta paralela
                for(int k = grafo.get(i).qnt_aresta() - 1; k > j; k--) {
                    if(k != j && grafo.get(i).getAresta(j) == grafo.get(i).getAresta(k)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * Verifica se o grafo eh regular
     * @return true, se for regular, false, caso contrario
     */
    public boolean isRegular() {
        if(isGrafosEmpty()) {
            return false;
        }

        char verticeAux;
        char vertice = grafo.get(0).getId();
        int grauEntrada = calcularGrauEntrada(vertice);
        int grauSaida = calcularGrauSaida(vertice);
        
        for(int i = 1; i < grafo.size(); i++) {
            verticeAux = grafo.get(i).getId();

            if(grauEntrada != calcularGrauEntrada(verticeAux) || grauSaida != calcularGrauSaida(verticeAux)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Verifica se o grafo eh completo
     * @return true, se for completo, false, caso contrario
     */
    public boolean isCompleto() {
        if(isSimples()) {
            char vertice;
            boolean exist;
            for(int i = 0; i < grafo.size(); i++) {
                vertice = grafo.get(i).getId();

                int ge = calcularGrauEntrada(vertice);
                int gs = calcularGrauSaida(vertice);

                // Verifica se o vertice possui aresta e se grau esta correto
                if(ge + gs == 0 || ge != gs) {
                    return false;
                }

                exist = true;
                // Verifica se há aresta
                for (int j = 0; j < grafo.get(i).qnt_aresta(); j++) {
                    exist = false;

                    for(int k = 0; k < grafo.size(); k++) {
                        if(grafo.get(i).getAresta(j) == grafo.get(k).getId() && grafo.get(k).getId() != grafo.get(i).getId()) {
                            exist = true;
                        }
                    }

                    if(!exist) {
                        return false;
                    }
                }
            }
            return true;
        }

        return false;
    }

    /**
     * Confere se o grafo é conexo ou não
     * @return true, se for conexo, false, caso contrário
     */
    public boolean isConexo() {    
        BreadthFirstSearch bfs = new BreadthFirstSearch(grafo);
        return bfs.getIsConexo();
    } 

    /**
     * Verifica se o grafo eh bipartido
     * 
     * @return true, se for bipartido, false, caso contrar
     */
    public boolean isBipartido () {
        Map<Character, Byte> mapeamento = new HashMap<Character, Byte>();
        byte color = 0;
        byte result;
        boolean allIsVerficado = false;
        char[] vizinhos;

        // Inicializa o mapeamento
        for(int i = 1; i < grafo.size(); i++) {
            mapeamento.put(Character.valueOf(grafo.get(i).getId()), (byte) -1);
        }

        mapeamento.put(Character.valueOf(grafo.get(0).getId()), color);
        
        while (!allIsVerficado) {
            // Encontra o proximo vertice a ser vericado
            for(int i = 0; i < grafo.size(); i++) {
                color = mapeamento.get(Character.valueOf(grafo.get(i).getId()));
                
                if (color != -1) {
                    if(color == 0) {
                        color ++;
                    } else if (color == 1) {
                        color = 0;
                    }

                    // Identifica os predecessores 
                    vizinhos = encontrarPredecessores(grafo.get(i).getId());

                    // Verifica os predecessores do vertice
                    for(int j = 0; j < vizinhos.length; j++) {
                        result = mapeamento.get(Character.valueOf(vizinhos[j]));
                        
                        // Inicializa cor
                        if(result == (byte) -1) {
                            mapeamento.put(vizinhos[j], color);
                            
                            // Caso a cor ser invalida
                        } else if ((color == 0 && result == 1) || (color == 1 && result == 0)) {
                            return false;
                        }
                    }
                    
                    // Verifica os sucessores do vertice
                    for (int j = 0; j < grafo.get(i).qnt_aresta(); j++) {
                        result = mapeamento.get(Character.valueOf(grafo.get(i).getAresta(j)));
                        
                        // Verifica se ha laco
                        if(grafo.get(i).getId() == grafo.get(i).getAresta(j)) {
                            return false;
                        }
                        // Inicializa cor
                        else if(result == (byte) -1) {
                            mapeamento.put(Character.valueOf(grafo.get(i).getAresta(j)), color);
                            
                            // Caso a cor ser invalida
                        } else if ((color == 0 && result == 1) || (color == 1 && result == 0)) {
                            return false;
                        }
                    }
                
                }
            }

            allIsVerficado = true;

            // Verifica se algum vertice nao foi verificado; colorido
            for(int i = 0; i < grafo.size(); i++) {
                if(mapeamento.get(Character.valueOf((grafo.get(i).getId()))) == -1) {
                    mapeamento.put(Character.valueOf(grafo.get(i).getId()), (byte) 0);
                    allIsVerficado = false;
                    break;
                }
            }
        }

        return true;
    }

    /**
     * Calcula o caminho minimo entre a os vertices
     * @param a char (id do vertice a ser analisado)
     * @param b char (id do vertice a ser analisado)
     * @return Integer (tamanho do caminho minimo da raiz ate a saida), ou null (caso ocorra um erro) ou Integer.MAX_VALUE (caso nao exista caminho entre os vertices)
     */
    public Integer calcularCaminhoMinimo(char a, char b) {
        Dijkstra dijkstra = new Dijkstra(grafo);
        return dijkstra.calcularCaminhoMinimo(a, b);
    }

    /**
     * Realiza uma busca em largura no grafo
     * @param verticeInicial char (vertice que a busca em largura ira iniciar)
     * @return vetor de char (ordem de visitacao dos vertices na busca em largura)
     */
    public char[] realizarBuscaLargura(char verticeInicial) {
        BreadthFirstSearch BFS = new BreadthFirstSearch(grafo);
        return toArrayChar(BFS.bfs(verticeInicial));
    }

    /**
     * Realiza uma busca em profundidade no grafo
     * @param raiz char (vertice que a busca em profundidade irá iniciar)
     * @return objeto da classe DepthFirstSearch
     */
    public DepthFirstSearch realizarBuscaProfundidade(char raiz) {
        if(isNoExist(raiz)) {
            DepthFirstSearch DFS = new DepthFirstSearch(grafo);
            DFS.dfs(raiz);
            return DFS;
        }
        return null;
    }

    /**
     * Imprime a lista de adjacencia no console
     */
    public void exibir_lista() {
        for (int i = 0; i < grafo.size(); i++) {
            No no = grafo.get(i);
            System.out.print("\t" + no.getId() + ": [ ");

            if (no.qnt_aresta() > 0) {
                System.out.print(no.getAresta(0));
            }

            for (int j = 1; j < no.qnt_aresta(); j++) {
                System.out.print(", " + no.getAresta(j));
            }

            System.out.println(" ]");
        }
    }

    /**
     * Faz uma ordenacao topologica com os vertices do grafo
     * @return objeto da ordenacao topologica
     */
    public OrdenacaoTopologica ordenacaoTopologica() {
        OrdenacaoTopologica ordenacaoTopologicaList = new OrdenacaoTopologica(grafo);
        ordenacaoTopologicaList.imprimirOrdenacaoLista();
        return ordenacaoTopologicaList;
    }

    /**
     * Se o Grafo eh poderado
     * @param isPonderado
     */
    public void setIsPonderado(boolean isPonderado) {
        this.ponderado = isPonderado;
    }

    /**
     * Se o grafo eh poderado
     * 
     * @return true ou false
     */
    public boolean isPonderado() {
        return ponderado;
    }

    /**
     * @return List<No> return the grafo
     */
    public List<No> getGrafo() {
        return grafo;
    }
}
