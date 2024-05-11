package graph.busca;

import java.util.ArrayList;
import java.util.List;

import graph.representacao.lista.No;
import graph.representacao.matriz.MatrizNaoDirecionada;

public class ArvoreGeradoraMinima{
    private List<No> graphL;
    private List<No> graphLAGM;
    private int[][] graphM;
    private int[][] graphMAGM;
    private ArrayList<Character> vertices;
    private String[] arestasOrdenadas;

    public ArvoreGeradoraMinima(List<No> graph) {
        graphL = graph;
        graphLAGM = new ArrayList<No>();
        
        ArrayList<String> arestas = new ArrayList<String>();
        for(int i = 0; i < graphL.size(); i++) {
            No verticeAtual = graphL.get(i);
            graphLAGM.add(verticeAtual);
            for(int j = 0; j < verticeAtual.qnt_aresta(); j++) {
                if(verticeAtual.getId() != verticeAtual.getAresta(j)) {
                    arestas.add(String.valueOf(i) + String.valueOf(j));
                }
            }
        }
        arestasOrdenadas = arestas.toArray(new String[0]);
        ordenarArestas();

        
    }

    public ArvoreGeradoraMinima(int[][] graph, ArrayList<Character> vertices) {
        graphM = graph;
        this.vertices = vertices;
            
        int tamanho = vertices.size();
        ArrayList<String> arestas = new ArrayList<String>();
        for(int i = 0; i < tamanho; i++) {
            for(int j = i; j < tamanho; j++) {
                if(i != j && graphM[i][j] != 0) {
                    arestas.add(String.valueOf(i) + String.valueOf(j));
                }
            }
        }
        arestasOrdenadas = arestas.toArray(new String[0]);
        ordenarArestas();
        
        graphMAGM = new int[tamanho][tamanho];
        for(int i = 0; i < tamanho; i++) {
            for(int j = 0; j < tamanho; j++) {
                graphMAGM[i][j] = 0;
            }
        }
    }

    private void ordenarArestas() {
        if(graphL == null) {
            for (int i = 1; i < arestasOrdenadas.length; i++) {
                String key = new String(arestasOrdenadas[i]);
                int j = i - 1;

                int matrizI = Character.getNumericValue(arestasOrdenadas[j].charAt(0));
                int matrizJ = Character.getNumericValue(arestasOrdenadas[j].charAt(1));

                int keyI = Character.getNumericValue(key.charAt(0));
                int keyJ = Character.getNumericValue(key.charAt(1));

                while (j >= 0 && graphM[matrizI][matrizJ] > graphM[keyI][keyJ]) {
                    arestasOrdenadas[j + 1] = arestasOrdenadas[j];
                    j = j - 1;
                }
                arestasOrdenadas[j + 1] = key;
            }
        }
        else {
            for (int i = 1; i < arestasOrdenadas.length; i++) {
                String key = new String(arestasOrdenadas[i]);
                int j = i - 1;

                int listaI = Character.getNumericValue(arestasOrdenadas[j].charAt(0));
                int listaJ = Character.getNumericValue(arestasOrdenadas[j].charAt(1));
                char verticeJ = graphL.get(listaI).getAresta(listaJ);

                int keyI = Character.getNumericValue(arestasOrdenadas[j].charAt(0));
                int keyJ = Character.getNumericValue(arestasOrdenadas[j].charAt(1));
                char keyVerticeJ = graphL.get(keyI).getAresta(keyJ);

                while (j >= 0 && graphL.get(listaI).getPeso(verticeJ) > graphL.get(keyI).getPeso(keyVerticeJ)) {
                    arestasOrdenadas[j + 1] = arestasOrdenadas[j];
                    j = j - 1;
                }
                arestasOrdenadas[j + 1] = key;
            }
        }
    }

    private boolean formaCiclo(int numAtualVertices)
    {
        char[] verticesVisitados = new char[numAtualVertices];

        for (int i = 0; i < verticesVisitados.length; i++) {
            if (verticesVisitados[i] == '\u0000')
                if (formaCicloUtil(verticesVisitados, i, -1))
                    return true;
        }

        return false;
    }

    private boolean formaCicloUtil(char[] verticesVisitados, int vertice, int pai) {
        verticesVisitados[vertice] = (char) vertice; 

        for(int i = 0; i < verticesVisitados.length; i++) {
            if(graphL == null) {
                if(graphMAGM[vertice][i] != 0) {
                    if(verticesVisitados[i] == '\u0000') {
                        if(formaCicloUtil(verticesVisitados, i, vertice)) return true;
                    }
                    else if(i != pai) {
                        return true;
                    }
                }
            }
            else {
                if(verticesVisitados[i] == '\u0000') {
                    if(formaCicloUtil(verticesVisitados, i, vertice)) return true;
                }
                else if(i != pai) {
                    return true;
                }
            }
        }
        return false;
    }

    private void encontrarMatrizAGM() {
        int i = 0;
        int indexI;
        int indexJ;
        ArrayList<String> arestasVisitadas = new ArrayList<String>();

        while(arestasVisitadas.size() < vertices.size() - 1) {
            indexI = Character.getNumericValue(arestasOrdenadas[i].charAt(0));
            indexJ = Character.getNumericValue(arestasOrdenadas[i].charAt(1));

            graphMAGM[indexI][indexJ] = graphM[indexI][indexJ];
            graphMAGM[indexJ][indexI] = graphM[indexJ][indexI];

            if(formaCiclo(vertices.size())) {
                graphMAGM[indexI][indexJ] = 0;
                graphMAGM[indexJ][indexI] = 0;
            }
            else {
                arestasVisitadas.add(arestasOrdenadas[i]);
            }
            i += 1;
        }
    }

    private void encontrarListaAGM() {
        int i = 0;
        int indexI;
        int indexJ;
        int pesoI;
        int pesoJ;
        char verticeI;
        char verticeJ;
        ArrayList<String> arestasVisitadas = new ArrayList<String>();
        
        while(arestasVisitadas.size() < vertices.size() - 1) {
            indexI = Character.getNumericValue(arestasOrdenadas[i].charAt(0));
            indexJ = Character.getNumericValue(arestasOrdenadas[i].charAt(1));
            verticeI = graphL.get(indexI).getAresta(indexJ);
            verticeJ = graphL.get(indexI).getAresta(indexJ);
            pesoI = graphL.get(indexI).getPeso(verticeJ);
            pesoJ = graphL.get(indexJ).getPeso(verticeI);
            
            graphLAGM.get(indexI).inserir_aresta(verticeJ, pesoI);
            graphLAGM.get(indexJ).inserir_aresta(verticeI, pesoJ);
            
            if(formaCiclo(graphLAGM.size())) {
                graphLAGM.get(indexI).remover_aresta(verticeJ);
                graphLAGM.get(indexJ).remover_aresta(verticeI);
            }
            else {
                arestasVisitadas.add(arestasOrdenadas[i]);
            }
            i += 1;
        }
    }

    public void encontrarAGM() {
        if(graphL == null) {
            encontrarMatrizAGM();  
        }
        else {
            encontrarListaAGM();
        }
    }

    public int[][] getGraphMAGM() {
        return graphMAGM;
    }

    public List<No> getGraphLAGM() {
        return graphLAGM;
    }


}
