package lista;

import java.util.List;

public class ListaManage {
    private boolean direcionado;
    private boolean ponderado;
    private ListaDirecionada ld;
    private ListaNaoDirecionada lnd;

    /**
     * Verifica se o grafo está vazio
     * @return true, se estiver vazio, false, caso contrario
     */
    public boolean isGrafosEmpty() {
        if(direcionado) {
            return ld.isGrafosEmpty();
        }
        
        return lnd.isGrafosEmpty();
    }

    /**
     * Verifica se o grafo possui aresta
     * @return true, se tiver aresta, false caso contrario
     */
    public boolean temAresta() {
        if(direcionado) {
            return ld.temAresta();
        }
        
        return lnd.temAresta();
    }

    /**
     * Verifica se um vertice existe
     * 
     * @param id do vertice a ser verificado
     * @return true, se o vertice existir, false, caso contrario
     */
    public boolean isVerticeExist(char id) {
        if(direcionado) {
            return ld.isNoExist(id);
        }
        
        return lnd.isNoExist(id);
    }

    /**
     * Adiciona um vertice no grafo
     * 
     * @param id_vertice a ser inserido
     */
    public void inserir_vertice(char id_vertice) {
        if(direcionado) {
            ld.inserir_vertice(id_vertice);
        } else {
            lnd.inserir_vertice(id_vertice);
        }
    }

    /**
     * Adiciona uma aresta ao grafo
     *
     * @param aresta a ser inserida (informe os vertices adjacentes, ex: AB, CD, 12)
     */
    public boolean inserir_aresta(String aresta) {
        if(direcionado) {
            return ld.inserir_aresta(aresta);
        }
        
        return lnd.inserir_aresta(aresta);
    }

    /**
     * Adiciona uma aresta ao grafo
     *
     * @param aresta a ser inserida (informe os vertices adjacentes, ex: AB, CD, 12)
     * @param peso da aresta
     */
    public boolean inserir_aresta(String aresta, int peso) {
        if(direcionado) {
            return ld.inserir_aresta(aresta, peso);
        }
        
        return lnd.inserir_aresta(aresta, peso);
    }

    /**
     * Remove uma aresta do grafo
     * 
     * @param aresta - informada pelo id dos vertices adjacentes
     * @return true, se foi possivel remover, false, se a aresta for invalida
     */
    public boolean remover_aresta(String aresta) {
        if(direcionado) {
            return ld.remover_aresta(aresta);
        }

        return lnd.remover_aresta(aresta);
    }

    public boolean isArestaeExist(String aresta) {
        if(direcionado) {
            return ld.isArestaeExist(aresta);
        }

        return lnd.isArestaeExist(aresta);
    }

    /**
     * Adiciona uma aresta ao grafo
     *
     * @param aresta a ser inserida (informe os vertices adjacentes, ex: AB, CD, 12)
     * @param peso da aresta
     */
    public boolean atualizarPeso(String aresta, int peso) {
        if(direcionado) {
            return ld.atualizarPeso(aresta, peso);
        }
        
        return lnd.atualizarPeso(aresta, peso);
    }

    /**
     * Verifica os predecessores de um vertice
     * @param vertice a ser analisado
     * @return vetor de char (vertices predecessores) ou null (caso o grafo nao ser direcionado)
     */
    public char[] encontrarPredecessores(char vertice) {
        if(direcionado) {
            return ld.encontrarPredecessores(vertice);
        }

        return null;
    }

    /**
     * Verifica os sucessores de um vertice
     * @param vertice a ser analisado
     * @return vetor de char (vertices sucessores) ou null (caso o grafo nao ser direcionado)
     */
    public char[] encontrarSucessores(char vertice) {
        if(direcionado) {
            return ld.encontrarSucessores(vertice);
        }

        return null;
    }

    /**
     * Calcula o grau de entrada de um vertice
     * @param vertice a ser analisado
     * @return int (grau de entrada do vertice) ou -1 (caso o grafo nao ser direcionado)
     */
    public int calcularGrauEntrada(char vertice) {
        if(direcionado) {
            return ld.calcularGrauEntrada(vertice);
        }

        return -1;
    }
    
    /**
     * Calcula o grau de saida de um vertice
     * @param vertice a ser analisado
     * @return int (grau de saida do vertice) ou -1 (caso o grafo nao ser direcionado)
     */
    public int calcularGrauSaida(char vertice) {
        if(direcionado) {
            return ld.calcularGrauSaida(vertice);
        }

        return -1;
    }

    /**
     * Verifica a vizinhaca do vertice
     * @param vertice a ser analisado
     * @return vetor de char (vertices adjacentes ao analisado) ou null (caso o grafo ser direcionado)
     */
    public char[] vizinhaca(char vertice) {
        if(!direcionado) {
            return lnd.vizinhaca(vertice);
        }

        return null;
    }

    /**
     * Calcula o grau de um vertice
     * @param vertice a ser analisado
     * @return int (grau do vertice)
     */
    public int calcularGrau(char vertice) {
        if(direcionado) {
            return -1;
        }
        return lnd.calcularGrau(vertice);
    }

    /**
     * Verifica se o grafo eh simples
     * @return true, se for simples, false, caso contrario
     */
    public boolean isSimples() {
        if(direcionado) {
            return ld.isSimples();
        }

        return lnd.isSimples();
    }

    /**
     * Verifica se o grafo eh regular
     * @return true, se for regular, false, caso contrario
     */
    public boolean isRegular() {
        if(direcionado) {
            return ld.isRegular();
        }

        return lnd.isRegular();
    }

    /**
     * Verifica se o grafo eh completo
     * @return true, se for completo, false, caso contrario
     */
    public boolean isCompleto() {
        if(direcionado) {
            return ld.isCompleto();
        }

        return lnd.isCompleto();
    }

    /**
     * Verifica se o grafo eh bipartido
     * 
     * @return true, se for bipartido, false, caso contrar
     */
    public boolean isBipartido () {
        if(direcionado) {
            return ld.isBipartido();
        }
        return lnd.isBipartido();
    }

    /**
     * Confere se o grafo é conexo ou não
     * @return true, se for conexo, false, caso contrário
     */
    public boolean isConexo () {
        if(direcionado) {
            return ld.isConexo();
        }
        return lnd.isConexo();
    }
    
    /**
     * Imprime o grafo em forma de lista
     */
    public void exibir_lista () {
        if(direcionado) {
            ld.exibir_lista();
        } else {
            lnd.exibir_lista();
        }
    }

    /**
     * Se o Grafo eh poderado
     * @param isPonderado
     */
    public void setIsPonderado(boolean isPonderado) {
        this.ponderado = isPonderado;

        if(direcionado) {
            ld.setIsPonderado(isPonderado);
        } else {
            lnd.setIsPonderado(isPonderado);
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

    /**
     * Se o grafo é ou não direcionado
     * @return boolean return the direcionado
     */
    public boolean isDirecionado() {
        return direcionado;
    }

    /**
     * Se o grafo eh ou nao direcionado
     * @param isDirecionado
     */
    public void setIsDirecionado(boolean isDirecionado) {
        this.direcionado = isDirecionado;

        if (this.direcionado) {
            ld = new ListaDirecionada();
        } else {
            lnd = new ListaNaoDirecionada();
        }
    }

    /**
     * @return List<No> return the grafo
     */
    public List<No> getGrafo() {
        if(direcionado) {
            return ld.getGrafo();
        }

        return lnd.getGrafo();
    }
}
