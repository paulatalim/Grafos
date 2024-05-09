package graph.busca;

import java.util.ArrayList;
import java.util.List;

import graph.representacao.lista.No;

public class ArvoreGeradoraMinima {
    private List<No> graphL;
    private List<No> graphLAGM;
    private int[][] graphM;
    private int[][] graphMAGM;
    private ArrayList<Character> vertices;
    private String[] arestasOrdenadas;

    public ArvoreGeradoraMinima(List<No> graph) {
        graphL = graph;
        graphLAGM = new ArrayList<No>();
        
        int arestasNecessarias = 0;
        for(int i = 1; i < graphL.size(); i++) arestasNecessarias += i;
        arestasOrdenadas = new String[arestasNecessarias];
        for(int i = 0; i < graphL.size(); i++) {
            for(int j = i; j < graphL.size(); j++) {
                if(i != j) {
                    arestasOrdenadas[i] = String.valueOf(graphL.get(i).getId()) + String.valueOf(graphL.get(j).getId());
                }
            }
        }
    }

    public ArvoreGeradoraMinima(int[][] graph, ArrayList<Character> vertices) {
        graphM = graph;
        this.vertices = vertices;
        
        int arestasNecessarias = 0;
        for(int i = 1; i < vertices.size(); i++) arestasNecessarias += i;
        arestasOrdenadas = new String[arestasNecessarias];
        for(int i = 0; i < vertices.size(); i++) {
            for(int j = i; j < vertices.size(); j++) {
                if(i != j) {
                    arestasOrdenadas[i] = String.valueOf(vertices.get(i)) + String.valueOf(vertices.get(j));
                }
            }
        }
        
        int tamanho = vertices.size();
        graphMAGM = new int[tamanho][tamanho];
        for(int i = 0; i < tamanho; i++) {
            for(int j = 0; j < tamanho; j++) {
                graphMAGM[i][j] = 0;
            }
        }
    }

    private void ordenarArestas() {
        if(graphL == null) {
            for (int i = 1; i < arestasOrdenadas.length; ++i) {
                String key = new String(arestasOrdenadas[i]);
                int j = i - 1;

                /* Move elements of arr[0..i-1], that are
                    greater than key, to one position ahead
                    of their current position */
                while (j >= 0 && graphM[arestasOrdenadas[j].charAt(0)][arestasOrdenadas[j].charAt(1)] > graphM[key.charAt(0)][key.charAt(1)]) {
                    arestasOrdenadas[j + 1] = arestasOrdenadas[j];
                    j = j - 1;
                }
                arestasOrdenadas[j + 1] = key;
            }
        }
    }


}
