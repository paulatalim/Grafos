package matriz;

public class MatrizManage {
    private boolean direcionado;
    private MatrizDirecionada md;
    private MatrizNaoDirecionada mnd;

    /**
     * Verifica se o grafo está vazio
     * @return true, se estiver vazio, false, caso contrario
     */
    public boolean isGrafosEmpty() {
        if(direcionado) {
            return md.isGrafosEmpty();
        }
        
        return mnd.isGrafosEmpty();
    }

    /**
     * Confere se o grafo é simples ou não
     * @return true se for, caso contrário, false
     */
    public boolean isGrafosSimples() {
        if(direcionado) {
            return md.isGrafosSimples();
        }
        return mnd.isGrafosSimples();

    }

    /**
     * Confere se o grafo é regular ou não
     * @return true se for, caso contrário, false
     */
    public boolean isGrafosRegular() {
        if(direcionado) {
            return md.isGrafosRegular();
        }
        return mnd.isGrafosRegular();
    }

    /**
     * Confere se o grafo é completo ou não
     * @return true se for, caso contrário, false
     */
    public boolean isGrafosCompleto() {
        if(direcionado) {
            return md.isGrafosCompleto();
        }
        return mnd.isGrafosCompleto();
    }

    /**
     * Verifica se um vertice existe
     * 
     * @param id do vertice a ser verificado
     * @return true, se o vertice existir, false, caso contrario
     */
    public boolean isVerticeExist(char id) {
        if(direcionado) {
            return md.isNoExist(id);
        }
        
        return mnd.isNoExist(id);
    }

    /**
     * Adiciona um vertice no grafo
     * 
     * @param id_vertice a ser inserido
     */
    public void inserir_vertice (char id_vertice) {
        if(direcionado) {
            md.inserir_vertice(id_vertice);
        } else {
            mnd.inserir_vertice(id_vertice);
        }
    }

    /**
     * Recebe o ID de um vértice e calcula os possíveis graus
     * @param id_vertice
     * @return vetor de inteiros contendo o grau ou os graus do vértice
     */
    public int[] grau_vertice (char id_vertice) {
        if(direcionado) {
            return md.grau_vertice(id_vertice);
        }
        return mnd.grau_vertice(id_vertice);
    }

    /**
     * Verifica se o grafo possui aresta
     * @return true, se tiver aresta, false caso contrario
     */
    public boolean temAresta() {
        if(direcionado) {
            return md.temAresta();
        }
        
        return mnd.temAresta();
    }

    /**
     * Adiciona uma aresta ao grafo
     *
     * @param aresta a ser inserida (informe os vertices adjacentes, ex: AB, CD, 12)
     */
    public boolean inserir_aresta (String aresta) {
        if(direcionado) {
            return md.inserir_aresta(aresta);
        }
        
        return mnd.inserir_aresta(aresta);
    }

    /**
     * Remove uma aresta do grafo
     * 
     * @param aresta - informada pelo id dos vertices adjacentes
     * @return true, se foi possivel remover, false, se a aresta for invalida
     */
    public boolean remover_aresta(String aresta) {
        if(direcionado) {
            return md.remover_aresta(aresta);
        }

        return mnd.remover_aresta(aresta);
    }

    /**
     * Se o grafo eh ou nao direcionado
     * @param isDirecionado
     */
    public void setIsDirecionado(boolean isDirecionado) {
        this.direcionado = isDirecionado;

        if (this.direcionado) {
            md = new MatrizDirecionada();
        } else {
            mnd = new MatrizNaoDirecionada();
        }
    }

    /**
     * Se o grafo é ou não direcionado
     * @return boolean return the direcionado
     */
    public boolean isDirecionado() {
        return direcionado;
    }

    /**
     * Imprime o grafo em forma de matriz
     */
    public void exibir_matriz () {
        if(direcionado) {
            md.exibir_matriz();
        } else {
            mnd.exibir_matriz();
        }
    }

    public char[] verfica_vizinhos (char id_vertice) {
        if(direcionado){
           return null;
        } else {
            return mnd.verifica_vizinhanca(id_vertice);   
        }
    }

    public char[] verifica_sucessores (char id_vertice){
        if(direcionado){
            char[] arraySucessores = md.verifica_sucessores(id_vertice);
            return arraySucessores;
            
        } else {
            return null;
        }
    }

    public char[] verifica_predecessores (char id_vertice){
        if(direcionado){
            char[] arrayPredecessores = md.verifica_predecessores(id_vertice);
            return arrayPredecessores;
        } else { 
            return null;
        }
    }
}
