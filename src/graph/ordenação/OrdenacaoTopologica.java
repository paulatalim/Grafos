package graph.ordenação;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Collections;

import graph.representacao.lista.No;

public class OrdenacaoTopologica {
    private List<No> graphL;
    private Integer[][] graphM;
    private ArrayList<Character> vertices;
    private boolean ponderado;

    public OrdenacaoTopologica(List<No> grafo) {
        graphL = grafo;
    }

    public OrdenacaoTopologica(Integer[][] grafo, ArrayList<Character> vertices, boolean ponderado) {
        graphM = grafo;
        this.ponderado = ponderado;
        this.vertices = vertices;
    }

    public ArrayList<Character> otMatriz(char verticeInicial) {
        if (hasCycle()) {
            System.out.println("\tO grafo contém um ciclo. Não é possível realizar uma ordenação topológica.");
            return new ArrayList<>(); // Se houver um ciclo, retornamos uma lista vazia
        }

        boolean[] visited = new boolean[graphM.length];
        ArrayList<Character> ordenacao = new ArrayList<>();

        for (int i = 0; i < graphM.length; i++) {
            if (!visited[i]) {
                dfs(graphM, i, visited, ordenacao);
            }
        }
        Collections.reverse(ordenacao);

        if (ordenacao.get(0) != verticeInicial) {
            ordenacao.remove(Character.valueOf(verticeInicial));
        }

        return ordenacao;
    }

    public boolean hasCycle() {
        boolean[] visitado = new boolean[graphM.length];
        boolean[] pilhaRecursao = new boolean[graphM.length]; // Pilha para rastrear recursivamente os vértices
                                                              // visitados

        for (int i = 0; i < graphM.length; i++) {
            if (hasCycleUtil(i, visitado, pilhaRecursao)) {
                return true; // Se encontrarmos um ciclo, retornamos true
            }
        }
        return false; // Se não encontrarmos nenhum ciclo, retornamos false
    }

    private boolean hasCycleUtil(int vertice, boolean[] visitado, boolean[] pilhaRecursao) {
        if (!visitado[vertice]) {
            visitado[vertice] = true;
            pilhaRecursao[vertice] = true;

            for (int i = 0; i < graphM.length; i++) {
                if ((ponderado && graphM[vertice][i] != null || (!ponderado && graphM[vertice][i] != 0))) {
                    if (!visitado[i] && hasCycleUtil(i, visitado, pilhaRecursao)) {
                        return true;
                    } else if (pilhaRecursao[i]) {
                        return true;
                    }
                }
            }
        }
        pilhaRecursao[vertice] = false; // Remove o vértice da pilha de recursão antes de retornar
        return false;
    }

    private void dfs(Integer[][] grafo, int vertice, boolean[] visitado, ArrayList<Character> ordenacao) {
        visitado[vertice] = true;

        for (int i = 0; i < grafo.length; i++) {
            if ((ponderado && grafo[vertice][i] != null || (!ponderado && grafo[vertice][i] != 0)) && !visitado[i]) {
                dfs(grafo, i, visitado, ordenacao);
            }
        }

        ordenacao.add((char) ('A' + vertice));
    }

    public void imprimirOrdenacao(char verticeInicial) {
        ArrayList<Character> ordenacao = otMatriz(verticeInicial);
        if (!ordenacao.isEmpty()) {
            System.out.println("\tOrdenação Topológica:");
            System.out.print("\t");
            int tamanho = ordenacao.size();
            for (int i = 0; i < tamanho; i++) {
                System.out.print(ordenacao.get(i));
                if (i < tamanho - 1) { // Verifica se não é o último vértice
                    System.out.print(" -> ");
                }
            }
            System.out.println();
        }
    }

    public char escolherVerticeInicial() {
        int[] grauEntrada = new int[graphM.length]; // Array para armazenar o grau de entrada de cada vértice

        // Calcular o grau de entrada de cada vértice
        for (int i = 0; i < graphM.length; i++) {
            for (int j = 0; j < graphM.length; j++) {
                if ((ponderado && graphM[i][j] != null) || (!ponderado && graphM[i][j] != 0)){
                    grauEntrada[j]++;
                }
            }
        }

        // Encontrar o primeiro vértice com grau de entrada zero
        for (int i = 0; i < graphM.length; i++) {
            if (grauEntrada[i] == 0) {
                return (char) ('A' + i); // Retorna o vértice com grau de entrada zero
            }
        }

        return ' '; // Retorna um espaço em branco se não houver vértice com grau de entrada zero
    }

    // ==========================================================================================================================
    // ==========================================================================================================================
    // =============================================== GRAFO - LISTA ADJACENCIA
    // =================================================

    public ArrayList<Character> otLista(char verticeInicial) {
        if (hasCycleLista()) {
            System.out.println("\tO grafo contém um ciclo. Não é possível realizar uma ordenação topológica.");
            return new ArrayList<>(); // Se houver um ciclo, retornamos uma lista vazia
        }

        boolean[] visited = new boolean[graphL.size()];
        ArrayList<Character> ordenacao = new ArrayList<>();

        for (int i = 0; i < graphL.size(); i++) {
            if (!visited[i]) {
                dfs(i, visited, ordenacao);
            }
        }
        Collections.reverse(ordenacao);

        if (ordenacao.get(0) != verticeInicial) {
            ordenacao.remove(Character.valueOf(verticeInicial));
        }

        return ordenacao;
    }

    public boolean hasCycleLista() {
        boolean[] visitado = new boolean[graphL.size()];
        boolean[] pilhaRecursao = new boolean[graphL.size()]; // Pilha para rastrear recursivamente os vértices
                                                              // visitados

        for (int i = 0; i < graphL.size(); i++) {
            if (hasCycleUtilLista(i, visitado, pilhaRecursao)) {
                return true; // Se encontrarmos um ciclo, retornamos true
            }
        }
        return false; // Se não encontrarmos nenhum ciclo, retornamos false
    }

    private boolean hasCycleUtilLista(int vertice, boolean[] visitado, boolean[] pilhaRecursao) {
        if (!visitado[vertice]) {
            visitado[vertice] = true;
            pilhaRecursao[vertice] = true;

            No currentNo = graphL.get(vertice);
            for (int i = 0; i < currentNo.qnt_aresta(); i++) {
                char adjacentId = currentNo.getAresta(i);
                int adjacentIndex = adjacentId - 'A';
                if (!visitado[adjacentIndex] && hasCycleUtilLista(adjacentIndex, visitado, pilhaRecursao)) {
                    return true;
                } else if (pilhaRecursao[adjacentIndex]) {
                    return true;
                }
            }
        }
        pilhaRecursao[vertice] = false; // Remove o vértice da pilha de recursão antes de retornar
        return false;
    }

    private void dfs(int vertice, boolean[] visitado, ArrayList<Character> ordenacao) {
        visitado[vertice] = true;

        No currentNo = graphL.get(vertice);
        for (int i = 0; i < currentNo.qnt_aresta(); i++) {
            char adjacentId = currentNo.getAresta(i);
            int adjacentIndex = adjacentId - 'A';
            if (!visitado[adjacentIndex]) {
                dfs(adjacentIndex, visitado, ordenacao);
            }
        }

        ordenacao.add((char) ('A' + vertice));
    }

    public void imprimirOrdenacaoLista(char verticeInicial) {
        ArrayList<Character> ordenacao = otLista(verticeInicial);
        if (!ordenacao.isEmpty()) {
            System.out.println("\tOrdenação Topológica:");
            System.out.print("\t");
            int tamanho = ordenacao.size();
            for (int i = 0; i < tamanho; i++) {
                System.out.print(ordenacao.get(i));
                if (i < tamanho - 1) { // Verifica se não é o último vértice
                    System.out.print(" -> ");
                }
            }
            System.out.println();
        }
    }

    public char escolherVerticeInicialLista() {
        boolean[] grauEntradaZero = new boolean[graphL.size()]; // Array para marcar vértices com grau de entrada zero

        // Percorrer os nós para marcar aqueles com grau de entrada zero
        for (No no : graphL) {
            for (int i = 0; i < no.qnt_aresta(); i++) {
                char adjacentId = no.getAresta(i);
                grauEntradaZero[adjacentId - 'A'] = true;
            }
        }

        // Encontrar o primeiro vértice com grau de entrada zero
        for (int i = 0; i < grauEntradaZero.length; i++) {
            if (!grauEntradaZero[i]) {
                return (char) ('A' + i); // Retorna o vértice com grau de entrada zero
            }
        }

        return ' '; // Retorna um espaço em branco se não houver vértice com grau de entrada zero
    }
}