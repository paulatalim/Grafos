package graph.busca;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import graph.representacao.lista.No;

// Classe para realizar a busca em largura
public class BreadthFirstSearch {
    private List<No> graphL;
    private int[][] graphM;
    private ArrayList<Character> vertices;

    // int vertices;
    // LinkedList<Integer>[] adjList;

    public BreadthFirstSearch(List<No> grafo) {
        graphL = grafo;
    }

    public BreadthFirstSearch(int[][] grafo, ArrayList<Character> vertices) {
        graphM = grafo;
        this.vertices = vertices;
    }

    private ArrayList<Character> bfsMatriz(char verticeInicial) {
        // Create a queue for BFS
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[vertices.size()];
        ArrayList<Character> tree = new ArrayList<>();
        boolean allNodeVisited = false;

        // Mark the current node as visited and enqueue it
        visited[vertices.indexOf(verticeInicial)] = true;
        queue.add(vertices.indexOf(verticeInicial));

        while(allNodeVisited) {

            // Iterate over the queue
            while (!queue.isEmpty()) {
                // Dequeue a vertex from queue and print it
                int currentNode = queue.poll();
                tree.add(vertices.get(currentNode));
                //System.out.print(currentNode + " ");
                
                // Get all adjacent vertices of the dequeued
                // vertex currentNode If an adjacent has not
                // been visited, then mark it visited and
                // enqueue 
                
                for (int i = 0; i < vertices.size(); i++) {
                    if(graphM[currentNode][i] != 0 || graphM[i][currentNode] != 0) {
                        if(!visited[i]) {
                            visited[i] = true;
                            queue.add(i);
                        }
                    }
                }
            }

            // Verifica se todos os vertices foram visitados
            allNodeVisited = true;
            for(int i = 0; i < visited.length; i ++) {
                if(!visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                    allNodeVisited = false;
                    tree.add('-');
                }
            }
        }


        return tree;
    }

    private ArrayList<Character> bfsList(char verticeInicial) {
        // Create a queue for BFS
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[graphL.size()];
        boolean allNodeVisited = false;
        ArrayList<Character> tree = new ArrayList<>();

        // Mark the current node as visited and enqueue 
        for(int i = 0; i < graphL.size(); i++) {
            if(graphL.get(i).getId() == verticeInicial) {
                visited[i] = true;
                queue.add(i);
                break;
            }
        }

        while (!allNodeVisited) {
            
            // Iterate over the queue
            while (!queue.isEmpty()) {
                // Dequeue a vertex from queue and print it
                int currentNode = queue.poll();
                tree.add(graphL.get(currentNode).getId());
                
                // Verifica se os vizinhos de cada vertice ja foram visitados
                // Se nao foram, sao marcados como visitas e eh adiconado a fila
                for(int i = 0; i < graphL.get(currentNode).qnt_aresta(); i++) {
                    for(int j = 0; j < graphL.size(); j++) {
                        if(graphL.get(j).getId() == graphL.get(currentNode).getAresta(i)) {
                            if (!visited[j]) {
                                visited[j] = true;
                                queue.add(j);
                                break;
                            }
                        }
                    }
                }
            }

            // Verifica se ha vertice nao visitado
            allNodeVisited = true;
            for(int i = 0; i < visited.length; i++) {
                if(!visited[i]) {
                    allNodeVisited = false;
                    queue.add(i);
                    visited[i] = true;
                    tree.add('-');
                }
            }
        }

        return tree;
    }

    // Function to perform Breadth First Search on a graph
    // represented using adjacency list
    public ArrayList<Character> bfs(char startNode) {
        if(graphL == null) {
            return bfsMatriz(startNode);
        }
        return bfsList(startNode);
    }
}
