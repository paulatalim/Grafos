package graph.busca;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import graph.representacao.lista.No;

/**
 * Classe para realizar a busca em largura
 */ 
public class BreadthFirstSearch {
    private List<No> graphL;
    private Integer[][] graphM;
    private ArrayList<String> vertices;
    private boolean isPonderado;
    private boolean isConexo;

    public BreadthFirstSearch(List<No> grafo) {
        graphL = grafo;
    }

    public BreadthFirstSearch(Integer[][] grafo, ArrayList<String> vertices, boolean isPonderado) {
        graphM = grafo;
        this.vertices = vertices;
        this.isPonderado = isPonderado;
    }

    /**
     * Realiza uma busca em largura na matriz de adjacencia
     * @return ArrayList<Character> - lista da sequencia em q os vertices foram visitados
     */
    private ArrayList<String> bfsMatriz(String verticeInicial) {
        // Create a queue for BFS
        Queue<Integer> queue = new LinkedList<>();
        ArrayList<String> tree = new ArrayList<>();
        boolean allNodeVisited = false;

        int[] L = new int[vertices.size()];
        int[] nivel = new int[vertices.size()];
        String[] pai = new String[vertices.size()];
        int time = 1;

        // Inicializa as variaveis
        for (int i = 0; i < vertices.size(); i++) {
            L[i] = 0;
            nivel[i] = 0;
            pai[i] = "-";
        }

        // Inicializa o primeiro vertice
        for(int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).equals(verticeInicial)) {
                queue.add(i);
                L[i] = time;
                tree.add(vertices.get(i));
                break;
            } else if(i + 1 == vertices.size()) {
                // caso nao encontrar o vertice
                return null;
            }
        }

        while(!allNodeVisited) {
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

            // Verifica se todos os vertices foram visitados
            allNodeVisited = true;
            for(int i = 0; i < vertices.size(); i ++) {
                if(L[i] == 0) {
                    allNodeVisited = false;
                    queue.add(i);
                    time++;
                    L[i] = time;
                    tree.add(vertices.get(i));
                    isConexo = false;
                    break;
                }
            }
            
        }

        return tree;
    }

    /**
     * Realiza uma busca em largura na lista de adjacencia
     * @return ArrayList<String> - lista da sequencia em que os vertices foram visitados
     */
    private ArrayList<String> bfsList(String verticeInicial) {
        // Create a queue for BFS
        Queue<Integer> queue = new LinkedList<>();
        boolean allNodeVisited = false;
        ArrayList<String> tree = new ArrayList<>();

        int[] L = new int[graphL.size()];
        int[] nivel = new int[graphL.size()];
        String[] pai = new String[graphL.size()];
        int time = 1;

        // Inicializa as variaveis
        for (int i = 0; i < graphL.size(); i++) {
            L[i] = 0;
            nivel[i] = 0;
            pai[i] = "-";
        }

        // Inicializa o primeiro vertice
        for(int i = 0; i < graphL.size(); i++) {
            if(graphL.get(i).getId().equals(verticeInicial)) {
                L[i] = time;
                queue.add(i);
                tree.add(verticeInicial);
                break;
            } else if(i + 1 == graphL.size()) {
                // caso nao encontrar o vertice
                return null;
            }
        }

        while (!allNodeVisited) {
            while (!queue.isEmpty()) {
                // Dequeue a vertex from queue and print it
                int currentNode = queue.poll();
                
                // Verifica se os vizinhos de cada vertice ja foram visitados
                // Se nao foram, sao marcados como visitas e eh adiconado a fila
                for(int i = 0; i < graphL.get(currentNode).qnt_aresta(); i++) {
                    for(int neighbor = 0; neighbor < graphL.size(); neighbor++) {
                        if(graphL.get(neighbor).getId().equals(graphL.get(currentNode).getAresta(i))) {

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

            // Verifica se ha vertice nao visitado
            allNodeVisited = true;
            for(int i = 0; i < graphL.size(); i++) {
                if(L[i] == 0) {
                    time ++;
                    L[i] = time;
                    queue.add(i);
                    allNodeVisited = false;
                    tree.add(graphL.get(i).getId());
                    isConexo = false;
                    break;
                }
            }
        }

        return tree;
    }

    /**
     * Realiza uma busca em largura no grafo
     * @param startNode char (vertice que a busca em largura ira iniciar)
     * @return vetor de char (ordem de visitacao dos vertices na busca em largura)
     */
    public ArrayList<String> bfs(String startNode) {
        if(graphL == null) {
            return bfsMatriz(startNode);
        }
        return bfsList(startNode);
    }

    /**
     * Verifica se o grafo eh conexo atraves da busca em largura
     * @return boolean (true, se o grafo ser conexo, ou false, caso contrario)
     */
    public boolean getIsConexo() {
        // Inicializa variavel
        isConexo = true;

        // Verificacao de qual metodo chamar
        if(graphL == null) {
            // Realiza uma busca em largura atraves da matriz de adjacencia
            bfsMatriz(vertices.get(0));
        } else {
            // Realiza uma busca em largura atraves da lista de adjacencia
            bfsList(graphL.get(0).getId());
        }

        return isConexo;
    }
}
