package graph.busca;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import graph.representacao.lista.No;

// Classe para realizar a busca em largura
public class BreadthFirstSearch {
    private List<No> graphL;
    private Integer[][] graphM;
    private ArrayList<Character> vertices;
    private boolean isPonderado;

    public BreadthFirstSearch(List<No> grafo) {
        graphL = grafo;
    }

    public BreadthFirstSearch(Integer[][] grafo, ArrayList<Character> vertices, boolean isPonderado) {
        graphM = grafo;
        this.vertices = vertices;
        this.isPonderado = isPonderado;
    }

    private ArrayList<Character> bfsMatriz(char verticeInicial) {
        // Create a queue for BFS
        Queue<Integer> queue = new LinkedList<>();
        ArrayList<Character> tree = new ArrayList<>();
        boolean allNodeVisited = false;

        int[] L = new int[vertices.size()];
        int[] nivel = new int[vertices.size()];
        char[] pai = new char[vertices.size()];
        int time = 0;

        // Inicializa as variaveis
        for (int i = 0; i < vertices.size(); i++) {
            L[i] = 0;
            nivel[i] = 0;
            pai[i] = '-';
        }

        while(!allNodeVisited) {

            // Verifica se todos os vertices foram visitados
            allNodeVisited = true;
            for(int i = 0; i < vertices.size(); i ++) {
                if(L[i] == 0) {
                    allNodeVisited = false;
                    queue.add(i);
                    time++;
                    L[i] = time;
                    tree.add(vertices.get(i));
                    break;
                }
            }

            // Realiza a busca em largura
            while (!queue.isEmpty()) {
                // Dequeue a vertex from queue and print it
                int currentNode = queue.poll();

                // Anlise dos visinhos
                for (int neighbor = 0; neighbor < vertices.size(); neighbor++) {
                    // Busca do vertice vizinho
                    if((!isPonderado && graphM[currentNode][neighbor] != 0) ||
                        (isPonderado && graphM[currentNode][neighbor] != null)) {
                        if(L[neighbor] == 0) {
                            // Aresta pai
                            pai[neighbor] = vertices.get(currentNode);
                            nivel[neighbor] = nivel[currentNode] + 1;
                            time++;
                            L[neighbor] = time;
                            queue.add(neighbor);
                            tree.add(vertices.get(neighbor));
                        }
                        // else if(nivel[neighbor] == nivel[currentNode] + 1) {
                        //     // Aresta tio
                        // } else if(
                        //         nivel[neighbor] == nivel[currentNode] && 
                        //         pai[neighbor] == pai[currentNode] && 
                        //         L[neighbor] > L[currentNode]) 
                        //     {
                        //     // Aresta irmao
                        // } else if(
                        //         nivel[neighbor] == nivel[currentNode] && 
                        //         pai[neighbor] != pai[currentNode] && 
                        //         L[neighbor] > L[currentNode]) 
                        //     {
                        //     // Aresta primo
                        // }
                    }
                }
            }

            
        }

        return tree;
    }

    private ArrayList<Character> bfsList(char verticeInicial) {
        // Create a queue for BFS
        Queue<Integer> queue = new LinkedList<>();
        boolean allNodeVisited = false;
        ArrayList<Character> tree = new ArrayList<>();

        int[] L = new int[graphL.size()];
        int[] nivel = new int[graphL.size()];
        char[] pai = new char[graphL.size()];
        int time = 0;

        // Inicializa as variaveis
        for (int i = 0; i < graphL.size(); i++) {
            L[i] = 0;
            nivel[i] = 0;
            pai[i] = '-';
        }

        while (!allNodeVisited) {
            // Verifica se ha vertice nao visitado
            allNodeVisited = true;
            for(int i = 0; i < graphL.size(); i++) {
                if(L[i] == 0) {
                    time ++;
                    L[i] = time;
                    queue.add(i);
                    allNodeVisited = false;
                    tree.add(graphL.get(i).getId());
                    break;
                }
            }
            
            // Iterate over the queue
            while (!queue.isEmpty()) {
                // Dequeue a vertex from queue and print it
                int currentNode = queue.poll();
                
                // Verifica se os vizinhos de cada vertice ja foram visitados
                // Se nao foram, sao marcados como visitas e eh adiconado a fila
                for(int i = 0; i < graphL.get(currentNode).qnt_aresta(); i++) {
                    for(int neighbor = 0; neighbor < graphL.size(); neighbor++) {
                        if(graphL.get(neighbor).getId() == graphL.get(currentNode).getAresta(i)) {

                            if(L[neighbor] == 0) {
                                // Aresta pai
                                pai[neighbor] = graphL.get(currentNode).getId();
                                nivel[neighbor] = nivel[currentNode] + 1;
                                time++;
                                L[neighbor] = time;
                                queue.add(neighbor);
                                tree.add(graphL.get(neighbor).getId());
                            }
                            // else if(nivel[neighbor] == nivel[currentNode] + 1) {
                            //     // Aresta tio
                            // } else if(
                            //         nivel[neighbor] == nivel[currentNode] && 
                            //         pai[neighbor] == pai[currentNode] && 
                            //         L[neighbor] > L[currentNode]) 
                            //     {
                            //     // Aresta irmao
                            // } else if(
                            //         nivel[neighbor] == nivel[currentNode] && 
                            //         pai[neighbor] != pai[currentNode] && 
                            //         L[neighbor] > L[currentNode]) 
                            //     {
                            //     // Aresta primo
                            // }

                            break;
                        }
                    }
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
