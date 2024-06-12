package graph.ordenacao;

import java.util.ArrayList;
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

    /**
     * Realiza uma ordenacao topologica dos vertices do grafo em matriz de adjacencia
     * @return ArrayList<Character> (lista dos vertices ordenados)
     */
    private ArrayList<Character> otMatriz() {
        // Verifica se ha ciclo
        if (hasCycle()) {
            System.out.println("\tO grafo contém um ciclo. Não é possível realizar uma ordenação topológica.");
            return new ArrayList<>(); // Se houver um ciclo, retornamos uma lista vazia
        }

        // Inicializa variaveis
        boolean[] visited = new boolean[graphM.length];
        ArrayList<Character> ordenacao = new ArrayList<>();
        boolean allVerticesVisitados = false;

        // Ordena os vertices
        while(!allVerticesVisitados) {
            // Realiza uma busca em profundidade
            for (int i = 0; i < graphM.length; i++) {
                if (!visited[i]) {
                    dfs(graphM, i, visited, ordenacao);
                }
            }

            // Verifica se todos os vertices foram visitados
            allVerticesVisitados = true;
            for(int i = 0; i < vertices.size(); i++) {
                if(!visited[i]) {
                    allVerticesVisitados = false;
                    break;
                }
            }
        }

        // Inverte a ordenacao
        Collections.reverse(ordenacao);

        return ordenacao;
    }

    /**
     * Verifica se ha um ciclo na lista de adjacencia
     * @return true (se haver ciclo) ou false (se nao haver ciclo)
     */
    private boolean hasCycle() {
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

     /**
     * Verifica se ha um ciclo na entre um vertice especificado na lista de ajacencia
     * @param vertice int (index do vertice)
     * @param visitado vetor de boolean (vetor verificando quais vertices foram visitados)
     * @param pilhaRecurso o conjunto de vertices verificado
     * @return true (se haver ciclo) ou false (se nao haver ciclo)
     */
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

    /**
     * Realiza uma busca em profundidade na matriz de adjacencia e ordena os vertices
     * @param grafo vetor de interos (grafo que esta sendo utilizado)
     * @param vertice int (index do vertice)
     * @param visitado vetor de boolean (vetor verificando quais vertices foram visitados)
     * @param ordenacao lista de char (vertor que possui a odenacao)
     */
    private void dfs(Integer[][] grafo, int vertice, boolean[] visitado, ArrayList<Character> ordenacao) {
        visitado[vertice] = true;

        // Verifica todos os sucessores do vertice
        for (int i = 0; i < grafo.length; i++) {
            if ((ponderado && grafo[vertice][i] != null || (!ponderado && grafo[vertice][i] != 0)) && !visitado[i]) {
                dfs(grafo, i, visitado, ordenacao);
            }
        }

        // Adiciona vertice a ordenacao topologica
        ordenacao.add(vertices.get(vertice));
    }

     /**
     * Faz uma ordenacao topologica com os vertices da matriz de adjacencia e a imprime
     */
    public void imprimirOrdenacao() {
        ArrayList<Character> ordenacao = otMatriz();
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

    /**
     * Realiza uma ordenacao topologica dos vertices do grafo em lista de adjacencia
     * @return ArrayList<Character> (lista dos vertices ordenados)
     */
    private ArrayList<Character> otLista() {
        if (hasCycleLista()) {
            System.out.println("\tO grafo contém um ciclo. Não é possível realizar uma ordenação topológica.");
            return new ArrayList<>(); // Se houver um ciclo, retornamos uma lista vazia
        }

        boolean[] visited = new boolean[graphL.size()];
        ArrayList<Character> ordenacao = new ArrayList<>();
        boolean allVerticesVisitados = false;

        // Realiza a ordenacao topologica
        while (!allVerticesVisitados) {
            // Realiza uma busca em profundidadade
            for (int i = 0; i < graphL.size(); i++) {
                if (!visited[i]) {
                    dfs(i, visited, ordenacao);    
                }
            }

            // Verifica se todos os vertice foram visitados
            allVerticesVisitados = true;
            for(int i = 0; i < graphL.size(); i++) {
                if(!visited[i]) {
                    allVerticesVisitados = false;
                    break;
                }
            }
        }

        // Inverte a ordenacao obtida
        Collections.reverse(ordenacao);

        return ordenacao;
    }

    /**
     * Verifica se ha um ciclo na lista de adjacencia
     * @return true (se haver ciclo) ou false (se nao haver ciclo)
     */
    private boolean hasCycleLista() {
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

    /**
     * Verifica se ha um ciclo na entre um vertice especificado na lista de ajacencia
     * @param vertice int (index do vertice)
     * @param visitado vetor de boolean (vetor verificando quais vertices foram visitados)
     * @param pilhaRecurso o conjunto de vertices verificado
     * @return true (se haver ciclo) ou false (se nao haver ciclo)
     */
    private boolean hasCycleUtilLista(int vertice, boolean[] visitado, boolean[] pilhaRecursao) {
        if (!visitado[vertice]) {
            visitado[vertice] = true;
            pilhaRecursao[vertice] = true;

            No currentNo = graphL.get(vertice);
            for (int i = 0; i < currentNo.qnt_aresta(); i++) {
                for(int j = 0; j < graphL.size(); j++) {
                    if(currentNo.getAresta(i) == graphL.get(j).getId()) {
                        // char adjacentId = currentNo.getAresta(i);
                        // int adjacentIndex = adjacentId - 'A';
                        if (!visitado[j] && hasCycleUtilLista(j, visitado, pilhaRecursao)) {
                            return true;
                        } else if (pilhaRecursao[j]) {
                            return true;
                        }

                        break;
                    }
                }
            }
        }
        pilhaRecursao[vertice] = false; // Remove o vértice da pilha de recursão antes de retornar
        return false;
    }

    /**
     * Realiza uma busca em profundidade na lista de adjacencia e ordena os vertices
     * @param vertice int (index do vertice)
     * @param visitado vetor de boolean (vetor verificando quais vertices foram visitados)
     * @param ordenacao lista de char (vertor que possui a odenacao)
     */
    private void dfs(int vertice, boolean[] visitado, ArrayList<Character> ordenacao) {
        visitado[vertice] = true;

        No currentNo = graphL.get(vertice);
        for (int i = 0; i < currentNo.qnt_aresta(); i++) {
            for(int j = 0; j < graphL.size(); j++) {
                if(graphL.get(j).getId() == currentNo.getAresta(i)) {
                    // char adjacentId = currentNo.getAresta(i);
                    // int adjacentIndex = adjacentId - 'A';
                    if (!visitado[j]) {
                        dfs(j, visitado, ordenacao);
                    }

                    break;
                }
            }
        }

        ordenacao.add(graphL.get(vertice).getId());
    }

    /**
     * Faz uma ordenacao topologica com os vertices da lista de adjacencia e a imprime
     */
    public void imprimirOrdenacaoLista() {
        ArrayList<Character> ordenacao = otLista();
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
}