package lista;

import java.util.List;

public class ListaManage {
    private boolean direcionado;
    private ListaDirecionada ld;
    private ListaNaoDirecionada lnd;

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
