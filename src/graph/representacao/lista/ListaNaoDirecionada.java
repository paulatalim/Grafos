package graph.representacao.lista;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import graph.busca.BreadthFirstSearch;

public class ListaNaoDirecionada {
    private List<No> grafo;
    private boolean isPonderado;

    ListaNaoDirecionada () {
        grafo = new ArrayList<No>();
    }

    /**
     * Encontra um vertice no grafo
     * @param id do vertice a ser procurado
     * @return indice na lista no grafo
     */
    private int buscar_vertice(char id) {
        for(int i = 0; i < grafo.size(); i++) {
            if (grafo.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Verifica se o grafo está vazio
     * @return true, se estiver vazio, false, caso contrario
     */
    public boolean isGrafosEmpty() {
        if(grafo.isEmpty()){
            return true;
        }
        return false;
    }

    /**
     * Verifica se o grafo possui aresta
     * @return true, se tiver aresta, false caso contrario
     */
    public boolean temAresta() {
        for(int i = 0; i < grafo.size(); i++) {
            if(grafo.get(i).qnt_aresta() != 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica se um vertice existe
     * 
     * @param id do vertice a ser verificado
     * @return true, se o vertice existir, false, caso contrario
     */
    public boolean isNoExist(char id) {
        // Encontra o vertice no grafo
        if(buscar_vertice(id) >= 0) {
            return true;
        }
        
        return false;
    }

    /**
     * Adiciona um novo vertice ao grafo
     * @param id_vertice
     */
    public void inserir_vertice (char id_vertice) {
        // Verifica se o vertice exite
        if(!isNoExist(id_vertice)) {
            // Cria o novo vertice
            No new_vertice = new No(id_vertice);

            // Adiciona o vertice a lista
            grafo.add(new_vertice);
        }
    }

    /**
     * Adiciona uma aresta do grafo
     * @param aresta a ser inserida (String), indicada com seus vertices adjacentes
     * @return true, caso encontrar os vertices, ou false, caso não encontrar algum dos vertices adjacentes
     */
    public boolean inserir_aresta(String aresta) {
        // Encontra os vertice no grafo
        int aux1 = buscar_vertice(aresta.charAt(0));
        int aux2 = buscar_vertice(aresta.charAt(1));

        if (aux1 >= 0 && aux2 >= 0) {
            if(aux1 != aux2) {
                grafo.get(aux1).inserir_aresta(aresta.charAt(1));
                grafo.get(aux2).inserir_aresta(aresta.charAt(0));
            } else {
                // Adiciona uma laco
                grafo.get(aux1).inserir_aresta(aresta.charAt(1));
            }
            return true;
        }

        return false;
    }

    /**
     * Adiciona uma aresta do grafo
     * @param aresta a ser inserida (String), indicada com seus vertices adjacentes
     * @param peso da aresta
     * @return true, caso encontrar os vertices, ou false, caso não encontrar algum dos vertices adjacentes
     */
    public boolean inserir_aresta(String aresta, int peso) {
        if(isPonderado) {

            // Encontra os vertice no grafo
            int aux1 = buscar_vertice(aresta.charAt(0));
            int aux2 = buscar_vertice(aresta.charAt(1));
            
            if (aux1 >= 0 && aux2 >= 0) {
                if(aux1 != aux2) {
                    // Adiciona uma aresta
                    return (
                        grafo.get(aux1).inserir_aresta(aresta.charAt(1), peso) &&
                        grafo.get(aux2).inserir_aresta(aresta.charAt(0), peso)
                    );
                }

                // Adiciona uma laco
                return grafo.get(aux1).inserir_aresta(aresta.charAt(1), peso);
            }
        }

        return false;
    }
    
    /**
     * Remove uma aresta do grafo
     * @param aresta a ser retirada (String), indicada com seus vertices adjacentes
     * @return true, caso encontrar os vertices, ou false, caso não encontrar algum dos vertices adjacentes
     */
    public boolean remover_aresta(String aresta) {
        // Encontra os vertice no grafo
        int aux1 = buscar_vertice(aresta.charAt(0));
        int aux2 = buscar_vertice(aresta.charAt(1));

        if (aux1 >= 0 && aux2 >= 0) {
            if(aux1 != aux2) {
                grafo.get(aux1).remover_aresta(aresta.charAt(1));
                grafo.get(aux2).remover_aresta(aresta.charAt(0));
            } else {
                grafo.get(aux2).remover_aresta(aresta.charAt(0));
            }
            return true;
        }

        // Caso não encontrar algum dos vertices
        return false;
    }

    public boolean isArestaeExist(String aresta) {
        int aux1 = buscar_vertice(aresta.charAt(0));

        if(aux1 >= 0) {
            return grafo.get(aux1).isArestaeExist(aresta.charAt(1));
        }

        return false;
    }

    /**
     * Atualiza o Peso de uma aresta
     * 
     * @param aresta que o peso vai ser atualizado
     * @param newPeso o novo peso da aresta
     * @return true (caso a atualizacao ocorrer com sucesso) ou false (caso ocorrer algum erro)
     */
    public boolean atualizarPeso(String aresta, int newPeso) {
        if(isPonderado) {
            return (
                grafo.get(buscar_vertice(aresta.charAt(0))).updatePeso(aresta.charAt(1), newPeso) &&
                grafo.get(buscar_vertice(aresta.charAt(1))).updatePeso(aresta.charAt(0), newPeso)
            );
        }
        return false;
    }

    /**
     * Converte uma List<Character> para um vertor de char
     * @param list - lista a ser convertida
     * @return Array de caracteres
     */
    private char[] toArrayChar(List<Character> list) {
        // Verifica se a lista existe
        if(list == null) {
            return null;
        }

        // Cria um vetor auxiliar para armazenar os valores convertidos
        char[] vetor = new char[list.size()];
        
        // Conversao dos valores da lista para vetor
        for(int i = 0; i < list.size(); i++) {
            vetor[i] = Character.valueOf(list.get(i));
        }

        // Retorno da lista convertida para vetor
        return vetor;
    }

    /**
     * Verifica a vizinhaca do vertice
     * @param vertice a ser analisado
     * @return vetor de char (vertices adjacentes ao analisado)
     */
    public char[] vizinhaca(char vertice) {
        List<Character> vizinhos = new ArrayList<Character>();

        for(int i = 0; i < grafo.size(); i++) {
            if(grafo.get(i).getId() == vertice) {
                for(int j = 0; j < grafo.get(i).qnt_aresta(); j++) {
                    if(!vizinhos.contains(grafo.get(i).getAresta(j))) {
                        vizinhos.add(grafo.get(i).getAresta(j));
                    }
                }
            }
        }

        return toArrayChar(vizinhos);
    }

    /**
     * Calcula o grau de um vertice
     * @param vertice a ser analisado
     * @return int (grau do vertice)
     */
    public int calcularGrau(char vertice) {
        int grau = 0;

        for(int i = 0; i < grafo.size(); i++) {
            if(grafo.get(i).getId() == vertice) {
                
                // Verifica se ha laco
                for(int j = 0; j < grafo.get(i).qnt_aresta(); j++) {
                    if(grafo.get(i).getId() == grafo.get(i).getAresta(j)) {
                        grau += 2 * grafo.get(i).getPeso(j);
                    } else {
                        grau += grafo.get(i).getPeso(j);
                    }
                }

                return grau;
            }
        }

        return -1;
    }

    /**
     * Verifica se o grafo eh simples
     * @return true, se for simples, false, caso contrario
     */
    public boolean isSimples() {
        for(int i = 0; i < grafo.size(); i++) {
            for(int j = 0; j < grafo.get(i).qnt_aresta(); j++) {
                // Verifica se ha laco
                if(grafo.get(i).getId() == grafo.get(i).getAresta(j)) {
                    return false;
                }

                // Verifica se há aresta paralela
                for(int k = grafo.get(i).qnt_aresta() - 1; k > j; k--) {
                    if(k != j && grafo.get(i).getAresta(j) == grafo.get(i).getAresta(k)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * Verifica se o grafo eh regular
     * @return true, se for regular, false, caso contrario
     */
    public boolean isRegular() {
        if(isGrafosEmpty()) {
            return false;
        }

        int grauAtual = calcularGrau(grafo.get(0).getId());

        for(int i = 1; i < grafo.size(); i++) {
            if(calcularGrau(grafo.get(i).getId()) != grauAtual) {
                return false;
            }
        }

        return true;
    }

    /**
     * Verifica se o grafo eh completo
     * @return true, se for completo, false, caso contrario
     */
    public boolean isCompleto() {
        if(isSimples()) {
            for(int i = 0; i < grafo.size(); i++) {
                if(grafo.get(i).qnt_aresta() != grafo.size() - 1) {
                    return false;
                }
            }
            return true;
        }

        return false;
    }

    /**
     * Verifica se o grafo eh bipartido
     * 
     * @return true, se for bipartido, false, caso contrar
     */
    public boolean isBipartido () {
        Map<Character, Byte> mapeamento = new HashMap<Character, Byte>();
        byte color = 0;
        byte result;
        boolean allIsVerficado = false;

        // Inicializa o mapeamento
        for(int i = 1; i < grafo.size(); i++) {
            mapeamento.put(Character.valueOf(grafo.get(i).getId()), (byte) -1);
        }

        mapeamento.put(Character.valueOf(grafo.get(0).getId()), color);
        
        while (!allIsVerficado) {
            // Encontra o proximo vertice a ser vericado
            for(int i = 0; i < grafo.size(); i++) {
                color = mapeamento.get(Character.valueOf(grafo.get(i).getId()));
                
                if (color != -1) {
                    if(color == 0) {
                        color ++;
                    } else if (color == 1) {
                        color = 0;
                    }
                    
                    // verifica a vizinhanca
                    for (int j = 0; j < grafo.get(i).qnt_aresta(); j++) {
                        result = mapeamento.get(Character.valueOf(grafo.get(i).getAresta(j)));
                        
                        // Verifica se ha laco
                        if(grafo.get(i).getId() == grafo.get(i).getAresta(j)) {
                            return false;
                        }
                        // Inicializa cor
                        else if(result == (byte) -1) {
                            mapeamento.put(Character.valueOf(grafo.get(i).getAresta(j)), color);
                            
                            // Caso a cor ser invalida
                        } else if ((color == 0 && result == 1) || (color == 1 && result == 0)) {
                            return false;
                        }
                    }
                }
            }
            
            allIsVerficado = true;

            // Verifica se algum vertice nao foi verificado; colorido
            for(int i = 0; i < grafo.size(); i++) {
                if(mapeamento.get(Character.valueOf((grafo.get(i).getId()))) == -1) {
                    mapeamento.put(Character.valueOf(grafo.get(i).getId()), (byte) 0);
                    allIsVerficado = false;
                    break;
                }
            }
        }

        return true;
    }

    /**
     * Confere se o grafo é conexo ou não
     * @return true, se for conexo, false, caso contrário
     */
    public boolean isConexo() {    
        BreadthFirstSearch bfs = new BreadthFirstSearch(grafo);
        return bfs.getIsConexo();
    } 

    /**
     * Realiza uma busca em largura no grafo
     * @param verticeInicial char (vertice que a busca em largura ira iniciar)
     * @return vetor de char (ordem de visitacao dos vertices na busca em largura)
     */
    public char[] realizarBuscaLargura(char verticeInicial) {
        BreadthFirstSearch BFS = new BreadthFirstSearch(grafo);
        return toArrayChar(BFS.bfs(verticeInicial));
    }

    /**
     * Imprime a lista de adjacencia no console
     */
    public void exibir_lista () {
        for (int i = 0; i < grafo.size(); i++) {
            No no = grafo.get(i);
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

    /**
     * Se o Grafo eh poderado
     * @param isPonderado
     */
    public void setIsPonderado(boolean isPonderado) {
        this.isPonderado = isPonderado;
    }

    /**
     * Se o grafo eh poderado
     * 
     * @return true ou false
     */
    public boolean isPonderado() {
        return isPonderado;
    }

    /**
     * @return List<No> return the grafo
     */
    public List<No> getGrafo() {
        return grafo;
    }
}
