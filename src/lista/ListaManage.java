package lista;

import java.util.List;

public class ListaManage {
    private boolean direcionado;
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
    public void inserir_vertice (char id_vertice) {
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
    public boolean inserir_aresta (String aresta) {
        if(direcionado) {
            return ld.inserir_aresta(aresta);
        }
        
        return lnd.inserir_aresta(aresta);
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
