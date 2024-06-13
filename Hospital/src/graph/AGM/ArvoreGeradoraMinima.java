// package graph.AGM;

// import java.util.ArrayList;
// import java.util.List;

// import graph.representacao.lista.No;

// public class ArvoreGeradoraMinima{
//     private List<No> graphL;
//     private List<No> graphLAGM;
//     private Integer[][] graphM;
//     private Integer[][] graphMAGM;
//     private ArrayList<Character> vertices;
//     private String[] arestasOrdenadas;
//     private int custoTotal;

//     public ArvoreGeradoraMinima(List<No> graph) {
//         graphL = graph;
//         graphLAGM = new ArrayList<No>();
//         custoTotal = 0;
        
//         ArrayList<String> arestas = new ArrayList<String>();
//         for(int i = 0; i < graphL.size(); i++) {
//             No verticeAtual = graphL.get(i);
//             // Inicializa 'graphLAGM' com os vértices existentes.
//             char verticeAtualId = verticeAtual.getId();
//             No verticeNovo = new No(verticeAtualId);
//             graphLAGM.add(verticeNovo);
//             // Ignora laços e adiciona as arestas presentes no grafo sem repetições.
//             for(int j = 0; j < verticeAtual.qnt_aresta(); j++) {
//                 String aresta = Character.toString(verticeAtualId) + Character.toString(verticeAtual.getAresta(j));
//                 if(verticeAtualId != verticeAtual.getAresta(j) && !contemAresta(arestas, aresta)) {
//                     arestas.add(String.valueOf(i) + "," + String.valueOf(j));
//                 }
//             }
//         }
//         // Passa as arestas obtidas de um "ArrayList<String>" para um "String[]" e as ordena com base no peso. 
//         arestasOrdenadas = arestas.toArray(new String[0]); 
//         ordenarArestas();
//     }

//     public ArvoreGeradoraMinima(Integer[][] graph, ArrayList<Character> vertices) {
//         graphM = graph;
//         this.vertices = vertices;
//         custoTotal = 0;
            
//         int tamanho = vertices.size();
//         ArrayList<String> arestas = new ArrayList<String>();
//         // Ignora laços e adiciona as arestas presentes no grafo sem repetições.
//         for(int i = 0; i < tamanho; i++) {
//             for(int j = i; j < tamanho; j++) {
//                 if(i != j && graphM[i][j] != null) {
//                     arestas.add(String.valueOf(i) + "," + String.valueOf(j));
//                 }
//             }
//         }

//         // Passa as arestas obtidas de um "ArrayList<String>" para um "String[]" e as ordena com base no peso.
//         arestasOrdenadas = arestas.toArray(new String[0]);
//         ordenarArestas();
        
//         // Inicializa 'graphMAGM' com os vértices existentes.
//         graphMAGM = new Integer[tamanho][tamanho];
//         for(int i = 0; i < tamanho; i++) {
//             for(int j = 0; j < tamanho; j++) {
//                 graphMAGM[i][j] = null;
//             }
//         }
//     }

//     /**
//      * Método que confere se 'aresta' já existe em 'arestas'.
//      * @param arestas do tipo ArrayList<String> contendo as arestas contabilizadas até o momento.
//      * @param aresta do tipo String que informa qual aresta será analisada.
//      * @return true caso a aresta já tenha sido contabilizada, caso contrário, retorna false.
//      */
//     private boolean contemAresta(ArrayList<String> arestas, String aresta) {
//         String arestaInvertida = aresta.substring(1, 2) + aresta.substring(0, 1);
//         for (int i = 0; i < arestas.size(); i++) {
//             // Extrai a aresta atual de 'arestas' e formata como letra-letra ('AA', por exemplo).
//             String[] arestaIDividida = arestas.get(i).split(",");
//             int arestaAtualI = Integer.parseInt(arestaIDividida[0]);
//             int arestaAtualJ = Integer.parseInt(arestaIDividida[1]);
//             String arestaAtual = Character.toString(graphL.get(arestaAtualI).getId()) + Character.toString(graphL.get(arestaAtualI).getAresta(arestaAtualJ));
//             // Compara as duas arestas e retorna true na primeira correspondência.
//             if(arestaInvertida.equals(arestaAtual)) return true;
//         }
//         return false;
//     }

//     /**
//      * Método que ordena de maneira crescente as arestas do grafo por peso.
//      */
//     private void ordenarArestas() {
//         // Código diferente baseado na representação do grafo.
//         if(graphL == null) {
//             // Ordenação por meio de Insertion Sort.
//             for (int i = 1; i < arestasOrdenadas.length; i++) {
//                 String key = new String(arestasOrdenadas[i]);
//                 int j = i - 1;

//                 String[] arestaJDividida = arestasOrdenadas[j].split(",");
//                 int matrizI = Integer.parseInt(arestaJDividida[0]);
//                 int matrizJ = Integer.parseInt(arestaJDividida[1]);

//                 String[] keyDividida = key.split(",");
//                 int keyI = Integer.parseInt(keyDividida[0]);
//                 int keyJ = Integer.parseInt(keyDividida[1]);

//                 while (j >= 0 && graphM[matrizI][matrizJ] > graphM[keyI][keyJ]) {
//                     arestasOrdenadas[j + 1] = arestasOrdenadas[j];
//                     j--;
//                     // Evita acesso a índice negativo após a retirada de uma unidade de 'j'.
//                     if(j >= 0) {
//                         arestaJDividida = arestasOrdenadas[j].split(",");
//                         matrizI = Integer.parseInt(arestaJDividida[0]);
//                         matrizJ = Integer.parseInt(arestaJDividida[1]);
//                     }
//                 }
//                 arestasOrdenadas[j + 1] = key;
//             }
//         }
//         else {
//             // Ordenação por meio de Insertion Sort.
//             for (int i = 1; i < arestasOrdenadas.length; i++) {
//                 String key = new String(arestasOrdenadas[i]);
//                 int j = i - 1;

//                 String[] arestaJDividida = arestasOrdenadas[j].split(",");
//                 int listaI = Integer.parseInt(arestaJDividida[0]);
//                 int listaJ = Integer.parseInt(arestaJDividida[1]);

//                 String[] keyDividida = key.split(",");
//                 int keyI = Integer.parseInt(keyDividida[0]);
//                 int keyJ = Integer.parseInt(keyDividida[1]);

//                 while (j >= 0 && graphL.get(listaI).getPeso(listaJ) > graphL.get(keyI).getPeso(keyJ)) {
//                     arestasOrdenadas[j + 1] = arestasOrdenadas[j];
//                     j--;
//                     // Evita acesso a índice negativo após a retirada de uma unidade de 'j'.
//                     if(j >= 0) {
//                         arestaJDividida = arestasOrdenadas[j].split(",");
//                         listaI = Integer.parseInt(arestaJDividida[0]);
//                         listaJ = Integer.parseInt(arestaJDividida[1]);
//                     }
//                 }
//                 arestasOrdenadas[j + 1] = key;
//             }
//         }
//     }

//     /**
//      * Método auxiliar do cheque se o grafo possui ciclo.
//      * Garante que todos os vértices sejam visitados, mesmo que o grafo atual seja desconexo.
//      * @param numAtualVertices do tipo int informa quantos vértices o grafo tem atualmente.
//      * @return true caso o grafo atual possua ciclo, false caso contrário.
//      */
//     private boolean formaCiclo(int numAtualVertices)
//     {
//         String[] verticesVisitados = new String[numAtualVertices];

//         for (int i = 0; i < verticesVisitados.length; i++) {
//             if (verticesVisitados[i] == null)
//                 if (formaCicloUtil(verticesVisitados, i, -1))
//                     return true;
//         }

//         return false;
//     }

//     /**
//      * Método principal do cheque se o grafo possui ciclo.
//      * Visita todos os vértices alcançáveis a partir do vértice raiz e confere se há ciclo baseado nos vértices visitados e no pai do vértice atual.
//      * @param verticesVisitados vetor do tipo String que acompanha quais vértices já foram visitados.
//      * @param vertice do tipo int que informa o índice do vértice que está sendo analisado.
//      * @param pai do tipo int que informa o índice do pai do vértice que está sendo analisado.
//      * @return
//      */
//     private boolean formaCicloUtil(String[] verticesVisitados, int vertice, int pai) {
//         verticesVisitados[vertice] = String.valueOf(vertice); 

//         // Código diferente baseado na representação do grafo.
//         if (graphL == null) {
//             for(int i = 0; i < verticesVisitados.length; i++) {
//                 // Confere os vértices adjacentes
//                 if(graphMAGM[vertice][i] != null) {
//                     // Caso não tenha sido visitado, chama o método recursivamente para a análise do vértice adjacente.
//                     if(verticesVisitados[i] == null) {
//                         if(formaCicloUtil(verticesVisitados, i, vertice)) return true;
//                     }
//                     // Caso o vértice adjacente já tenha sido visitado e não seja o pai do vértice sendo analisado, pode-se afirmar que há ciclo. 
//                     else if(i != pai) {
//                         return true;
//                     }
//                 }
//             }
//         }
//         else {
//             No verticeAtual = graphLAGM.get(vertice);
//             for(int i = 0; i < verticeAtual.qnt_aresta(); i++) {
//                 // Descobre o índice do vértice adjacente na lista principal do grafo.
//                 char verticeAdjacente = verticeAtual.getAresta(i);
//                 int indexRealAdjacente = indiceListaPrincipal(verticeAdjacente, graphLAGM);
//                 // Caso não tenha sido visitado, chama o método recursivamente para a análise do vértice adjacente.
//                 if(verticesVisitados[indexRealAdjacente] == null) {
//                     if(formaCicloUtil(verticesVisitados, indexRealAdjacente, vertice)) return true;
//                 }
//                 // Caso o vértice adjacente já tenha sido visitado e não seja o pai do vértice sendo analisado, pode-se afirmar que há ciclo.
//                 else if(indexRealAdjacente != pai){
//                     return true;
//                 }
//             }
//         }
//         return false;
//     }

//     /**
//      * Método que encontra a AGM do grafo para a representação em matriz.
//      */
//     private void encontrarMatrizAGM() {
//         int i = 0;
//         int indexI;
//         int indexJ;
//         ArrayList<String> arestasAdicionadas = new ArrayList<String>();

//         // Executa enquanto o novo grafo ainda for desconexo.
//         while(arestasAdicionadas.size() < vertices.size() - 1) {
//             // Extrai os índices do vértices que formam a aresta.
//             String[] arestaIDividida = arestasOrdenadas[i].split(",");
//             indexI = Integer.parseInt(arestaIDividida[0]);
//             indexJ = Integer.parseInt(arestaIDividida[1]);

//             // Adiciona a aresta com o seu devido peso no novo grafo.
//             graphMAGM[indexI][indexJ] = graphM[indexI][indexJ];
//             graphMAGM[indexJ][indexI] = graphM[indexJ][indexI];

//             // Confere se a nova aresta forma um ciclo no novo grafo.
//             if(formaCiclo(vertices.size())) {
//                 // Retira a nova aresta caso forme ciclo.
//                 graphMAGM[indexI][indexJ] = null;
//                 graphMAGM[indexJ][indexI] = null;
//             }
//             else {
//                 arestasAdicionadas.add(arestasOrdenadas[i]); // Adiciona a aresta à lista de arestas adicionadas ao novo grafo.
//                 custoTotal += graphM[indexI][indexJ]; // Contabiliza o peso da nova aresta ao custo total.
//             }
//             i += 1;
//         }
//     }

//     /**
//      * Método que encontra a AGM do grafo para a representação em lista.
//      */
//     private void encontrarListaAGM() {
//         int i = 0;
//         int indexNo1Real;
//         int indexNo2Real;
//         int indexNo2Falso;
//         int peso;
//         char no1;
//         char no2;
//         ArrayList<String> arestasAdicionadas = new ArrayList<String>();
        
//         // Executa enquanto o novo grafo ainda for desconexo.
//         while(arestasAdicionadas.size() < graphLAGM.size() - 1) {
//             // Extrai os índices do vértices que formam a aresta.
//             // O primeiro índice é referente ao nó 1 na lista principal, o segundo é referente ao nó 2 na lista de vértices adjacentes.
//             String[] arestaIDividida = arestasOrdenadas[i].split(",");
//             indexNo1Real = Integer.parseInt(arestaIDividida[0]);
//             indexNo2Falso = Integer.parseInt(arestaIDividida[1]);

//             // Obtem-se os nós 1 e 2 como char, e o peso da aresta.  
//             no1 = graphL.get(indexNo1Real).getId();
//             no2 = graphL.get(indexNo1Real).getAresta(indexNo2Falso);
//             peso = graphL.get(indexNo1Real).getPeso(indexNo2Falso);

//             // Descobre-se o índice do nó 2 na lista principal.
//             indexNo2Real = indiceListaPrincipal(no2, graphL);
            
//             // Adiciona a aresta com o seu devido peso no novo grafo.
//             graphLAGM.get(indexNo1Real).inserir_aresta(no2, peso);
//             graphLAGM.get(indexNo2Real).inserir_aresta(no1, peso);
            
//             // Confere se a nova aresta forma um ciclo no novo grafo.
//             if(formaCiclo(graphLAGM.size())) {
//                 // Retira a nova aresta caso forme ciclo.
//                 graphLAGM.get(indexNo1Real).remover_aresta(no2);
//                 graphLAGM.get(indexNo2Real).remover_aresta(no1);
//             }
//             else {
//                 arestasAdicionadas.add(arestasOrdenadas[i]); // Adiciona a aresta à lista de arestas adicionadas ao novo grafo.
//                 custoTotal += peso; // Contabiliza o peso da nova aresta ao custo total.
//             }
//             i += 1;
//         }
//     }

//     /**
//      * Método que encontra o índice na lista principal que represente um dado vértice.
//      * @param vertice do tipo char que informa o vértice o qual se deseja saber o índice.
//      * @param grafo do tipo List<No> informando qual grafo será analisado.
//      * @return do tipo int que informa o índice do vértice na lista principal caso ele exista no grafo, senão retorna -1.
//      */
//     private int indiceListaPrincipal(char vertice, List<No> grafo) {
//         for (int i = 0; i < grafo.size(); i++) {
//             if(grafo.get(i).getId() == vertice) return i;
//         }
//         return -1;
//     }

//     /**
//      * Método auxiliar da AGM que chama métodos diferentes baseado na representação do grafo.
//      */
//     public void encontrarAGM() {
//         if(graphL == null) {
//             encontrarMatrizAGM();  
//         }
//         else {
//             encontrarListaAGM();
//         }
//     }

//     /**
//      * Método que exibe a AGM do grafo em forma de matriz.
//      */
//     public void printGraphMAGM() {
//         System.out.print("\t   ");
//         for (int i = 0; i < vertices.size(); i++) {
//             System.out.print(vertices.get(i) + " ");
//         }
//         System.out.print("\n");
//         for (int i = 0; i < vertices.size(); i++) {
//             System.out.print("\t" + vertices.get(i) + "| ");
            
//             for (int j = 0; j < vertices.size(); j++) {
//                 if(graphMAGM[i][j] != null) {
//                     System.out.print(graphMAGM[i][j] + " ");
//                 } else {
//                     System.out.print("- ");
//                 }
//             }
//             System.out.println("|");
//         }
//     }

//     /**
//      * Método que exibe a AGM do grafo em forma de lista.
//      */
//     public void printGraphLAGM() {
//         for (int i = 0; i < graphLAGM.size(); i++) {
//             No no = graphLAGM.get(i);
//             System.out.print("\t" + no.getId() + ": [ ");

//             if (no.qnt_aresta() > 0) {
//                 System.out.print("(" + no.getAresta(0) + ", " + no.getPeso(0) + ")");
//             }

//             for (int j = 1; j < no.qnt_aresta(); j++) {
//                 System.out.print(", (" + no.getAresta(j) + ", " + no.getPeso(j) + ")");
//             }

//             System.out.println(" ]");
//         }
//     }

//     /**
//      * Método para acesso do atributo privado 'custoTotal'.
//      * @return int contendo o conteúdo de 'custoTotal'.
//      */
//     public int getCustoTotal() {
//         return custoTotal;
//     }

// }
