package system;

import java.util.ArrayList;
import graph.representacao.matriz.MatrizManage;

public class Hospital {
    //ListaManage grafoLista = new ListaManage();
    MatrizManage grafoMatriz = new MatrizManage();
    ArrayList<String> HolidayName;
    ArrayList<Integer> D;
    ArrayList<String> DoctorName;
    ArrayList<Integer> S;

    private boolean isStringValida(String str) {
        for(char c : str.toCharArray()) {
            if(!Character.isDigit(c) && !Character.isLetter(c)) {
                return false;
            }
        }

        return true;
    }
    
    public Hospital() {
        // Criacao de grafo
        grafoMatriz = new MatrizManage();

        // Configuracao para o grafo direcionado
        grafoMatriz.setIsDirecionado(true);

        // Configuracao para grafo ponderado
        grafoMatriz.setIsPonderado(true);

        // Adicao de conjuntos
        grafoMatriz.inserir_vertice("S");
        grafoMatriz.inserir_vertice("U");

        // Inicializa variaveis
        HolidayName = new ArrayList<>();
        DoctorName = new ArrayList<>();
        D = new ArrayList<>();
        S = new ArrayList<>();
    }

    public boolean addHoliday(String name, Integer days) {
        // Validacao dao nome do feriado
        if(HolidayName.contains(name) || name == "" || !isStringValida(name)) return false;

        // Adiciona o vertice
        grafoMatriz.inserir_vertice("D" + HolidayName.size());
        
        // Adiciona a aresta
        grafoMatriz.inserir_aresta("D" + HolidayName.size(),"U", days);
        D.add(days);

        // Adiciona o nome a lista
        HolidayName.add(name);
        return true;
    }

    public boolean addDoctor(String name, int disponibilidade) {
        // Validacao do nome do medico
        if(DoctorName.contains(name) || name == "" || !isStringValida(name)) return false;

        // Adiciona o vertice
        grafoMatriz.inserir_vertice("S" + DoctorName.size());

        // Adiciona a aresta
        grafoMatriz.inserir_aresta("S", "S" + DoctorName.size(), disponibilidade);
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

        // Adiciona a aresta
        grafoMatriz.inserir_aresta("S" + DoctorName.indexOf(nameDoctor), "D" + HolidayName.indexOf(nameHoliday), 1);

        return true;        
    }

    public MatrizManage inicializarFluxoViavel() {
        // Inicializando matriz da rede.
        MatrizManage fluxoViavel = new MatrizManage();
        fluxoViavel.setIsDirecionado(true);
        fluxoViavel.setIsPonderado(true);

        ArrayList<String> vertices = grafoMatriz.getVertices();

        // Adição do vértices.
        for(int i = 0; i < vertices.size(); i++) {
            fluxoViavel.inserir_vertice(vertices.get(i));
        }

        // Inicializando os pesos das arestas do fluxo viável.
        for(int i = 0; i < vertices.size(); i++) {
            for(int j = 0; j < vertices.size(); j++) {
                if(grafoMatriz.getPeso(vertices.get(i) + "," + vertices.get(j)) != null) {
                    fluxoViavel.inserir_aresta(vertices.get(i), vertices.get(j), 0);
                }
            }
        }

        return fluxoViavel;
    }

    public ArrayList<String> haCaminhoAumentanteUtil(MatrizManage rede) {
        ArrayList<String> vertices = rede.getVertices();
        ArrayList<String> verticesVisitados = new ArrayList<>();
        ArrayList<String> caminhoAumentante = new ArrayList<>();
        return haCaminhoAumentante(rede, caminhoAumentante, vertices, verticesVisitados, "S");
    }

    // TODO: Preencher a função que checa se há caminho aumentante ou não.
    public ArrayList<String> haCaminhoAumentante(MatrizManage rede, ArrayList<String> caminhoAumentante, ArrayList<String> vertices, ArrayList<String> verticesVisitados, String verticeAtual) {
        verticesVisitados.add(verticeAtual);
        if(verticeAtual == "U") return caminhoAumentante;
        for(int i = 0; i < vertices.size(); i++) {
            if(verticesVisitados.contains("U")) break;
            // Confere se existe aresta entre os vértices
            if(rede.getPeso(verticeAtual + "," + vertices.get(i)) != null) {
                // Chama recursivamente o método passando o vértice analisado como parâmetro (preenche a pilha).
                caminhoAumentante.add(verticeAtual + "," + vertices.get(i));
                haCaminhoAumentante(rede, caminhoAumentante, vertices, verticesVisitados, vertices.get(i)); 
            }
        }
        if(!verticesVisitados.contains("U")) caminhoAumentante.clear();
        return caminhoAumentante;
    }

    public ArrayList<String> verificarAtribuicaoMedicos() throws Exception {
        if(!grafoMatriz.isGrafosConexo()) return null;
        
        MatrizManage redeResidual = (MatrizManage)grafoMatriz.clone();
        MatrizManage fluxoViavel = inicializarFluxoViavel();
        ArrayList<String> caminhoAumentante = haCaminhoAumentanteUtil(redeResidual);
        int gargalo = 1;

        while(caminhoAumentante.size() > 0) {
            for(int i = 0; i < caminhoAumentante.size(); i++) {
                String aresta = caminhoAumentante.get(i);
                String[] arestaDividida = aresta.split(",");
                Integer peso = redeResidual.getPeso(aresta);

                //TODO: Acredito que o problema esteja nessa parte aqui.
                if(peso != null) {
                    redeResidual.atualizarPeso(arestaDividida[0], arestaDividida[1], peso - gargalo);
                    fluxoViavel.atualizarPeso(arestaDividida[0], arestaDividida[1], peso + gargalo);
                    peso = redeResidual.getPeso(aresta);
                }

                if(peso <= 0) redeResidual.remover_aresta(arestaDividida[0], arestaDividida[1]);
            }
            caminhoAumentante = haCaminhoAumentanteUtil(redeResidual);
        }

        boolean atribuicaoPossivel = true;
        
        if(redeResidual.grau_vertice("U")[1] != 0) atribuicaoPossivel = false;
        
        if(atribuicaoPossivel) return DoctorName;
        else return null;
    }

    public String HolidayListToString() {
        if(HolidayName.isEmpty()) return null;

        String str = "";

        for (int i = 0; i < HolidayName.size(); i++) {
            str += "\t- " + HolidayName.get(i) + "\n";
        }

        return str;
    }

    public String DoctorsListToString() {
        if(DoctorName.isEmpty()) return null;

        String str = "";

        for (int i = 0; i < DoctorName.size(); i++) {
            str += "\t- " + DoctorName.get(i) + "\n";
        }

        return str;
    }
}
