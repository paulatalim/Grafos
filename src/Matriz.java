import java.util.List;
import java.util.Scanner;

public class Matriz {
    private List<int[]> grafoMatriz;
    private boolean direcionado;

    public Matriz(int vertices, boolean direcionado) {
        for (int i = 0; i < vertices; i++) {
            // grafoMatriz.add(new int[] {});
        }
        this.direcionado = direcionado;
    }

    public void criarAresta() {
        Scanner scVertice1 = new Scanner(System.in);
        Scanner scVertice2 = new Scanner(System.in);
        if (this.direcionado) {
            System.out.println("opa!");
        }
        else {
            System.out.printf("Deseja criar uma aresta entre quais vertices? ");
            int vertice1 = scVertice1.nextInt();
            int vertice2 = scVertice2.nextInt();
             
        }
        scVertice1.close();
        scVertice2.close();
    }

    public void removerAresta(int quantidadeDeArestas) {
        
    }
}
