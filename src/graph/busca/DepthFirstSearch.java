package graph.busca;

import java.util.ArrayList;
import java.util.List;

import graph.representacao.lista.No;

public class DepthFirstSearch {
    private List<No> graphL;
    private int[][] graphM;
    private ArrayList<Character> vertices;
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

    public DepthFirstSearch(int[][] graph, ArrayList<Character> vertices) {
        graphM = graph;
        this.vertices = vertices;
        t = 0;
        td = new int[vertices.size()];
        tt = new int[vertices.size()];
        ordemVisita = new ArrayList<>();
    }

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

    private void dfsMatriz (char verticeAtual) {
        int indiceVerticeAtual = vertices.indexOf(verticeAtual);
        ordemVisita.add(verticeAtual);
        t += 1;
        td[indiceVerticeAtual] = t;
        for(int i = 0; i < vertices.size(); i++) {
            if(graphM[indiceVerticeAtual][i] != 0) {
                if(td[i] == 0) {
                    dfsMatriz(vertices.get(i));
                }
            }
        }
        t += 1;
        tt[indiceVerticeAtual] = t;
    }

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
    
    private void dfsLista (char verticeAtual) {
        int indiceVerticeAtual = -1;
        for(int i = 0; i < graphL.size(); i++) {
            if(graphL.get(i).getId() == verticeAtual) {
                indiceVerticeAtual = i;
            } 
        }
        ordemVisita.add(verticeAtual);
        t += 1;
        td[indiceVerticeAtual] = t;
        char verticeSendoAnalisado;
        for(int i = 0; i < graphL.get(indiceVerticeAtual).qnt_aresta(); i++) {
            verticeSendoAnalisado = graphL.get(indiceVerticeAtual).getAresta(i);
            for(int j = 0; j < graphL.size(); j++) {
                if(graphL.get(j).getId() == verticeSendoAnalisado) {
                    if(td[j] == 0) {
                        dfsLista(verticeSendoAnalisado);
                    }
                }
            }
        }
        t += 1;
        tt[indiceVerticeAtual] = t;
    }

    public void dfs (char verticeInicial) {
        if (graphL == null) {
            dfsMatrizRaiz(verticeInicial);
        }
        else {
            dfsListaRaiz(verticeInicial);
        }
    }

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

    public int[] getTD() {
        return td;
    }

    public int[] getTT() {
        return tt;
    }

    public char[] getOrdem() {
        char[] ordem = new char[ordemVisita.size()];
        for(int i = 0; i < ordem.length; i++) {
            ordem[i] = ordemVisita.get(i);
        }
        return ordem;
    }
}
