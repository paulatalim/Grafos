package system;

// import graph.AGM.ArvoreGeradoraMinima;
// import graph.busca.DepthFirstSearch;
import graph.representacao.lista.ListaManage;
import graph.representacao.matriz.MatrizManage;

public class Hospital {
    ListaManage grafoLista = new ListaManage();
    MatrizManage grafoMatriz = new MatrizManage();
    
    Hospital() {
        grafoLista = new ListaManage();
        grafoMatriz = new MatrizManage();

        grafoLista.setIsDirecionado(true);
        grafoMatriz.setIsDirecionado(true);

        grafoLista.setIsPonderado(true);
        grafoMatriz.setIsPonderado(true);
    }


}
