package graph.caminho;

import java.util.ArrayList;
import java.util.List;

import graph.representacao.lista.No;

/*
 * Calculo de caminho minimo entre dois vertices atraves do metodo de Dijkstra
 */
public class Dijkstra {
    private List<No> graphL;
    private Integer[][] graphM;
    private ArrayList<String> vertices;
    private boolean isPonderado;

    public Dijkstra(List<No> grafo) {
        graphL = grafo;
    }

    public Dijkstra(Integer[][] grafo, ArrayList<String> vertices, boolean isPonderado) {
        graphM = grafo;
        this.vertices = vertices;
        this.isPonderado = isPonderado;
    }

    /**
     * Verifica se o grafo possui peso negativo
     * @return true (se haver peso negativo) ou false (se nao haver peso negativo)
     */
    private boolean containPesoNegativo() {
        if(graphL == null) {
            if(isPonderado) {
                for(int i = 0; i < vertices.size(); i++) {
                    for(int j = 0; j <vertices.size(); j++) {
                        if(graphM[i][j] != null) {
                            if(graphM[i][j] < 0) {
                                return true;
                            }
                        }
                    }
                }
            }
        } else {
            for(int i = 0; i < graphL.size(); i++) {
                for(int j = 0; j < graphL.get(i).qnt_aresta(); j++) {
                    if(graphL.get(i).getPeso(j) < 0) {
                        return true;
                    }
                    
                }
            }
        }

        return false;
    }

    /**
     * Verifica se um vertice existe na lista de adjacencia
     * @param vertice - char (vertice a ser verificado)
     * @return true (se o vertice estiver no grafo) ou false (se o vertice ser invalido)
     */
    private boolean validarVerticeLista(String vertice) {
        for(int i = 0; i < graphL.size(); i++) {
            if(graphL.get(i).getId().equals(vertice)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Verifica se um vertice existe na matriz de adjacencia
     * @param vertice - char (vertice a ser verificado)
     * @return true (se o vertice estiver no grafo) ou false (se o vertice ser invalido)
     */
    private boolean validarVerticeMatriz(String vertice) {
        for(int i = 0; i < vertices.size(); i++) {
            if(vertices.get(i).equals(vertice)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Calcula o caminho minimo entre a raiz e a saida na matriz de adjaencia
     * @param raiz vertice em que o algoritimo ira iniciar
     * @param saida vertice em que sera calculado o caminho
     * @return Integer (tamanho do caminho minimo da raiz ate a saida), ou null (caso ocorra um erro) ou Integer.MAX_VALUE (caso nao exista caminho entre os vertices)
     */
    private Integer calcularCaminhoMinimoMatriz(String raiz, String saida) {
        Integer[] distance = new Integer[vertices.size()];
        String[] predecessores = new String[vertices.size()];
        boolean[] explorado = new boolean[vertices.size()];
        int verticeAtual = 0;
        boolean loop = false;

        // Inicializa as variaveis de controle
        for(int i = 0; i < vertices.size(); i ++) {
            distance[i] = Integer.MAX_VALUE;
            predecessores[i] = null;
            explorado[i] = false;
        }

        // Inicializa raiz
        for(int i = 0; i < vertices.size(); i++) {
            if(vertices.get(i).equals(raiz)) {
                distance[i] = 0;
                loop = true;
                break;
            } 
        }

        while(loop) {

            // Encontra os vertices explorados
            for(int i = 0; i < vertices.size(); i++) {
                if(!explorado[i] && distance[i] != Integer.MAX_VALUE) {
                    verticeAtual = i;

                    for(int j = 0; j < vertices.size(); j ++) {
                        if(j != i && !explorado[j] && distance[j] < distance[verticeAtual]) {
                            verticeAtual = j;
                        }
                    }

                    break;
                }
            }
        
            explorado[verticeAtual] = true;

            for(int i = 0; i < vertices.size(); i++) {
                if((isPonderado && graphM[verticeAtual][i] != null) || (!isPonderado && graphM[verticeAtual][i] != 0)) {
                    if(!explorado[i]) {
                        if((isPonderado && distance[verticeAtual] + graphM[verticeAtual][i] < distance[i]) || (!isPonderado && distance[verticeAtual] + 1 < distance[i])) {
                            if(isPonderado) {
                                distance[i] = distance[verticeAtual] + graphM[verticeAtual][i];
                            } else {
                                distance[i] = distance[verticeAtual] + 1;
                            }

                            predecessores[i] = vertices.get(verticeAtual);
                        }
                    }
                }
            }

            loop = false;

            for(int i = 0; i < vertices.size(); i++) {
                if(explorado[i] == false &&  distance[i] != Integer.MAX_VALUE) {
                    loop = true;
                    break;
                }
            }
        
        }

        // Procura o caracter que deseja encontrar a distancia
        for(int i = 0; i < vertices.size(); i++) {
            if(vertices.get(i).equals(saida)) {
                return distance[i];
            }
        }

        return null;
    }

    /**
     * Calcula o caminho minimo entre a raiz e a saida na lista de adjaencia
     * @param raiz vertice em que o algoritimo ira iniciar
     * @param saida vertice em que sera calculado o caminho
     * @return Integer (tamanho do caminho minimo da raiz ate a saida), ou null (caso ocorra um erro) ou Integer.MAX_VALUE (caso nao exista caminho entre os vertices)
     */
    private Integer calcularCaminhoMinimoLista(String raiz, String saida) {
        Integer[] distance = new Integer[graphL.size()];
        String[] predecessores = new String[graphL.size()];
        boolean[] explorado = new boolean[graphL.size()];
        int verticeAtual = 0;
        boolean loop = false;

        // Inicializa as variaveis de controle
        for(int i = 0; i < graphL.size(); i ++) {
            distance[i] = Integer.MAX_VALUE;
            predecessores[i] = null;
            explorado[i] = false;
        }

        // Inicializa raiz
        for(int i = 0; i < graphL.size(); i++) {
            if(graphL.get(i).getId().equals(raiz)) {
                distance[i] = 0; 
                loop = true;
                break;
            } 
        }

        while(loop) {

            // Encontra os vertices explorados
            for(int i = 0; i < graphL.size(); i++) {
                if(!explorado[i] && distance[i] != Integer.MAX_VALUE) {
                    verticeAtual = i;

                    for(int j = 0; j < graphL.size(); j ++) {
                        if(j != i && !explorado[j] && distance[j] < distance[verticeAtual]) {
                            verticeAtual = j;
                        }
                    }

                    break;
                }
            }
        
            explorado[verticeAtual] = true;

            for(int i = 0; i < graphL.get(verticeAtual).qnt_aresta(); i++) {
                for(int j = 0; j < graphL.size(); j++) {
                    if(graphL.get(verticeAtual).getAresta(i) == graphL.get(j).getId()) {
                        if(!explorado[j]) {
                            if(distance[verticeAtual] + graphL.get(verticeAtual).getPeso(i) < distance[j]) {
                                distance[j] = distance[verticeAtual] + graphL.get(verticeAtual).getPeso(i);
                                predecessores[j] = graphL.get(verticeAtual).getId();
                            }

                            break;
                        }
                    }
                }
            } 

            loop = false;

            for(int i = 0; i < graphL.size(); i++) {
                if(explorado[i] == false &&  distance[i] != Integer.MAX_VALUE) {
                    loop = true;
                    break;
                }
            }
        
        }

        // Procura o caracter que deseja encontrar a distancia
        for(int i = 0; i < graphL.size(); i++) {
            if(graphL.get(i).getId().equals(saida)) {
                return distance[i];
            }
        }

        return null;
    }

    /**
     * Calcula o caminho minimo entre a os vertices
     * @param vertice1 vertice a ser analisado
     * @param vertice2 vertice a ser analisado
     * @return Integer (tamanho do caminho minimo da raiz ate a saida), ou null (caso ocorra um erro) ou Integer.MAX_VALUE (caso nao exista caminho entre os vertices)
     */
    public Integer calcularCaminhoMinimo(String vertice1, String vertice2) {
        // Verifica se ha peso negativo no grafo
        if(containPesoNegativo()) return null;

        // vaerifica o caminho minimo com a matriz
        if(graphL == null) {
            if(!validarVerticeMatriz(vertice1)) return null;
            return calcularCaminhoMinimoMatriz(vertice1, vertice2);
        }

        // vaerifica o caminho minimo com a lista
        if(!validarVerticeLista(vertice1)) return null;
        return calcularCaminhoMinimoLista(vertice1, vertice2);
    }
}