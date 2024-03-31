package lista;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListaNaoDirecionada {
    private List<No> grafo;

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
     * Remove uma aresta do grafo
     * @param aresta a ser retirada (String), indicada com seus vertices adjacentes
     * @return true, caso encontrar os vertices, ou false, caso não encontrar algum dos vertices adjacentes
     */
    public boolean remover_aresta(String aresta) {
        // Encontra os vertice no grafo
        int aux1 = buscar_vertice(aresta.charAt(0));
        int aux2 = buscar_vertice(aresta.charAt(1));

        if (aux1 >= 0 && aux2 >= 0) {
            grafo.get(aux1).remover_aresta(aresta.charAt(1));
            grafo.get(aux2).remover_aresta(aresta.charAt(0));
            return true;
        }

        // Caso não encontrar algum dos vertices
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
     * Verifica a vizinhaca do vertice
     * @param vertice a ser analisado
     * @return vetor de char (vertices adjacentes ao analisado)
     */
    public char[] vizinhaca(char vertice) {
        List<Character> vizinhos = new ArrayList<Character>();

        for(int i = 0; i < grafo.size(); i++) {
            if(grafo.get(i).getId() == vertice) {
                for(int j = 0; j < grafo.get(i).qnt_aresta(); j++) {
                    vizinhos.add(grafo.get(i).getAresta(j));
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
        for(int i = 0; i < grafo.size(); i++) {
            if(grafo.get(i).getId() == vertice) {
                
                // Verifica se ha laco
                for(int j = 0; j < grafo.get(i).qnt_aresta(); j++) {
                    if(grafo.get(i).getId() == grafo.get(i).getAresta(j)) {
                        return grafo.get(i).qnt_aresta() + 1;
                    }
                }

                return grafo.get(i).qnt_aresta();
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
        boolean[] isVerificado = new boolean[grafo.size()];
        byte color = 0;
        byte result;

        // Inicializa o mapeamento
        for(int i = 1; i < grafo.size(); i++) {
            mapeamento.put(Character.valueOf(grafo.get(i).getId()), (byte) -1);
            isVerificado[i] = false;
        }

        mapeamento.put(Character.valueOf(grafo.get(0).getId()), color);
            
        // Encontra o proximo vertice a ser vericado
        for(int i = 0; i < grafo.size(); i++) {
            color = mapeamento.get(Character.valueOf(grafo.get(i).getId()));

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

        // Verifica se ha mais de uma componente no grafo
        for (int i = 0; i < mapeamento.size(); i++) {
            if(mapeamento.get(Character.valueOf(grafo.get(i).getId())) == -1) {
                return false;
            }
        }

        return true;
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
