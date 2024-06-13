package system;

import java.util.ArrayList;

// import graph.AGM.ArvoreGeradoraMinima;
// import graph.busca.DepthFirstSearch;
import graph.representacao.lista.ListaManage;
import graph.representacao.matriz.MatrizManage;

public class Hospital {
    ListaManage grafoLista = new ListaManage();
    MatrizManage grafoMatriz = new MatrizManage();
    ArrayList<Integer> D;
    ArrayList<String> HolidayName;
    int c;

    ArrayList<Integer> S;
    ArrayList<String> DoctorName;
    
    Hospital() {
        // Criacao de grafo
        grafoLista = new ListaManage();
        grafoMatriz = new MatrizManage();

        // Configuracao para o grafo direcionado
        grafoLista.setIsDirecionado(true);
        grafoMatriz.setIsDirecionado(true);

        // Configuracao para grafo ponderado
        grafoLista.setIsPonderado(true);
        grafoMatriz.setIsPonderado(true);

        // Adicao de conjuntos
        grafoLista.inserir_vertice("S");
        grafoLista.inserir_vertice("U");
        grafoMatriz.inserir_vertice("S");
        grafoMatriz.inserir_vertice("U");

        // Inicializa variaveis
        D = new ArrayList<Integer>();
        HolidayName = new ArrayList<>();
        S = new ArrayList<>();
        DoctorName = new ArrayList<>();
        c = -1;
    }

    public void addHoliday(String name, Integer days) {
        grafoLista.inserir_vertice("D" + D.size());
        grafoMatriz.inserir_vertice("D" + D.size());
        
        grafoLista.inserir_aresta("D" + D.size(),"U", days);
        grafoMatriz.inserir_aresta("D" + D.size(),"U", days);

        D.add(days);
        HolidayName.add(name);
    }

    public void addDoctor(String name) {
        grafoLista.inserir_vertice("S" + S.size());
        grafoMatriz.inserir_vertice("S" + S.size());

        grafoLista.inserir_aresta("S", "S" + S.size(), c);
        grafoMatriz.inserir_aresta("S", "S" + S.size(), c);

        S.add(1);
        DoctorName.add(name);
    }

    /**
     * Adiciona os dias de disponibilidade do medico
     * @param nameDoctor String (nome do medico)
     * @param nameHoliday String (nome do feriado que o medico estara disponivel)
     * @return true, se conseguir adicionar a disponilidade, false, caso contrario
     */
    public boolean addDoctorAvailability(String nameDoctor, String nameHoliday) {
        if(!DoctorName.contains(nameDoctor) || !HolidayName.contains(nameHoliday)) {
            return false;
        }

        grafoLista.inserir_aresta("S" + DoctorName.indexOf(nameDoctor), "D" + HolidayName.indexOf(nameHoliday), 1);
        grafoMatriz.inserir_aresta("S" + DoctorName.indexOf(nameDoctor), "D" + HolidayName.indexOf(nameHoliday), 1);

        return true;        
    }

    public void verificarAtribuicaoMedicos() {
        // TODO chama o algoritmo de fluxo
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

}
