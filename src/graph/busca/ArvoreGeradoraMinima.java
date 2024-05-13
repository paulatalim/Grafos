package graph.busca;

import java.util.ArrayList;
import java.util.List;

import graph.representacao.lista.No;

public class ArvoreGeradoraMinima{
    private List<No> graphL;
    private List<No> graphLAGM;
    private Integer[][] graphM;
    private Integer[][] graphMAGM;
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

    public ArvoreGeradoraMinima(Integer[][] graph, ArrayList<Character> vertices) {
        graphM = graph;
        this.vertices = vertices;
            
        int tamanho = vertices.size();
        ArrayList<String> arestas = new ArrayList<String>();
        for(int i = 0; i < tamanho; i++) {
            for(int j = i; j < tamanho; j++) {
                if(i != j && graphM[i][j] != null) {
                    arestas.add(String.valueOf(i) + String.valueOf(j));
                }
            }
        }
        arestasOrdenadas = arestas.toArray(new String[0]);
        ordenarArestas();
        
        graphMAGM = new Integer[tamanho][tamanho];
        for(int i = 0; i < tamanho; i++) {
            for(int j = 0; j < tamanho; j++) {
                graphMAGM[i][j] = null;
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
                    j--;
                    if(j >= 0) {
                        matrizI = Character.getNumericValue(arestasOrdenadas[j].charAt(0));
                        matrizJ = Character.getNumericValue(arestasOrdenadas[j].charAt(1));
                    }
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
                // char verticeJ = graphL.get(listaI).getAresta(listaJ);

                int keyI = Character.getNumericValue(arestasOrdenadas[j].charAt(0));
                int keyJ = Character.getNumericValue(arestasOrdenadas[j].charAt(1));
                // char keyVerticeJ = graphL.get(keyI).getAresta(keyJ);

                while (j >= 0 && graphL.get(listaI).getPeso(listaJ) > graphL.get(keyI).getPeso(keyJ)) {
                    arestasOrdenadas[j + 1] = arestasOrdenadas[j];
                    j--;
                    if(j >= 0) {
                        listaI = Character.getNumericValue(arestasOrdenadas[j].charAt(0));
                        listaJ = Character.getNumericValue(arestasOrdenadas[j].charAt(1));
                        // verticeJ = graphL.get(listaI).getAresta(listaJ);
                    }
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

        if (graphL == null) {
            for(int i = 0; i < verticesVisitados.length; i++) {
                if(graphMAGM[vertice][i] != null) {
                    if(verticesVisitados[i] == '\u0000') {
                        if(formaCicloUtil(verticesVisitados, i, vertice)) return true;
                    }
                    else if(i != pai) {
                        return true;
                    }
                }
            }
        }
        else {
            for(int i = 0; i < graphLAGM.get(vertice).qnt_aresta(); i++) {
                if(verticesVisitados[i] == '\u0000') {
                    if(formaCicloUtil(verticesVisitados, i, vertice)) return true;
                }
                else if(graphLAGM.get(vertice).getAresta(i) != graphLAGM.get(pai).getId()){
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
                graphMAGM[indexI][indexJ] = null;
                graphMAGM[indexJ][indexI] = null;
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
        int pesoJ;
        char verticeJ;
        ArrayList<String> arestasVisitadas = new ArrayList<String>();
        
        while(arestasVisitadas.size() < graphLAGM.size() - 1) {
            indexI = Character.getNumericValue(arestasOrdenadas[i].charAt(0));
            indexJ = Character.getNumericValue(arestasOrdenadas[i].charAt(1));

            verticeJ = graphL.get(indexI).getAresta(indexJ);
            pesoJ = graphL.get(indexJ).getPeso(verticeJ);
            
            graphLAGM.get(indexI).inserir_aresta(verticeJ, pesoJ);
            
            if(formaCiclo(graphLAGM.size())) {
                graphLAGM.get(indexI).remover_aresta(verticeJ);
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

    public void printGraphMAGM() {
        System.out.print("\t   ");
        for (int i = 0; i < vertices.size(); i++) {
            System.out.print(vertices.get(i) + " ");
        }
        System.out.print("\n");
        for (int i = 0; i < vertices.size(); i++) {
            System.out.print("\t" + vertices.get(i) + "| ");
            for (int j = 0; j < vertices.size(); j++) {
                System.out.print(graphMAGM[i][j] + " ");
            }
            System.out.println("|");
        }
    }

    public void printGraphLAGM() {
        for (int i = 0; i < graphLAGM.size(); i++) {
            No no = graphLAGM.get(i);
            System.out.print("\t" + no.getId() + ": [ ");

            if (no.qnt_aresta() > 0) {
                System.out.print(no.getAresta(0));
            }

            for (int j = 1; j < no.qnt_aresta(); j++) {
                System.out.print(", " + no.getAresta(j));
            }

            System.out.println(" ]");
        }
    }


}
