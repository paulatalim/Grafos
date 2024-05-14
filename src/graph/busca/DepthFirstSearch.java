package graph.busca;

import java.util.ArrayList;
import java.util.List;

import graph.representacao.lista.No;

public class DepthFirstSearch {
    private List<No> graphL;
    private Integer[][] graphM;
    private ArrayList<Character> vertices;
    private boolean isPonderado;
    private ArrayList<Character> ordemVisita;
    private int t;
    private int[] td;
    private int[] tt;

    public DepthFirstSearch(List<No> graph) {
        graphL = graph;
        t = 0;
        td = new int[graphL.size()];
        tt = new int[graphL.size()];
        ordemVisita = new ArrayList<>();
    }

    public DepthFirstSearch(Integer[][] graph, ArrayList<Character> vertices, boolean isPonderado) {
        graphM = graph;
        this.vertices = vertices;
        this.isPonderado = isPonderado;
        t = 0;
        td = new int[vertices.size()];
        tt = new int[vertices.size()];
        ordemVisita = new ArrayList<>();
    }

    /**
     * Método auxiliar da busca em profundidade na matriz de adjacência. 
     * Garante que todos os vértices sejam visitados, mesmo que o grafo seja desconexo ou direcionado.
     * @param verticeRaiz do tipo char informa qual será o vértice raiz da árvore.
     */
    private void dfsMatrizRaiz (char verticeRaiz) {
        boolean haVerticeNaoDescoberto;
        do {
            dfsMatriz(verticeRaiz); 
            haVerticeNaoDescoberto = false;
            for (int i = 0; i < td.length; i++) {
                if (td[i] == 0) {
                    verticeRaiz = vertices.get(i);
                    haVerticeNaoDescoberto = true;
                    break;
                }
            }
        } while(haVerticeNaoDescoberto);
    }

    /**
     * Método principal da busca em profundidade na matriz de adjacência.
     * Visita todos os vértices alcançáveis a partir do vértice raiz.
     * @param verticeAtual do tipo char informa qual é vértice atual sendo analisado.
     */
    private void dfsMatriz (char verticeAtual) {
        int indiceVerticeAtual = vertices.indexOf(verticeAtual); 
        ordemVisita.add(verticeAtual);
        t += 1;
        td[indiceVerticeAtual] = t; // Preenche o tempo de descoberta do vértice atual.
        for(int i = 0; i < vertices.size(); i++) {
            // Confere se existe aresta entre os vértices
            if((isPonderado && graphM[indiceVerticeAtual][i] != null) || (!isPonderado && graphM[indiceVerticeAtual][i] != 0) ) {
                // Confere se já foi descoberto.
                if(td[i] == 0) {
                    // Chama recursivamente o método passando o vértice analisado como parâmetro (preenche a pilha).
                    dfsMatriz(vertices.get(i)); 
                }
            }
        }
        t += 1;
        tt[indiceVerticeAtual] = t; // Preenche o tempo de término do vértice atual.
    }

    /**
     * Método auxiliar da busca em profundidade na lista de adjacência. 
     * Garante que todos os vértices sejam visitados, mesmo que o grafo seja desconexo ou direcionado.
     * @param verticeRaiz do tipo char informa qual será o vértice raiz da árvore.
     */
    private void dfsListaRaiz (char verticeRaiz) {
        boolean haVerticeNaoDescoberto;
        do {
            dfsLista(verticeRaiz);
            haVerticeNaoDescoberto = false;
            for (int i = 0; i < td.length; i++) {
                if (td[i] == 0) {
                    verticeRaiz = graphL.get(i).getId();
                    haVerticeNaoDescoberto = true;
                    break;
                }
            }
        } while(haVerticeNaoDescoberto);
    }
    
    /**
     * Método principal da busca em profundidade na lista de adjacência.
     * Visita todos os vértices alcançáveis a partir do vértice raiz.
     * @param verticeAtual do tipo char informa qual é vértice raiz da árvore atual.
     */
    private void dfsLista (char verticeAtual) {
        int indiceVerticeAtual = -1;
        for(int i = 0; i < graphL.size(); i++) {
            if(graphL.get(i).getId() == verticeAtual) {
                indiceVerticeAtual = i;
            } 
        }
        ordemVisita.add(verticeAtual);
        t += 1;
        td[indiceVerticeAtual] = t; // Preenche o tempo de descoberta do vértice atual.
        char verticeSendoAnalisado;
        for(int i = 0; i < graphL.get(indiceVerticeAtual).qnt_aresta(); i++) {
            verticeSendoAnalisado = graphL.get(indiceVerticeAtual).getAresta(i);
            for(int j = 0; j < graphL.size(); j++) {
                // Busca pelos vértices presentes na lista, comparando-os com 'verticeSendoAnalisado' para que o índice real seja obtido.
                if(graphL.get(j).getId() == verticeSendoAnalisado) {
                    // Confere se já foi descoberto.
                    if(td[j] == 0) {
                        // Chama recursivamente o método passando o vértice analisado como parâmetro (preenche a pilha).
                        dfsLista(verticeSendoAnalisado); 
                    }
                }
            }
        }
        t += 1;
        tt[indiceVerticeAtual] = t; // Preenche o tempo de término do vértice atual.
    }

    /**
     * Método auxiliar da busca em profundidade que chama métodos diferentes baseado na representação do grafo.
     * @param verticeInicial do tipo char informa o vértice escolhido pelo usuário para o início da busca.
     */
    public void dfs (char verticeInicial) {
        if (graphL == null) {
            dfsMatrizRaiz(verticeInicial); 
        }
        else {
            dfsListaRaiz(verticeInicial);
        }
    }

    /**
     * Método para acesso dos vértices presentes no grafo.
     * @return vetor de char contendo todos os vértices.
     */
    public char[] getVertices() {
        char[] vertices;
        if(graphL == null) {
            vertices = new char[this.vertices.size()];
            for(int i = 0; i < vertices.length; i++) {
                vertices[i] = this.vertices.get(i);
            }
            return vertices;
        }
        vertices = new char[graphL.size()];
        for(int i = 0; i < vertices.length; i++) {
            vertices[i] = graphL.get(i).getId();
        }
        return vertices;

    }

    /**
     * Método para acesso do atributo privado 'td'.
     * @return vetor de int contendo o conteúdo de 'td'.
     */
    public int[] getTD() {
        return td;
    }

    /**
     * Método para acesso do atributo privado 'tt'.
     * @return vetor de int contendo o conteúdo de 'tt'.
     */
    public int[] getTT() {
        return tt;
    }

    /**
     * Método para acesso do atributo privado 'ordemVisita'.
     * @return vetor de char contendo o conteúdo de 'ordemVisita'.
     */
    public char[] getOrdem() {
        char[] ordem = new char[ordemVisita.size()];
        for(int i = 0; i < ordem.length; i++) {
            ordem[i] = ordemVisita.get(i);
        }
        return ordem;
    }
}
