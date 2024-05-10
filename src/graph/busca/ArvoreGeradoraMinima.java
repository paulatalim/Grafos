package graph.busca;

import java.util.ArrayList;
import java.util.List;

import graph.representacao.lista.No;
import graph.representacao.matriz.MatrizNaoDirecionada;

public class ArvoreGeradoraMinima extends MatrizNaoDirecionada ListaNaoDirecionada{
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
            for(int j = 0; j < verticeAtual.qnt_aresta(); j++) {
                if(verticeAtual.getId() != verticeAtual.getAresta(j)) {
                    arestas.add(String.valueOf(verticeAtual.getId()) + String.valueOf(verticeAtual.getAresta(j)));
                }
            }
        }
        arestasOrdenadas = arestas.toArray(new String[0]);
    }

    public ArvoreGeradoraMinima(int[][] graph, ArrayList<Character> vertices) {
        graphM = graph;
        this.vertices = vertices;
            
        int tamanho = vertices.size();
        ArrayList<String> arestas = new ArrayList<String>();
        for(int i = 0; i < tamanho; i++) {
            for(int j = i; j < tamanho; j++) {
                if(i != j && graphM[i][j] != 0) {
                    arestas.add(String.valueOf(vertices.get(i)) + String.valueOf(vertices.get(j)));
                }
            }
        }
        arestasOrdenadas = arestas.toArray(new String[0]);
        
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
                int matrizI = arestasOrdenadas[j].charAt(0);
                int matrizJ =  arestasOrdenadas[j].charAt(1);

                /* Move elements of arr[0..i-1], that are
                    greater than key, to one position ahead
                    of their current position */
                while (j >= 0 && graphM[matrizI][matrizJ] > graphM[key.charAt(0)][key.charAt(1)]) {
                    arestasOrdenadas[j + 1] = arestasOrdenadas[j];
                    j = j - 1;
                }
                arestasOrdenadas[j + 1] = key;
            }
        }
        // else {
        //     for (int i = 1; i < arestasOrdenadas.length; i++) {
        //         String key = new String(arestasOrdenadas[i]);
        //         int j = i - 1;
        //         int listaI = 
        //         int listaJ = graphL.get(listaI).indexOf(arestasOrdenadas[j].charAt(1)); 
        //         char verticeJ = graphL.get(listaI).getAresta(listaJ);

        //         /* Move elements of arr[0..i-1], that are
        //             greater than key, to one position ahead
        //             of their current position */
        //         while (j >= 0 && graphL.get(listaI).getPeso(verticeJ) > graphM[key.charAt(0)][key.charAt(1)]) {
        //             arestasOrdenadas[j + 1] = arestasOrdenadas[j];
        //             j = j - 1;
        //         }
        //         arestasOrdenadas[j + 1] = key;
        //     }
        // }
    }

    private ArvoreGeradoraMinima encontrarMatrizAGM() {
        int i = 0;
        int indexI;
        int indexJ;
        ArrayList<String> arestasVisitadas = new ArrayList<String>();
        while(arestasVisitadas.size() < vertices.size()) {
                
                indexI = arestasOrdenadas[i].charAt(0);
                graphMAGM[indexI][indexJ] = graphM[indexI][indexJ];
                arestasVisitadas.add(arestasOrdenadas[i]);
            i += 1;
        }
        return null;
    }

    public ArvoreGeradoraMinima encontrarAGM() {
        if(graphL == null) {
            return encontrarMatrizAGM();    
        }
        return encontrarListaAGM();
    }


}
