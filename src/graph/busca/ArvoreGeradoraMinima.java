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
            char verticeAtualId = verticeAtual.getId();
            No verticeNovo = new No(verticeAtualId);
            graphLAGM.add(verticeNovo);
            for(int j = 0; j < verticeAtual.qnt_aresta(); j++) {
                String aresta = Character.toString(verticeAtualId) + Character.toString(verticeAtual.getAresta(j));
                if(verticeAtualId != verticeAtual.getAresta(j) && !contemAresta(arestas, aresta)) {
                    arestas.add(String.valueOf(i) + "," + String.valueOf(j));
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
                    arestas.add(String.valueOf(i) + "," + String.valueOf(j));
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

    private boolean contemAresta(ArrayList<String> arestas, String aresta) {
        String arestaInvertida = aresta.substring(1, 2) + aresta.substring(0, 1);
        for (int i = 0; i < arestas.size(); i++) {
            String[] arestaIDividida = arestas.get(i).split(",");
            int arestaAtualI = Integer.parseInt(arestaIDividida[0]);
            int arestaAtualJ = Integer.parseInt(arestaIDividida[1]);
            String arestaAtual = Character.toString(graphL.get(arestaAtualI).getId()) + Character.toString(graphL.get(arestaAtualI).getAresta(arestaAtualJ));
            if(arestaInvertida.equals(arestaAtual)) return true;
        }
        return false;
    }

    private void ordenarArestas() {
        if(graphL == null) {
            for (int i = 1; i < arestasOrdenadas.length; i++) {
                String key = new String(arestasOrdenadas[i]);
                int j = i - 1;

                String[] arestaJDividida = arestasOrdenadas[j].split(",");
                int matrizI = Integer.parseInt(arestaJDividida[0]);
                int matrizJ = Integer.parseInt(arestaJDividida[1]);

                String[] keyDividida = key.split(",");
                int keyI = Integer.parseInt(keyDividida[0]);
                int keyJ = Integer.parseInt(keyDividida[1]);

                while (j >= 0 && graphM[matrizI][matrizJ] > graphM[keyI][keyJ]) {
                    arestasOrdenadas[j + 1] = arestasOrdenadas[j];
                    j--;
                    if(j >= 0) {
                        arestaJDividida = arestasOrdenadas[j].split(",");
                        matrizI = Integer.parseInt(arestaJDividida[0]);
                        matrizJ = Integer.parseInt(arestaJDividida[1]);
                    }
                }
                arestasOrdenadas[j + 1] = key;
            }
        }
        else {
            for (int i = 1; i < arestasOrdenadas.length; i++) {
                String key = new String(arestasOrdenadas[i]);
                int j = i - 1;

                String[] arestaJDividida = arestasOrdenadas[j].split(",");
                int listaI = Integer.parseInt(arestaJDividida[0]);
                int listaJ = Integer.parseInt(arestaJDividida[1]);

                String[] keyDividida = key.split(",");
                int keyI = Integer.parseInt(keyDividida[0]);
                int keyJ = Integer.parseInt(keyDividida[1]);

                while (j >= 0 && graphL.get(listaI).getPeso(listaJ) > graphL.get(keyI).getPeso(keyJ)) {
                    arestasOrdenadas[j + 1] = arestasOrdenadas[j];
                    j--;
                    if(j >= 0) {
                        arestaJDividida = arestasOrdenadas[j].split(",");
                        listaI = Integer.parseInt(arestaJDividida[0]);
                        listaJ = Integer.parseInt(arestaJDividida[1]);
                    }
                }
                arestasOrdenadas[j + 1] = key;
            }
        }
    }

    private boolean formaCiclo(int numAtualVertices)
    {
        String[] verticesVisitados = new String[numAtualVertices];

        for (int i = 0; i < verticesVisitados.length; i++) {
            if (verticesVisitados[i] == null)
                if (formaCicloUtil(verticesVisitados, i, -1))
                    return true;
        }

        return false;
    }

    private boolean formaCicloUtil(String[] verticesVisitados, int vertice, int pai) {
        verticesVisitados[vertice] = String.valueOf(vertice); 

        if (graphL == null) {
            for(int i = 0; i < verticesVisitados.length; i++) {
                if(graphMAGM[vertice][i] != null) {
                    if(verticesVisitados[i] == null) {
                        if(formaCicloUtil(verticesVisitados, i, vertice)) return true;
                    }
                    else if(i != pai) {
                        return true;
                    }
                }
            }
        }
        else {
            No verticeAtual = graphLAGM.get(vertice);
            for(int i = 0; i < verticeAtual.qnt_aresta(); i++) {
                char verticeAdjacente = verticeAtual.getAresta(i);
                int indexRealAdjacente = indiceListaPrincipal(verticeAdjacente, graphLAGM);
                if(verticesVisitados[indexRealAdjacente] == null) {
                    if(formaCicloUtil(verticesVisitados, indexRealAdjacente, vertice)) return true;
                }
                else if(indexRealAdjacente != pai){
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
            String[] arestaIDividida = arestasOrdenadas[i].split(",");
            indexI = Integer.parseInt(arestaIDividida[0]);
            indexJ = Integer.parseInt(arestaIDividida[1]);

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
        int indexNo1Real;
        int indexNo2Real;
        int indexNo2Falso;
        int peso;
        char no1;
        char no2;
        ArrayList<String> arestasVisitadas = new ArrayList<String>();
        
        while(arestasVisitadas.size() < graphLAGM.size() - 1) {
            String[] arestaIDividida = arestasOrdenadas[i].split(",");
            indexNo1Real = Integer.parseInt(arestaIDividida[0]);
            indexNo2Falso = Integer.parseInt(arestaIDividida[1]);

            no1 = graphL.get(indexNo1Real).getId();
            no2 = graphL.get(indexNo1Real).getAresta(indexNo2Falso);
            peso = graphL.get(indexNo1Real).getPeso(indexNo2Falso);

            indexNo2Real = indiceListaPrincipal(no2, graphL);
            
            graphLAGM.get(indexNo1Real).inserir_aresta(no2, peso);
            graphLAGM.get(indexNo2Real).inserir_aresta(no1, peso);
            
            if(formaCiclo(graphLAGM.size())) {
                graphLAGM.get(indexNo1Real).remover_aresta(no2);
                graphLAGM.get(indexNo2Real).remover_aresta(no1);
            }
            else {
                arestasVisitadas.add(arestasOrdenadas[i]);
            }
            i += 1;
        }
    }

    private int indiceListaPrincipal(char vertice, List<No> grafo) {
        for (int i = 0; i < grafo.size(); i++) {
            if(grafo.get(i).getId() == vertice) return i;
        }
        return -1;
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
                if(graphMAGM[i][j] != null) {
                    System.out.print(graphMAGM[i][j] + " ");
                } else {
                    System.out.print("- ");
                }
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
