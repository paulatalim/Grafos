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
            grafo.get(aux1).inserir_aresta(aresta.charAt(1));
            grafo.get(aux2).inserir_aresta(aresta.charAt(0));
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
     * Verifica se o grafo eh bipartido
     * 
     * @return true, se for bipartido, false, caso contrar
     */
    public boolean isBipartido () {
        Map<Character, Byte> mapeamento = new HashMap<Character, Byte>();
        boolean[] isVerificado = new boolean[grafo.size()];
        byte color = 0;
        byte result;
        boolean hasComponent = false;

        // Inicializa o mapeamento
        for(int i = 0; i < grafo.size(); i++) {
            mapeamento.put(Character.valueOf(grafo.get(i).getId()), (byte) -1);
            isVerificado[i] = false;
        }

        mapeamento.put(Character.valueOf(grafo.get(0).getId()), color);
            
        while(mapeamento.containsValue((byte) -1) && !hasComponent) {
            
            // Encontra o proximo vertice a ser vericado
            for(int i = 0; i < grafo.size(); i++) {
                if(mapeamento.get(Character.valueOf(grafo.get(i).getId())) != (byte) -1 && !isVerificado[i]) {
                    color = mapeamento.get(Character.valueOf(grafo.get(i).getId()));
                    isVerificado[i] = true;

                    if(color == 0) {
                        color ++;
                    } else {
                        color = 0;
                    }

                    for (int j = 0; j < grafo.get(i).qnt_aresta(); j++) {
                        result = mapeamento.get(Character.valueOf(grafo.get(i).getAresta(j)));
        
                        if(result == (byte) -1) {
                            mapeamento.put(Character.valueOf(grafo.get(i).getAresta(j)), color);
                        } else if ((color == 0 && result == 1) || (color == 1 && result == 0)) {
                            return false;
                        }
                    }

                    break;
                }
            }

            hasComponent = true;

            // Verifica se ha componente no grafo
            for(int i = 0; i < grafo.size(); i++) {
                if(mapeamento.get(Character.valueOf(grafo.get(i).getId())) != -1 && !isVerificado[i]) {
                    hasComponent = false;
                    break;
                }
            }

            if(hasComponent) {
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
