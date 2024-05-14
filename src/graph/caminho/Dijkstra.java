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
    private ArrayList<Character> vertices;
    private boolean isPonderado;

    public Dijkstra(List<No> grafo) {
        graphL = grafo;
    }

    public Dijkstra(Integer[][] grafo, ArrayList<Character> vertices, boolean isPonderado) {
        graphM = grafo;
        this.vertices = vertices;
        this.isPonderado = isPonderado;
    }

    private boolean validarVerticeLista(char vertice) {
        for(int i = 0; i < graphL.size(); i++) {
            if(graphL.get(i).getId() == vertice) {
                return true;
            }
        }

        return false;
    }

    private boolean validarVerticeMatriz(char vertice) {
        for(int i = 0; i < vertices.size(); i++) {
            if(vertices.get(i) == vertice) {
                return true;
            }
        }

        return false;
    }

    public Integer calcularCaminhoMinimoMatriz(char raiz, char saida) {
        Integer[] distance = new Integer[vertices.size()];
        Character[] predecessores = new Character[vertices.size()];
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
            if(vertices.get(i) == raiz) {
                distance[i] = 0;
                break;
            } 
        }

        
        for(int i = 0; i < vertices.size(); i++) {
            if(explorado[i] == false &&  distance[i] != Integer.MAX_VALUE) {
                loop = true;
                break;
            }
        }

        while(loop) {

            // Encontra os vertices explorados
            for(int i = 0; i < vertices.size(); i++) {
                if(!explorado[i]) {
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
                        if(distance[verticeAtual] + graphM[verticeAtual][i] < distance[i]) {
                            distance[i] = distance[verticeAtual] + graphM[verticeAtual][i];
                            predecessores[i] = vertices.get(verticeAtual);
                        }

                        break;
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
            if(vertices.get(i) == saida) {
                return distance[i];
            }
        }

        return null;
    }

    public Integer calcularCaminhoMinimoLista(char raiz, char saida) {
        Integer[] distance = new Integer[graphL.size()];
        Character[] predecessores = new Character[graphL.size()];
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
            if(graphL.get(i).getId() == raiz) {
                distance[i] = 0;
                break;
            } 
        }

        
        for(int i = 0; i < graphL.size(); i++) {
            if(explorado[i] == false &&  distance[i] != Integer.MAX_VALUE) {
                loop = true;
                break;
            }
        }

        while(loop) {

            // Encontra os vertices explorados
            for(int i = 0; i < graphL.size(); i++) {
                if(!explorado[i]) {
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
            if(graphL.get(i).getId() == saida) {
                return distance[i];
            }
        }

        return null;
    }

    public Integer calcularCaminhoMinimo(char vertice1, char vertice2) {
        if(graphL == null) {
            if(!validarVerticeMatriz(vertice1)) return null;
            return calcularCaminhoMinimoMatriz(vertice1, vertice2);
        }

        if(!validarVerticeLista(vertice1)) return null;
        return calcularCaminhoMinimoLista(vertice1, vertice2);
    }
}