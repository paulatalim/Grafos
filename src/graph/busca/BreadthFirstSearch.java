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

        // Mark the current node as visited and enqueue it
        // visited[vertices.indexOf(verticeInicial)] = true;
        // queue.add(vertices.indexOf(verticeInicial));

        while(!allNodeVisited) {

            // Verifica se todos os vertices foram visitados
            allNodeVisited = true;
            for(int i = 0; i < visited.length; i ++) {
                if(L[i] == 0) {
                    allNodeVisited = false;
                    queue.add(i);
                    time++;
                    L[i] = time;
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
                    if(graphM[currentNode][neighbor] != 0) {
                        if(L[neighbor] == 0) {
                            // Aresta pai
                            pai[neighbor] = vertices.get(currentNode);
                            nivel[neighbor] = nivel[currentNode] + 1;
                            time++;
                            L[neighbor] = time;
                            queue.add(neighbor);
                            tree.add(vertices.get(currentNode));
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

                        // if(!visited[i]) {
                        //     visited[i] = true;
                        //     queue.add(i);
                        // }
                    }
                }
            }

            
        }

        exibirBusca(L, nivel, pai);

        return tree;
    }

    private ArrayList<Character> bfsList(char verticeInicial) {
        // Create a queue for BFS
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[graphL.size()];
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

        // Mark the current node as visited and enqueue 
        // for(int i = 0; i < graphL.size(); i++) {
        //     if(graphL.get(i).getId() == verticeInicial) {
        //         visited[i] = true;
        //         queue.add(i);
        //         break;
        //     }
        // }

        while (!allNodeVisited) {
            // Verifica se ha vertice nao visitado
            allNodeVisited = true;
            for(int i = 0; i < graphL.size(); i++) {
                if(L[i] == 0) {
                    time ++;
                    L[i] = time;
                    queue.add(i);
                    allNodeVisited = false;
                    break;
                }
            }

            // for(int i = 0; i < visited.length; i++) {
            //     if(!visited[i]) {
            //         allNodeVisited = false;
            //         queue.add(i);
            //         visited[i] = true;
            //         tree.add('-');
            //     }
            // }
            
            // Iterate over the queue
            while (!queue.isEmpty()) {
                // Dequeue a vertex from queue and print it
                int currentNode = queue.poll();
                // tree.add(graphL.get(currentNode).getId());
                
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
                                tree.add(graphL.get(currentNode).getId());
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

                            // if (!visited[j]) {
                            //     visited[j] = true;
                            //     queue.add(j);
                            //     break;
                            // }
                        }
                    }
                }
            }
        }

        
        exibirBusca(L, nivel, pai);

        return tree;
    }

    private void exibirBusca(int[] L, int[] nivel, char[] pai) {
        System.out.print("L ");

        for(int i = 0; i < L.length; i++) {
            System.out.print(L[i] + " ");
        }

        System.out.print("\nnivel ");

        for(int i = 0; i < nivel.length; i++) {
            System.out.print(nivel[i] + " ");
        }

        System.out.print("\npai ");

        for(int i = 0; i < pai.length; i++) {
            System.out.print(pai[i] + " ");
        }
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
