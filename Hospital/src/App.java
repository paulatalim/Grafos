import java.util.Arrays;
import java.util.Scanner;

import graph.AGM.ArvoreGeradoraMinima;
import graph.busca.DepthFirstSearch;
import graph.representacao.lista.ListaManage;
import graph.representacao.matriz.MatrizManage;

/**
 * PONTÍFICIA UNIVERSIDADE CATÓLICA DE MINAS GERAIS
 * Trabalho acadêmico de Algoritmos em Grafos
 * 
 * @author Gabriel Luís Pinto Cecconello
 * @author Paula Cristina Talim Gonçalves
 * @author Rafael Vicente Souza E Paula
 * 
 * DATA DE CRIAÇÃO: 12 de jun. de 2024
 */
public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        ListaManage grafoLista = new ListaManage();
        MatrizManage grafoMatriz = new MatrizManage();
        DepthFirstSearch buscaEmProfundidade;
        ArvoreGeradoraMinima arvoreGeradoraMinima;

        boolean opcao_invalida;
        String aresta;
        int opcao;
        char resposta;
        char resposta1; 
        int cont;
        boolean useAnaliseMatriz = true;


        // Repete o programa
        do {

            // Reinicia a variavel
            opcao_invalida = false;

            // Validacao da entrada do usuario
            do {
                // Exibe o menu das opcoes
                UI.print(UI.PURPLE_BACKGROUND + UI.WHITE + "\n\t\t\t\t\t" + "  *** DOCTORS WITHOUT WEEKENDS ***  " + "\n\n\n");
                UI.print(UI.BLACK_BACKGROUND + UI.YELLOW + "\t" + "O que deseja fazer no grafo" + "\n\n");

                UI.print(UI.RED_BACKGROUND + UI.WHITE + "\t" + " REPRESENTAÇÕES   " + "\n");
                UI.print(UI.BLACK_BACKGROUND + UI.YELLOW + "\t" + "1 - Exibir em forma de lista de adjacência" + "\n"
                        + "\t" + "2 - Exibir em forma de matriz de adjacência" + "\n\n");

                UI.print(UI.YELLOW_BACKGROUND + UI.BLACK + "\t" + " MANIPULAÇÃO   " + "\n");
                UI.print(UI.BLACK_BACKGROUND + UI.YELLOW + "\t" + "3 - Adicionar vértices" + "\n"
                        + "\t" + "4 - Adicionar arestas" + "\n"
                        + "\t" + "5 - Remover aresta" + "\n\n");

                UI.println(UI.GREEN_BACKGROUND + UI.BLACK + "\t" + " ANÁLISE   ");
                UI.print(UI.BLACK_BACKGROUND + UI.YELLOW);

                if (grafoLista.isDirecionado()) {
                    UI.print("\t" + "6 - Identificar sucessores e predecessores de um vértice\n");
                } else {
                    UI.print("\t" + "6 - Identificar vizinhaça de um vértice\n");
                }

                UI.print("\t" + "7 - Identificar grau de um vértice" + "\n");
                UI.print("\t" + "8 - Calcular Caminho Mínimo entre dois vértices" + "\n");
                UI.print("\t" + "9 - Analisar e classificar grafo" + "\n\n");
                
                UI.println(UI.BLUE_BACKGROUND + UI.BLACK + "\t" + " ALGORITMOS DE BUSCA, ORDENAÇÃO E ÁRVORE   ");
                UI.print(UI.BLACK_BACKGROUND + UI.YELLOW);
                UI.println("\t" + "10 - Realizar Busca em Largura");
                UI.print("\t" + "11 - Realizar Busca em Profundidade");

                if(grafoLista.isDirecionado()) {
                    UI.print("\n\t" + "12 - Realizar Ordenação Topológica");
                }
                
                if(grafoLista.isPonderado() && !grafoLista.isDirecionado()) {
                    UI.print("\n\t" + "13 - Gerar Árvore Geradora Mínima");
                }

                UI.print(UI.PURPLE_BACKGROUND + UI.BLACK + "\n\n\t" + " OUTROS   " + "\n");
                UI.println(UI.BLACK_BACKGROUND + UI.YELLOW + "\t" + "14 - Criar novo grafo" + "\n"
                        + "\t" + "0 - SAIR" + "\n");

                if (opcao_invalida) {
                    UI.println(UI.RED + "\t" + "Opcao inválida. Tente novavemente." + "\n");
                }
                UI.print(UI.CYAN + "\t" + "Insira o número da operação: " + UI.WHITE);
                opcao = scanner.nextInt();
                UI.limpar_console();

                opcao_invalida = true;

                // Valida a resposta do usuario
            } while (opcao < 0 || opcao > 14 || (!grafoLista.isDirecionado() && opcao== 12) || ((grafoLista.isDirecionado() || !grafoLista.isPonderado()) && opcao == 13));

            switch (opcao) {
                case 1:
                    
                    break;
            
                default:
                    break;
            }

            // Preparo para reiniciar programa

            if (opcao != 0) {
                UI.exibir_fim_tela();
            }

        } while (opcao != 0);

        scanner.close();
    }
}
