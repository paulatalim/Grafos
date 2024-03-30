package lista;

import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;

public class ListaDirecionada {
    private List<No> grafo;

    ListaDirecionada () {
        grafo = new ArrayList<No>();
    }

    /**
     * Encontra um vertice no grafo
     * @param id do vertice a ser procurado
     * @return indice na lista no grafo ou -1, se o vertice não existir
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
        // Verifica se o vertice ja existe
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
        int aux = buscar_vertice(aresta.charAt(0));

        if(aux >= 0 && buscar_vertice(aresta.charAt(1)) >= 0) {
            // Adiciona uma nova aresta
            grafo.get(aux).inserir_aresta(aresta.charAt(1));
            return true;
        }

        return false;
    }

    /**
     * Remove uma aresta do grafo
     * @param aresta a ser retirada (String), indicada com seus vertices adjacentes
     * @return true, caso encontrar os vertices, ou false, caso não encontrar algum dos vertices adjacentes
     */
    public boolean remover_aresta(String aresta) {
        int aux1 = buscar_vertice(aresta.charAt(0));

        if (aux1 >= 0) {
            return grafo.get(aux1).remover_aresta(aresta.charAt(1));
        }

        // Caso nao encontrar a aresta
        return false;
    }

    /**
     * Converte uma List<Character> para um vertor de char
     * @param list - lista a ser convertida
     * @return Array de caracteres
     */
    private char[] toArrayChar(List<Character> list) {
        char[] vetor = new char[list.size()];
        
        for(int i = 0; i < list.size(); i++) {
            vetor[i] = Character.valueOf(list.get(i));
        }

        return vetor;
    }

    /**
     * Verifica os predecessores de um vertice
     * @param vertice a ser analisado
     * @return vetor de char (vertices predecessores)
     */
    public char[] encontrarPredecessores(char vertice) {
        List<Character> predecessor = new ArrayList<Character>();

        for (int i = 0; i < grafo.size(); i++) {
            for(int j = 0; j < grafo.get(i).qnt_aresta(); j++) {
                if(grafo.get(i).getAresta(j) == vertice) {
                    predecessor.add(Character.valueOf(grafo.get(i).getId()));
                }
            }
        }

        return toArrayChar(predecessor);
    }

    /**
     * Verifica os sucessores de um vertice
     * @param vertice a ser analisado
     * @return vetor de char (vertices sucessores)
     */
    public char[] encontrarSucessores(char vertice) {
        List<Character> sucessores = new ArrayList<Character>();

        for (int i = 0; i < grafo.size(); i++) {
            if(grafo.get(i).getId() == vertice) {
                for(int j = 0; j < grafo.get(i).qnt_aresta(); j++) {
                    sucessores.add(grafo.get(i).getAresta(j));
                }
            }
        }

        return toArrayChar(sucessores);
    }

    /**
     * Calcula o grau de entrada de um vertice
     * @param vertice a ser analisado
     * @return int (grau de entrada do vertice)
     */
    public int calcularGrauEntrada(char vertice) {
        return encontrarPredecessores(vertice).length;
    }

     /**
     * Calcula o grau de saida de um vertice
     * @param vertice a ser analisado
     * @return int (grau de saida do vertice)
     */
    public int calcularGrauSaida(char vertice) {
        return encontrarSucessores(vertice).length;
    }

    /**
     * Calcula o grau de um vertice
     * @param vertice a ser analisado
     * @return int (grau do vertice)
     */
    public int calcularGrau(char vertice) {
        return calcularGrauEntrada(vertice) + calcularGrauSaida(vertice);
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
            char vertice;
            for(int i = 0; i < grafo.size(); i++) {
                vertice = grafo.get(i).getId();

                // Verifica se o grau do vertice esta correto
                if(calcularGrauEntrada(vertice) != calcularGrauSaida(vertice)) {
                    return false;
                }

                // Verifica se há aresta anti-paralela
                for (int j = 0; j < grafo.get(i).qnt_aresta(); j++) {
                    for(int k = grafo.get(i).qnt_aresta() - 1; k > j; k--) {
                        if(grafo.get(i).getAresta(j) == grafo.get(i).getAresta(k)) {
                            return false;
                        }
                    }
                }
            }
            return true;
        }

        return false;
    }

    /**
     * Adiciona cor aos vertices adjacentes ao vértice inserido
     * @param x vértice inserido
     * @param color vetor com o registro das cores dos vertices
     */
    private void colorirVerticesAjacentes(char x, Map<Character, Integer> color) {
        // Adicao do vertice a fila
        Queue<Character> fila = new PriorityQueue<Character>();
        fila.add(Character.valueOf(x));
        
        int pos = 0;
        
        while(pos < fila.size()) {
            
            char atual = (char) fila.remove();
            pos++;

            for(int i = 0; i < grafo.size(); i ++) {
                if(grafo.get(i).getId() == atual) {
            
                    for(int j = 0; j < grafo.get(i).qnt_aresta(); j++) {
                        char v = grafo.get(i).getAresta(j);
                        
                        if(color.get(Character.valueOf(v)) == -1) {
                            color.put(Character.valueOf(v), 1-color.get(atual));			
                            fila.add(Character.valueOf(v));
                        }
                    }

                    break;
                }
            }
        }
    }

    /**
     * Adiciona cor aos vertices
     * @param x vértice a ser verificado
     * @param color vetor com o registro das cores dos vertices
     */
    private void colorirVertice(char x, Map<Character, Integer> color) {
        // Caso a busca 
        if(!color.containsValue(0) && !color.containsValue(1)) {
            color.put(Character.valueOf(x), 0);
        } else {
            for(int i = 0; i <grafo.size(); i++) {
                if(grafo.get(i).getId() == x) {
                    for(int j = 0; j < grafo.get(i).qnt_aresta(); j++) {
                        if(color.get(grafo.get(i).getAresta(j)) == 0) {
                            color.put(grafo.get(i).getId(), 1);
                            break;
                        
                        } else if (color.get(grafo.get(i).getAresta(j)) == 1) {
                            color.put(grafo.get(i).getId(), 0);
                            break;
                        }
                        
                    } break;
                }
            }
        }

        // Busca pelos predecessores de x
        if(color.get(Character.valueOf(x)) == -1) {
            for(int i = 0; i < grafo.size(); i++) {
                for(int j = 0; j < grafo.get(i).qnt_aresta(); j++) {
                    if(grafo.get(i).getAresta(j) == x){
                        if(color.get(grafo.get(i).getId()) == 0) {
                            color.put(grafo.get(i).getAresta(j), 1);
                            break;
                        } else if (color.get(grafo.get(i).getId()) == 1) {
                            color.put(grafo.get(i).getAresta(j), 0);
                            break;
                        }
                    }
                }
            }
        }

        // Caso o vertice não tenha sucessor e predecessor
        if(color.get(Character.valueOf(x)) == -1) {
            color.put(Character.valueOf(x), -2);
        }

        colorirVerticesAjacentes(x, color);
    }

    /**
     * Verifica se o grafo é bipartido apartir das cores dos vertices
     * @param color dos vertices
     * @return true, se for bipartido, false, caso contrario
     */
    private boolean isBipartido(Map<Character, Integer> color) {
        for(int i = 0; i < grafo.size(); i++){
            if(color.get(Character.valueOf(grafo.get(i).getId())) == -1){
                colorirVertice(grafo.get(i).getId(), color);
            }
        }

        // Verifica se há vertice isolado
        if(color.containsValue(-2)) {
            return false;
        }
        
        // Verifica se existem dois vértices vizinhos com a mesma cor
        for(int i = 0; i < grafo.size(); i++){
            for(int j = 0; j < grafo.get(i).qnt_aresta(); j++){
                char v = grafo.get(i).getAresta(j);
                // se os dois vizinhos possuem a mesma cor, o grafo não é bipartido
                if(color.get(grafo.get(i).getId()) == color.get(Character.valueOf(v))) return false;
            }
        }
        
        return true;
    }

    /**
     * Verifica se o grafo eh bipartido
     * 
     * @return true, se for bipartido, false, caso contrario
     */
    public boolean isBipartido() {
        Map<Character, Integer> color = new HashMap<Character, Integer>();

        // Inicializa o mapeamento
            for(int i = 0; i < grafo.size(); i++) {
                color.put(Character.valueOf(grafo.get(i).getId()), -1);
            }

        return isBipartido(color);

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
     * @return List<No> return the grafo
     */
    public List<No> getGrafo() {
        return grafo;
    }
}
