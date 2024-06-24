package system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import graph.representacao.matriz.MatrizManage;

public class Hospital {
    //ListaManage grafoLista = new ListaManage();
    MatrizManage grafo = new MatrizManage();
    ArrayList<String> HolidayName;
    ArrayList<Integer> D;
    ArrayList<String> DoctorName;
    ArrayList<Integer> S;

    private boolean isStringValida(String str) {
        for(char c : str.toCharArray()) {
            if(!Character.isDigit(c) && !Character.isLetter(c) && c != ' ') {
                return false;
            }
        }

        return true;
    }
    
    public Hospital() {
        // Criacao de grafo
        grafo = new MatrizManage();

        // Configuracao para o grafo direcionado
        grafo.setIsDirecionado(true);

        // Configuracao para grafo ponderado
        grafo.setIsPonderado(true);

        // Adicao de conjuntos
        grafo.inserir_vertice("S");
        grafo.inserir_vertice("U");

        // Inicializa variaveis
        HolidayName = new ArrayList<>();
        DoctorName = new ArrayList<>();
        D = new ArrayList<>();
        S = new ArrayList<>();
    }

    public boolean addHoliday(String name, Integer days) {
        // Validacao dao nome do feriado
        if(HolidayName.contains(name) || name == "" || !isStringValida(name)) return false;
        if(days <= 0) return false;

        // Adiciona o vertice
        grafo.inserir_vertice("D" + HolidayName.size());
        
        // Adiciona a aresta
        grafo.inserir_aresta("D" + HolidayName.size(),"U", days);
        D.add(days);

        // Adiciona o nome a lista
        HolidayName.add(name);
        return true;
    }

    public boolean addDoctor(String name, int disponibilidade) {
        // Validacao do nome do medico
        if(DoctorName.contains(name) || name == "" || !isStringValida(name)) return false;
        if(disponibilidade <= 0) return false;

        // Adiciona o vertice
        grafo.inserir_vertice("S" + DoctorName.size());

        // Adiciona a aresta
        grafo.inserir_aresta("S", "S" + DoctorName.size(), disponibilidade);
        S.add(disponibilidade);

        // Adiciona o nome a lista
        DoctorName.add(name);
        return true;
    }

    /**
     * Adiciona os dias de disponibilidade do medico
     * @param nameDoctor String (nome do medico)
     * @param nameHoliday String (nome do feriado que o medico estara disponivel)
     * @return true, se conseguir adicionar a disponilidade, false, caso contrario
     */
    public boolean addDoctorAvailability(String nameDoctor, String nameHoliday) {
        // Valida os parametros
        if(!DoctorName.contains(nameDoctor) || !HolidayName.contains(nameHoliday)) return false;
        if(grafo.getPeso("S" + DoctorName.indexOf(nameDoctor) + ",D" + HolidayName.indexOf(nameHoliday)) != null) return false;

        // Adiciona a aresta
        grafo.inserir_aresta("S" + DoctorName.indexOf(nameDoctor), "D" + HolidayName.indexOf(nameHoliday), 1);

        return true;        
    }

    private MatrizManage inicializarFluxoViavel() {
        // Inicializando matriz da rede.
        MatrizManage fluxoViavel = new MatrizManage();
        fluxoViavel.setIsDirecionado(true);
        fluxoViavel.setIsPonderado(true);

        ArrayList<String> vertices = grafo.getVertices();

        // Adição do vértices.
        for(int i = 0; i < vertices.size(); i++) {
            fluxoViavel.inserir_vertice(vertices.get(i));
        }

        // Inicializando os pesos das arestas do fluxo viável.
        for(int i = 0; i < vertices.size(); i++) {
            for(int j = 0; j < vertices.size(); j++) {
                if(grafo.getPeso(vertices.get(i) + "," + vertices.get(j)) != null) {
                    fluxoViavel.inserir_aresta(vertices.get(i), vertices.get(j), 0);
                }
            }
        }

        return fluxoViavel;
    }

    private ArrayList<String> buscarCaminhoAumentante(MatrizManage rede) {
        ArrayList<String> vertices = rede.getVertices();
        ArrayList<String> verticesVisitados = new ArrayList<>();
        ArrayList<String> caminhoAumentante = new ArrayList<>();
        return buscarCaminhoAumentante(rede, caminhoAumentante, vertices, verticesVisitados, "S");
    }

    // TODO: Preencher a função que checa se há caminho aumentante ou não.
    private ArrayList<String> buscarCaminhoAumentante(MatrizManage rede, ArrayList<String> caminhoAumentante, ArrayList<String> vertices, ArrayList<String> verticesVisitados, String verticeAtual) {
        verticesVisitados.add(verticeAtual);
        if(verticeAtual == "U") return caminhoAumentante;
        for(int i = 0; i < vertices.size(); i++) {
            if(verticesVisitados.contains("U")) break;

            // Confere se existe aresta entre os vértices
            if(rede.getPeso(verticeAtual + "," + vertices.get(i)) != null && !verticesVisitados.contains(vertices.get(i))) {

                // Chama recursivamente o método passando o vértice analisado como parâmetro (preenche a pilha).
                caminhoAumentante.add(verticeAtual + "," + vertices.get(i));
                buscarCaminhoAumentante(rede, caminhoAumentante, vertices, verticesVisitados, vertices.get(i)); 
            }
        }
        if(!verticesVisitados.contains("U")) caminhoAumentante.clear();
        return caminhoAumentante;
    }

    private MatrizManage criarRedeResidual() {
        MatrizManage rede = new MatrizManage();
        rede.setIsDirecionado(true);
        rede.setIsPonderado(true);

        ArrayList<String> vertices = grafo.getVertices();

        // Adição do vértices.
        for(int i = 0; i < vertices.size(); i++) {
            rede.inserir_vertice(vertices.get(i));
        }

        // Inicializando os pesos das arestas do fluxo viável.
        for(int i = 0; i < vertices.size(); i++) {
            for(int j = 0; j < vertices.size(); j++) {
                if(grafo.getPeso(vertices.get(i) + "," + vertices.get(j)) != null) {
                    rede.inserir_aresta(vertices.get(i), vertices.get(j), grafo.getPeso(vertices.get(i) + "," + vertices.get(j)));
                }
            }
        }

        return rede;

    }

    public String verificarAtribuicaoMedicos() {
        if(!grafo.isGrafosConexo()) return null;
        
        MatrizManage redeResidual = criarRedeResidual();
        MatrizManage fluxoViavel = inicializarFluxoViavel();
        ArrayList<String> caminhoAumentante = buscarCaminhoAumentante(redeResidual);
        int gargalo = 1;

        while(caminhoAumentante.size() > 0) {
            for(int i = 0; i < caminhoAumentante.size(); i++) {
                String aresta = caminhoAumentante.get(i);
                String[] arestaDividida = aresta.split(",");
                Integer pesoRede = redeResidual.getPeso(aresta);
                Integer pesoFluxo = fluxoViavel.getPeso(aresta);

                if(pesoRede != null) {                    
                    // Atualiza peso no fluxo viavel
                    if(!fluxoViavel.atualizarPeso(arestaDividida[0], arestaDividida[1], (pesoFluxo.intValue() + gargalo)))
                        fluxoViavel.atualizarPeso(arestaDividida[1], arestaDividida[0], pesoFluxo.intValue() - gargalo);

                    // Atualiza aresta direta na rede residual
                    if(pesoRede.intValue() - gargalo > 0) redeResidual.atualizarPeso(arestaDividida[0], arestaDividida[1], (pesoRede.intValue() - gargalo));
                    else redeResidual.remover_aresta(arestaDividida[0], arestaDividida[1]);
                    
                    // Atualiza aresta reversa na rede residual
                    if(!redeResidual.atualizarPeso(arestaDividida[1], arestaDividida[0], (pesoRede.intValue() + gargalo)))
                        redeResidual.inserir_aresta(arestaDividida[1], arestaDividida[0], (pesoRede.intValue() + gargalo));
                }
            }
            caminhoAumentante = buscarCaminhoAumentante(redeResidual);
        }

        // Verifica se os feriados estarao ocupados
        boolean atribuicaoPossivel = true;        
        if(redeResidual.verifica_predecessores("U").size() != 0) atribuicaoPossivel = false;
        
        // Retorno
        if(!atribuicaoPossivel) return null;

        // if(atribu11

        String relatorio = "";

        for (int i = 0; i < HolidayName.size(); i++) {
            relatorio += "\n\t" + HolidayName.get(i) + " - [";

            ArrayList<String> escalonados = redeResidual.verifica_sucessores("D" + i);

            for (int j = 0; j < escalonados.size(); j++) {
                relatorio += DoctorName.get(Character.getNumericValue(escalonados.get(j).charAt(1)));
                if(j < escalonados.size() - 1) relatorio += ", ";
            }

            relatorio += "]\n";
        }

        return relatorio;
    }

    public String HolidayListToString() {
        if(HolidayName.isEmpty()) return null;

        String str = "";

        for (int i = 0; i < HolidayName.size(); i++) {
            str += "\t- " + HolidayName.get(i) + ": " + D.get(i) + " dias \n";
        }

        return str;
    }

    public String DoctorsListToString() {
        if(DoctorName.isEmpty()) return null;

        String str = "";

        for (int i = 0; i < DoctorName.size(); i++) {
            str += "\t- " + DoctorName.get(i) + ": " + S.get(i) + " dias \n";
        }

        return str;
    }
}
