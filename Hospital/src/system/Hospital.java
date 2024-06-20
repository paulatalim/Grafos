package system;

import java.util.ArrayList;

// import graph.representacao.lista.ListaManage;
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
        //grafoLista = new ListaManage();
        grafoMatriz = new MatrizManage();

        // Configuracao para o grafo direcionado
        //grafoLista.setIsDirecionado(true);
        grafoMatriz.setIsDirecionado(true);

        // Configuracao para grafo ponderado
        //grafoLista.setIsPonderado(true);
        grafoMatriz.setIsPonderado(true);

        // Adicao de conjuntos
        // grafoLista.inserir_vertice("S");
        // grafoLista.inserir_vertice("U");
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
        //grafoLista.inserir_vertice("D" + HolidayName.size());
        grafoMatriz.inserir_vertice("D" + HolidayName.size());
        
        // Adiciona a aresta
        //grafoLista.inserir_aresta("D" + HolidayName.size(),"U", days);
        grafoMatriz.inserir_aresta("D" + HolidayName.size(),"U", 0);
        D.add(days);

        // Adiciona o nome a lista
        HolidayName.add(name);
        return true;
    }

    public boolean addDoctor(String name, int disponibilidade) {
        // Validacao do nome do medico
        if(DoctorName.contains(name) || name == "" || !isStringValida(name)) return false;

        // Adiciona o vertice
        //grafoLista.inserir_vertice("S" + DoctorName.size());
        grafoMatriz.inserir_vertice("S" + DoctorName.size());

        // Adiciona a aresta
        //grafoLista.inserir_aresta("S", "S" + DoctorName.size(), disponibilidade);
        grafoMatriz.inserir_aresta("S", "S" + DoctorName.size(), 0);
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

        // Verifica se excedeu o limite
        // if(grafoMatriz.verifica_sucessores("S" + DoctorName.indexOf(nameDoctor)).size() >= c) return false;

        // Adiciona a aresta
        //grafoLista.inserir_aresta("S" + DoctorName.indexOf(nameDoctor), "D" + HolidayName.indexOf(nameHoliday), 1);
        grafoMatriz.inserir_aresta("S" + DoctorName.indexOf(nameDoctor), "D" + HolidayName.indexOf(nameHoliday), 0);

        return true;        
    }

    public MatrizManage gerarRedeResidual() {
        // Inicializando matriz da rede.
        MatrizManage redeResidual = new MatrizManage();
        redeResidual.setIsDirecionado(true);
        redeResidual.setIsPonderado(true);

        Integer[][] matriz = grafoMatriz.getGrafo();
        ArrayList<String> vertices = grafoMatriz.getVertices();

        // Adição do vértices.
        for(int i = 0; i < vertices.size(); i++) {
            redeResidual.inserir_vertice(vertices.get(i));
        }

        // Adição das capacidades máximas de cada médico.
        for(int i = 0; i < S.size(); i++) {
            redeResidual.inserir_aresta("S", "S" + i, S.get(i));
        }

        // Adição das durações de cada feriado.
        for(int i = 0; i < D.size(); i++) {
            redeResidual.inserir_aresta("D" + i, "U", D.get(i));
        }

        return redeResidual;
    }

    public void verificarAtribuicaoMedicos() throws Exception{
        if(!grafoMatriz.isGrafosConexo()) return;

        MatrizManage fluxoViavel = (MatrizManage)grafoMatriz.clone();
        MatrizManage redeResidual = gerarRedeResidual();
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
