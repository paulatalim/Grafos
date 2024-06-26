package graph.representacao.matriz;

import java.util.ArrayList;
import java.util.List;

// import graph.AGM.ArvoreGeradoraMinima;
// import graph.busca.DepthFirstSearch;
// import graph.ordenacao.OrdenacaoTopologica;

public class MatrizManage {
    private boolean direcionado;
    private boolean ponderado;
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
     * Confere se o grafo é bipartido ou não
     * @return true, se for bipartido, false, caso contrário
     */
    public boolean isGrafosBipartido() {
        if(direcionado) {
            return md.isGrafosBipartido();
        }
        return mnd.isGrafosBipartido();
    }

    /**
     * Confere se o grafo é conexo ou não
     * @return true, se for conexo, false, caso contrário
     */
    public boolean isGrafosConexo() {
        if(direcionado) {
            return md.isGrafosConexo();
        }
        return mnd.isGrafosConexo();
    }

    /**
     * Verifica se um vertice existe
     * 
     * @param id do vertice a ser verificado
     * @return true, se o vertice existir, false, caso contrario
     */
    public boolean isVerticeExist(String id) {
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
    public void inserir_vertice(String id_vertice) {
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
    public int[] grau_vertice(String id_vertice) {
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
    public boolean inserir_aresta(String vertice1, String vertice2) {
        if(direcionado) {
            return md.inserir_aresta(vertice1, vertice2);
        }
        
        return mnd.inserir_aresta(vertice1, vertice2);
    }

    /**
     * Adiciona uma aresta ao grafo
     *
     * @param aresta a ser inserida (informe os vertices adjacentes, ex: AB, CD, 12)
     */
    public boolean inserir_aresta(String vertice1, String vertice2, int peso) {
        if(direcionado) {
            return md.inserir_aresta(vertice1, vertice2, peso);
        }
        
        return mnd.inserir_aresta(vertice1, vertice2, peso);
    }

    /**
     * Remove uma aresta do grafo
     * 
     * @param aresta - informada pelo id dos vertices adjacentes
     * @return true, se foi possivel remover, false, se a aresta for invalida
     */
    public boolean remover_aresta(String vertice1, String vertice2) {
        if(direcionado) {
            return md.remover_aresta(vertice1, vertice2);
        }

        return mnd.remover_aresta(vertice1, vertice2);
    }

    /**
     * Atualiza o peso de uma aresta
     * @param aresta que o peso será atualizado, indicada com seus vertices adjacentes
     * @param newPeso novo peso da aresta
     * @return true, caso encontrar os vertices e haver aresta entre eles, ou false, caso contrario
     */
    public boolean atualizarPeso(String vertice1, String vertice2, int newPeso) {
        if(direcionado) {
            return md.atualizarPeso(vertice1, vertice2, newPeso);
        }
        
        return mnd.atualizarPeso(vertice1, vertice2, newPeso);
    }
    
    /**
     * Verifica a vizinhaca do vertice
     * @param id_vertice a ser analisado
     * @return vetor de char (vertices adjacentes ao analisado) ou null (caso o grafo ser direcionado)
     */
    public List<String> verfica_vizinhos(String id_vertice) {
        if(direcionado){
            return null;
        } else {
            return mnd.verifica_vizinhanca(id_vertice);   
        }
    }
    
    /**
     * Verifica os sucessores de um vertice
     * @param vertice a ser analisado
     * @return vetor de char (vertices sucessores) ou null (caso o grafo nao ser direcionado)
     */
    public ArrayList<String> verifica_sucessores(String id_vertice) {
        if(direcionado){
            return md.verifica_sucessores(id_vertice);
        }
        return null;
    }
    
    /**
     * Verifica os predecessores de um vertice
     * @param id_vertice a ser analisado
     * @return vetor de char (vertices predecessores) ou null (caso o grafo nao ser direcionado)
     */
    public ArrayList<String> verifica_predecessores(String id_vertice) {
        if(direcionado){
            return md.verifica_predecessores(id_vertice);
        }
        
        return null;
    }

    /**
     * Calcula o caminho minimo entre a os vertices
     * @param a char (id do vertice a ser analisado)
     * @param b char (id do vertice a ser analisado)
     * @return Integer (tamanho do caminho minimo da raiz ate a saida), ou null (caso ocorra um erro) ou Integer.MAX_VALUE (caso nao exista caminho entre os vertices)
     */
    public Integer calcularCaminhoMinimo(String a, String b) {
        if (direcionado) {
            return md.calcularCaminhoMinimo(a, b);
        } 

        return mnd.calcularCaminhoMinimo(a, b);
    }

    /**
     * Imprime o grafo em forma de matriz
     */
    public void exibir_matriz() {
        if(direcionado) {
            md.exibir_matriz();
        } else {
            mnd.exibir_matriz();
        }
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
     * Se o Grafo eh poderado
     * @param isPonderado
     */
    public void setIsPonderado(boolean isPonderado) {
        this.ponderado = isPonderado;

        if(direcionado) {
            md.setIsPonderado(isPonderado);
        } else {
            mnd.setIsPonderado(isPonderado);
        }
    }

    /**
     * Se o grafo eh poderado
     * 
     * @return true ou false
     */
    public boolean isPonderado() {
        return ponderado;
    }

    public ArrayList<String> getVertices() {
        if(direcionado) return md.getVertices();
        return mnd.getVertices();
    }

    public Integer[][] getGrafo() {
        if(direcionado) return md.getGrafo();
        return mnd.getGrafo();
    }

    public Integer getPeso(String aresta) {
        if(direcionado) return md.getPeso(aresta);
        return mnd.getPeso(aresta);
    }

    public void setMatrizDirecionada (MatrizDirecionada newMatriz) {
        this.md = newMatriz;
    }

    public MatrizDirecionada getMatrizDirecionada () {
        return this.md;
    }
}
