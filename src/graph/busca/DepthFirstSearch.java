package graph.busca;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import graph.representacao.lista.No;

public class DepthFirstSearch {
    private List<No> graphL;
    private int[][] graphM;
    private ArrayList<Character> vertices;
    private int t;
    private int[] td;
    private int[] tt;
    private char[] pai;

    public DepthFirstSearch(List<No> graph) {
        graphL = graph;
        t = 0;
        td = new int[graphL.size()];
        tt = new int[graphL.size()];
        pai = new char[graphL.size()];
    }

    public DepthFirstSearch(int[][] graph, ArrayList<Character> vertices) {
        graphM = graph;
        this.vertices = vertices;
        t = 0;
        td = new int[vertices.size()];
        tt = new int[vertices.size()];
        pai = new char[vertices.size()];
    }

    private void dfsMatriz (char verticeInicial) {
        int indiceVerticeInicial = vertices.indexOf(verticeInicial);
        boolean haVerticeNaoDescoberto = false;
        do {
            t += 1;
            td[indiceVerticeInicial] = t;
            for(int i = 0; i < vertices.size(); i++) {
                if(graphM[indiceVerticeInicial][i] != 0) {
                    if(td[i] == 0) {
                        pai[i] = verticeInicial;
                        dfsMatriz(vertices.get(i));
                    }
                }
            }
            t += 1;
            tt[indiceVerticeInicial] = t;
            for (int i = 0; i < td.length; i++) {
                if(td[i] == 0) {
                    haVerticeNaoDescoberto = true;
                    break;
                }
            }
        } while(haVerticeNaoDescoberto);
    }
    
    private void dfsLista (char verticeInicial) {
        int indiceVerticeInicial = vertices.indexOf(verticeInicial);
    }

    public void dfs (char verticeInicial) {
        if (graphL == null) {
            dfsMatriz(verticeInicial);
        }
        else {
            dfsLista(verticeInicial);
        }
        
    }
}
